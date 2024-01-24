package edu.alumno.hector.api_rest_mysql_futbol.srv.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.EquipoDb;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.EquipoList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;
import edu.alumno.hector.api_rest_mysql_futbol.srv.EquipoService;
import edu.alumno.hector.api_rest_mysql_futbol.srv.mapper.EquipoMapper;
import edu.alumno.hector.api_rest_mysql_futbol.srv.repository.EquipoRepository;
@Service
public class EquipoServiceImpl implements EquipoService {
    
       private final EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository){
        this.equipoRepository=equipoRepository;
    }

      public PaginaDto<EquipoList> findAll(Pageable paging){
            Page<EquipoDb> paginaEquipoDb=equipoRepository.findAll(paging);
            return new PaginaDto<EquipoList>(
                paginaEquipoDb.getNumber(),
                paginaEquipoDb.getSize(),
                paginaEquipoDb.getTotalElements(),
                paginaEquipoDb.getTotalPages(),
                EquipoMapper.INSTANCE.equiposDbToEquiposList(paginaEquipoDb.getContent()),
                paginaEquipoDb.getSort());
    }

  

   

  
}
