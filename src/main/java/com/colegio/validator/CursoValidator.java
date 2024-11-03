package com.colegio.validator;

import com.colegio.entity.Curso;
import com.colegio.exception.ValidateException;

public class CursoValidator {
    public static void validate(Curso curso) {
        if (curso.getNombre() == null || curso.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre del curso es requerido");
        }
        if (curso.getNombre().length() > 50) {
            throw new ValidateException("El nombre del curso no debe tener más de 50 caracteres");
        }
        if (curso.getHorasAcademicas() <= 0) {
            throw new ValidateException("Las horas académicas deben ser mayores a cero");
        }
    }
}
