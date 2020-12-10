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
public class FetchUser {
    @Inject
    private DbUtils dbUtils;

    public DbUtils getDbUtils() {
        return dbUtils;
    }

    public void setDbUtils(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }

    public User fetchUserBean(User user){
        try{
            Connection con= getDbUtils().createConnection();
            PreparedStatement ps=con.prepareStatement("select firstname,lastname,email from accounts where username=? and password=?");
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ResultSet rs=ps.executeQuery();


            if (rs.next() && !StringUtils.isNullOrEmpty(user.getUserName())) {
                user.setFirstName(rs.getString(1));
                user.setLastName(rs.getString(2));
                user.setEmail(rs.getString(3));
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
        user.setPassword(null);
        return user;
    }

}
