package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.repository.BuildingRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.utils.ConnectorJDBCUtil;
import com.javaweb.springbootnonjwt.utils.ObjectUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    @Override
    public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCodes) {
        StringBuilder finalSql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.ward, b.street, b.numberofbasement," +
                " b.managername, b.managerphonenumber, b.floorarea, b.rentprice, b.servicefee, b.brokeragefee FROM building b ");

        StringBuilder whereSql = new StringBuilder("WHERE 1 = 1 ");
        StringBuilder joinSql = new StringBuilder();
        querySqlJoin(params, typeCodes, joinSql);
        querySqlWhereNomal(params, typeCodes, whereSql);
        querySqlWhereSpecial(params, typeCodes, whereSql);

        finalSql.append(joinSql);
        finalSql.append(whereSql);
        finalSql.append("GROUP BY b.id");
        List<BuildingEntity> result = new ArrayList<>();
        try(Connection conn = ConnectorJDBCUtil.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(finalSql.toString());
        ){
            while (rs.next()){
                BuildingEntity building = new BuildingEntity();
                building.setId(rs.getLong("id"));
                building.setName(rs.getString("name"));
                building.setDistrictId(rs.getLong("districtid"));
                building.setStreet(rs.getString("street"));
                building.setWard(rs.getString("ward"));
                building.setNumberOfBasement(rs.getInt("numberofbasement"));
                building.setManagerName(rs.getString("managername"));
                building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
                building.setFloorArea(rs.getInt("floorarea"));
                building.setRentPrice(rs.getInt("rentprice"));
                building.setServiceFee(rs.getString("servicefee"));
                building.setBrokerageFee(rs.getDouble("brokeragefee"));
                result.add(building);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private void querySqlJoin(Map<String, Object> params, List<String> typeCodes, StringBuilder sb){
        Object staffId = params.get("staffId");
        if(ObjectUtil.checkObject(staffId)){
            sb.append("INNER JOIN assignmentbuilding a ON a.buildingid = b.id ");
        }
        Object areaFrom = params.get("areaFrom");
        Object areaTo = params.get("areaTo");
        if(ObjectUtil.checkObject(areaFrom) || ObjectUtil.checkObject(areaTo)){
            sb.append("INNER JOIN rentarea r ON r.buildingid = b.id ");
        }
        if(typeCodes != null && typeCodes.size() != 0){
            sb.append("INNER JOIN buildingrenttype brt on brt.buildingid = b.id " +
                    "INNER JOIN renttype rt on rt.id = brt.renttypeid ");
        }
    }
    private void querySqlWhereNomal(Map<String, Object> params, List<String> typeCodes, StringBuilder sb){
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("area") && !key.startsWith("rentPrice")){
                if(ObjectUtil.checkObject(value)){
                    if(ObjectUtil.isNumber(value)){
                        sb.append("AND b." + key + " = " + value + " ");
                    }else {
                        sb.append("AND b." + key + " LIKE '%" + value + "%' ");
                    }
                }
            }
        }
    }

    private void querySqlWhereSpecial(Map<String, Object> params, List<String> typeCodes, StringBuilder sb){
        Object staffId = params.get("staffId");
        if(ObjectUtil.checkObject(staffId)){
            sb.append("AND a.staffid = " + staffId + " ");
        }
        Object areaFrom = params.get("areaFrom");
        if(ObjectUtil.checkObject(areaFrom)){
            sb.append("AND r.value >= " + areaFrom + " ");
        }
        Object areaTo = params.get("areaTo");
        if(ObjectUtil.checkObject(areaTo)){
            sb.append("AND r.value <= " + areaTo + " ");
        }
        Object rentPriceFrom = params.get("rentPriceFrom");
        if(ObjectUtil.checkObject(rentPriceFrom)){
            sb.append("AND b.rentprice >= " + rentPriceFrom + " ");
        }
        Object rentPriceTo = params.get("rentPriceTo");
        if(ObjectUtil.checkObject(rentPriceTo)){
            sb.append("AND b.rentprice <= " + rentPriceTo + " ");
        }
        if(typeCodes != null && typeCodes.size() != 0){
            sb.append("AND rt.code IN('").append(String.join("', '", typeCodes)).append("') ");
//            sb.append("AND (");
//            String sql = typeCodes.stream().map(it -> "rt.code like '%" + it + "%' ").collect(Collectors.joining("OR "));
//            sb.append(sql + ") ");
        }
    }
}
