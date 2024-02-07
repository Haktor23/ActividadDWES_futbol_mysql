package edu.alumno.hector.api_rest_mysql_futbol.model.dto;


import java.util.Date;

import jakarta.persistence.Column;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlumnoList {
	
	private String dni;
	private String nombre;
	private Integer edad;
	private String ciclo;
	private Integer curso;
	private boolean erasmus=false;
	private String lenguajeFavorito="";
	private String genero;
	private String horario;
	private String pais;
	private String hobbies;
	private Date ts;
	@Column(name="modificadoPor")
	private String user;
	private String modificado;
}

