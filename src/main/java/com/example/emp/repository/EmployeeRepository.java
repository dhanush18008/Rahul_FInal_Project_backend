package com.example.emp.repository;

import com.example.emp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

//    Employee findByEmail(String email);
}
