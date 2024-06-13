package vn.esoft.platform.icustomer.entities.vo;

import lombok.Getter;

@Getter
public enum RoleEnum {

    ADMIN("ADMIN"),
    SUPER_ADMIN("SUPER_ADMIN"),
    EDITOR("EDITOR"),
    GUEST("GUEST");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

}
