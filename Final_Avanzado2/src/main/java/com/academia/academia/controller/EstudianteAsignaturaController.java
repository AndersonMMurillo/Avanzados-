package com.academia.academia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import com.academia.academia.model.service.AcademiaServiceIface;

@Controller
@RequestMapping("/estudiante")
@SessionAttributes("asignatura")
public class EstudianteAsignaturaController {
   
    private final AcademiaServiceIface service;

    public EstudianteAsignaturaController(AcademiaServiceIface service){
        this.service = service;
        
    }

    
}
