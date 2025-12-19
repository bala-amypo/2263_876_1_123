package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

   @Autowired
   EmployeeRepository ser;

    @Override
    public Employee createEmployee(Employee employee) {
        employeeRepository.findByEmail(employee.getEmail())
                .ifPresent(e -> {
                    throw new RuntimeException("Email already exists");
                });

        employee.setActive(true); // default active
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = getEmployeeById(id);

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());

        return employeeRepository.save(existing);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findByActiveTrue();
    }

    @Override
    public void deactivateEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employee.setActive(false);
        employeeRepository.save(employee);
    }
}

