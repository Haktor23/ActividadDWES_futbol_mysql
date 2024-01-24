package edu.alumno.hector.api_rest_mysql_futbol.srv.impl;



import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.CiudadDb;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.CiudadInfo;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.CiudadList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;
import edu.alumno.hector.api_rest_mysql_futbol.srv.CiudadService;
import edu.alumno.hector.api_rest_mysql_futbol.srv.mapper.CiudadMapper;
import edu.alumno.hector.api_rest_mysql_futbol.srv.repository.CiudadRepository;


@Service
public class CiudadServiceImpl implements CiudadService {
    private final CiudadRepository ciudadRepository;

    public CiudadServiceImpl(CiudadRepository ciudadRepository){
        this.ciudadRepository=ciudadRepository;
    }
     
    public List<CiudadList> findAllCiudadList() {
       return CiudadMapper.INSTANCE.ciudadesToCiudadList(ciudadRepository.findAll());
    }

    
    public List<CiudadList> findAllCiudadList(Sort sort) {
        return CiudadMapper.INSTANCE.ciudadesToCiudadList(ciudadRepository.findAll(sort));
    }
    
    public Optional<CiudadInfo> getCiudadInfoById(Long id) {
        Optional<CiudadDb> ciudadDb=ciudadRepository.findById(id);
        if (ciudadDb.isPresent()) {
            return Optional.of(CiudadMapper.INSTANCE.ciudadDbToCiudadInfo(ciudadDb.get()));
            
        }else{
            return Optional.empty();
        }
       
    }
 
    public List<CiudadList> findByNombreContraining(String nombre, Sort sort){
        List<CiudadDb> ciudadesDb = ciudadRepository.findByNombreContaining(nombre, sort);
        return CiudadMapper.INSTANCE.ciudadesToCiudadList(ciudadesDb); 
    }

        public PaginaDto<CiudadList> findAllPageCiudadList(Pageable paging){
            Page<CiudadDb> paginaCiudadDb=ciudadRepository.findAll(paging);
            return new PaginaDto<CiudadList>(
                paginaCiudadDb.getNumber(),
                paginaCiudadDb.getSize(),
                paginaCiudadDb.getTotalElements(),
                paginaCiudadDb.getTotalPages(),
                CiudadMapper.INSTANCE.ciudadesToCiudadList(paginaCiudadDb.getContent()),
                paginaCiudadDb.getSort());
    }

    public PaginaDto<CiudadList> findByNombreContaining(String nombre, Pageable pageable){
        Page<CiudadDb> paginaCiudadDb=ciudadRepository.findByNombreContaining(nombre, pageable);
        return new PaginaDto<CiudadList>(
            paginaCiudadDb.getNumber(),
            paginaCiudadDb.getSize(),
            paginaCiudadDb.getTotalElements(),
            paginaCiudadDb.getTotalPages(),
            CiudadMapper.INSTANCE.ciudadesToCiudadList(paginaCiudadDb.getContent()),
            paginaCiudadDb.getSort());
}

  
}
