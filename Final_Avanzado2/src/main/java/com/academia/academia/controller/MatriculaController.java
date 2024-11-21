package com.academia.academia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.academia.academia.model.entity.AsignaturaCursada;
import com.academia.academia.model.entity.AsignaturaPlan;
import com.academia.academia.model.entity.Curso;
import com.academia.academia.model.entity.CursoMatriculado;
import com.academia.academia.model.entity.Estudiante;
import com.academia.academia.model.entity.ProgramaAcademico;
import com.academia.academia.model.service.AcademiaServiceIface;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/plan/estudiantes")
@SessionAttributes("matricula")
public class MatriculaController {
    private final AcademiaServiceIface academiaService;

    public MatriculaController(AcademiaServiceIface academiaService) {
        this.academiaService = academiaService;
    }

    @GetMapping("/matriculanueva/{id}")
    public String matriculaNueva(@PathVariable Long id, Model model, RedirectAttributes flash) {

        Estudiante estudiante = academiaService.buscarEstudiante(id);

        if (estudiante == null) {
            flash.addFlashAttribute("warning", "El estudiante no se encuentra en la base de datos");
            return "redirect:/plan/estudiantes/listar";
        }

        ProgramaAcademico programaAcademico = estudiante.getProgramaAcademico();
        if (programaAcademico == null) {
            flash.addFlashAttribute("warning", "El estudiante no tiene un programa académico asignado");
            return "redirect:/plan/estudiantes/listar";
        }

        List<AsignaturaCursada> cursadas = academiaService.asignaturaCursadasEstudiante(estudiante);
        List<Long> asignaturasAprobadas = new ArrayList<>();

        for (AsignaturaCursada cursada : cursadas) {
            if (cursada.getNotaFinal() >= 3.0) {
                asignaturasAprobadas.add(cursada.getAsignatura().getId());
            }
        }

        List<Curso> todosCursos = academiaService.todosCursos();
        List<Curso> cursosFiltrados = new ArrayList<>();

        for (Curso curso : todosCursos) {
            if (curso != null &&
                    curso.getAsignatura() != null &&
                    !curso.getAsignatura().getAsignaturaPlans().isEmpty()) {

                AsignaturaPlan planAsignatura = curso.getAsignatura().getAsignaturaPlans().get(0);

                // Ander esto verifica estas condiciones:
                // 1. Si el semestre actual es igual
                // 2. Si no hay prerequisito o el prerequisito ya ha sido aprobado
                if (planAsignatura.getPlanEstudio().getProgramaAcademico().getId().equals(programaAcademico.getId()) &&
                        (planAsignatura.getPrerequisito() == null || asignaturasAprobadas.contains(planAsignatura.getPrerequisito()))) {
                    cursosFiltrados.add(curso);
                }
            }
        }

        CursoMatriculado cursoMatriculado = new CursoMatriculado();
        cursoMatriculado.setEstudiante(estudiante);

        model.addAttribute("cursos", cursosFiltrados);
        model.addAttribute("titulo", "Matricula nueva");
        model.addAttribute("matricula", cursoMatriculado);

        return "matriculas/matricula_nueva";
    }

    @PostMapping("/guardarmatricula")
    public String guardarMatricula(@Valid @ModelAttribute CursoMatriculado cursoMatriculado, BindingResult result,
            RedirectAttributes flash, @RequestParam(name = "estudiante_id", required = true) Long estudianteId,
            SessionStatus status, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Nueva Matricula");
            flash.addAttribute("error", "Complemente o corrija la información de los campos del formulario");  
            return "matriculas/matricula_nueva";
        }

        if (cursoMatriculado.getEstudiante() == null) {
            Estudiante estudiante = academiaService.buscarEstudiante(estudianteId);
            cursoMatriculado.setEstudiante(estudiante);
        }

        List<CursoMatriculado> cursoMatriculados = academiaService.cursoMatriculadoEstudiante(cursoMatriculado.getEstudiante());

        int creditos = 0;
        for (CursoMatriculado curmat : cursoMatriculados) {
            creditos += curmat.getCurso().getAsignatura().getNumero_creditos();
        }

        int creditoCursonuevo = cursoMatriculado.getCurso().getAsignatura().getNumero_creditos();

        if (creditos + creditoCursonuevo  > 22) {
            model.addAttribute("titulo", "Nueva matricula");
            flash.addFlashAttribute("warning", "No se puede matricular. Límite de créditos excedido o se puede exceder");
            return "redirect:/plan/estudiantes/consultar/" + cursoMatriculado.getEstudiante().getId();
        }

        academiaService.guardarMatricula(cursoMatriculado);
        status.setComplete();
        flash.addFlashAttribute("success", "La matricula fue guardada correctamente");

        return "redirect:/plan/estudiantes/consultar/" + cursoMatriculado.getEstudiante().getId();
    }

    @GetMapping("/eliminarmatricula/{id}")
    public String eliminarMatricula(@PathVariable Long id, Model model, RedirectAttributes flash) {

        CursoMatriculado cursoMatriculado = academiaService.buscarCursoMatriculado(id);

        if (cursoMatriculado != null) {
            academiaService.eliminarMatricula(id);
            flash.addFlashAttribute("success", "La matricula con número " + cursoMatriculado.getId() + " fue eliminada");
        } else {
            flash.addFlashAttribute("error", "La matricula con el número " + id + " No fue encontrada");
        }

        return "redirect:/plan/estudiantes/consultar/" + cursoMatriculado.getEstudiante().getId();
    }
}
