package com.oracle.osvc.cxzoom.operations;

import com.oracle.osvc.cxzoom.bean.Meeting;
import com.oracle.osvc.cxzoom.bean.User;
import com.oracle.osvc.cxzoom.connections.DbUtils;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public Meeting changeSchedule(Timestamp time, String meetid){
        Meeting meeting = new Meeting();
        try{
            Connection con= getDbUtils().createConnection();
            PreparedStatement ps=con.prepareStatement("update meeting set time=? where id=?");
            ps.setTimestamp(1, time);
            ps.setString(2, meetid);
            ps.executeUpdate();
            List<User> parts = new ArrayList<User>();
            List<User> hosts = new ArrayList<User>();

            ps=con.prepareStatement("select a.username,a.firstname,a.lastname,a.email,p.host from accounts a inner join participants p on p.username=a.username where p.id=?");
            ps.setString(1,meetid);
            ResultSet rs3=ps.executeQuery();

            while(rs3.next()){
                User users = new User();

                users.setUserName(rs3.getString(1));
                users.setFirstName(rs3.getString(2));
                users.setLastName(rs3.getString(3));
                users.setEmail(rs3.getString(4));
                if(rs3.getBoolean(5)==true) {
                    hosts.add(users);
                }else{
                    parts.add(users);
                }

            }

            meeting.setUsers(parts);
            meeting.setHosts(hosts);
            meeting.setMeetingId(meetid);

            ps=con.prepareStatement("select * from meeting where id=?");
            ps.setString(1,meetid);
            ResultSet rs2=ps.executeQuery();

            if(rs2.next()){
                meeting.setMeetingName(rs2.getString(3));
                meeting.setTimestamp(rs2.getTimestamp(2));
            }
            return meeting;
        }catch(Exception e){ System.out.println(e);}
        return meeting;
    }
}
