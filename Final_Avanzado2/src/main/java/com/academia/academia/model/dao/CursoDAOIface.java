package com.academia.academia.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.academia.model.entity.Curso;

@Repository
public interface CursoDAOIface extends JpaRepository<Curso, Long> {
    
}
