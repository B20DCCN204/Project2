package com.javaweb.springbootnonjwt.repository.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "district")
public class DistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "district")
    private List<BuildingEntity> buildingEntities;

    public DistrictEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BuildingEntity> getBuildingEntities() {
        return buildingEntities;
    }

    public DistrictEntity setBuildingEntities(List<BuildingEntity> buildingEntities) {
        this.buildingEntities = buildingEntities;
        return this;
    }
}
