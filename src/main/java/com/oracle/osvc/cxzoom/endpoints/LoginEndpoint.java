package com.oracle.osvc.cxzoom.endpoints;

import com.oracle.osvc.cxzoom.bean.User;
import com.oracle.osvc.cxzoom.operations.FetchUser;
import io.helidon.microprofile.cors.CrossOrigin;

import javax.inject.Inject;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginEndpoint {
    @OPTIONS
    @CrossOrigin(
            value = {"http://152.67.161.137:8000"},
            allowMethods = {HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.POST})
    public void optionsForGreeting() {

    }

    @Inject
    private FetchUser fetchUser;

    public FetchUser getFetchUser() {
        return fetchUser;
    }

    @POST
    public Response login(User user) {
        User userBean = getFetchUser().fetchUserBean(user);
        if(userBean.getFirstName() != null){
            return  Response.ok(user).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
}
