package vn.esoft.platform.icustomer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.esoft.platform.icustomer.dtos.LoginUserDto;
import vn.esoft.platform.icustomer.entities.CustomerEntity;
import vn.esoft.platform.icustomer.entities.SecurityTokenEntity;
import vn.esoft.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;
import vn.esoft.platform.icustomer.responses.LoginResponse;
import vn.esoft.platform.icustomer.utils.CustomerUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final SecurityTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(
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


    public LoginResponse authenticate(LoginUserDto input) {

        LoginResponse loginResponse = null;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        if (authentication.isAuthenticated()) {
            Map<String, Object> userInfo = new HashMap<>();
            CustomerEntity customerAuthenticated = userRepository.findByEmail(input.getEmail()).orElseThrow();
            Map<String, List<String>> scopeCustomer = customerAuthenticated.scope();
            userInfo = CustomerUtils.claims(customerAuthenticated, scopeCustomer);
            SecurityTokenEntity token = jwtService.generateSecurityToken(userInfo, customerAuthenticated);
            loginResponse = new LoginResponse()
                    .setAccessToken(token.getAccessToken())
                    .setRefreshToken(token.getRefreshToken())
                    .setExpiresIn(jwtService.getExpirationTime())
                    .setUserInfo(userInfo);
            this.tokenRepository.save(token);

        } else throw new BadCredentialsException("Can not login");
        return loginResponse;
    }

}
