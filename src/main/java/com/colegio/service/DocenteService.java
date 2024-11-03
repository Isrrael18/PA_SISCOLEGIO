package com.colegio.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.colegio.entity.Docente;

public interface DocenteService {

    List<Docente> findAll(Pageable pageable);

    List<Docente> findAll();

    List<Docente> findByNombre(String nombre, Pageable pageable);

    List<Docente> findByApellido(String apellido, Pageable pageable);

    Docente findById(int id);

    Docente save(Docente docente);

    void delete(int id);
}
