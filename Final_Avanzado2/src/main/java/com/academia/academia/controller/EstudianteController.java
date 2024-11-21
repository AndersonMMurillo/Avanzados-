package com.academia.academia.controller;

import com.academia.academia.model.entity.Estudiante;
import com.academia.academia.model.entity.ProgramaAcademico;
import com.academia.academia.model.service.AcademiaServiceIface;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/plan/estudiantes")
@SessionAttributes("estudiante")
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

        List<ProgramaAcademico> programaAcademicos = service.todosProgramasAcademicos();

        model.addAttribute("titulo", "Nuevo Estudiante");
        model.addAttribute("accion", "Crear");
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("programas", programaAcademicos);
        return "estudiante/formulario_estudiante";
    }

    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Estudiante estudiante = service.buscarEstudiante(id);
        List<ProgramaAcademico> programaAcademicos = service.todosProgramasAcademicos();
        if (estudiante == null) {
            flash.addFlashAttribute("error", "El estudiante no existe");
            return "redirect:/plan/estudiantes/listar";
        }

        model.addAttribute("programas", programaAcademicos);
        model.addAttribute("titulo", "Editar Estudiante");
        model.addAttribute("estudiante", estudiante);
        return "estudiante/formulario_estudiante";
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(@Valid @ModelAttribute Estudiante estudiante, 
        BindingResult result, RedirectAttributes flash,Model model, SessionStatus status) {

            String accion = (estudiante.getId() == null) ? "Guardar" : "Modificar"  ;
         
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Estudiante");
            model.addAttribute("accion", accion);
            model.addAttribute("info", "Complemente o corrija la información de los campos del formulario");
            return "estudiante/formulario_estudiante";
        }

        service.guardarEstudiante(estudiante);
        status.setComplete();
        flash.addFlashAttribute("success", "El registro fue " + (estudiante.getId() == null ? "agregado" : "modificado" ) + " con éxito");
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

        model.addAttribute("titulo", "Detalle del Estudiante: " + estudiante.getApellidos());
        model.addAttribute("estudiante", estudiante);
        return "estudiante/consultar_estudiante";
    }
   

}