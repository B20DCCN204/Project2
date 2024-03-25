package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.repository.DistrictRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import com.javaweb.springbootnonjwt.utils.ConnectorJDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
    @Override
    public DistrictEntity findById(Long id) {
        DistrictEntity district = null;
        String sql = "Select d.name FROM district d WHERE id = " + id;
        try(Connection conn = ConnectorJDBCUtil.getConnection();
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
