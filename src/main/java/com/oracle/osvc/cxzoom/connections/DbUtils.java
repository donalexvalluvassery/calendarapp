package com.oracle.osvc.cxzoom.connections;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.DriverManager;

@Model
public class DbUtils {
    public Connection createConnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://152.67.161.137:3306/calendar", "root", "hydrogen");
        }catch (Exception e){
            System.out.println(e);
        }
        return con;
    }

}
