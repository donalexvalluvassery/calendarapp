package com.oracle.osvc.cxzoom.operations;

import com.oracle.osvc.cxzoom.connections.DbUtils;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Model
public class DeleteMeeting {
    @Inject
    private DbUtils dbUtils;

    public DbUtils getDbUtils() {
        return dbUtils;
    }

    public void setDbUtils(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }
    public String deleteMeeting(String meetId){
        try{
            Connection con= getDbUtils().createConnection();
            PreparedStatement ps=con.prepareStatement("delete from participants where id=?");
            ps.setString(1,meetId);
            ps.executeUpdate();
            ps=con.prepareStatement("delete from meeting where id=?");
            ps.setString(1,meetId);
            ps.executeUpdate();
            con.close();
            return "Meeting Deleted";
        }catch(Exception e){ System.out.println(e);return "Couldn't delete meeting";}
    }
}
