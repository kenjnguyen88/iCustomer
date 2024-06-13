package vn.esoft.platform.icustomer.controllers.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class RegisterResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1840890107195668720L;
    @NotBlank
    private String email;
    private String fullName;

    public String getEmail() {
        return email;
    }
}
