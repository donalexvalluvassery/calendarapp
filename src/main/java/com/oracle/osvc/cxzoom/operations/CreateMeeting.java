package com.oracle.osvc.cxzoom.operations;

import com.oracle.osvc.cxzoom.bean.Meeting;
import com.oracle.osvc.cxzoom.bean.User;
import com.oracle.osvc.cxzoom.connections.DbUtils;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Model
public class CreateMeeting {

    @Inject
    private DbUtils dbUtils;

    public DbUtils getDbUtils() {
        return dbUtils;
    }

    public void setDbUtils(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }

    public Meeting createMeeting(Meeting meetings){
        try{

            Connection con= getDbUtils().createConnection();
            PreparedStatement ps=con.prepareStatement("insert into meeting(id,time,meetingName) values(?,?,?)");
            ps.setString(1,meetings.getMeetingId());
            ps.setTimestamp(2,meetings.getTimestamp());
            ps.setString(3, meetings.getMeetingName());
            ps.executeUpdate();

            List<User> hosts= new ArrayList<User>();
            hosts=meetings.getHosts();

            for (User item : hosts) {
                ps=con.prepareStatement("insert into participants(id,username,host) values(?,?,?)");
                ps.setString(1,meetings.getMeetingId());
                ps.setString(2,item.getUserName());
                ps.setBoolean(3,true);
                ps.executeUpdate();
            }

            List<User> users= new ArrayList<User>();
            users=meetings.getUsers();

            for (User item : users) {
                ps=con.prepareStatement("insert into participants(id,username,host) values(?,?,?)");
                ps.setString(1,meetings.getMeetingId());
                ps.setString(2, item.getUserName());
                ps.setBoolean(3,false);
                ps.executeUpdate();
                ps=con.prepareStatement("select username,firstname,lastname,email from accounts where username=?");
                ps.setString(1,item.getUserName());
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    item.setFirstName(rs.getString(2));
                    item.setLastName(rs.getString(3));
                    item.setEmail(rs.getString(4));
                }
            }
            con.close();
            return meetings;
        }catch(Exception e){ System.out.println(e);return meetings;}
    }
}
