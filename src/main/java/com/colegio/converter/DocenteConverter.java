package com.colegio.converter;

import org.springframework.stereotype.Component;

import com.colegio.dto.DocenteDto;
import com.colegio.entity.Docente;

@Component
public class DocenteConverter extends AbstractConverter<Docente, DocenteDto> {

    @Override
    public DocenteDto fromEntity(Docente entity) {
        if (entity == null) return null;
        return DocenteDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .dni(entity.getDni())
                .genero(entity.getGenero())
                .direccion(entity.getDireccion())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .fechaContratacion(entity.getFechaContratacion())
                .build();
    }

    @Override
    public Docente fromDTO(DocenteDto dto) {
        if (dto == null) return null;
        return Docente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .dni(dto.getDni())
                .genero(dto.getGenero())
                .direccion(dto.getDireccion())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .fechaContratacion(dto.getFechaContratacion())
                .build();
    }
}
