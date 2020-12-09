package com.oracle.osvc.cxzoom.operations;

import com.oracle.osvc.cxzoom.connections.DbUtils;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Model
public class DeleteParticipant {
    @Inject
    private DbUtils dbUtils;

    public DbUtils getDbUtils() {
        return dbUtils;
    }

    public void setDbUtils(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }
    public String deleteUser(String uname, String meetid){
        try{
            Connection con= getDbUtils().createConnection();
            PreparedStatement ps=con.prepareStatement("delete from participants where username=? and id=?");
            ps.setString(1,uname);
            ps.setString(2,meetid);
            System.out.println(uname);
            ps.executeUpdate();
            con.close();
            return "Participant Deleted";
        }catch(Exception e){ System.out.println(e);return "User Not Found";}
    }
}
