package com.example.emp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:5173")
public class AuthController {
    @PostMapping("/")
    @CrossOrigin
    public String secure(){
        return "hello";
    }
}
