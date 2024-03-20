package com.javaweb.springbootnonjwt.service;

import com.javaweb.springbootnonjwt.model.BuildingDTO;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingDTO> fillAll(Map<String, Object> params, List<String> typeCodes);
}
