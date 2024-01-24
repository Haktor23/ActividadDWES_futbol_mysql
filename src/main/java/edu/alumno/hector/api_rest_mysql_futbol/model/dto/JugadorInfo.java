package edu.alumno.hector.api_rest_mysql_futbol.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorInfo {
    private static final long serialVersionUID = 1L;
    private String idEquipo;
    private Long dorsal;
    private String nombre;
    private String posicion;
    private Long sueldo; 
}
