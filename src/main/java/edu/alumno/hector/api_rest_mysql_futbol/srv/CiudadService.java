package edu.alumno.hector.api_rest_mysql_futbol.srv;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort; // Importar la interfaz Sort

import edu.alumno.hector.api_rest_mysql_futbol.model.dto.CiudadInfo;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.CiudadList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;



public interface CiudadService {
    
    public Optional<CiudadInfo> getCiudadInfoById(Long id);
    public List<CiudadList> findAllCiudadList();
    public List<CiudadList> findAllCiudadList(Sort sort);
    public List<CiudadList> findByNombreContraining(String nombre, Sort sort);
    public PaginaDto<CiudadList> findAllPageCiudadList(Pageable paging);
    public PaginaDto<CiudadList> findByNombreContaining(String nombre, Pageable paging);
}
