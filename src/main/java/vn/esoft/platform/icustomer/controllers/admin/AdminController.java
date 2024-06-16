package vn.esoft.platform.icustomer.controllers.admin;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.esoft.platform.icustomer.controllers.dto.request.AssignRoleRequest;
import vn.esoft.platform.icustomer.controllers.dto.request.RegisterRequest;
import vn.esoft.platform.icustomer.controllers.dto.response.PageData;
import vn.esoft.platform.icustomer.services.user.UserService;

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

    @GetMapping("/user/all")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    public ResponseEntity<PageData<Object>> getAllUser(@Param("pageSize") int pageSize, @Param("pageNum") int pageNum ) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);

        return ResponseEntity.ok(userService.fetchAllUser(pageRequest));
    }
}
