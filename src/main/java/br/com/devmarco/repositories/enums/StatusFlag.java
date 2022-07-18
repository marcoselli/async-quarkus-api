package br.com.devmarco.repositories.enums;

public enum StatusFlag {

    ACTIVE("A"),
    INACTIVE("I"),
    BANNED("B");

    private String value;

    StatusFlag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
