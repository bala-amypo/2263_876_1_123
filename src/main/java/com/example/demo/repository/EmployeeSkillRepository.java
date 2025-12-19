package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    @Query("""
        SELECT es.employee
        FROM EmployeeSkill es
        WHERE es.active = true
          AND es.employee.active = true
          AND es.skill.name IN :skills
        GROUP BY es.employee
        HAVING COUNT(DISTINCT es.skill.name) = :#{#skills.size()}
    """)
    List<Employee> findEmployeesByAllSkillNames(
            @Param("skills") List<String> skills,
            Long userId   // keep this parameter for tests, but DO NOT annotate it
    );

    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);

    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);
}
