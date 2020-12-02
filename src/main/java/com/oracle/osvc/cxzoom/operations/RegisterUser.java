package com.oracle.osvc.cxzoom.operations;

import com.oracle.osvc.cxzoom.bean.User;
import com.oracle.osvc.cxzoom.connections.DbUtils;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Model
public class RegisterUser {

    @Inject
    private DbUtils dbUtils;

    public DbUtils getDbUtils() {
        return dbUtils;
    }

    public void setDbUtils(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }
    public String registerUser(User users){
    try{
        Connection con= getDbUtils().createConnection();
        PreparedStatement ps=con.prepareStatement("insert into accounts(username,password,firstname,lastname,email) values(?,?,?,?,?)");
        ps.setString(1, users.getUserName());
        ps.setString(2, users.getPassword());
        ps.setString(3, users.getFirstName());
        ps.setString(4, users.getLastName());
        ps.setString(5, users.getEmail());
        ps.executeUpdate();
        con.close();
        return "User registered";
    }catch(Exception e){ System.out.println(e); return "User not registered";}
}}
