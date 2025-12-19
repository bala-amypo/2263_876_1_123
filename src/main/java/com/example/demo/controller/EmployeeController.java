package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService ser;

    // constructor injection
    public EmployeeController(EmployeeService ser) {
        this.ser = ser;
    }

    // POST /api/employees
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return ser.createEmployee(employee);
    }

    // PUT /api/employees/{id}
    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {
        return ser.updateEmployee(id, employee);
    }

    // GET /api/employees/{id}
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return ser.getEmployeeById(id);
    }

    // GET /api/employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return ser.getAllEmployees();
    }

    // PUT /api/employees/{id}/deactivate
    @PutMapping("/{id}/deactivate")
    public String deactivateEmployee(@PathVariable Long id) {
        employeeService.deactivateEmployee(id);
        return "Employee deactivated successfully";
    }
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
