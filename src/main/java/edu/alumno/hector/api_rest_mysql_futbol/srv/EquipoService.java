package edu.alumno.hector.api_rest_mysql_futbol.srv;


import org.springframework.data.domain.Pageable;

import edu.alumno.hector.api_rest_mysql_futbol.model.dto.EquipoList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;


public interface EquipoService {
    public PaginaDto<EquipoList> findAll(Pageable pageable);
}
