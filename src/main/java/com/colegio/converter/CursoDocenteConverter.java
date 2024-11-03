package com.colegio.converter;

import org.springframework.stereotype.Component;

import com.colegio.dto.CursoDocenteDto; // Debes crear este DTO
import com.colegio.entity.CursoDocente; 

@Component
public class CursoDocenteConverter extends AbstractConverter<CursoDocente, CursoDocenteDto> {

    @Override
    public CursoDocenteDto fromEntity(CursoDocente entity) {
        if (entity == null) return null;
        return CursoDocenteDto.builder()
                .cursoId(entity.getCursoId())
                .docenteId(entity.getDocenteId())
                .build();
    }

    @Override
    public CursoDocente fromDTO(CursoDocenteDto dto) {
        if (dto == null) return null;
        return CursoDocente.builder()
                .cursoId(dto.getCursoId())
                .docenteId(dto.getDocenteId())
                .build();
    }
}
