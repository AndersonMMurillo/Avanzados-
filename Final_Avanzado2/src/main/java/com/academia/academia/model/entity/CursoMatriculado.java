package com.academia.academia.model.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cursos_matriculados")
public class CursoMatriculado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @NotEmpty
    @Size(min = 4, max = 6)
    @Column(length = 50)
    private String periodo;

    @NotEmpty
    @Size(min = 6)
    @Column(length = 20)
    private String estado;

    @Column
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "5.0", inclusive = true)
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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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

    @Override
    public String toString() {
        return "CursoMatriculado [id=" + id + ", estudiante=" + estudiante + ", curso=" + curso + ", periodo=" + periodo
                + ", estado=" + estado + ", notaFinal=" + notaFinal + "]";
    }

    public Integer getTotalCreditos() {
        return curso.getAsignatura().getNumero_creditos();
    }
    
}
