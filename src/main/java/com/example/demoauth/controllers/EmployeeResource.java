package com.example.demoauth.controllers;

import com.example.demoauth.models.Employee;
import com.example.demoauth.repository.EmployeeRepository;
import com.example.demoauth.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/employees")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Employee getEmployee(@PathParam("id") int id) {
        return employeeService.getEmployeeById(id);
    }
}