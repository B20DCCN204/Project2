package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.repository.RentAreaRepository;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import com.javaweb.springbootnonjwt.repository.entity.RentAreaEntity;
import com.javaweb.springbootnonjwt.utils.ConnectorJDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
    @Override
    public List<RentAreaEntity> findByBuildingId(Long buildingId) {
        List<RentAreaEntity> rentAreaEntityList = new ArrayList<>();
        String sql = "SELECT r.value FROM rentarea r WHERE buildingid = " + buildingId;
        try(Connection conn = ConnectorJDBCUtil.getConnection();
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
