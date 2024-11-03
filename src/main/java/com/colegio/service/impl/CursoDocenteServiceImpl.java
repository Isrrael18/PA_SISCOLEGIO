package com.colegio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.colegio.entity.CursoDocente;
import com.colegio.repository.CursoDocenteRepository;
import com.colegio.service.CursoDocenteService;

@Service
public class CursoDocenteServiceImpl implements CursoDocenteService {

    @Autowired
    private CursoDocenteRepository cursoDocenteRepository;

    @Override
    public List<CursoDocente> findAll(Pageable pageable) {
        return cursoDocenteRepository.findAll(pageable).getContent();
    }

    @Override
    public List<CursoDocente> findAll() {
        return cursoDocenteRepository.findAll();
    }

    @Override
    public CursoDocente findById(int id) {
        Optional<CursoDocente> cursoDocente = cursoDocenteRepository.findById(id);
        return cursoDocente.orElse(null);
    }

    @Override
    public CursoDocente save(CursoDocente cursoDocente) {
        return cursoDocenteRepository.save(cursoDocente);
    }

    @Override
    public void delete(int id) {
        cursoDocenteRepository.deleteById(id);
    }
}
