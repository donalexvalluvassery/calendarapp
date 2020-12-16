package com.oracle.osvc.cxzoom.endpoints;

import com.oracle.osvc.cxzoom.bean.Meeting;
import com.oracle.osvc.cxzoom.operations.CreateMeeting;
import com.oracle.osvc.cxzoom.operations.DeleteMeeting;
import com.oracle.osvc.cxzoom.operations.ViewMeeting;
import io.helidon.microprofile.cors.CrossOrigin;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.List;

@Path("/meetings")
@RequestScoped
public class MeetingsEndpoint {
    @OPTIONS
    @CrossOrigin(
            value = {"http://152.67.161.137:8000"},
            allowMethods = {HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.POST, HttpMethod.GET})
    public void optionsForGreeting() {

    }
    @Inject
    private CreateMeeting createMeeting;

    public CreateMeeting getCreateMeeting() {
        return createMeeting;
    }


    @POST
    public Response createMeeting(Meeting meetings) {
        Meeting result= getCreateMeeting().createMeeting(meetings);
        return  Response.ok(result).build();
    }

    @Inject
    private ViewMeeting viewMeeting;

    public ViewMeeting getViewMeeting() {
        return viewMeeting;
    }

    @GET
    public Response viewMeeting(@QueryParam("username") String uname, @QueryParam("startdate") Timestamp startDate,@QueryParam("enddate") Timestamp endDate){
        List<Meeting> meetingList = getViewMeeting().viewMeeting(uname,startDate,endDate);
        return  Response.ok(meetingList).build();
    }

    @Inject
    private DeleteMeeting deleteMeeting;

    public DeleteMeeting getDeleteMeeting() {
        return deleteMeeting;
    }

    @DELETE
    public Response deleteMeeting(String meetId) {
        String[] arr= meetId.split("=",2);
        String result= getDeleteMeeting().deleteMeeting(arr[1]);
        return  Response.ok(result).build();
    }

}

