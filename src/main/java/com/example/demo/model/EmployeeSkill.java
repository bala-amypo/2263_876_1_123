package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_skills")
public class EmployeeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    public enum ProficiencyLevel {
        Beginner,
        Intermediate,
        Advanced,
        Expert
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProficiencyLevel proficiencyLevel;

    @Column(nullable = false)
    private Integer yearsOfExperience;

    @Column(nullable = false)
    private Boolean active = true;

    public EmployeeSkill() {}

    public EmployeeSkill(Employee employee, Skill skill,
                         ProficiencyLevel proficiencyLevel,
                         Integer yearsOfExperience, Boolean active) {
        setEmployee(employee);
        setSkill(skill);
        setProficiencyLevel(proficiencyLevel);
        setYearsOfExperience(yearsOfExperience);
        this.active = active != null ? active : true;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) {
        if (employee == null || Boolean.FALSE.equals(employee.getActive())) {
            throw new IllegalArgumentException("inactive employee");
        }
        this.employee = employee;
    }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) {
        if (skill == null || Boolean.FALSE.equals(skill.getActive())) {
            throw new IllegalArgumentException("inactive skill");
        }
        this.skill = skill;
    }

    public ProficiencyLevel getProficiencyLevel() { return proficiencyLevel; }
    public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
        if (proficiencyLevel == null) {
            throw new IllegalArgumentException("Invalid proficiency");
        }
        this.proficiencyLevel = proficiencyLevel;
    }

    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Integer yearsOfExperience) {
        if (yearsOfExperience == null || yearsOfExperience < 0) {
            throw new IllegalArgumentException("Experience years");
        }
        this.yearsOfExperience = yearsOfExperience;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
