package com.colegio.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.colegio.entity.Curso;

public interface CursoService {
    List<Curso> findAll(Pageable pageable);
    List<Curso> findAll();
    List<Curso> findByNombre(String nombre, Pageable pageable);
    Curso findById(int id);
    Curso save(Curso curso);
    void delete(int id);
}

