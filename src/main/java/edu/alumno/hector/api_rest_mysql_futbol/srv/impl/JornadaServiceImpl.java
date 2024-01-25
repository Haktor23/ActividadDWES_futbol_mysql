package edu.alumno.hector.api_rest_mysql_futbol.srv.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.JornadaDb;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.JornadaList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;
import edu.alumno.hector.api_rest_mysql_futbol.srv.JornadaService;
import edu.alumno.hector.api_rest_mysql_futbol.srv.mapper.JornadaMapper;
import edu.alumno.hector.api_rest_mysql_futbol.srv.repository.JornadaRepository;

@Service
public class JornadaServiceImpl implements JornadaService{
    
 private final JornadaRepository jornadaRepository;

 public JornadaServiceImpl(JornadaRepository jornadaRepository){
    this.jornadaRepository=jornadaRepository;
}

@Override
public PaginaDto<JornadaList> findAll(Pageable paging) {
    Page<JornadaDb> paginaJornadaDb=jornadaRepository.findAll(paging);
            return new PaginaDto<JornadaList>(
                paginaJornadaDb.getNumber(),
                paginaJornadaDb.getSize(),
                paginaJornadaDb.getTotalElements(),
                paginaJornadaDb.getTotalPages(),
               JornadaMapper.INSTANCE.jornadasDbToJornadasList(paginaJornadaDb.getContent()),
                paginaJornadaDb.getSort());
}

    

}
