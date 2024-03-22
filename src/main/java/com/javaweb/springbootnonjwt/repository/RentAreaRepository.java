package com.javaweb.springbootnonjwt.repository;

import com.javaweb.springbootnonjwt.repository.entity.RentAreaEntity;

import java.util.List;

public interface RentAreaRepository {
    List<RentAreaEntity> findByBuildingId(Long buildingId);
}
