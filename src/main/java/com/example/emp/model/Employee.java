package com.example.emp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String secondName;

    private String dob;
    private String department;
    private String managerId;

//    private String email;

//    public void setEmail(String email) {
//        this.email = email;
//    }

    @ElementCollection
    private List<String> skills;

    public String getFirstName() {
        return firstName;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getDob() {
        return dob;
    }

    public String getDepartment() {
        return department;
    }

//    @Column(unique = true) // Ensure email uniqueness at the database level
//    public String getEmail() {
//        return email;
//    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerId() {
        return managerId;
    }

    public Employee() {
    }


    public Employee(String firstName, String secondName, String dob, String department, String managerId,List<String> skills) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.dob = dob;
        this.department = department;
        this.managerId = managerId;
        this.skills=skills;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", dob='" + dob + '\'' +
                ", department='" + department + '\'' +
                ", manager='" + managerId + '\'' +
                '}';
    }
}

