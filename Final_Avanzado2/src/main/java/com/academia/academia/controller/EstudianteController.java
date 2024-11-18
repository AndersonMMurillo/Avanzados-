package com.academia.academia.controller;

import com.academia.academia.model.entity.Estudiante;
import com.academia.academia.model.service.AcademiaServiceIface;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/plan/estudiantes")
public class EstudianteController {
    
    private final AcademiaServiceIface service;

    public EstudianteController(AcademiaServiceIface service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public String listarEstudiantes(Model model) {
        List<Estudiante> estudiantes = service.todosEstudiantes();
        model.addAttribute("titulo", "Listado de Estudiantes");
        model.addAttribute("estudiantes", estudiantes);
        return "estudiante/listado_estudiantes";
    }

    @GetMapping("/nuevo")
    public String nuevoEstudiante(Model model) {
        model.addAttribute("titulo", "Nuevo Estudiante");
        model.addAttribute("estudiante", new Estudiante());
        return "estudiante/formulario_estudiante";
    }

    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Estudiante estudiante = service.buscarEstudiante(id);
        if (estudiante == null) {
            flash.addFlashAttribute("error", "El estudiante no existe");
            return "redirect:/plan/estudiantes/listar";
        }
        model.addAttribute("titulo", "Editar Estudiante");
        model.addAttribute("estudiante", estudiante);
        return "estudiante/formulario_estudiante";
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(@Valid @ModelAttribute Estudiante estudiante, 
                                  BindingResult result, 
                                  RedirectAttributes flash,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", estudiante.getId() != null ? "Editar Estudiante" : "Nuevo Estudiante");
            return "estudiante/formulario_estudiante";
        }

        String mensajeFlash = (estudiante.getId() != null) ? "Estudiante editado con éxito" : "Estudiante creado con éxito";
        service.guardarEstudiante(estudiante);
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/plan/estudiantes/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id, RedirectAttributes flash) {
        try {
            service.eliminarEstudiante(id);
            flash.addFlashAttribute("success", "Estudiante eliminado con éxito");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar el estudiante");
        }
        return "redirect:/plan/estudiantes/listar";
    }

    @GetMapping("/consultar/{id}")
    public String verEstudiante(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Estudiante estudiante = service.buscarEstudiante(id);
        if (estudiante == null) {
            flash.addFlashAttribute("error", "El estudiante no existe");
            return "redirect:/plan/estudiantes/listar";
        }
        model.addAttribute("titulo", "Detalle del Estudiante");
        model.addAttribute("estudiante", estudiante);
        return "estudiante/consultar_estudiante";
    }
}