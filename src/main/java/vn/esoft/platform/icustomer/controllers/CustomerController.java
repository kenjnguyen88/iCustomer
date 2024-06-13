package vn.esoft.platform.icustomer.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.esoft.platform.icustomer.services.UserService;

@RequestMapping("/customer/v1")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final UserService userService;
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    public ResponseEntity<String> getAllCustomer() {
        return ResponseEntity.ok("success");
    }

    @GetMapping("/info")
    @PreAuthorize("hasAnyRole('EDITOR', 'GUEST')")
    public ResponseEntity<String> createContent() {
        return ResponseEntity.ok("success");
    }

}
