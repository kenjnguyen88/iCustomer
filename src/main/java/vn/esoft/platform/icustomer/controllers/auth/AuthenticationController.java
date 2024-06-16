package vn.esoft.platform.icustomer.controllers.auth;

import jakarta.annotation.security.PermitAll;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.esoft.platform.icustomer.controllers.BaseController;
import vn.esoft.platform.icustomer.controllers.dto.request.AuthentRequest;
import vn.esoft.platform.icustomer.controllers.dto.request.RegisterRequest;
import vn.esoft.platform.icustomer.controllers.dto.response.AuthenResponse;
import vn.esoft.platform.icustomer.controllers.dto.response.RegisterResponse;
import vn.esoft.platform.icustomer.model.services.auth.EsoftAuthService;
import vn.esoft.platform.icustomer.model.services.auth.FacebookAuthService;
import vn.esoft.platform.icustomer.model.services.auth.GoogleAuthService;
import vn.esoft.platform.icustomer.model.services.user.UserService;

@RequestMapping("/auth/v1")
@RestController
public class AuthenticationController extends BaseController {


    public AuthenticationController(UserService userService, EsoftAuthService esoftAuthService, GoogleAuthService googleAuthService, FacebookAuthService facebookAuthService) {
        super(userService, esoftAuthService, googleAuthService, facebookAuthService);
    }

    @PostMapping("/signup")
    @PermitAll()
    public ResponseEntity<RegisterResponse> signup(@RequestBody RegisterRequest request) {
        RegisterResponse response = esoftAuthService.signup(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @PermitAll()
    public ResponseEntity<AuthenResponse> authenticate(@Param("provider") String provider,
                                                       @RequestBody AuthentRequest request) {
        AuthenResponse response = this.authService(provider).authenticate(request);
        return ResponseEntity.ok(response);
    }
}