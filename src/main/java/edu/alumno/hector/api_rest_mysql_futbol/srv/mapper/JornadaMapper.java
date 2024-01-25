package edu.alumno.hector.api_rest_mysql_futbol.srv.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.JornadaDb;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.JornadaList;

@Mapper
public interface JornadaMapper {
    JornadaMapper INSTANCE=Mappers.getMapper(JornadaMapper.class);

   

    List<JornadaList> jornadasDbToJornadasList(List<JornadaDb> content);
    


    
}
