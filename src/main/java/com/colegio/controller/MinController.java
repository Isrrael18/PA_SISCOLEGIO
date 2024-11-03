package com.colegio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MinController {
    
    @GetMapping("/cursos")
    public String cursos() {
        return "cursos";
    }
    
    @GetMapping("/docentes")
    public String docentes() {
        return "docentes";
    }
    
    @GetMapping("/cursosdocentes")
    public String cursosdocentes() {
        return "cursosdocentes";
    }
}
