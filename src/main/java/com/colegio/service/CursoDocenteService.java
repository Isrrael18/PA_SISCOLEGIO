package com.colegio.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.colegio.entity.CursoDocente;

public interface CursoDocenteService {

    List<CursoDocente> findAll(Pageable pageable);

    List<CursoDocente> findAll();

    CursoDocente findById(int id);

    CursoDocente save(CursoDocente cursoDocente);

    void delete(int id);
}
