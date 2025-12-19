package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceimpl implements EmployeeService {

   @Autowired
   EmployeeRepository repo;

    @Override
    public Employee createEmployee(Employee employee) {
        repo.findByEmail(employee.getEmail())
                .ifPresent(e -> {
                    throw new RuntimeException("Email already exists");
                });

        employee.setActive(true); // default active
        return repo.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = getEmployeeById(id);

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());

        return repo.save(existing);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
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

