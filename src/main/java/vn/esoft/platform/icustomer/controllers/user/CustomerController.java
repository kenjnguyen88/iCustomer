package vn.esoft.platform.icustomer.controllers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.esoft.platform.icustomer.entities.CustomerEntity;
import vn.esoft.platform.icustomer.services.user.UserService;

@RequestMapping("/customer/v1")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final UserService userService;

    @GetMapping("/info")
    @PreAuthorize("hasAnyRole('EDITOR', 'GUEST', 'ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<CustomerEntity> fetchUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomerEntity currentUser = (CustomerEntity) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

}
