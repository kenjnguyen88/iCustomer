package vn.esoft.platform.icustomer.controllers.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import vn.esoft.platform.icustomer.entities.vo.RoleEnum;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class AssignRoleRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -6660455340465579609L;

    @NotBlank
    private String email;
    private RoleEnum role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
