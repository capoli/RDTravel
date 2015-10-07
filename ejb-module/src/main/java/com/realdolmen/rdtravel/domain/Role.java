package com.realdolmen.rdtravel.domain;

public enum Role {
    CUSTOMER("customer"), EMPLOYEE("employee"), PARTNER("partner");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
