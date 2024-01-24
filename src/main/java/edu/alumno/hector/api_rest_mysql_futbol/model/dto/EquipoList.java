package edu.alumno.hector.api_rest_mysql_futbol.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoList {
    @Size(min = 3,message = "El id tiene un tamaño minimo de 3")
    private String id;
    @Size(min = 10,max = 40,message = "El nombre largo debe de tener un tamaño entre 10 y 40 caracteres")
    private String nombreLargo;
    private String nombreCiudad;
}
