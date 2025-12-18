package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee emp) {
        return ser.createEmployee(emp);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Employee emp) {

        Optional<Employee> e = ser.getEmployeeById(id);

        if (e.isPresent()) {
            emp.setId(id);
            ser.createEmployee(emp);
            return "Employee Updated Successfully";
        }

        return "Employee not found";
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return ser.getEmployeeById(id);
    }

    @GetMapping("/")
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
