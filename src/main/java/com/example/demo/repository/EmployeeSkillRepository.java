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

    // Find employees who have ALL the given skills
    @Query("""
        SELECT e.employee 
        FROM EmployeeSkill e 
        WHERE e.active = true 
          AND e.skill.name IN :skills 
          AND e.employee.active = true 
        GROUP BY e.employee 
        HAVING COUNT(DISTINCT e.skill.name) = :#{#skills.size()}
    """)
    List<Employee> findEmployeesByAllSkillNames(@Param("skills") List<String> skills, @Param("userId") Long userId);

    // Other required repository methods
    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);

    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);
}
