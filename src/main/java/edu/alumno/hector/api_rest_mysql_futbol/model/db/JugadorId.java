package edu.alumno.hector.api_rest_mysql_futbol.model.db;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Embeddable
public class JugadorId implements Serializable {

    @Size(min = 3, message = "El id del equipo debe tener un tamaño mínimo de 3")
    @Column(name = "idEquipo")
    private String idEquipo;

    @Column(name = "dorsal", nullable = false)
    private Long dorsal;

}