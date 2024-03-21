package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.repository.DistrictRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "giang412345";
    @Override
    public DistrictEntity findById(Long id) {
        DistrictEntity district = null;
        String sql = "Select d.name FROM district d WHERE id = " + id;
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ){
            if(rs.next()){
                district = new DistrictEntity();
                district.setName(rs.getString("name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return district;
    }
}
