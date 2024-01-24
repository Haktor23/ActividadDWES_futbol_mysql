package edu.alumno.hector.api_rest_mysql_futbol.model.db;
import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class EquipoDb implements Serializable {
    @Id
    @Size(min=3,message = "El id tiene un tamaño minimo de 3")
    private String id;
    @Size(min=3,max=20,message = "El nombre corto debe de tener un tamaño entre 3 y 20 caracteres")
    @Column(name = "nombreCorto")
    private String nombreCorto;
    @Size(min=10,max=40,message = "El nombre largo debe de tener un tamaño entre 10 y 40 caracteres")
    @Column(name = "nombreLargo")
    private String nombreLargo;
    @ManyToOne
    @JoinColumn(name = "ciudad")
    private CiudadDb ciudadDb;
    @Size(min=10,max=30,message = "El nombre del entrenador debe de tener un tamaño entre 10 y 30 caracteres")
    private String entrenador;
    @Size(min=10,max=30,message = "El nombre de el estadio debe de tener un tamaño entre 10 y 30 caracteres")
    private String estadio;
    @Size(min=4,max=30,message = "El nombre de la marca debe de tener un tamaño entre 4 y 30 caracteres")
    private String marca;
    @Size(min=4,max=30,message = "El nombre de el patrocinador debe de tener un tamaño entre 4 y 30 caracteres")
    private String patrocinador;
    private Long presupuesto;
}


