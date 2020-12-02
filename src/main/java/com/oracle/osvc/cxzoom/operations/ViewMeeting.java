package com.oracle.osvc.cxzoom.operations;

import com.oracle.osvc.cxzoom.bean.Meeting;
import com.oracle.osvc.cxzoom.bean.User;
import com.oracle.osvc.cxzoom.connections.DbUtils;
import org.json.simple.JSONArray;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Model
public class ViewMeeting {
    @Inject
    private DbUtils dbUtils;

    public DbUtils getDbUtils() {
        return dbUtils;
    }

    public void setDbUtils(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }
    public List<Meeting> viewMeeting(String uname, Timestamp startDate, Timestamp endDate){
        List<Meeting> meetingList = new ArrayList<Meeting>();

        JSONArray array = new JSONArray();

        try {
            Connection con= getDbUtils().createConnection();
            PreparedStatement ps = con.prepareStatement("select p.id,p.username,p.host from participants p inner join meeting m on p.id=m.id where p.username=? && m.time between ? and ?");
            ps.setString(1, uname);
            ps.setTimestamp(2,startDate);
            ps.setTimestamp(3,endDate);
            ResultSet rs1 = ps.executeQuery();
            //rs1 has username,meeting id


            while(rs1.next()){

                List<User> parts = new ArrayList<User>();
                List<User> hosts = new ArrayList<User>();
                Meeting meeting = new Meeting();

                ps=con.prepareStatement("select a.username,a.firstname,a.lastname,a.email,p.host from accounts a inner join participants p on p.username=a.username where p.id=?");
                ps.setString(1,rs1.getString(1));
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
                meeting.setMeetingId(rs1.getString(1));
                meeting.setHost(rs1.getBoolean(3));

                ps=con.prepareStatement("select * from meeting where id=?");
                ps.setString(1,rs1.getString(1));
                ResultSet rs2=ps.executeQuery();

                if(rs2.next()){
                    meeting.setMeetingName(rs2.getString(3));
                    meeting.setTimestamp(rs2.getTimestamp(2));
                }

                meetingList.add(meeting);

            }
            con.close();
        }catch(Exception e){ System.out.println(e);}return meetingList;
    }
}
