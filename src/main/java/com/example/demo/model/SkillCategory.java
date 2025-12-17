package com.example.demo.model;


import jakarta.persistence.*;

@Entity
public class SkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String categoryName;
    private String description;
    private Boolean active;
 public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}


public String getCategoryName() {
    return categoryName;
}

public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public Boolean getActive() {
    return active;
}

public void setActive(Boolean active) {
    this.active = active;
}
public SkillCategory(String categoryName,
                String description, Boolean active) {

    this.categoryName = categoryName;
    this.description = description;
    this.active = active;
}
public SkillCategory(){

}
}

