package edu.alumno.hector.api_rest_mysql_futbol.srv.exception;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.AlumnoDb;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.AlumnoEdit;

public class AlumnoDuplicadoException extends Exception {
	AlumnoDb alumnoExistente;
	AlumnoEdit alumnoNuevo;
	
	
	
	public AlumnoDuplicadoException(AlumnoDb existente,AlumnoEdit alumnoEdit) {
		this.alumnoExistente=existente;
		this.alumnoNuevo=alumnoEdit;
	}
	public String toString() {
		return "ERROR insertando Alumno:<br>"+
				"Alumno existente:<br>"+
				"dni:"+ alumnoExistente.getDni()+ "<br>"+
				"nombre:"+alumnoExistente.getNombre()+ "<br>"+
				"Alumno nuevo:<br>"+
				"dni:"+ alumnoNuevo.getDni()+ "<br>"+
				"nombre:"+alumnoNuevo.getNombre()+ "<br>";
				
	}
	
	
}
