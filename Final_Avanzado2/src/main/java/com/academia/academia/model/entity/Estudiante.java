package com.academia.academia.model.entity;

import java.io.Serializable;

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
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programaAcademico_id", nullable = false)
    @NotNull
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

  

    public ProgramaAcademico getProgramaAcademico() {
        return programaAcademico;
    }

    public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
        this.programaAcademico = programaAcademico;
    }

    @Override
    public String toString() {
        return "Estudiante [id=" + id + ", identificacion=" + identificacion + ", apellidos=" + apellidos + ", nombres="
                + nombres + ", semestre_actual=" + semestre_actual +  "]";
    }
}
