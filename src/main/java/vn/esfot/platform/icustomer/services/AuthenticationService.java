package vn.esfot.platform.icustomer.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.esfot.platform.icustomer.dtos.LoginUserDto;
import vn.esfot.platform.icustomer.dtos.RegisterUserDto;
import vn.esfot.platform.icustomer.entities.CustomerEntity;
import vn.esfot.platform.icustomer.entities.SecurityTokenEntity;
import vn.esfot.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esfot.platform.icustomer.repositories.UserRepository;
import vn.esfot.platform.icustomer.responses.LoginResponse;
import vn.esfot.platform.icustomer.utils.CustomerUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final SecurityTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationService(
            UserRepository userRepository,
            SecurityTokenRepository tokenRepository, AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public CustomerEntity signup(RegisterUserDto input) {
        var user = new CustomerEntity()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
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
            CustomerEntity customerAuthenticated = userRepository.findByEmail(input.getEmail()).orElseThrow();
//            String jwtToken = jwtService.generateToken(CustomerUtils.claims(customerAuthenticated), customerAuthenticated);
//            String jwtRefreshToken = jwtService.generateRefreshToken(CustomerUtils.claims(customerAuthenticated), customerAuthenticated);
            SecurityTokenEntity token = jwtService.generateSecurityToken(CustomerUtils.claims(customerAuthenticated), customerAuthenticated);
            loginResponse = new LoginResponse()
                    .setAccessToken(token.getAccessToken())
                    .setRefreshToken(token.getRefreshToken())
                    .setUserAttributes(CustomerUtils.claims(customerAuthenticated))
                    .setExpiresIn(jwtService.getExpirationTime());
            this.tokenRepository.save(token);

        } else throw new BadCredentialsException("Can not login");
        return loginResponse;
    }

    public List<CustomerEntity> allUsers() {
        List<CustomerEntity> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
