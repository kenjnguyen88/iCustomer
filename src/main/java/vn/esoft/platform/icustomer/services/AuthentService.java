package vn.esoft.platform.icustomer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vn.esoft.platform.icustomer.dtos.AuthentRequest;
import vn.esoft.platform.icustomer.entities.CustomerEntity;
import vn.esoft.platform.icustomer.entities.SecurityTokenEntity;
import vn.esoft.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;
import vn.esoft.platform.icustomer.responses.AuthentResponse;
import vn.esoft.platform.icustomer.utils.CustomerUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AuthentService {
    private final UserRepository userRepository;
    private final SecurityTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthentService(
            UserRepository userRepository,
            SecurityTokenRepository tokenRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public AuthentResponse authenticate(AuthentRequest request) {

        Assert.hasText(request.getEmail(), "email cannot empty");
        Assert.hasText(request.getPassword(), "password cannot empty");
        AuthentResponse loginResponse = null;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        if (authentication.isAuthenticated()) {
            Map<String, Object> userInfo = new HashMap<>();
            CustomerEntity customerAuthenticated = userRepository.findByEmail(request.getEmail()).get();
//            Map<String, List<String>> scopeCustomer = customerAuthenticated.scope();
//            userInfo = CustomerUtils.claims(customerAuthenticated, scopeCustomer);
//            String token = jwtService.generateToken(userInfo, customerAuthenticated);
            loginResponse = new AuthentResponse()
                    .setAccessToken(jwtService.generateToken(userInfo, customerAuthenticated))
                    .setRefreshToken(jwtService.generateRefreshToken(userInfo, customerAuthenticated))
                    .setExpiresIn(jwtService.getExpirationTime())
                    .setUserInfo(userInfo);

        } else throw new BadCredentialsException("username or password not correct!");
        return loginResponse;
    }

}
