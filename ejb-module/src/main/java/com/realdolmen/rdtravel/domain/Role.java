package com.realdolmen.rdtravel.domain;

public enum Role {
    CUSTOMER("CUSTOMER"), EMPLOYEE("EMPLOYEE"), PARTNER("PARTNER");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
