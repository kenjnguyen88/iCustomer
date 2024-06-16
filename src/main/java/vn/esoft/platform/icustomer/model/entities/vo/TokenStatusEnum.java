package vn.esoft.platform.icustomer.model.entities.vo;

public enum TokenStatusEnum {

    ACTIVE("ACTIVE"),
    INACTIVE("IN-ACTIVE");

    private final String value;

    TokenStatusEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
