package com.colegio.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.converter.DocenteConverter;
import com.colegio.dto.DocenteDto;
import com.colegio.entity.Docente;
import com.colegio.service.DocenteService;
import com.colegio.util.WrapperResponse;

@RestController
@RequestMapping("/v1/docentes")
//localhost:8090/v1/docentes versionado en la URI
public class DocenteController {

    @Autowired
    private DocenteService service;

    @Autowired
    private DocenteConverter converter;

    @GetMapping
    public ResponseEntity<List<DocenteDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Docente> docentes = service.findAll(pageable);
        List<DocenteDto> docenteDtos = converter.fromEntity(docentes);
        return new WrapperResponse("success", true, docenteDtos).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DocenteDto> create(@RequestBody DocenteDto docenteDto) {
        Docente docenteEntity = converter.fromDTO(docenteDto);
        Docente savedDocente = service.save(docenteEntity);
        DocenteDto savedDocenteDto = converter.fromEntity(savedDocente);
        return new WrapperResponse("success", true, savedDocenteDto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDto> update(@PathVariable("id") int id, @RequestBody DocenteDto docenteDto) {
        Docente docenteEntity = converter.fromDTO(docenteDto);
        docenteEntity.setId(id); // Ensure we set the ID for the update
        Docente updatedDocente = service.save(docenteEntity);
        DocenteDto updatedDocenteDto = converter.fromEntity(updatedDocente);
        return new WrapperResponse("success", true, updatedDocenteDto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse("success", true, null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDto> findById(@PathVariable("id") int id) {
        Docente docente = service.findById(id);
        DocenteDto docenteDto = converter.fromEntity(docente);
        return new WrapperResponse("success", true, docenteDto).createResponse(HttpStatus.OK);
    }
}
