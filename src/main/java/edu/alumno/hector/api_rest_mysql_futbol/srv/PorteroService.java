package edu.alumno.hector.api_rest_mysql_futbol.srv;


import org.springframework.data.domain.Pageable;


import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PorteroList;

public interface PorteroService {

    public PaginaDto<PorteroList> findAll(Pageable pageable);
    
} 
