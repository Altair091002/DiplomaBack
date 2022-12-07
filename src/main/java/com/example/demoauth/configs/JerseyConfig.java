package com.example.demoauth.configs;

import com.example.demoauth.controllers.EmployeeResource;
import com.example.demoauth.service.EmployeeService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    public void init() {
        register(EmployeeResource.class);
    }
}
