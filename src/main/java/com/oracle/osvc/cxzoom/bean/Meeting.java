package com.oracle.osvc.cxzoom.bean;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class Meeting implements Serializable {
    private String meetingId;
    private String meetingName;
    private Timestamp timestamp;
    private List<User> users = new ArrayList<User>();
    private List<User> hosts = new ArrayList<User>();
    private Boolean host;

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Boolean getHost() {
        return host;
    }

    public void setHost(Boolean host) {
        this.host = host;
    }

    public List<User> getHosts() {
        return hosts;
    }

    public void setHosts(List<User> hosts) {
        this.hosts = hosts;
    }
}
