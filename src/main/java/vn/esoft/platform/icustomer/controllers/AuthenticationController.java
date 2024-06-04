package vn.esoft.platform.icustomer.controllers;

import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.esoft.platform.icustomer.dtos.AuthentRequest;
import vn.esoft.platform.icustomer.responses.AuthentResponse;
import vn.esoft.platform.icustomer.services.AuthentService;

@RequestMapping("/auth/v1")
@RestController
public class AuthenticationController {
    private final AuthentService authenticationService;

    public AuthenticationController(AuthentService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/login")
    @PermitAll()
    public ResponseEntity<AuthentResponse> authenticate(@RequestBody AuthentRequest loginUserDto) {
        AuthentResponse response = authenticationService.authenticate(loginUserDto);
        return ResponseEntity.ok(response);
    }
}