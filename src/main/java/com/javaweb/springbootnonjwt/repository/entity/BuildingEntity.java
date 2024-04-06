package com.javaweb.springbootnonjwt.repository.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "street")
    private String street;
    @Column(name = "ward")
    private String ward;
    @ManyToOne
    @JoinColumn(name = "districtid", nullable = false)
    private DistrictEntity district;
    @OneToMany(mappedBy = "building")
    private List<RentAreaEntity> rentAreaEntities;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "managerphonenumber")
    private String managerPhoneNumber;
    @Column(name = "servicefee")
    private String serviceFee;
    @Column(name = "numberofbasement")
    private Integer numberOfBasement;
    @Column(name = "rentprice", nullable = false)
    private Integer rentPrice;
    @Column(name = "floorarea")
    private Integer floorArea;
    @Column(name = "brokeragefee")
    private Double brokerageFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public BuildingEntity setDistrict(DistrictEntity district) {
        this.district = district;
        return this;
    }

    public List<RentAreaEntity> getRentAreaEntities() {
        return rentAreaEntities;
    }

    public BuildingEntity setRentAreaEntities(List<RentAreaEntity> rentAreaEntities) {
        this.rentAreaEntities = rentAreaEntities;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public BuildingEntity setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
        return this;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public BuildingEntity setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
        return this;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public BuildingEntity setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
        return this;
    }

    public Double getBrokerageFee() {
        return brokerageFee;
    }

    public BuildingEntity setBrokerageFee(Double brokerageFee) {
        this.brokerageFee = brokerageFee;
        return this;
    }
}
