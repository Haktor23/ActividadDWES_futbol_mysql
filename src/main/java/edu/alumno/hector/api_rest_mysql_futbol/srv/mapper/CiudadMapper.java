package edu.alumno.hector.api_rest_mysql_futbol.srv.mapper;

 
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.CiudadDb;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.CiudadInfo;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.CiudadList;


@Mapper(uses = EquipoMapper.class)
public interface CiudadMapper {
    CiudadMapper INSTANCE=Mappers.getMapper(CiudadMapper.class);

    CiudadInfo CiudadDbToCiudadInfo(CiudadDb ciudadDb);
    CiudadList CiudadDbToCiudadList(CiudadDb ciudadDb);
    List<CiudadList> ciudadesToCiudadList(List<CiudadDb> ciudadesDb);

    @Mapping(target = "equiposInfoNombres", source = "equiposNombresDb")
    CiudadInfo ciudadDbToCiudadInfo(CiudadDb ciudadDb);
}  
