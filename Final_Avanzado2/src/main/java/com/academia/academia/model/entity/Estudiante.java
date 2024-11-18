package com.academia.academia.model.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "estudiantes")
public class Estudiante implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(length = 20)
    private String identificacion;
    
    @NotEmpty
    @Column(length = 50)
    private String apellidos;
    
    @NotEmpty
    @Column(length = 50)
    private String nombres;
    
    @NotNull
    @Column(name = "semestre_actual")
    private Integer semestre_actual;
    
    @NotEmpty
    @Column(name = "tipo_identificacion", length = 20)
    private String tipoIdentificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programaAcademico_id", nullable = false)
    private ProgramaAcademico programaAcademico;

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
        return semestre_actual;
    }

    public void setSemestre_actual(Integer semestre_actual) {
        this.semestre_actual = semestre_actual;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public ProgramaAcademico getProgramaAcademico() {
        return programaAcademico;
    }

    public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
        this.programaAcademico = programaAcademico;
    }

    @Override
    public String toString() {
        return "Estudiante [id=" + id + ", identificacion=" + identificacion + ", apellidos=" + apellidos + ", nombres="
                + nombres + ", semestre_actual=" + semestre_actual + ", tipoIdentificacion=" + tipoIdentificacion + "]";
    }
}
