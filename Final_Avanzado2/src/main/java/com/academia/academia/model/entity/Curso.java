package com.academia.academia.model.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String horario;

    @Column(length = 10)
    private String periodo;

    @Column(length = 11, nullable = false)
    private int cupoMaximo;

    @Column(length = 30)
    private String aula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;


    public Curso() {
    }

    public Curso(Long id, String nombre, String horario, String periodo, int cupoMaximo, String aula) {
        this.id = id;
        this.horario = horario;
        this.periodo = periodo;
        this.cupoMaximo = cupoMaximo;
        this.aula = aula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Curso [id=" + id + ", horario=" + horario + ", periodo=" + periodo + ", cupoMaximo=" + cupoMaximo
                + ", aula=" + aula + ", asignatura=" + asignatura + ", profesor=" + profesor + "]";
    }

}
