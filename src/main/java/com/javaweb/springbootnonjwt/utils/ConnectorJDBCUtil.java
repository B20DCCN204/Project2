package com.javaweb.springbootnonjwt.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectorJDBCUtil {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "giang412345";

    public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
