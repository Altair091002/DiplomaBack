package com.example.demoauth.repository;

import com.example.demoauth.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee getEmployeeById(int id);
}
