package vn.esoft.platform.icustomer.controllers.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.esoft.platform.icustomer.entities.vo.PermissionEnum;
import vn.esoft.platform.icustomer.entities.vo.RoleEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class CustomerInfoResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 3483020010832252056L;

    @NotBlank
    private String email;

    private String fullName;

    @NotBlank
    private List<String> roles;

    @NotBlank
    private List<String> permissions;
}
