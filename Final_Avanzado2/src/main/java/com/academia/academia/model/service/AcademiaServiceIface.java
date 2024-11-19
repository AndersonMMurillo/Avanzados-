package com.academia.academia.model.service;

import com.academia.academia.model.entity.Asignatura;
import com.academia.academia.model.entity.AsignaturaCursada;
import com.academia.academia.model.entity.Curso;
import com.academia.academia.model.entity.Estudiante;
import com.academia.academia.model.entity.ProgramaAcademico;

import java.util.List;

public interface AcademiaServiceIface {
    List<Estudiante> todosEstudiantes();
    void guardarEstudiante(Estudiante estudiante);
    Estudiante buscarEstudiante(Long id);
    void eliminarEstudiante(Long id);

    //servicio para asignatura 
     List<Asignatura> todasAsignaturas();
    void guardarAsignatura(Asignatura asignatura);
    Asignatura buscarAsignatura(Long id);
    void eliminarAsignatura(Long id);

    //services para curso
    List<Curso> todosCursos();
    void guardarCurso(Curso curso);
    Curso buscarCurso(Long id);
    void eliminarCurso(Long id);

    //services para programa academicos
     List<ProgramaAcademico> todosProgramasAcademicos();
    void guardarProgramaAcademico(ProgramaAcademico programaAcademico);
    ProgramaAcademico buscarProgramaAcademico(Long id);
    void eliminarProgramaAcademico(Long id);

    //services para Asignatura cursada
    List<AsignaturaCursada> todasasignaturaCursadas();
    AsignaturaCursada asignaturaCursada(Long id);
    List<AsignaturaCursada> asignaturaCursadasEstudiante(Estudiante estudiante); // Asignaturas cursadas del estudiante
}