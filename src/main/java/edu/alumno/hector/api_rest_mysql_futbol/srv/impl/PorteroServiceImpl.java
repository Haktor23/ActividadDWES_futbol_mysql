package edu.alumno.hector.api_rest_mysql_futbol.srv.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.PorteroDb;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PorteroList;
import edu.alumno.hector.api_rest_mysql_futbol.srv.PorteroService;
import edu.alumno.hector.api_rest_mysql_futbol.srv.mapper.PorteroMapper;
import edu.alumno.hector.api_rest_mysql_futbol.srv.repository.PorteroRepository;

@Service
public class PorteroServiceImpl implements PorteroService {
    private final PorteroRepository porteroRepository;

    public PorteroServiceImpl(PorteroRepository porteroRepository) {
        this.porteroRepository = porteroRepository;
    }

@Override
public PaginaDto<PorteroList> findAll(Pageable paging) {
    Page<PorteroDb> paginaPorteroDb = porteroRepository.findAll(paging);
    return new PaginaDto<PorteroList>(
        paginaPorteroDb.getNumber(),
        paginaPorteroDb.getSize(),
        paginaPorteroDb.getTotalElements(),
        paginaPorteroDb.getTotalPages(),
        PorteroMapper.INSTANCE.porteroDbToPorterosList(paginaPorteroDb.getContent()),
        paginaPorteroDb.getSort()
    );
}




    

}
