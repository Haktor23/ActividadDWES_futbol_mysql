package edu.alumno.hector.api_rest_mysql_futbol.model.dto;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "equipos")
public class EquipoNombreDb implements Serializable{
    @Id
     @Size(min=3, message = "El id tiene un tamaño minimo de 3")
    private String id;
    @Size(min=10,max=40,message = "El nombre largo debe tener de tener un tamaño entre 10 y 40 caracteres")
    private String nombreLargo;
    private Long ciudad;
}
