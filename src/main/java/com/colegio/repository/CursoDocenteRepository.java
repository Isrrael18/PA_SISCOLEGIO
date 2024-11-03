package com.colegio.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.entity.CursoDocente;

import java.util.List;

@Repository
public interface CursoDocenteRepository extends JpaRepository<CursoDocente, Integer> {
    List<CursoDocente> findByCursoId(int cursoId, Pageable pageable);
    List<CursoDocente> findByDocenteId(int docenteId, Pageable pageable);
}
