package vn.esoft.platform.icustomer.controllers;

import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.esoft.platform.icustomer.controllers.request.AuthentRequest;
import vn.esoft.platform.icustomer.controllers.request.RegisterRequest;
import vn.esoft.platform.icustomer.controllers.response.AuthenResponse;
import vn.esoft.platform.icustomer.controllers.response.RegisterResponse;
import vn.esoft.platform.icustomer.services.AuthentService;

@RequestMapping("/auth/v1")
@RestController
public class AuthenticationController {
    private final AuthentService authenticationService;

    public AuthenticationController(AuthentService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    @PermitAll()
    public ResponseEntity<RegisterResponse> signup(@RequestBody RegisterRequest request) {
        RegisterResponse response = authenticationService.signup(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @PermitAll()
    public ResponseEntity<AuthenResponse> authenticate(@RequestBody AuthentRequest loginUserDto) {
        AuthenResponse response = authenticationService.authenticate(loginUserDto);
        return ResponseEntity.ok(response);
    }
}