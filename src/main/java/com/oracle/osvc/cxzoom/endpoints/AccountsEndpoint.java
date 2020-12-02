package com.oracle.osvc.cxzoom.endpoints;

import com.oracle.osvc.cxzoom.bean.User;
import com.oracle.osvc.cxzoom.operations.DeleteUser;
import com.oracle.osvc.cxzoom.operations.RegisterUser;
import io.helidon.microprofile.cors.CrossOrigin;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.lang.String;


@Path("/account")
@RequestScoped
public class AccountsEndpoint {

    @OPTIONS
    @CrossOrigin(
            value = {"http://localhost:8000"},
            allowMethods = {HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.POST})
    public void optionsForGreeting() {

    }

    @Inject
    private RegisterUser registerUser;

    public RegisterUser getRegisterUser() {
        return registerUser;
    }

    @PUT
        public Response register(User users){
        String result = getRegisterUser().registerUser(users);
        return  Response.ok(result).build();
    }

    @Inject
    private DeleteUser deleteUser;

    public DeleteUser getDeleteUser() {
        return deleteUser;
    }

    @DELETE
    public Response deleteUser(@QueryParam("username") String uname) {
        String result = getDeleteUser().deleteUser(uname);
        return  Response.ok(result).build();
    }
}

