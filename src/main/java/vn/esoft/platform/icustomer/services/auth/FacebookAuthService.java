package vn.esoft.platform.icustomer.services.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.esoft.platform.icustomer.controllers.dto.request.AuthentRequest;
import vn.esoft.platform.icustomer.controllers.dto.response.AuthenResponse;
import vn.esoft.platform.icustomer.repositories.CustomerRolePermissionRepository;
import vn.esoft.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;
import vn.esoft.platform.icustomer.services.AbstractAuthService;
import vn.esoft.platform.icustomer.services.IAuthService;
import vn.esoft.platform.icustomer.services.JwtService;
import vn.esoft.platform.icustomer.services.user.UserService;

import java.util.concurrent.Executor;

@Service
@Slf4j
public class FacebookAuthService extends AbstractAuthService implements IAuthService {

    public FacebookAuthService(UserRepository userRepository, UserService userService, CustomerRolePermissionRepository customerRolePermissionRepository, SecurityTokenRepository tokenRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, Executor executor) {
        super(userRepository, userService, customerRolePermissionRepository, tokenRepository, passwordEncoder, authenticationManager, jwtService, executor);
    }

    @Override
    public AuthenResponse authenticate(AuthentRequest request) {
        return null;
    }
}
