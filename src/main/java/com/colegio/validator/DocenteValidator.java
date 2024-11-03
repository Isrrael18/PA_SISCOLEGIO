package com.colegio.validator;

import com.colegio.entity.Docente;
import com.colegio.exception.ValidateException;

public class DocenteValidator {
    public static void validate(Docente docente) {
        if (docente.getNombre() == null || docente.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre del docente es requerido");
        }
        if (docente.getNombre().length() > 50) {
            throw new ValidateException("El nombre del docente no debe tener más de 50 caracteres");
        }
        if (docente.getApellido() == null || docente.getApellido().trim().isEmpty()) {
            throw new ValidateException("El apellido del docente es requerido");
        }
        if (docente.getApellido().length() > 50) {
            throw new ValidateException("El apellido del docente no debe tener más de 50 caracteres");
        }
        if (docente.getDni() == null || docente.getDni().trim().isEmpty()) {
            throw new ValidateException("El DNI del docente es requerido");
        }
        if (docente.getDni().length() != 8) { // Suponiendo que el DNI debe tener 8 caracteres
            throw new ValidateException("El DNI del docente debe tener 8 caracteres");
        }
        if (docente.getGenero() == null || (!docente.getGenero().equals("M") && !docente.getGenero().equals("F"))) {
            throw new ValidateException("El género del docente es requerido y debe ser 'M' o 'F'");
        }
        if (docente.getEmail() != null && !docente.getEmail().trim().isEmpty() && !isValidEmail(docente.getEmail())) {
            throw new ValidateException("El correo electrónico del docente no es válido");
        }
        if (docente.getTelefono() != null && docente.getTelefono().length() > 20) {
            throw new ValidateException("El teléfono del docente no debe tener más de 20 caracteres");
        }
        if (docente.getFechaContratacion() == null) {
            throw new ValidateException("La fecha de contratación del docente es requerida");
        }
    }

    private static boolean isValidEmail(String email) {
        // Expresión regular simple para validar el formato de correo electrónico
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
}
