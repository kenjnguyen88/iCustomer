package vn.esoft.platform.icustomer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vn.esoft.platform.icustomer.controllers.request.AuthentRequest;
import vn.esoft.platform.icustomer.controllers.request.RegisterRequest;
import vn.esoft.platform.icustomer.controllers.response.AuthenResponse;
import vn.esoft.platform.icustomer.controllers.response.RegisterResponse;
import vn.esoft.platform.icustomer.entities.CustomerEntity;
import vn.esoft.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthentService {
    private final UserRepository userRepository;
    private final SecurityTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenResponse authenticate(AuthentRequest request) {

        Assert.hasText(request.getEmail(), "email cannot empty");
        Assert.hasText(request.getPassword(), "password cannot empty");
        AuthenResponse loginResponse = null;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        if (authentication.isAuthenticated()) {
            Map<String, Object> userInfo = new HashMap<>();
            CustomerEntity customerAuthenticated = userRepository.findByEmail(request.getEmail()).get();
//            Map<String, List<String>> scopeCustomer = customerAuthenticated.scope();
//            userInfo = CustomerUtils.claims(customerAuthenticated, scopeCustomer);
//            String token = jwtService.generateToken(userInfo, customerAuthenticated);
            loginResponse = new AuthenResponse();
            loginResponse.setAccessToken(jwtService.generateToken(userInfo, customerAuthenticated));
            loginResponse.setRefreshToken(jwtService.generateRefreshToken(userInfo, customerAuthenticated));
            loginResponse.setExpiresIn(jwtService.getExpirationTime());
            loginResponse.setUserInfo(userInfo);

        } else throw new BadCredentialsException("username or password not correct!");
        return loginResponse;
    }

    public RegisterResponse signup(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new RuntimeException("Customer is exist!");

        CustomerEntity entity = new CustomerEntity();
        entity.setEmail(request.getEmail());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setFullName(request.getFullName());
        entity = userRepository.save(entity);

        return RegisterResponse.builder()
                .email(entity.getEmail())
                .fullName(entity.getFullName())
                .build();
    }
}
