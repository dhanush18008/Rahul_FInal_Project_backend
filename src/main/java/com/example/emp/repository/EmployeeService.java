package com.example.emp.repository;

import com.example.emp.controller.Controller;
import com.example.emp.exception.InvalidPageNumberException;
import com.example.emp.model.Employee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(Integer pageNumber,Integer pageSize){

        if (pageNumber < 0) {
            throw new InvalidPageNumberException("Page number cannot be negative");
        }

        Pageable p= PageRequest.of(pageNumber,pageSize);
        Page<Employee> allEmployeePage=employeeRepository.findAll(p);
        return allEmployeePage.getContent();
    }

    public Employee createEmployee(Employee employee){

//        if (employeeRepository.findByEmail(employee.getEmail()) != null) {
//            throw new IllegalArgumentException("Employee with email " + employee.getEmail() + " already exists");
//        }

        return employeeRepository.save(employee);
    }

    public void removeEmployee(Integer id){
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            Employee employee=employeeOptional.get();
            employeeRepository.delete(employee);

        }
        else{
            throw new EntityNotFoundException("Employee does not exists");
        }
    }


    public Employee addSkill(Integer employeeId, String skill) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            List<String> skills = employee.getSkills();
            skills.add(skill);
            employee.setSkills(skills);
            return employeeRepository.save(employee);
        }

        return null; // Employee not found
    }

    public Employee removeSkill(Integer employeeId, String skill) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            List<String> skills = employee.getSkills();
            skills.remove(skill);
            employee.setSkills(skills);
            return employeeRepository.save(employee);
        }

        return null; // Employee not found
    }

    public List<Employee> findEmployeesBySkills(List<String> skills, Controller.SearchType searchType) {
        List<Employee> allEmployees = employeeRepository.findAll();

        return allEmployees.stream()
                .filter(employee -> {
                    List<String> employeeSkills = employee.getSkills().stream()
                            .map(String::toLowerCase)
                            .collect(Collectors.toList());
                    if (searchType == Controller.SearchType.ANY) {
                        return employeeSkills.stream().anyMatch(skills::contains);
                    } else if(searchType == Controller.SearchType.ALL) {
                        return employeeSkills.containsAll(skills);
                    }
                    else if(searchType == Controller.SearchType.REGEX){
                        return skills.isEmpty() || skills.stream().allMatch(partialSkill -> employeeSkills.stream().anyMatch(skill -> skill.matches(partialSkill)));
                    }
                    return  true;
                })
                .collect(Collectors.toList());
    }


    private boolean isPartialMatch(String skill, List<String> skills) {
        return skills.isEmpty() || skills.stream().anyMatch(partialSkill -> skill.matches(createRegex(partialSkill)));
    }

    private String createRegex(String partialSkill) {
        return partialSkill.replace("*", ".*");
    }

}
