package com.academia.academia.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "estudiantes")
public class Estudiante implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 10)
    @NotEmpty
    @Column(length = 20)
    private String identificacion;
    
    @NotEmpty
    @Size(min = 2, max = 50)
    @Column(length = 50)
    private String apellidos;
    
    @NotEmpty
    @Size(min = 2, max = 50)
    @Column(length = 50)
    private String nombres;
    
    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    @Column(name = "semestre_actual")
    private Integer semestre_actual;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programaAcademico_id", nullable = false)
    @NotNull
    private ProgramaAcademico programaAcademico;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CursoMatriculado> cursoMatriculados;

    public Estudiante() {
        cursoMatriculados = new ArrayList<>();
    }

    public Estudiante(Long id, String identificacion, String apellidos, String nombres,
             Integer semestre_actual, ProgramaAcademico programaAcademico) {
        this.id = id;
        this.identificacion = identificacion;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.semestre_actual = semestre_actual;
        this.programaAcademico = programaAcademico;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Integer getSemestre_actual() {
        return semestre_actual != null ? semestre_actual : 1;
    }

    public void setSemestre_actual(Integer semestre_actual) {
        this.semestre_actual = semestre_actual;
    } 

    public ProgramaAcademico getProgramaAcademico() {
        return programaAcademico;
    }

    public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
        this.programaAcademico = programaAcademico;
    }

    public List<CursoMatriculado> getCursoMatriculados() {
        return cursoMatriculados;
    }

    public void setCursoMatriculados(List<CursoMatriculado> cursoMatriculados) {
        this.cursoMatriculados = cursoMatriculados;
    }

    public void addCurso(CursoMatriculado cursoMatriculado) {
        this.cursoMatriculados.add(cursoMatriculado);
    }

    public Integer getTotalCreditosMatriculados() {
        int totalCreditos = 0;
        for (CursoMatriculado cursoMatriculado : cursoMatriculados) {
            totalCreditos += cursoMatriculado.getCurso().getAsignatura().getNumero_creditos();
        }
        return totalCreditos;
    }

    @Override
    public String toString() {
        return "Estudiante [id=" + id + ", identificacion=" + identificacion + ", apellidos=" + apellidos + ", nombres="

                + nombres + ", semestre_actual=" + semestre_actual + ", programaAcademico=" + programaAcademico + "]";
    }

}
