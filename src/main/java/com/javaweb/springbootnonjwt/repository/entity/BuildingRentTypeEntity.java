package com.javaweb.springbootnonjwt.repository.entity;

public class BuildingRentTypeEntity {
    private long id;
    private BuildingEntity building;
    private RentTypeEntity rentType;

    public BuildingRentTypeEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }

    public RentTypeEntity getRentType() {
        return rentType;
    }

    public void setRentType(RentTypeEntity rentType) {
        this.rentType = rentType;
    }
}
