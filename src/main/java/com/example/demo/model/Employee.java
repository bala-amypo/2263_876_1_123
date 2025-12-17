package com.example.demo.model;
import jakarta.persistence.*;
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Primary key
    private String fullname;
    @Column(unique = true)
    private String email;  // Unique column
    private String department;
    private String jobTitle;
    private Boolean active;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
