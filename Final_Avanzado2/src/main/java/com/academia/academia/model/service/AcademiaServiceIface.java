package com.academia.academia.model.service;

import com.academia.academia.model.entity.Asignatura;
import com.academia.academia.model.entity.AsignaturaCursada;
import com.academia.academia.model.entity.AsignaturaPlan;
import com.academia.academia.model.entity.Curso;
import com.academia.academia.model.entity.CursoMatriculado;
import com.academia.academia.model.entity.Estudiante;
import com.academia.academia.model.entity.ProgramaAcademico;

import java.util.List;

public interface AcademiaServiceIface {
    public List<Estudiante> todosEstudiantes();
    public void guardarEstudiante(Estudiante estudiante);
    public Estudiante buscarEstudiante(Long id);
    public void eliminarEstudiante(Long id);

    //servicio para asignatura 
    public List<Asignatura> todasAsignaturas();
    public void guardarAsignatura(Asignatura asignatura);
    public Asignatura buscarAsignatura(Long id);
    public void eliminarAsignatura(Long id);

    //services para curso
    public List<Curso> todosCursos();
    public void guardarCurso(Curso curso);
    public Curso buscarCurso(Long id);
    public void eliminarCurso(Long id);

    //services para programa academicos
    public List<ProgramaAcademico> todosProgramasAcademicos();
    public void guardarProgramaAcademico(ProgramaAcademico programaAcademico);
    public ProgramaAcademico buscarProgramaAcademico(Long id);
    public void eliminarProgramaAcademico(Long id);

    //services para Asignatura cursada
    public List<AsignaturaCursada> todasasignaturaCursadas();
    public AsignaturaCursada buscarAsignaturaCursada(Long id);
    public List<AsignaturaCursada> asignaturaCursadasEstudiante(Estudiante estudiante); // Asignaturas cursadas del estudiante

    // services para Curso matriculado
    public List<CursoMatriculado> cursoMatriculadoEstudiante(Estudiante estudiante);
    public void guardarMatricula(CursoMatriculado cursoMatriculado);
    public void eliminarMatricula (Long id);
    public CursoMatriculado buscarCursoMatriculado (Long id);

    // services para Plan de asignaturas
    public List<AsignaturaPlan> todAsignaturaPlans();
}