package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService ser;

    public EmployeeController(EmployeeService ser) {
        this.ser = ser;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return ser.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {
        return ser.updateEmployee(id, employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return ser.getEmployeeById(id);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return ser.getAllEmployees();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivate(@PathVariable Long id) {

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
