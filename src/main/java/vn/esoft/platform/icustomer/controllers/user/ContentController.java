package vn.esoft.platform.icustomer.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.esoft.platform.icustomer.controllers.BaseController;
import vn.esoft.platform.icustomer.services.auth.EsoftAuthService;
import vn.esoft.platform.icustomer.services.auth.FacebookAuthService;
import vn.esoft.platform.icustomer.services.auth.GoogleAuthService;
import vn.esoft.platform.icustomer.services.user.UserService;

@RequestMapping("/content/v1")
@RestController
public class ContentController extends BaseController {

    public ContentController(UserService userService, EsoftAuthService esoftAuthService, GoogleAuthService googleAuthService, FacebookAuthService facebookAuthService) {
        super(userService, esoftAuthService, googleAuthService, facebookAuthService);
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
