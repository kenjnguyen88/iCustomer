package vn.esoft.platform.icustomer.controllers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.esoft.platform.icustomer.services.user.UserService;

@RequestMapping("/content/v1")
@RestController
@RequiredArgsConstructor
public class ContentController {
    private final UserService userService;

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
