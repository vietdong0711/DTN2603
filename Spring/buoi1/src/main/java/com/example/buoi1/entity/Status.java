package com.example.buoi1.entity;

public enum Status {
    OPEN("O", "ADMIN"), REVIEW("REV", "ADMIN"), APPROVED("A", "USER"), REJECTED("REJ", "USER");

    private String value;

    private String role;

    private Status(String value, String code) {
        this.value = value;
        this.role = code;
    }

    public String getValue() {
        return value;
    }

    public String getRole() {
        return role;
    }

    // chuyen gtri tu sql -> enum trong java
    public static Status toEnum(String valueSql) {
        for (Status status : Status.values()) {
            if (status.getValue().equals(valueSql)) {
                return status;
            }
        }
        return null;
    }

}
