package com.academia.academia.model.service;

import com.academia.academia.model.dao.AsignaturaCursadaDAOIface;
import com.academia.academia.model.dao.AsignaturaDAOIface;
import com.academia.academia.model.dao.CursoDAOIface;
import com.academia.academia.model.dao.EstudianteDAOIface;
import com.academia.academia.model.dao.ProgramaAcademicoDAOIface;
import com.academia.academia.model.entity.Asignatura;
import com.academia.academia.model.entity.AsignaturaCursada;
import com.academia.academia.model.entity.Curso;
import com.academia.academia.model.entity.Estudiante;
import com.academia.academia.model.entity.ProgramaAcademico;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademiaServiceImpl implements AcademiaServiceIface {
    
    private final EstudianteDAOIface estudianteDao;
    private final AsignaturaDAOIface asignaturaDao;
    private final CursoDAOIface cursoDao;
    private final ProgramaAcademicoDAOIface programaAcademicoDao;
    private final AsignaturaCursadaDAOIface asignaturaCursadaDAO;

    public AcademiaServiceImpl(EstudianteDAOIface estudianteDao, AsignaturaDAOIface asignaturaDao, 
    CursoDAOIface cursoDao, ProgramaAcademicoDAOIface programaAcademicoDao, AsignaturaCursadaDAOIface asignaturaCursadaDAO) {
        this.estudianteDao = estudianteDao;
        this.asignaturaDao = asignaturaDao;
        this.cursoDao = cursoDao;
        this.programaAcademicoDao = programaAcademicoDao;
        this.asignaturaCursadaDAO = asignaturaCursadaDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Estudiante> todosEstudiantes() {
        return estudianteDao.findAll();
    }

    @Override
    @Transactional
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteDao.save(estudiante);
    }

    @Override
    @Transactional(readOnly = true)
    public Estudiante buscarEstudiante(Long id) {
        return estudianteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarEstudiante(Long id) {
        estudianteDao.deleteById(id);
    }

    // servicio para asignatura
     @Override
    @Transactional(readOnly = true)
    public List<Asignatura> todasAsignaturas() {
        return asignaturaDao.findAll();
    }
    
    @Override
    @Transactional
    public void guardarAsignatura(Asignatura asignatura) {
        asignaturaDao.save(asignatura);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Asignatura buscarAsignatura(Long id) {
        return asignaturaDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public void eliminarAsignatura(Long id) {
        asignaturaDao.deleteById(id);
    }

    // servicios para curso
    @Override
    @Transactional(readOnly = true)
    public List<Curso> todosCursos() {
        return cursoDao.findAll();
    }
    
    @Override
    @Transactional
    public void guardarCurso(Curso curso) {
        cursoDao.save(curso);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Curso buscarCurso(Long id) {
        return cursoDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public void eliminarCurso(Long id) {
        cursoDao.deleteById(id);
    }


    // service para programa academico
    @Override
    @Transactional(readOnly = true)
    public List<ProgramaAcademico> todosProgramasAcademicos() {
        return programaAcademicoDao.findAll();
    }
    
    @Override
    @Transactional
    public void guardarProgramaAcademico(ProgramaAcademico programaAcademico) {
        programaAcademicoDao.save(programaAcademico);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProgramaAcademico buscarProgramaAcademico(Long id) {
        return programaAcademicoDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public void eliminarProgramaAcademico(Long id) {
        programaAcademicoDao.deleteById(id);
    }

    // Cursadas
    @Override
    @Transactional
    public List<AsignaturaCursada> todasasignaturaCursadas() {
        return asignaturaCursadaDAO.findAll();
    }

    @Override
    @Transactional
    public AsignaturaCursada asignaturaCursada(Long id) {
        return asignaturaCursadaDAO.findById(id).orElse(null);
    }

    @Override
    public List<AsignaturaCursada> asignaturaCursadasEstudiante(Estudiante estudiante) {
       return asignaturaCursadaDAO.findByEstudiante(estudiante);
    }
}
