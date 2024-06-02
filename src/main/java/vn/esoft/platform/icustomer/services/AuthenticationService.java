package vn.esoft.platform.icustomer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.esoft.platform.icustomer.dtos.LoginUserDto;
import vn.esoft.platform.icustomer.dtos.RegisterUserDto;
import vn.esoft.platform.icustomer.entities.CustomerEntity;
import vn.esoft.platform.icustomer.entities.CustomerRoleEntity;
import vn.esoft.platform.icustomer.entities.RoleEntity;
import vn.esoft.platform.icustomer.entities.SecurityTokenEntity;
import vn.esoft.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;
import vn.esoft.platform.icustomer.responses.LoginResponse;
import vn.esoft.platform.icustomer.utils.CustomerUtils;
import vn.esoft.platform.icustomer.utils.JSonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

//            List<String> rolesName = customerAuthenticated.getRoles();
//            List<String> permissionName = customerAuthenticated.getPermissions();
//            List<String> resourceURLName = customerAuthenticated.getResources();
//            log.info("roles: {}", JSonUtils.toJson(customerRoles.stream().map(CustomerRoleEntity::getRole)));
            Map<String, List<String>> scopeCustomer = customerAuthenticated.scope();
            SecurityTokenEntity token = jwtService.generateSecurityToken(CustomerUtils.claims(customerAuthenticated, scopeCustomer), customerAuthenticated);
            loginResponse = new LoginResponse()
                    .setAccessToken(token.getAccessToken())
                    .setRefreshToken(token.getRefreshToken())
                    .setUserAttributes(CustomerUtils.claims(customerAuthenticated, scopeCustomer))
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
