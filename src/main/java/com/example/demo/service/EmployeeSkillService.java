package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.EmployeeSkill;

public interface EmployeeSkillService {

    EmployeeSkill createData(EmployeeSkill es);

    List<EmployeeSkill> fetchAll();

    Optional<EmployeeSkill> fetchById(Long id);

    List<EmployeeSkill> fetchByEmployee(Long employeeId);

    List<EmployeeSkill> fetchBySkill(Long skillId);

    void deactivate(Long id);
}
