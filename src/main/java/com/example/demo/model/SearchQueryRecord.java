package com.example.demo.model;
import java.sql.Timestamp;

import org.hibernate.annotations.*;

import jakarta.persistence.*;

@Entity
@Table(name = "search_query_records")
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // PK

    @Column(nullable = false)
    private Long searcherId;

    @Column(nullable = false)
    private String skillsRequested;   // must not be empty

    private Integer resultsCount;

  
    @Column(updatable = false)
    private Timestamp searchedAt;   // auto on create
   @PrePersist
    public void onCreate() {
        if (skillsRequested == null || skillsRequested.trim().isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }
        this.searchedAt = new Timestamp(System.currentTimeMillis());
    }
   
    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSearcherId() {
        return searcherId;
    }

    public void setSearcherId(Long searcherId) {
        this.searcherId = searcherId;
    }

    public String getSkillsRequested() {
        return skillsRequested;
    }

    public void setSkillsRequested(String skillsRequested) {
        this.skillsRequested = skillsRequested;
    }

    public Integer getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(Integer resultsCount) {
        this.resultsCount = resultsCount;
    }

    public Timestamp getSearchedAt() {
        return searchedAt;
    }
    
    // Parameterized constructor
    public SearchQueryRecord(Long searcherId, String skillsRequested,
                             Integer resultsCount) {
        this.searcherId = searcherId;
        this.skillsRequested = skillsRequested;
        this.resultsCount = resultsCount;
    }
     // No-arg constructor
    public SearchQueryRecord() {

    }
}
