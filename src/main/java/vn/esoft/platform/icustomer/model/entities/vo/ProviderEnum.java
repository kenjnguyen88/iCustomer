package vn.esoft.platform.icustomer.model.entities.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProviderEnum {
    Google("Google"),
    Facebook("Facebook"),
    Esoft("Esoft");
    private String value;

    ProviderEnum(String value) {
        this.value = value;
    }

}
