package com.example.demo.service;

import com.example.demo.model.EmployeeSkill;

import java.util.List;
import java.util.Optional;

public interface EmployeeSkillService {

    EmployeeSkill createData(EmployeeSkill es);

    Optional<EmployeeSkill> fetchById(Long id);

    List<EmployeeSkill> fetchByEmployee(Long employeeId);

    List<EmployeeSkill> fetchBySkill(Long skillId);
}
