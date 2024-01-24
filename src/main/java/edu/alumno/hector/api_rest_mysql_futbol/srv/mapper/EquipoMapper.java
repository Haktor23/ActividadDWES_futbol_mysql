package edu.alumno.hector.api_rest_mysql_futbol.srv.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.EquipoDb;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.EquipoInfoNombre;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.EquipoList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.EquipoNombreDb;

@Mapper
public interface EquipoMapper {
    EquipoMapper INSTANCE=Mappers.getMapper(EquipoMapper.class);

    @Mapping(target = "nombreCiudad", source="ciudadDb.nombre")
    EquipoList EquipoDbToEquipoList(EquipoDb equipoDb);

    List<EquipoList> equiposDbToEquiposList(List<EquipoDb> equiposDb);
    Set<EquipoInfoNombre> equiposDbtoEquiposInfoNombre(Set<EquipoDb> equiposDb);

    EquipoInfoNombre equipoNombreDbToEquipoInfoNombre(EquipoNombreDb equipoNombreDb);
}
