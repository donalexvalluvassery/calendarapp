package com.oracle.osvc.cxzoom.operations;

import com.oracle.osvc.cxzoom.bean.User;
import com.oracle.osvc.cxzoom.connections.DbUtils;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

@Model
public class ChangeSchedule {
    @Inject
    private DbUtils dbUtils;

    public DbUtils getDbUtils() {
        return dbUtils;
    }

    public void setDbUtils(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }

    public void changeSchedule(Timestamp time, String meetid){
        try{
            Connection con= getDbUtils().createConnection();
            PreparedStatement ps=con.prepareStatement("update meeting set time=? where id=?");
            ps.setTimestamp(1, time);
            ps.setString(2, meetid);
            ps.executeUpdate();;
        }catch(Exception e){ System.out.println(e);}
    }
}
