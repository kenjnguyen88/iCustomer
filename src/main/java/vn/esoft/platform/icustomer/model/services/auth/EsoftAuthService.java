package vn.esoft.platform.icustomer.model.services.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vn.esoft.platform.icustomer.controllers.dto.request.AuthentRequest;
import vn.esoft.platform.icustomer.controllers.dto.request.RefreshTokenRequest;
import vn.esoft.platform.icustomer.controllers.dto.request.RegisterRequest;
import vn.esoft.platform.icustomer.controllers.dto.response.AuthenResponse;
import vn.esoft.platform.icustomer.controllers.dto.response.RefreshTokenResponse;
import vn.esoft.platform.icustomer.controllers.dto.response.RegisterResponse;
import vn.esoft.platform.icustomer.model.entities.CustomerEntity;
import vn.esoft.platform.icustomer.model.entities.SecurityTokenEntity;
import vn.esoft.platform.icustomer.model.services.AbstractAuthService;
import vn.esoft.platform.icustomer.model.services.IAuthService;
import vn.esoft.platform.icustomer.model.services.JwtService;
import vn.esoft.platform.icustomer.model.services.user.UserService;
import vn.esoft.platform.icustomer.repositories.CustomerRolePermissionRepository;
import vn.esoft.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;
import vn.esoft.platform.icustomer.utils.CustomerUtils;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;

@Service
@Slf4j
public class EsoftAuthService extends AbstractAuthService implements IAuthService {


    public EsoftAuthService(UserRepository userRepository, UserService userService, UserDetailsService userDetailsService, CustomerRolePermissionRepository customerRolePermissionRepository, SecurityTokenRepository tokenRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, Executor executor) {
        super(userRepository, userService, userDetailsService, customerRolePermissionRepository, tokenRepository, passwordEncoder, authenticationManager, jwtService, executor);
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
                this.saveTokenAsyncBy(tokenEntity);
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

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {

        RefreshTokenResponse response = null;
        String username = jwtService.extractUsername(request.getRefreshToken());
        if (null == username) {
            throw new RuntimeException("The user not exist!");
        }
        CustomerEntity customer = userService.fetchCustomerInfo(username);
        if (null == customer) {
            throw new RuntimeException("The user not exist!");
        }
        if (jwtService.isTokenValid(request.getRefreshToken(), customer.getEmail())) {
            Optional<SecurityTokenEntity> optSecurityTokenEntity = tokenRepository.findByCustomerId(customer.getId());
            if (optSecurityTokenEntity.isPresent()) {
                Map<String, Object> claims = CustomerUtils.claims(customer);
                String accessToken = jwtService.generateToken(claims, customer);
                optSecurityTokenEntity.get().setAccessToken(accessToken);
                response = RefreshTokenResponse.builder()
                        .customerId(customer.getId())
                        .accessToken(accessToken)
                        .refreshToken(optSecurityTokenEntity.get().getRefreshToken())
                        .build();
                this.updateTokenAsyncBy(optSecurityTokenEntity.get());
            }
        }
        return response;
    }


}
