package com.javaweb.springbootnonjwt.service.impl;

import com.javaweb.springbootnonjwt.converter.BuildingDTOConverter;
import com.javaweb.springbootnonjwt.model.BuildingDTO;
import com.javaweb.springbootnonjwt.repository.BuildingRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingDTOConverter buildingDTOConverter;
    @Override
    public List<BuildingDTO> fillAll(Map<String, Object> params, List<String> typeCodes) {
        List<BuildingDTO> result = new ArrayList<>();
        List<BuildingEntity> input = buildingRepository.findAll(params, typeCodes);
        for(BuildingEntity building : input){
            BuildingDTO buildingDTO = buildingDTOConverter.toBuildingDTO(building);
            result.add(buildingDTO);
        }
        return result;
    }
}
