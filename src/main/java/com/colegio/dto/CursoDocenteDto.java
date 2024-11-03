package com.colegio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CursoDocenteDto {
    private Integer cursoId;  // ID del curso
    private Integer docenteId; // ID del docente
}
