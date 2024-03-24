package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.repository.BuildingRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "giang412345";
    @Override
    public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCodes) {
        StringBuilder finalSql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.ward, b.street, b.numberofbasement," +
                " b.managername, b.managerphonenumber, b.floorarea, b.rentprice, b.servicefee, b.brokeragefee FROM building b ");
        StringBuilder whereSql = new StringBuilder();
        StringBuilder joinSql = new StringBuilder();
        whereSql.append("WHERE 1 = 1 ");
        finalSql.append(querySqlJoin(params, typeCodes, joinSql));
        finalSql.append(querySqlWhere(params, typeCodes, whereSql));
        finalSql.append("GROUP BY b.id");
        List<BuildingEntity> result = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
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

    private String querySqlJoin(Map<String, Object> params, List<String> typeCodes, StringBuilder sb){
        boolean joinAdded = false;
        for(Map.Entry<String, Object> entry : params.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value != null && !value.toString().isEmpty()){
                if(key.equalsIgnoreCase("staffId")){
                    sb.append("INNER JOIN assignmentbuilding a ON a.buildingid = b.id ");
                }
                if(key.equalsIgnoreCase("areaFrom") || key.equalsIgnoreCase("areaTo") && !joinAdded){
                    sb.append("INNER JOIN rentarea r ON r.buildingid = b.id ");
                    joinAdded = true;
                }
            }
        }
        if(typeCodes != null && typeCodes.size() != 0){
            sb.append("INNER JOIN buildingrenttype brt on brt.buildingid = b.id " +
                    "INNER JOIN renttype rt on rt.id = brt.renttypeid ");
        }
        return sb.toString();
    }
    private String querySqlWhere(Map<String, Object> params, List<String> typeCodes, StringBuilder sb){
        for(Map.Entry<String, Object> entry : params.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value != null && !value.toString().isEmpty()){
                if(key.equalsIgnoreCase("name")) sb.append("AND b.name LIKE '%" + value + "%' ");
                if(key.equalsIgnoreCase("districtId")) sb.append("AND b.districtid = " + value + " ");
                if(key.equalsIgnoreCase("street")) sb.append("AND b.street LIKE '%" + value + "%' ");
                if(key.equalsIgnoreCase("ward")) sb.append("AND b.ward LIKE '%" + value + "%' ");
                if(key.equalsIgnoreCase("numberOfBasement")) sb.append("AND b.numberofbasement = " + value + " ");
                if(key.equalsIgnoreCase("direction")) sb.append("AND b.direction LIKE '%" + value + "%' ");
                if(key.equalsIgnoreCase("level")) sb.append("AND b.level LIKE '%" + value + "%' ");
                if(key.equalsIgnoreCase("areaFrom")) sb.append("AND r.value >= " + value + " ");
                if(key.equalsIgnoreCase("areaTo")) sb.append("AND r.value <= " + value + " ");
                if(key.equalsIgnoreCase("rentPriceFrom")) sb.append("AND b.rentprice >= " + value + " ");
                if(key.equalsIgnoreCase("rentPriceTo")) sb.append("AND b.rentprice <= " + value + " ");
                if(key.equalsIgnoreCase("managerName")) sb.append("AND b.managername LIKE '%" + value + "%' ");
                if(key.equalsIgnoreCase("managerPhoneNumber")) sb.append("AND b.managerphonenumber LIKE '%" + value + "%' ");
                if(key.equalsIgnoreCase("staffId")) sb.append("AND a.staffid = " + value + " ");
            }
        }
        if(typeCodes != null && typeCodes.size() != 0){
//            for(String typeCode : typeCodes){
//                sb.append("AND rt.code = '" + typeCode + "' ");
//            }
            sb.append("AND rt.code IN('").append(String.join("', '", typeCodes)).append("') ");
        }
        return sb.toString();
    }
}
