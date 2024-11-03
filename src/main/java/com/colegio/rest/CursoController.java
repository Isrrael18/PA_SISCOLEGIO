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

import com.colegio.converter.CursoConverter;
import com.colegio.dto.CursoDto;
import com.colegio.entity.Curso;
import com.colegio.service.CursoService;
import com.colegio.util.WrapperResponse;

@RestController
@RequestMapping("/v1/cursos")
//localhost:8090/v1/cursos versionado en la URI
public class CursoController {
    @Autowired
    private CursoService service;

    @Autowired
    private CursoConverter converter;

    @GetMapping
    public ResponseEntity<List<CursoDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<CursoDto> cursos = converter.fromEntity(service.findAll(pageable));
        return new WrapperResponse("success", true, cursos).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoDto> create(@RequestBody CursoDto cursoDto) {
        Curso cursoEntity = converter.fromDTO(cursoDto);
        CursoDto registro = converter.fromEntity(service.save(cursoEntity));
        return new WrapperResponse("success", true, registro).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDto> update(@PathVariable("id") int id, @RequestBody CursoDto cursoDto) {
        Curso cursoEntity = converter.fromDTO(cursoDto);
        cursoEntity.setId(id); // Aseguramos que el id se setea correctamente
        CursoDto registro = converter.fromEntity(service.save(cursoEntity));
        return new WrapperResponse("success", true, registro).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse("success", true, null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDto> findById(@PathVariable("id") int id) {
        CursoDto registro = converter.fromEntity(service.findById(id));
        return new WrapperResponse("success", true, registro).createResponse(HttpStatus.OK);
    }
}
