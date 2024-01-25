package edu.alumno.hector.api_rest_mysql_futbol.srv;

import org.springframework.data.domain.Pageable;

import edu.alumno.hector.api_rest_mysql_futbol.model.dto.JornadaList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;

public interface JornadaService {

     public PaginaDto<JornadaList> findAll(Pageable pageable);

    
}
