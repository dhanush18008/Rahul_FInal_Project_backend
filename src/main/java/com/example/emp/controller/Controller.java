package com.example.emp.controller;

import com.example.emp.model.Dto.SearchRequest;
import com.example.emp.model.Dto.SkillRequest;
import com.example.emp.model.Employee;
import com.example.emp.repository.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class Controller {

    public enum SkillAction{
        ADD,
        DELETE
    }

    public enum SearchType{
        ALL,
        ANY,
        REGEX
    }


    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/add-employee")
    @CrossOrigin
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.OK);
    }
@CrossOrigin
    @PostMapping("/get-all")
    public ResponseEntity<List< Employee>> getAll(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize ){
        if(pageNumber<0){
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        return new ResponseEntity<>(employeeService.getAllEmployees(pageNumber, pageSize),HttpStatus.OK);
    }

    @PostMapping("/add-skills")
    @CrossOrigin
    public ResponseEntity<Employee> manageSkill(
            @RequestBody SkillRequest skillRequest) {

        Integer id = skillRequest.getId();
        String skill = skillRequest.getSkill();
        SkillAction action = skillRequest.getAction();

        Employee updatedEmployee = null;

        if (action == SkillAction.ADD) {
            updatedEmployee = employeeService.addSkill(id, skill);
        } else if (action == SkillAction.DELETE) {
            updatedEmployee = employeeService.removeSkill(id, skill);
        }

        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/remove-employee/{id}")
    @CrossOrigin
    public ResponseEntity<String> removeEmployee(@PathVariable Integer id){
        employeeService.removeEmployee(id);
        return new ResponseEntity<>("employee deleted",HttpStatus.OK);
    }


    @PostMapping("/find-by-skills")
    @CrossOrigin
    public ResponseEntity<List<Employee>> searchEmployeesBySkills(@RequestBody SearchRequest searchRequest) {
        List<String> skills = searchRequest.getSkills();
        SearchType searchType = searchRequest.getSearchType();

        if (skills != null && !skills.isEmpty() && searchType != null) {
            // Convert the skills to lowercase
            List<String> lowerCaseSkills = skills.stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

            List<Employee> matchingEmployees = employeeService.findEmployeesBySkills(lowerCaseSkills, searchType);
            if (!matchingEmployees.isEmpty()) {
                return new ResponseEntity<>(matchingEmployees, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
