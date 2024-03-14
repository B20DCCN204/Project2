package com.javaweb.springbootnonjwt.repository.entity;

public class RentAreaEntity {
    private long id;
    private int value;

    public RentAreaEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
