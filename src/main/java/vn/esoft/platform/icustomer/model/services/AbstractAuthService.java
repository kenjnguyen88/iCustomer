package vn.esoft.platform.icustomer.model.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.esoft.platform.icustomer.controllers.dto.request.RefreshTokenRequest;
import vn.esoft.platform.icustomer.controllers.dto.request.RegisterRequest;
import vn.esoft.platform.icustomer.controllers.dto.response.AuthenResponse;
import vn.esoft.platform.icustomer.controllers.dto.response.RefreshTokenResponse;
import vn.esoft.platform.icustomer.controllers.dto.response.RegisterResponse;
import vn.esoft.platform.icustomer.model.entities.SecurityTokenEntity;
import vn.esoft.platform.icustomer.model.services.user.UserService;
import vn.esoft.platform.icustomer.repositories.CustomerRolePermissionRepository;
import vn.esoft.platform.icustomer.repositories.SecurityTokenRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;

import java.util.Optional;
import java.util.concurrent.Executor;

@RequiredArgsConstructor
public abstract class AbstractAuthService implements IAuthService {

    protected final UserRepository userRepository;
    protected final UserService userService;
    protected final UserDetailsService userDetailsService;
    protected final CustomerRolePermissionRepository customerRolePermissionRepository;
    protected final SecurityTokenRepository tokenRepository;
    protected final PasswordEncoder passwordEncoder;
    protected final AuthenticationManager authenticationManager;
    protected final JwtService jwtService;
    protected final Executor executor;

    public RegisterResponse signup(RegisterRequest request) {
        return null;
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        return null;
    }

    protected void securityToken(SecurityTokenEntity tokenEntity) {
        Optional<SecurityTokenEntity> optToken = tokenRepository.findByCustomerId(tokenEntity.getCustomerId());
        if (optToken.isPresent())
            tokenRepository.deleteById(optToken.get().getId());
        else
            tokenRepository.save(tokenEntity);
    }
}
