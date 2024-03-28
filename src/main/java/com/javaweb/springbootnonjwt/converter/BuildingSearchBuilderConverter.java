package com.javaweb.springbootnonjwt.converter;

import com.javaweb.springbootnonjwt.builder.BuildingSearchBuilder;
import com.javaweb.springbootnonjwt.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCodes){
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtil.getObject(params, "name", String.class))
                .setStreet(MapUtil.getObject(params, "street", String.class))
                .setWard(MapUtil.getObject(params, "ward", String.class))
                .setDistrictId(MapUtil.getObject(params, "districtId", Long.class))
                .setDirection(MapUtil.getObject(params, "direction", String.class))
                .setLevel(MapUtil.getObject(params, "level", String.class))
                .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
                .setManagerName(MapUtil.getObject(params, "managerName", String.class))
                .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
                .setAreaFrom(MapUtil.getObject(params, "areaFrom", Integer.class))
                .setAreaTo(MapUtil.getObject(params, "areaTo", Integer.class))
                .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Integer.class))
                .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Integer.class))
                .setStaffId(MapUtil.getObject(params, "staffId", Long.class))
                .setTypeCode(typeCodes)
                .build();
        return buildingSearchBuilder;
    }
}
