package com.colegio.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Docente;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    List<Docente> findByNombreContaining(String nombre, Pageable pageable);
    List<Docente> findByApellidoContaining(String apellido, Pageable pageable);
    List<Docente> findByDni(String dni);
}
