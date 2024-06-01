package vn.esfot.platform.icustomer.controllers;

import vn.esfot.platform.icustomer.entities.customer;
import vn.esfot.platform.icustomer.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<customer> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        customer currentUser = (customer) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    public ResponseEntity<List<customer>> allUsers() {
        List <customer> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}
