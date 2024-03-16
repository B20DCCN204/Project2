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
    public List<BuildingEntity> findAll(Map<String, Object> params) {
        StringBuilder sql = new StringBuilder("SELECT * FROM building b WHERE 1 = 1 ");

        for(Map.Entry<String, Object> entry : params.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
        }
        List<BuildingEntity> result = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql.toString());
        ){
            while (rs.next()){
                BuildingEntity building = new BuildingEntity();
                building.setName(rs.getString("name"));
                building.setStreet(rs.getString("street"));
                building.setWard(rs.getString("ward"));
                result.add(building);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
