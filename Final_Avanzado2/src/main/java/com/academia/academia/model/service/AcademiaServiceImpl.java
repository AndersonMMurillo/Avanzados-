package com.academia.academia.model.service;

import com.academia.academia.model.dao.AsignaturaCursadaDAOIface;
import com.academia.academia.model.dao.AsignaturaDAOIface;
import com.academia.academia.model.dao.AsignaturaPlanDAOIface;
import com.academia.academia.model.dao.CursoDAOIface;
import com.academia.academia.model.dao.CursoMatriculadoDAOIface;
import com.academia.academia.model.dao.EstudianteDAOIface;
import com.academia.academia.model.dao.ProgramaAcademicoDAOIface;
import com.academia.academia.model.entity.Asignatura;
import com.academia.academia.model.entity.AsignaturaCursada;
import com.academia.academia.model.entity.AsignaturaPlan;
import com.academia.academia.model.entity.Curso;
import com.academia.academia.model.entity.CursoMatriculado;
import com.academia.academia.model.entity.Estudiante;
import com.academia.academia.model.entity.ProgramaAcademico;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AcademiaServiceImpl implements AcademiaServiceIface {
    
    private final EstudianteDAOIface estudianteDao;
    private final AsignaturaDAOIface asignaturaDao;
    private final CursoDAOIface cursoDao;
    private final ProgramaAcademicoDAOIface programaAcademicoDao;
    private final AsignaturaCursadaDAOIface asignaturaCursadaDAO;
    private final CursoMatriculadoDAOIface cursoMatriculadoDAO;
    private final AsignaturaPlanDAOIface asignaturaPlanDAO; 

    public AcademiaServiceImpl(EstudianteDAOIface estudianteDao, AsignaturaDAOIface asignaturaDao, 
    CursoDAOIface cursoDao, ProgramaAcademicoDAOIface programaAcademicoDao, AsignaturaCursadaDAOIface asignaturaCursadaDAO,
    CursoMatriculadoDAOIface cursoMatriculadoDAO, AsignaturaPlanDAOIface asignaturaPlanDAO) {
        this.estudianteDao = estudianteDao;
        this.asignaturaDao = asignaturaDao;
        this.cursoDao = cursoDao;
        this.programaAcademicoDao = programaAcademicoDao;
        this.asignaturaCursadaDAO = asignaturaCursadaDAO;
        this.cursoMatriculadoDAO = cursoMatriculadoDAO;
        this.asignaturaPlanDAO = asignaturaPlanDAO;
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
    @Transactional(readOnly = true)
    public List<AsignaturaCursada> todasasignaturaCursadas() {
        return asignaturaCursadaDAO.findAll();
    }

    @Override
    @Transactional
    public AsignaturaCursada buscarAsignaturaCursada(Long id) {
        return asignaturaCursadaDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsignaturaCursada> asignaturaCursadasEstudiante(Estudiante estudiante) {
       return asignaturaCursadaDAO.findByEstudiante(estudiante);
    }

    // matriculados
    @Override
    @Transactional(readOnly = true)
    public List<CursoMatriculado> cursoMatriculadoEstudiante(Estudiante estudiante) {
        return cursoMatriculadoDAO.findByEstudiante(estudiante);
    }

    @Override
    @Transactional
    public void guardarMatricula(CursoMatriculado cursoMatriculado) {
        cursoMatriculadoDAO.save(cursoMatriculado);
    }

    @Override
    @Transactional(readOnly = true)
    public CursoMatriculado buscarCursoMatriculado(Long id) {
        return cursoMatriculadoDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarMatricula(Long id) {
        cursoMatriculadoDAO.deleteById(id);
    }

    // plan asignaturas
    @Override
    @Transactional(readOnly = true)
    public List<AsignaturaPlan> todAsignaturaPlans() {
        return asignaturaPlanDAO.findAll();
    }


}
