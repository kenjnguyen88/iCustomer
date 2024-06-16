package vn.esoft.platform.icustomer.model.entities.vo;

import lombok.Getter;

@Getter
public enum PermissionEnum {

    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    GET("GET"),
    DELETE("DELETE");

    private final String value;

    PermissionEnum(String value) {
        this.value = value;
    }

}
