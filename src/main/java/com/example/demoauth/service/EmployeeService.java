package com.example.demoauth.service;

import com.example.demoauth.models.Employee;
import com.example.demoauth.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }
}
