package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.repository.RentAreaRepository;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import com.javaweb.springbootnonjwt.repository.entity.RentAreaEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "giang412345";
    @Override
    public List<RentAreaEntity> findByBuildingId(Long buildingId) {
        List<RentAreaEntity> rentAreaEntityList = new ArrayList<>();
        String sql = "SELECT r.value FROM rentarea r WHERE buildingid = " + buildingId;
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ){
            while(rs.next()){
                RentAreaEntity rentArea = new RentAreaEntity();
                rentArea.setValue(rs.getInt("value"));
                rentAreaEntityList.add(rentArea);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return rentAreaEntityList;
    }
}
