package vn.esoft.platform.icustomer.controllers.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class RefreshTokenResponse implements Serializable {

    @NotBlank
    private Long customerId;

    @NotBlank
    private String refreshToken;

    @NotBlank
    private String accessToken;

}
