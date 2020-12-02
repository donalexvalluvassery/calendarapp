package com.oracle.osvc.cxzoom.applications;

import com.oracle.osvc.cxzoom.endpoints.AccountsEndpoint;
import com.oracle.osvc.cxzoom.endpoints.LoginEndpoint;
import com.oracle.osvc.cxzoom.endpoints.MeetingsEndpoint;
import com.oracle.osvc.cxzoom.endpoints.UpdateEndpoint;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("/")
@ApplicationScoped
public class CalendarApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(AccountsEndpoint.class, MeetingsEndpoint.class, LoginEndpoint.class, UpdateEndpoint.class);
    }
}
