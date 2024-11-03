package com.colegio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colegio.entity.Curso;
import com.colegio.exception.GeneralException;
import com.colegio.exception.NoDataFoundException;
import com.colegio.exception.ValidateException;
import com.colegio.repository.CursoRepository;
import com.colegio.service.CursoService;
import com.colegio.validator.CursoValidator;

@Service
public class CursoServiceImpl implements CursoService {
    @Autowired
    private CursoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll(Pageable pageable) {
        try {
            return repository.findAll(pageable).toList();
        } catch (GeneralException e) {
            throw new GeneralException("Error en el servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        try {
            return repository.findAll();
        } catch (GeneralException e) {
            throw new GeneralException("Error en el servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findByNombre(String nombre, Pageable pageable) {
        try {
            return repository.findByNombreContaining(nombre, pageable);
        } catch (GeneralException e) {
            throw new GeneralException("Error en el servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Curso findById(int id) {
        try {
            return repository.findById(id)
                .orElseThrow(() -> new NoDataFoundException("No se encontró el registro con ese ID"));
        } catch (GeneralException e) {
            throw new GeneralException("Error en el servidor");
        }
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        try {
            // Validar el nuevo registro
            CursoValidator.validate(curso);
            return repository.save(curso);
        } catch (ValidateException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error en el servidor");
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Curso curso = repository.findById(id)
                .orElseThrow(() -> new NoDataFoundException("No se encontró el registro con ese ID"));
            repository.delete(curso);
        } catch (GeneralException e) {
            throw new GeneralException("Error en el servidor");
        }
    }
}
