package com.academia.academia.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.academia.academia.model.entity.AsignaturaCursada;
import com.academia.academia.model.entity.Curso;
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
        List<Curso> cursos = academiaService.todosCursos();
        List<AsignaturaCursada> cursadas = academiaService.asignaturaCursadasEstudiante(estudiante);

        for (int i = 0; i < cursadas.size(); i++) {
            AsignaturaCursada asignatura = cursadas.get(i);
            if (asignatura.getNotaFinal() < 3.0) {
                model.addAttribute("titulo", "Matricula nueva");
                flash.addFlashAttribute("warning", "La materia no ha sido ganada");
                return "redirect:/plan/estudiantes/consultar/" + estudiante.getId();
            }
        }

        if (estudiante == null) {
            flash.addFlashAttribute("warning", "El estudiante no se encuentra en la base de datos");
            return "redirect:/plan/estudiantes/listar";
        }

        CursoMatriculado cursoMatriculado = new CursoMatriculado();
        cursoMatriculado.setEstudiante(estudiante);

        model.addAttribute("cursos", cursos);
        model.addAttribute("titulo", "Matricula nueva");
        model.addAttribute("matricula", cursoMatriculado);


        return "matriculas/matricula_nueva";
    }

    @PostMapping("/guardarmatricula")
    public String guardarMatricula(@ModelAttribute CursoMatriculado cursoMatriculado, RedirectAttributes flash) {

        Estudiante estudiante = academiaService.buscarEstudiante(cursoMatriculado.getEstudiante().getId());

        List<CursoMatriculado> cursoMatriculados = academiaService.cursoMatriculadoEstudiante(estudiante);
        List<AsignaturaCursada> cursados = academiaService.asignaturaCursadasEstudiante(estudiante);

        for (AsignaturaCursada mat : cursados) {
            if (mat.getEstudiante() != null && cursoMatriculado.getCurso() != null && 
                mat.getEstudiante().getId().equals(cursoMatriculado.getCurso().getId())) {

                    flash.addFlashAttribute("warning", "la materia ya ha sido cursada");
                    return "redirect:/plan/estudiantes/consultar/" + cursoMatriculado.getEstudiante().getId();
            }
        }
        
        int creditos = 0;
        for (CursoMatriculado curmat : cursoMatriculados){
            creditos += curmat.getCurso().getAsignatura().getNumero_creditos();
        }

        if (creditos > 22) {
            flash.addFlashAttribute("warning", "Has alcanzado el limite de creditos");
            return "redirect:/plan/estudiantes/consultar/" + cursoMatriculado.getEstudiante().getId();
        }
         
        return "redirect:/plan/estudiantes/consultar/" + cursoMatriculado.getEstudiante().getId();
    }
}
