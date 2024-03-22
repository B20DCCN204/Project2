package com.javaweb.springbootnonjwt.service.impl;

import com.javaweb.springbootnonjwt.model.BuildingDTO;
import com.javaweb.springbootnonjwt.repository.BuildingRepository;
import com.javaweb.springbootnonjwt.repository.DistrictRepository;
import com.javaweb.springbootnonjwt.repository.RentAreaRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.repository.entity.RentAreaEntity;
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
    private DistrictRepository districtRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Override
    public List<BuildingDTO> fillAll(Map<String, Object> params, List<String> typeCodes) {
        List<BuildingDTO> result = new ArrayList<>();
        List<BuildingEntity> input = buildingRepository.findAll(params, typeCodes);
        for(BuildingEntity building : input){
            BuildingDTO buildingDTO = new BuildingDTO();
            buildingDTO.setName(building.getName());

            String districtName = districtRepository.findById(building.getDistrictId()).getName();
            buildingDTO.setAddress(building.getStreet()+", "+building.getWard()+", "+districtName);

            List<RentAreaEntity> rentAreaEntityList = rentAreaRepository.findByBuildingId(building.getId());
            String rentAreas = convertRentAreaList(rentAreaEntityList);
            buildingDTO.setRentArea(rentAreas);

            buildingDTO.setNumberOfBasement(building.getNumberOfBasement());
            buildingDTO.setManagerName(building.getManagerName());
            buildingDTO.setManagerPhoneNumber(building.getManagerPhoneNumber());
            buildingDTO.setFloorArea(building.getFloorArea());
            buildingDTO.setRentPrice(building.getRentPrice());
            buildingDTO.setServiceFee(building.getServiceFee());
            buildingDTO.setBrokerageFee(building.getBrokerageFee());
            result.add(buildingDTO);
        }
        return result;
    }

    private String convertRentAreaList(List<RentAreaEntity> rentAreaEntityList){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rentAreaEntityList.size(); i++){
            if(i != rentAreaEntityList.size()-1){
                sb.append(rentAreaEntityList.get(i).getValue()).append(", ");
            }else sb.append(rentAreaEntityList.get(i).getValue());
        }
        return sb.toString().trim();
    }
}
