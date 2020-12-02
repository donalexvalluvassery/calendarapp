package com.oracle.osvc.cxzoom.configs;

import com.oracle.osvc.cxzoom.connections.DbUtils;
import io.helidon.config.Config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CalendarConfig {

    @Inject
    private Config rootConfig;

    public Config getRootConfig() {
        return rootConfig;
    }

    public void setRootConfig(Config rootConfig) {
        this.rootConfig = rootConfig;
    }
}
