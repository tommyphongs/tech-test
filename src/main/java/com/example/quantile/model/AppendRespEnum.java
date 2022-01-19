package com.example.quantile.model;

public enum AppendRespEnum {

    APPENDED("appended"),
    INSERTED("inserted");

    private final String name;

    AppendRespEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
