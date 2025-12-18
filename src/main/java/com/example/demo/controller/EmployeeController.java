package com.example.demo.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @PostMapping("/")
    public Employee create(@RequestBody Employee emp) {
        return service.createEmployee(emp);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Employee emp) {
        Optional<Employee> e = service.getEmployeeById(id);
        if (e.isPresent()) {
            emp.setId(id);
            service.createEmployee(emp);
            return "Employee Updated Successfully";
        }
        return "Employee not found";
    }

    @GetMapping("/{id}")
    public Optional<Employee> getById(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    @GetMapping("/")
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivate(@PathVariable int id, @RequestBody Employee emp) {
        Optional<Employee> e = service.getEmployeeById(id);
        if (e.isPresent()) {
            Employee emp = e.get();
            emp.setActive(false);
            service.createEmployee(emp);
            return "Employee Deactivated";
        }
        return "Employee not found";
    }
}
