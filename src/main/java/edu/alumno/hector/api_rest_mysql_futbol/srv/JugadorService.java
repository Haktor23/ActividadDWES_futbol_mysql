package edu.alumno.hector.api_rest_mysql_futbol.srv;


import org.springframework.data.domain.Pageable;


import edu.alumno.hector.api_rest_mysql_futbol.model.dto.JugadorList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;

public interface JugadorService {

    public PaginaDto<JugadorList> findAll(Pageable pageable);
    
} 
