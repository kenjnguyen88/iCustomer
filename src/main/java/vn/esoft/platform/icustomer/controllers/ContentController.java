package vn.esoft.platform.icustomer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.esoft.platform.icustomer.entities.CustomerEntity;
import vn.esoft.platform.icustomer.services.UserService;

import java.util.List;

@RequestMapping("/content/v1")
@RestController
public class ContentController {
    private final UserService userService;

    public ContentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('GUEST', 'EDITOR', 'ADMIN')")
    public ResponseEntity<String> contentInfo() {
        return ResponseEntity.ok("success");
    }

    @PostMapping()
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<String> createContent() {
        return ResponseEntity.ok("success");
    }

    @PutMapping()
    @PreAuthorize("hasRole('EDITOR')")
    public ResponseEntity<String> updateContent() {
        return ResponseEntity.ok("success");
    }

}
