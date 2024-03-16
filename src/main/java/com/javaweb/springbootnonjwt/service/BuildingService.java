package com.javaweb.springbootnonjwt.service;

import com.javaweb.springbootnonjwt.model.BuildingDTO;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingDTO> getBuildings(Map<String, Object> params);
}
