package com.academia.academia.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.academia.model.entity.AsignaturaCursada;
import com.academia.academia.model.entity.Estudiante;

@Repository
public interface AsignaturaCursadaDAOIface extends JpaRepository<AsignaturaCursada, Long> {
    
    public List<AsignaturaCursada> findByEstudiante(Estudiante estudiante);
}
