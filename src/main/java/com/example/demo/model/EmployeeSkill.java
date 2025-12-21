package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "employee_skills")
public class EmployeeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @ManyToOne
   @JoinColumn(name = "employee_id")
   @JsonIgnore
   private Employee employee;

   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(name = "skill_id", nullable = false)
   private Skill skill;

    // MUST be String (tests expect String, not enum)
    @Column(nullable = false)
    private String proficiencyLevel;

    @Column(nullable = false)
    private Integer yearsOfExperience;

    @Column(nullable = false)
    private Boolean active = true;

    public EmployeeSkill() {
    }

    public EmployeeSkill(Employee employee,
                         Skill skill,
                         String proficiencyLevel,
                         Integer yearsOfExperience,
                         Boolean active) {
        this.employee = employee;
        this.skill = skill;
        this.proficiencyLevel = proficiencyLevel;
        this.yearsOfExperience = yearsOfExperience;
        this.active = active;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public String getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(String proficiencyLevel) {
     this.proficiencyLevel = proficiencyLevel; 
     }

    
    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
