package com.colegio.converter;

import org.springframework.stereotype.Component;

import com.colegio.dto.CursoDto;
import com.colegio.entity.Curso;

@Component
public class CursoConverter extends AbstractConverter<Curso, CursoDto> {

    @Override
    public CursoDto fromEntity(Curso entity) {
        if (entity == null) return null;
        return CursoDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .horasAcademicas(entity.getHorasAcademicas())
                .build();
    }

    @Override
    public Curso fromDTO(CursoDto dto) {
        if (dto == null) return null;
        return Curso.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .horasAcademicas(dto.getHorasAcademicas())
                .build();
    }
}

