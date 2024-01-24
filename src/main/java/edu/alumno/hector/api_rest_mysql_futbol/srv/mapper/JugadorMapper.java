package edu.alumno.hector.api_rest_mysql_futbol.srv.mapper;

import java.util.List;

import org.mapstruct.factory.Mappers;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.JugadorDb;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.JugadorList;

public interface JugadorMapper {
    JugadorMapper INSTANCE=Mappers.getMapper(JugadorMapper.class);



    List<JugadorList> jugadorDbTojugadoresList(List<JugadorDb> content);
    


    
}
