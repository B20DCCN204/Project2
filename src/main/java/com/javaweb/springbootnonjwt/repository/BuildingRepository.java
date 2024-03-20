package com.javaweb.springbootnonjwt.repository;

import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepository {
    List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCodes);
}
