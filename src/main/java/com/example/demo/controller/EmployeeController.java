package com.example.demo.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService ser;

    @PostMapping("/api/employees")
    public Employee create(@RequestBody Employee emp) {
        return ser.createEmployee(emp);
    }

    @PutMapping("/api/employees/{id}")
    public String update(@PathVariable int id, @RequestBody Employee emp) {
        Optional<Employee> e = ser.getEmployeeById(id);
        if (e.isPresent()) {
            emp.setId(id);
            ser.createEmployee(emp);
            return "Employee Updated Successfully";
        }
        return "Employee not found";
    }

    @GetMapping("/api/employees/{id}")
    public Optional<Employee> getById(@PathVariable int id) {
        return ser.getEmployeeById(id);
    }

    @GetMapping("/api/employees/")
    public List<Employee> getAll() {
        return ser.getAllEmployees();
    }

    @PutMapping("/api/employees/{id}/deactivate")
    public String deactivate(@PathVariable int id,@RequestBody Employee emp) {
        Optional<Employee> e = ser.getEmployeeById(id);
        if (e.isPresent()) {
            Employee emp = e.get();
            emp.setActive(false);
            ser.createEmployee(emp);
            return "Employee Deactivated";
        }
        return "Employee not found";
    }
}
