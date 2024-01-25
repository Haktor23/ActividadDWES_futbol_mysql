package edu.alumno.hector.api_rest_mysql_futbol.model.dto;

import java.io.Serializable;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.JugadorDb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PorteroList implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String idEquipo;
    private Long dorsal;
    
}
