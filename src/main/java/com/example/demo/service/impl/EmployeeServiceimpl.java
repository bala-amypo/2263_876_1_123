package com.example.demo.service.implementation;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceimpl implements EmployeeService {

    @Autowired
    EmployeeRepository repo;

    @Override
    public Employee createEmployee(Employee emp) {
        return repo.save(emp);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return repo.findById(id);
    }
}
