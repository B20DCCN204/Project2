package com.javaweb.springbootnonjwt.converter;

import com.javaweb.springbootnonjwt.model.BuildingDTO;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.repository.entity.RentAreaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingDTO toBuildingDTO(BuildingEntity building){
        BuildingDTO buildingDTO = modelMapper.map(building, BuildingDTO.class);

        buildingDTO.setAddress(building.getStreet()+", "+building.getWard()+", "+building.getDistrict().getName());

        List<RentAreaEntity> rentAreaEntityList = building.getRentAreaEntities();
        String rentAreas = rentAreaEntityList.stream().map(ra -> String.valueOf(ra.getValue())).collect(Collectors.joining(", "));
        buildingDTO.setRentArea(rentAreas);

        return buildingDTO;
    }
}
