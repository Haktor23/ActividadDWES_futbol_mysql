package edu.alumno.hector.api_rest_mysql_futbol.srv;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.AlumnoEdit;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.AlumnoInfo;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.AlumnoList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;

public interface AlumnoService {
    public Optional<AlumnoEdit> getAlumnoEditByDni(String dni);
    public Optional<AlumnoInfo> getAlumnoInfoByDni(String dni);
    public AlumnoEdit add(AlumnoEdit alumnoEdit,String usuarioQueHaceAdd)  throws Exception ;
    public String deleteByDni(String dni,String usuarioQueHaceBorrado) throws Exception ;
    public Optional<AlumnoEdit> update(AlumnoEdit alumnoEdit,String usuarioQueHaceModificacion) throws Exception;
    public PaginaDto<AlumnoList> findAllAlumnoList(Pageable paging);
    //De momento añadimos también:
    public List<String> listaInteresadoEn();
    public Map < String, String > listaGenero();
    public Map < String, String > listaHorarios();
    public Map < String, String > listaPais();
}
