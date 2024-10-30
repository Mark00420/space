package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NameController {

    @Autowired
    private NameRepository nameRepository;

    @PostMapping("/names")
    public @ResponseBody Name createName(@RequestBody Name name) {
        return nameRepository.save(name);
    }

    // Add a test endpoint
    @GetMapping("/test")
    public String test() {
        return "Backend is up!";
    }
}
