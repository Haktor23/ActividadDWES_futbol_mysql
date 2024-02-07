package edu.alumno.hector.api_rest_mysql_futbol.srv.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.AlumnoDb;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.AlumnoEdit;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.AlumnoInfo;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.AlumnoList;

@Mapper
public interface AlumnoMapper {
    AlumnoMapper INSTANCE = Mappers.getMapper(AlumnoMapper.class);

    AlumnoInfo alumnoDbToAlumnoEdit(AlumnoDb alumnoDb);

    @Mapping(target = "modificado", source = "alumnoDb.ts", dateFormat = "dd-MM-yyyy HH:mm:ss")
    AlumnoList alumnoDbToAlumnoList(AlumnoDb alumnoDb);

    List<AlumnoList> alumnosDbToAlumnoList(List<AlumnoDb> alumnosDb);

    @Mapping(source = "dni", target = "dni_alumno")
    @Mapping(source = "nombre", target = "nombre_alumno")
    @Mapping(source = "ciclo", target = "ciclo_alumno")
    @Mapping(source = "curso", target = "curso_alumno")
    AlumnoInfo alumnoDbToAlumnoInfo(AlumnoDb alumnoDb);

    AlumnoDb alumnoEditAlumnoDb(AlumnoEdit alumnoEdit);

    void updateAlumnoDbFromAlumnoEdit(AlumnoEdit alumnoEdit, @MappingTarget AlumnoDb alumnoDb);
}