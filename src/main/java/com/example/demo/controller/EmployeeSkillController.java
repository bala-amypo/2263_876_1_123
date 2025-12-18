package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    @Autowired
    EmployeeSkillService service;

    @PostMapping("/")
    public EmployeeSkill create(@RequestBody EmployeeSkill es) {
        return service.createData(es);
    }

    @GetMapping("/")
    public List<EmployeeSkill> getAll() {
        return service.fetchAll();
    }

    @GetMapping("/{id}")
    public Optional<EmployeeSkill> getById(@PathVariable Long id) {
        return service.fetchById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EmployeeSkill> getByEmployee(@PathVariable Long employeeId) {
        return service.fetchByEmployee(employeeId);
    }

    @GetMapping("/skill/{skillId}")
    public List<EmployeeSkill> getBySkill(@PathVariable Long skillId) {
        return service.fetchBySkill(skillId);
    }

    @PutMapping("/{id}/deactivate")
public String deactivate(@PathVariable Long id) {

    Optional<EmployeeSkill> es = service.fetchById(id);

    if (es.isPresent()) {
        es.get().setActive(false);
        service.createData(es.get());
        return "EmployeeSkill deactivated successfully";
    } 
    else {
        return id + " not found";
    }
}

}
