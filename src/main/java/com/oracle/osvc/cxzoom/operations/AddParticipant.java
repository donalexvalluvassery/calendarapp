package com.oracle.osvc.cxzoom.operations;

import com.mysql.cj.util.StringUtils;
import com.oracle.osvc.cxzoom.bean.User;
import com.oracle.osvc.cxzoom.connections.DbUtils;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Model
public class AddParticipant {
    @Inject
    private DbUtils dbUtils;

    public DbUtils getDbUtils() {
        return dbUtils;
    }

    public void setDbUtils(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }

    public User addParticipant(String uname, String meetid){
        User user = new User();
        try{
            Connection con= getDbUtils().createConnection();
            PreparedStatement ps=con.prepareStatement("insert into participants values(?,?,0)");
            ps.setString(1, meetid);
            ps.setString(2, uname);
            ps.executeUpdate();
            ps=con.prepareStatement("select username,firstname,lastname,email from accounts where username=?");
            ps.setString(1,uname);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setUserName(rs.getString(1));
            }
            con.close();
            return user;
        }catch(Exception e){ System.out.println(e);return null;}
    }
}
