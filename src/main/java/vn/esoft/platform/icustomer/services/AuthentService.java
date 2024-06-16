package vn.esoft.platform.icustomer.services;

import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vn.esoft.platform.icustomer.controllers.request.AuthentRequest;
import vn.esoft.platform.icustomer.controllers.request.RegisterRequest;
import vn.esoft.platform.icustomer.controllers.response.AuthenResponse;
import vn.esoft.platform.icustomer.controllers.response.RegisterResponse;
import vn.esoft.platform.icustomer.entities.CustomerEntity;
import vn.esoft.platform.icustomer.entities.CustomerRolePermissionEntity;
import vn.esoft.platform.icustomer.repositories.CustomerRolePermissionRepository;
import vn.esoft.platform.icustomer.repositories.CustomerRoleRepository;
import vn.esoft.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;
import vn.esoft.platform.icustomer.utils.CustomerUtils;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthentService implements IAuthentService {

    private final UserRepository userRepository;
    private final CustomerRoleRepository customerRoleRepository;
    private final CustomerRolePermissionRepository customerRolePermissionRepository;
    private final SecurityTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthenResponse authenticate(AuthentRequest request) {

        Assert.hasText(request.getEmail(), "email cannot empty");
        Assert.hasText(request.getPassword(), "password cannot empty");
        AuthenResponse loginResponse = null;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            Map<String, Object> userInfo = new HashMap<>();
            CustomerEntity customerAuthenticated = userRepository.findByEmail(request.getEmail()).get();
            loginResponse = new AuthenResponse();
            loginResponse.setAccessToken(jwtService.generateToken(userInfo, customerAuthenticated));
            loginResponse.setRefreshToken(jwtService.generateRefreshToken(userInfo, customerAuthenticated));
            loginResponse.setExpiresIn(jwtService.getExpirationTime());
            loginResponse.setUserInfo(userInfo);
        } else throw new BadCredentialsException("username or password not correct!");
        return loginResponse;
    }

    @Override
    public AuthenResponse auth(AuthentRequest request) {

        AuthenResponse response = null;
        Assert.hasText(request.getEmail(), "email cannot empty");
        Assert.hasText(request.getPassword(), "password cannot empty");
        Optional<CustomerEntity> optCust;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            optCust = userRepository.findByEmail(request.getEmail());
            if (optCust.isPresent()) {
                List<CustomerRolePermissionEntity> permissionEntities = customerRolePermissionRepository.findByCustomerId(optCust.get().getId());

                List<String> roles = permissionEntities.stream().map(CustomerRolePermissionEntity::getRoleName).distinct().toList();
                List<String> permissions = permissionEntities.stream().map(CustomerRolePermissionEntity::getPermissionName).toList();

                optCust.get().setRoles(roles);
                optCust.get().setPermissions(permissions);

                Map<String, Object> claims = null;
                claims = CustomerUtils.claims(optCust.get(), roles, permissions);
                response = AuthenResponse.builder().accessToken(jwtService.generateToken(claims, optCust.get())).refreshToken(jwtService.generateToken(claims, optCust.get())).userInfo(claims).build();
            }
        }

        return response;
    }

    @Override
    public RegisterResponse signup(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new RuntimeException("Customer is exist!");

        CustomerEntity entity = new CustomerEntity();
        entity.setEmail(request.getEmail());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setFullName(request.getFullName());
        entity = userRepository.save(entity);

        return RegisterResponse.builder().email(entity.getEmail()).fullName(entity.getFullName()).build();
    }

    private Object loadScope(final Long custId) {


        return null;
    }
}
