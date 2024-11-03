package com.colegio.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.colegio.converter.CursoDocenteConverter; // Debes crear este convertidor
import com.colegio.dto.CursoDocenteDto; // Debes crear este DTO
import com.colegio.entity.CursoDocente; // Debes crear la entidad CursoDocente
import com.colegio.service.CursoDocenteService; // Debes crear el servicio correspondiente
import com.colegio.util.WrapperResponse;

@RestController
@RequestMapping("/v1/curso-docentes")
// localhost:8090/v1/curso-docentes versionado en la URI
public class CursoDocenteController {

    @Autowired
    private CursoDocenteService service;

    @Autowired
    private CursoDocenteConverter converter;

    @GetMapping
    public ResponseEntity<List<CursoDocenteDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<CursoDocente> cursoDocentes = service.findAll(pageable);
        List<CursoDocenteDto> cursoDocenteDtos = converter.fromEntity(cursoDocentes);
        return new WrapperResponse("success", true, cursoDocenteDtos).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoDocenteDto> create(@RequestBody CursoDocenteDto cursoDocenteDto) {
        CursoDocente cursoDocenteEntity = converter.fromDTO(cursoDocenteDto);
        CursoDocente savedCursoDocente = service.save(cursoDocenteEntity);
        CursoDocenteDto savedCursoDocenteDto = converter.fromEntity(savedCursoDocente);
        return new WrapperResponse("success", true, savedCursoDocenteDto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDocenteDto> update(@PathVariable("id") int id, @RequestBody CursoDocenteDto cursoDocenteDto) {
        CursoDocente cursoDocenteEntity = converter.fromDTO(cursoDocenteDto);
        cursoDocenteEntity.setId(id); // Asegúrate de establecer el ID para la actualización
        CursoDocente updatedCursoDocente = service.save(cursoDocenteEntity);
        CursoDocenteDto updatedCursoDocenteDto = converter.fromEntity(updatedCursoDocente);
        return new WrapperResponse("success", true, updatedCursoDocenteDto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse("success", true, null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDocenteDto> findById(@PathVariable("id") int id) {
        CursoDocente cursoDocente = service.findById(id);
        CursoDocenteDto cursoDocenteDto = converter.fromEntity(cursoDocente);
        return new WrapperResponse("success", true, cursoDocenteDto).createResponse(HttpStatus.OK);
    }
}
