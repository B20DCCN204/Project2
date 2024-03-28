package com.javaweb.springbootnonjwt.service.impl;

import com.javaweb.springbootnonjwt.builder.BuildingSearchBuilder;
import com.javaweb.springbootnonjwt.converter.BuildingDTOConverter;
import com.javaweb.springbootnonjwt.converter.BuildingSearchBuilderConverter;
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
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Override
    public List<BuildingDTO> fillAll(Map<String, Object> params, List<String> typeCodes) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCodes);
        List<BuildingEntity> input = buildingRepository.findAll(buildingSearchBuilder);
        List<BuildingDTO> result = new ArrayList<>();
        for(BuildingEntity building : input){
            BuildingDTO buildingDTO = buildingDTOConverter.toBuildingDTO(building);
            result.add(buildingDTO);
        }
        return result;
    }
}
