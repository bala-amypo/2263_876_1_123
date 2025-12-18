package com.example.demo.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

   
   public EmployeeService ser;
   public 
    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee emp) {
        return ser.createEmployee(emp);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Employee emp) {
        Optional<Employee> e = ser.getEmployeeById(id);
        if (e.isPresent()) {
            emp.setId(id);
            ser.createEmployee(emp);
            return "Employee Updated Successfully";
        }
        return "Employee not found";
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable int id) {
        return ser.getEmployeeById(id);
    }

    @GetMapping("/")
    public List<Employee>getAllEmployees() {
        return ser.getAllEmployees();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivate(@PathVariable int id) {
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
