package com.photoz_clone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class health {

    @GetMapping("")
    public String checkHealth() {
        return "ok";
    }
    
}
