package com.colegio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.colegio.entity.Docente;
import com.colegio.repository.DocenteRepository;
import com.colegio.service.DocenteService;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    public List<Docente> findAll(Pageable pageable) {
        return docenteRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }

    @Override
    public List<Docente> findByNombre(String nombre, Pageable pageable) {
        return docenteRepository.findByNombreContaining(nombre, pageable);
    }

    @Override
    public Docente findById(int id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        return docente.orElse(null);
    }

    @Override
    public Docente save(Docente docente) {
        return docenteRepository.save(docente);
    }

    @Override
    public void delete(int id) {
        docenteRepository.deleteById(id);
    }

	@Override
	public List<Docente> findByApellido(String apellido, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
