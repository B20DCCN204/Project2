package com.javaweb.springbootnonjwt.repository;

import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepository {
    List<BuildingEntity> findAll();
}
