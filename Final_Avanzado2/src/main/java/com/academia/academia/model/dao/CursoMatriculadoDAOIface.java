package com.academia.academia.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.academia.model.entity.CursoMatriculado;
import com.academia.academia.model.entity.Estudiante;

@Repository
public interface CursoMatriculadoDAOIface extends JpaRepository<CursoMatriculado, Long> {
    
    public List<CursoMatriculado> findByEstudiante(Estudiante estudiante);
}
