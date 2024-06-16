package vn.esoft.platform.icustomer.model.services.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.esoft.platform.icustomer.controllers.dto.request.AuthentRequest;
import vn.esoft.platform.icustomer.controllers.dto.response.AuthenResponse;
import vn.esoft.platform.icustomer.model.services.JwtService;
import vn.esoft.platform.icustomer.model.services.user.UserService;
import vn.esoft.platform.icustomer.repositories.CustomerRolePermissionRepository;
import vn.esoft.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;
import vn.esoft.platform.icustomer.model.services.AbstractAuthService;
import vn.esoft.platform.icustomer.model.services.IAuthService;

import java.util.concurrent.Executor;

@Service
@Slf4j
public class GoogleAuthService extends AbstractAuthService implements IAuthService {

    public GoogleAuthService(UserRepository userRepository, UserService userService, CustomerRolePermissionRepository customerRolePermissionRepository, SecurityTokenRepository tokenRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, Executor executor) {
        super(userRepository, userService, customerRolePermissionRepository, tokenRepository, passwordEncoder, authenticationManager, jwtService, executor);
    }

    @Override
    public AuthenResponse authenticate(AuthentRequest request) {
        return null;
    }
}
