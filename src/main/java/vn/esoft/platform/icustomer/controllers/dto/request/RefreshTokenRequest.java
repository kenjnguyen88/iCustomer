package vn.esoft.platform.icustomer.controllers.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RefreshTokenRequest implements Serializable {

    @NotBlank
    private String refreshToken;

}
