package vn.esfot.platform.icustomer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.esfot.platform.icustomer.dtos.LoginUserDto;
import vn.esfot.platform.icustomer.dtos.RegisterUserDto;
import vn.esfot.platform.icustomer.entities.CustomerEntity;
import vn.esfot.platform.icustomer.responses.LoginResponse;
import vn.esfot.platform.icustomer.services.AuthenticationService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<CustomerEntity> register(@RequestBody RegisterUserDto registerUserDto) {
        CustomerEntity registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        LoginResponse response = authenticationService.authenticate(loginUserDto);
        return ResponseEntity.ok(response);
    }
}