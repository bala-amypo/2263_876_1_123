package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.*;

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);

    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);

     @Query("SELECT DISTINCT es.employee FROM EmployeeSkill es WHERE es.skill.name IN :skills")
    List<Employee> findEmployeesByAllSkillNames(
            List<String> skills,
            Long userId
    );
}
