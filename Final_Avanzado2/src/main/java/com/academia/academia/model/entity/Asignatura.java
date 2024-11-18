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
@Table(name = "asignaturas")
public class Asignatura implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String nombre;

    @Column(length = 11)
    private int numero_creditos;

    @Column(length = 50)
    private String departamento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "asignatura")
    private List<AsignaturaPlan> asignaturaPlans;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "asignatura")
    private List<Curso> cursos;

    public Asignatura() {
    }

    public Asignatura(Long id, String nombre, int numero_creditos, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.numero_creditos = numero_creditos;
        this.departamento = departamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero_creditos() {
        return numero_creditos;
    }

    public void setNumero_creditos(int numero_creditos) {
        this.numero_creditos = numero_creditos;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Asignatura [id=" + id + ", nombre=" + nombre + ", numero_creditos=" + numero_creditos
                + ", departamento=" + departamento + "]";
    }

    public List<AsignaturaPlan> getAsignaturaPlans() {
        return asignaturaPlans;
    }

    public void setAsignaturaPlans(List<AsignaturaPlan> asignaturaPlans) {
        this.asignaturaPlans = asignaturaPlans;
    }
    
}
