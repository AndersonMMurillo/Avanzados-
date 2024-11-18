package com.academia.academia.model.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos_matriculados")
public class CursoMatriculado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(length = 20)
    private String estado;

    @Column
    private double notaFinal;

    public CursoMatriculado() {
    }

    public CursoMatriculado(Long id, Estudiante estudiante, Curso curso, String estado, double notaFinal) {
        this.id = id;
        this.estudiante = estudiante;
        this.curso = curso;
        this.estado = estado;
        this.notaFinal = notaFinal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    
    
}
