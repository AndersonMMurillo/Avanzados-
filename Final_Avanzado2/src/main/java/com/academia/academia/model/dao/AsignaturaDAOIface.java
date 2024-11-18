package com.academia.academia.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.academia.model.entity.Asignatura;

@Repository
public interface AsignaturaDAOIface extends JpaRepository<Asignatura, Long> {
     
}
