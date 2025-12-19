package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceimpl implements EmployeeService {

    private final EmployeeRepository repo;   // constructor injection only

    // constructor signature must be exactly (EmployeeRepository)
    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        // unique email check, no lambda if you prefer
        Employee existing = repo.findByEmail(employee.getEmail()).orElse(null);
        if (existing != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        // default active = true if not set
        if (employee.getActive() == null) {
            employee.setActive(true);
        }

        return repo.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = repo.findById(id).orElse(null);
        if (existing == null) {
            throw new ResourceNotFoundException("Employee not found");
        }

        // use the correct field names from your Employee entity
        existing.setFullName(employee.getFullName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setJobTitle(employee.getJobTitle());

        if (employee.getActive() != null) {
            existing.setActive(employee.getActive());
        }

        return repo.save(existing);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee emp = repo.findById(id).orElse(null);
        if (emp == null) {
            throw new ResourceNotFoundException("Employee not found");
        }
        return emp;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findByActiveTrue();
    }

    @Override
    public void deactivateEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employee.setActive(false);
        repo.save(employee);
    }
}

