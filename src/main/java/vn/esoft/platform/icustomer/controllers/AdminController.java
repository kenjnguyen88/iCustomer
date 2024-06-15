package vn.esoft.platform.icustomer.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.esoft.platform.icustomer.controllers.request.AssignRoleRequest;
import vn.esoft.platform.icustomer.controllers.request.RegisterRequest;
import vn.esoft.platform.icustomer.services.UserService;

@RequestMapping("/admin/v1")
@RestController
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<String> createAdministrator(@RequestBody RegisterRequest registerUserDto) {
        return ResponseEntity.ok("success");
    }

    @PostMapping("/user/role")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    public ResponseEntity<String> assignRole(@RequestBody AssignRoleRequest request) {

        userService.assignRole(request);
        return ResponseEntity.ok("success");
    }
}
