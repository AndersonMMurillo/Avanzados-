package com.academia.academia.model.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "asignaturas_planes")
public class AsignaturaPlan implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planEstudio_id", nullable = false)
    private PlanEstudio planEstudio;
    
    @ManyToOne
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

    @Column(length = 11)
    private Integer semestre_nivel;
    
    @Column(length = 20)
    private Long prerequisito;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanEstudio getPlanEstudio() {
        return planEstudio;
    }

    public void setPlanEstudio(PlanEstudio planEstudio) {
        this.planEstudio = planEstudio;
    }

    public int getSemestre_nivel() {
        return semestre_nivel;
    }

    public void setSemestre_nivel(int semestre_nivel) {
        this.semestre_nivel = semestre_nivel;
    }

    public Long getPrerequisito() {
        return prerequisito;
    }

    public void setPrerequisito(Long prerequisito) {
        this.prerequisito = prerequisito;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public String toString() {
        return "AsignaturaPlan [id=" + id + ", planEstudio=" + planEstudio + ", asignatura=" + asignatura
                + ", semestre_nivel=" + semestre_nivel + ", prerequisito=" + prerequisito + "]";
    }
    
}