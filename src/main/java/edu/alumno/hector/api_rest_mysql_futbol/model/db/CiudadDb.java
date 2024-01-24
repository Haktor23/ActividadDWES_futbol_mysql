package edu.alumno.hector.api_rest_mysql_futbol.model.db;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import edu.alumno.hector.api_rest_mysql_futbol.model.dto.EquipoNombreDb;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name= "ciudades")
public class CiudadDb implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=4,message ="El nombre debe de tener un tamaño minimo de 4 caracteres")
    private String nombre;
    @Column(nullable = true)
    private Long habitantes;
    @OneToMany(mappedBy = "ciudad")
    private Set<EquipoNombreDb> equiposNombresDb = new HashSet<>();
    
}
