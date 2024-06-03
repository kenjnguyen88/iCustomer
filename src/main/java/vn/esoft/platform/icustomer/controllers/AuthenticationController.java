package vn.esoft.platform.icustomer.controllers;

import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.esoft.platform.icustomer.dtos.LoginUserDto;
import vn.esoft.platform.icustomer.responses.LoginResponse;
import vn.esoft.platform.icustomer.services.AuthenticationService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/login")
    @PermitAll()
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        LoginResponse response = authenticationService.authenticate(loginUserDto);
        return ResponseEntity.ok(response);
    }
}