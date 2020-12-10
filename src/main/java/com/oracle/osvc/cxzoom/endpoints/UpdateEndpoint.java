package com.oracle.osvc.cxzoom.endpoints;

import com.oracle.osvc.cxzoom.bean.User;
import com.oracle.osvc.cxzoom.operations.AddParticipant;
import com.oracle.osvc.cxzoom.operations.ChangeSchedule;
import com.oracle.osvc.cxzoom.operations.DeleteParticipant;
import io.helidon.microprofile.cors.CrossOrigin;
import org.json.simple.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

@Path("/update")
@RequestScoped
public class UpdateEndpoint {
    @OPTIONS
    @CrossOrigin(
            value = {"http://localhost:8000"},
            allowMethods = {HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.POST, HttpMethod.GET})
    public void optionsForGreeting(){

    }

    @Inject
    private DeleteParticipant deleteParticipant;

    public DeleteParticipant getDeleteParticipant() {
        return deleteParticipant;
    }

    @DELETE
    public Response deleteParticipant(JSONObject data){
        String uname = (String) data.get("userName");
        String meetid = (String) data.get("meetId");
        getDeleteParticipant().deleteUser(uname,meetid);
        return Response.ok().build();
    }

    @Inject
    private AddParticipant addParticipant;

    public AddParticipant getAddParticipant() {
        return addParticipant;
    }

    @POST
    public Response addParticipant(JSONObject data){
        String uname = (String) data.get("userName");
        String meetid = (String) data.get("meetId");
        User result=getAddParticipant().addParticipant(uname,meetid);
        return Response.ok(result).build();
    }

    @Inject
    private ChangeSchedule changeSchedule;

    public ChangeSchedule getChangeSchedule() {
        return changeSchedule;
    }

    @GET
    public Response fetchUserType(@QueryParam("date") Timestamp time,@QueryParam("meetid") String meetid){
        return Response.ok(getChangeSchedule().changeSchedule(time,meetid)).build();
    }

}
