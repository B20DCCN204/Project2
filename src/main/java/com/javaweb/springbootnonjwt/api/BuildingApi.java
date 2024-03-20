package com.javaweb.springbootnonjwt.api;

import com.javaweb.springbootnonjwt.model.BuildingDTO;
import com.javaweb.springbootnonjwt.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BuildingApi {

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/api/buildings/")
    public List<BuildingDTO> searchBuildings(@RequestParam Map<String, Object> params,
                                             @RequestParam(name="typeCode", required = false) List<String> typeCodes){
        List<BuildingDTO> buildingDTOS = buildingService.fillAll(params, typeCodes);
        return buildingDTOS;
    }
}
