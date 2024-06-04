package vn.esoft.platform.icustomer.services;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.esoft.platform.icustomer.dtos.AuthentRequest;
import vn.esoft.platform.icustomer.entities.*;
import vn.esoft.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;
import vn.esoft.platform.icustomer.responses.AuthentResponse;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthentServiceTest {

    private String email = "ken.nguyen@gmail.com";
    private String password = "$2a$12$mBxXL61jaYTxBM.KoxhZ8eCo/cquppLl/L8M4gjmKPpNL6afuFHUG";
    private CustomerEntity customerEntity;
    private Optional<CustomerEntity> optionalCustomerEntity;
    private RoleEntity roleEntity;
    private ResourceEntity resourceEntity;
    private PermissionEntity permissionEntity;
    private CustomerRoleEntity customerRoleEntity;
    private RolePermissionEntity rolePermissionEntity;
    private RoleResourceEntity roleResourceEntity;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SecurityTokenRepository tokenRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    private AuthentService service;

    @BeforeEach
    void setup() {

        customerEntity = new CustomerEntity();
        customerEntity.setEmail(email);
        customerEntity.setPassword(password);
        customerEntity.setFullName("Ken Nguyen");
        customerEntity.setId(1l);

        resourceEntity = new ResourceEntity();
        resourceEntity.setId(1l);
        resourceEntity.setName("content");
        resourceEntity.setUrl("http://localhost:8080/content");

        roleEntity = new RoleEntity();
        roleEntity.setId(1l);
        roleEntity.setName("EDITOR");


        permissionEntity = new PermissionEntity();
        permissionEntity.setName("EDITOR");


        customerRoleEntity = new CustomerRoleEntity();
        customerRoleEntity.setCustomerRoleId(1l);
        customerRoleEntity.setRole(roleEntity);
        customerRoleEntity.setCustomerRoleId(1l);

        roleResourceEntity = new RoleResourceEntity();
        roleResourceEntity.setResource(resourceEntity);
        roleResourceEntity.setrRoleInstance(roleEntity);

        rolePermissionEntity = new RolePermissionEntity();
        rolePermissionEntity.setPermission(permissionEntity);
        rolePermissionEntity.setpRoleInstance(roleEntity);

        customerEntity.setCustomerRoles(Set.of(customerRoleEntity));
        optionalCustomerEntity = Optional.of(customerEntity);

        roleEntity.setCustomerRoles(Set.of(customerRoleEntity));
        roleEntity.setRolePermissions(Set.of(rolePermissionEntity));
        roleEntity.setRoleResources(Set.of(roleResourceEntity));

        when(authenticationManager.authenticate(any())).thenReturn(new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public Object getCredentials() {
                return password;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return email;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
                this.isAuthenticated();
            }

            @Override
            public String getName() {
                return null;
            }
        });

        this.service = new AuthentService(
                userRepository,
                tokenRepository,
                authenticationManager,
                passwordEncoder,
                jwtService);
    }

    @AfterEach
    public void reset() {
        if(userRepository instanceof Mock) {
            Mockito.reset(userRepository);
        } else userRepository = null;

        if(tokenRepository instanceof Mock) {
            Mockito.reset(tokenRepository);
        } else tokenRepository = null;


        if(authenticationManager instanceof Mock) {
            Mockito.reset(authenticationManager);
        } else authenticationManager = null;


        if(passwordEncoder instanceof Mock)
            Mockito.reset(passwordEncoder);
        else passwordEncoder = null;

        if(jwtService instanceof Mock) {
            Mockito.reset(jwtService);
        } else jwtService = null;

    }

    @Test
    public void givenAuthentSuccess_thenReturnAccessToken() {

        SecurityTokenEntity securityTokenEntity = new SecurityTokenEntity(
                customerEntity.getId().toString(),
                "eyJhbGciOiJIUzI1NiJ9.eyJzY29wZSI6eyJ1cmxSZXNvdXJjZXMiOlsiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2N1c3RvbWVyLyoiXSwicGVybWlzc2lvbnMiOlsiUE9TVCIsIkdFVCIsIlBVVCJdLCJyb2xlcyI6WyJBRE1JTiJdLCJuYW1lUmVzb3VyY2VzIjpbImN1c3RvbWVyIl19LCJjdXN0b21lcklkIjoyLCJmdWxsTmFtZSI6ImFkbWluIiwiZW1haWwiOiJrZW4ubmd1eWVuQGdtYWlsLmNvbSIsInN1YiI6Imtlbi5uZ3V5ZW5AZ21haWwuY29tIiwiaWF0IjoxNzE3NTAwNTA1LCJleHAiOjE3MTc2NDQ1MDV9.0WiUD_W_4_lV9flK4LPbYcm8E4ovtNxjb3bflRk9YbA",
                "eyJhbGciOiJIUzI1NiJ9.eyJzY29wZSI6eyJ1cmxSZXNvdXJjZXMiOlsiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2N1c3RvbWVyLyoiXSwicGVybWlzc2lvbnMiOlsiUE9TVCIsIkdFVCIsIlBVVCJdLCJyb2xlcyI6WyJBRE1JTiJdLCJuYW1lUmVzb3VyY2VzIjpbImN1c3RvbWVyIl19LCJjdXN0b21lcklkIjoyLCJmdWxsTmFtZSI6ImFkbWluIiwiZW1haWwiOiJrZW4ubmd1eWVuQGdtYWlsLmNvbSIsInN1YiI6Imtlbi5uZ3V5ZW5AZ21haWwuY29tIiwiaWF0IjoxNzE3NTAwNTA1LCJleHAiOjE3MTk2NjA1MDV9.orcXToYJQ4AD_770SfT-eGlfPV4osy_b2XKDwPHBEvY",
                "enable",
                Instant.now(),
                Instant.now(),
                Instant.now()
        );
        AuthentResponse response = null;
        when(userRepository.findByEmail(any(String.class))).thenReturn(optionalCustomerEntity);
        when(jwtService.generateSecurityToken(any(), any())).thenReturn(securityTokenEntity);
        AuthentRequest request = new AuthentRequest(email, password);
        response = service.authenticate(request);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getAccessToken());
    }

    @Test
    public void givenUserNameIsEmpty_thenReturnIllegalArgumentException() {

        AuthentRequest request = new AuthentRequest("", password);
        Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                service.authenticate(request);
            }
        }, "email cannot empty");
    }

    @Test
    public void givenPasswordIsEmpty_thenReturnIllegalArgumentException() {

        AuthentRequest request = new AuthentRequest(email, null);
        Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                service.authenticate(request);
            }
        }, "password cannot empty");
    }

    @Test
    public void givenPasswordIsNotCorrect_throwBadCredentialsException() {

        AuthentRequest request = new AuthentRequest(email, "isNotCorrectPassword");
        if(authenticationManager instanceof Mock) {
            Mockito.reset(authenticationManager);
        } else authenticationManager = null;
        this.authenticationManager = new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return new Authentication() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return null;
                    }

                    @Override
                    public Object getCredentials() {
                        return password;
                    }

                    @Override
                    public Object getDetails() {
                        return null;
                    }

                    @Override
                    public Object getPrincipal() {
                        return email;
                    }

                    @Override
                    public boolean isAuthenticated() {
                        return false;
                    }

                    @Override
                    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

                    }

                    @Override
                    public String getName() {
                        return email;
                    }
                };
            }
        };
        if(passwordEncoder instanceof Mock) {
            Mockito.reset(passwordEncoder);
        } else passwordEncoder = null;
        this.passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return "isNotCorrectPassword";
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return false;
            }
        };
        this.service = new AuthentService(
                userRepository,
                tokenRepository,
                authenticationManager,
                passwordEncoder,
                jwtService);
        Assertions.assertThrows(BadCredentialsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                service.authenticate(request);
            }
        }, "username or password not correct!");
    }

//    @Test
//    public void givenPasswordCorrect_thenReturnSuccess() {
//
//        AuthentRequest request = new AuthentRequest(email, "kenj@12345");
//        if(authenticationManager instanceof Mock) {
//            Mockito.reset(authenticationManager);
//        } else authenticationManager = null;
//        this.authenticationManager = new AuthenticationManager() {
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                return new Authentication() {
//                    @Override
//                    public Collection<? extends GrantedAuthority> getAuthorities() {
//                        return null;
//                    }
//
//                    @Override
//                    public Object getCredentials() {
//                        return password;
//                    }
//
//                    @Override
//                    public Object getDetails() {
//                        return null;
//                    }
//
//                    @Override
//                    public Object getPrincipal() {
//                        return email;
//                    }
//
//                    @Override
//                    public boolean isAuthenticated() {
//                        return true;
//                    }
//
//                    @Override
//                    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//
//                    }
//
//                    @Override
//                    public String getName() {
//                        return email;
//                    }
//                };
//            }
//        };
//        if(passwordEncoder instanceof Mock) {
//            Mockito.reset(passwordEncoder);
//        } else passwordEncoder = null;
//        this.passwordEncoder = new BCryptPasswordEncoder();
//        this.service = new AuthentService(
//                userRepository,
//                tokenRepository,
//                authenticationManager,
//                passwordEncoder,
//                jwtService);
//        AuthentResponse response = service.authenticate(request);
//        Assertions.assertNotNull(response.getAccessToken());
//    }

}
