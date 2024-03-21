package com.javaweb.springbootnonjwt.repository;

import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;

public interface DistrictRepository {
    DistrictEntity findById(Long id);
}
