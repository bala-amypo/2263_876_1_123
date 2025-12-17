package com.example.demo.model;
import jakarta.persistence.*;
@Entity
public class Employee{
    @Id
    private long id;
    private String name;
}