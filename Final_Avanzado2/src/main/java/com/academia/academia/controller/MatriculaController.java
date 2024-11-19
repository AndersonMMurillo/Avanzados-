package com.academia.academia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.academia.academia.model.entity.AsignaturaCursada;
import com.academia.academia.model.entity.CursoMatriculado;
import com.academia.academia.model.entity.Estudiante;
import com.academia.academia.model.service.AcademiaServiceIface;

@Controller
@RequestMapping("/plan/estudiantes")
@SessionAttributes("matricula")
public class MatriculaController {
    private final AcademiaServiceIface academiaService;

    public MatriculaController (AcademiaServiceIface academiaService) {
        this.academiaService = academiaService;
    }

    @GetMapping("/matriculanueva/{id}")
    public String  matriculaNueva (@PathVariable Long id, Model model, RedirectAttributes flash) {

        Estudiante estudiante = academiaService.buscarEstudiante(id);
        
        if (estudiante == null) {
            flash.addFlashAttribute("warning", "El estudiante no se encuentra en la base de datos");
            return "redirect:/plan/estudiantes/listar";
        }

        CursoMatriculado cursoMatriculado = new CursoMatriculado();
        cursoMatriculado.setEstudiante(estudiante);

        model.addAttribute("titulo", "Matricula nueva");
        model.addAttribute("matricula", cursoMatriculado);


        return "matriculas/matricula_nueva";
    }
}
