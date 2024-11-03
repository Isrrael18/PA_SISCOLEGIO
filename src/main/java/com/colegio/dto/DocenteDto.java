package com.colegio.dto;

import java.util.Date;

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
public class DocenteDto {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String genero; // 'M' o 'F'
    private String direccion;
    private String email;
    private String telefono;
    private Date fechaContratacion;
}
