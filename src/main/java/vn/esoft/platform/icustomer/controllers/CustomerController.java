package vn.esoft.platform.icustomer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.esoft.platform.icustomer.entities.CustomerEntity;
import vn.esoft.platform.icustomer.services.UserService;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CustomerEntity> customerInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomerEntity currentUser = (CustomerEntity) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<CustomerEntity>> all() {
        List<CustomerEntity> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }
}
