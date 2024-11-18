package com.academia.academia.model.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "programas_academicos")
public class ProgramaAcademico implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String nombre_programa;
    
    @Column(length = 50)
    private String facultad;
    
    @Column(length = 70)
    private String titulo_otorgado;
    
    @Column(length = 11)
    private int duracion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programaAcademico")
    private List<Estudiante> estudiantes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programaAcademico")
    private List<PlanEstudio> planEstudios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_programa() {
        return nombre_programa;
    }

    public void setNombre_programa(String nombre_programa) {
        this.nombre_programa = nombre_programa;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getTitulo_otorgado() {
        return titulo_otorgado;
    }

    public void setTitulo_otorgado(String titulo_otorgado) {
        this.titulo_otorgado = titulo_otorgado;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<PlanEstudio> getPlanEstudios() {
        return planEstudios;
    }

    public void setPlanEstudios(List<PlanEstudio> planEstudios) {
        this.planEstudios = planEstudios;
    }

}
