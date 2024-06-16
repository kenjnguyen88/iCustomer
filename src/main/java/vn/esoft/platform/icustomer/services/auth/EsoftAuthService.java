package vn.esoft.platform.icustomer.services.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vn.esoft.platform.icustomer.controllers.dto.request.AuthentRequest;
import vn.esoft.platform.icustomer.controllers.dto.request.RegisterRequest;
import vn.esoft.platform.icustomer.controllers.dto.response.AuthenResponse;
import vn.esoft.platform.icustomer.controllers.dto.response.RegisterResponse;
import vn.esoft.platform.icustomer.entities.CustomerEntity;
import vn.esoft.platform.icustomer.entities.SecurityTokenEntity;
import vn.esoft.platform.icustomer.repositories.CustomerRolePermissionRepository;
import vn.esoft.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;
import vn.esoft.platform.icustomer.services.AbstractAuthService;
import vn.esoft.platform.icustomer.services.IAuthService;
import vn.esoft.platform.icustomer.services.JwtService;
import vn.esoft.platform.icustomer.services.user.UserService;
import vn.esoft.platform.icustomer.utils.CustomerUtils;

import java.util.Map;
import java.util.concurrent.Executor;

@Service
@Slf4j
public class EsoftAuthService extends AbstractAuthService implements IAuthService {


    public EsoftAuthService(UserRepository userRepository, UserService userService, CustomerRolePermissionRepository customerRolePermissionRepository, SecurityTokenRepository tokenRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, Executor executor) {
        super(userRepository, userService, customerRolePermissionRepository, tokenRepository, passwordEncoder, authenticationManager, jwtService, executor);
    }

    @Override
    public AuthenResponse authenticate(AuthentRequest request) {

        AuthenResponse response = null;
        Assert.hasText(request.getEmail(), "email cannot empty");
        Assert.hasText(request.getPassword(), "password cannot empty");
        CustomerEntity optCust = null;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (authentication.isAuthenticated()) {
//            optCust = userRepository.findByEmail(request.getEmail());
            optCust = userService.fetchCustomerInfo(request.getEmail());
            if (optCust != null) {
                optCust.setRoles(optCust.getRoles());
                optCust.setPermissions(optCust.getPermissions());

                Map<String, Object> claims = CustomerUtils.claims(optCust, optCust.getRoles(), optCust.getPermissions());
                SecurityTokenEntity tokenEntity = jwtService.generateSecurityToken(claims, optCust);
                response = AuthenResponse.builder()
                        .accessToken(tokenEntity.getAccessToken())
                        .refreshToken(tokenEntity.getRefreshToken())
                        .userInfo(claims).build();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        securityToken(tokenEntity);
                    }
                });
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

}
