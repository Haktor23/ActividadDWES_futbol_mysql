package edu.alumno.hector.api_rest_mysql_futbol.model.db;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@IdClass(JugadorId.class)
@Table(name= "porteros")
public class PorteroDb implements Serializable {
    @Id
    @Size(min = 3, message="El id del equipo tiene que tener un tamaño mínimo de 3")
    private String idEquipo;

    @Id
    @Column(nullable = false)
    private Long dorsal;
    private Long partidos;
    private Long goles;
   @OneToOne
   @JoinColumn(name="idEquipo", referencedColumnName = ("idEquipo"))
   @JoinColumn(name="dorsal", referencedColumnName = ("dorsal"))

    private JugadorDb jugadorDb;
}
