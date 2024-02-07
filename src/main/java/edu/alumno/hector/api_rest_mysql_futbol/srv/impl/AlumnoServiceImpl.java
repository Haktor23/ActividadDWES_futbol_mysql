package edu.alumno.hector.api_rest_mysql_futbol.srv.impl;

import org.springframework.stereotype.Service;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.AlumnoDb;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.AlumnoEdit;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.AlumnoInfo;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.AlumnoList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;
import edu.alumno.hector.api_rest_mysql_futbol.srv.AlumnoService;
import edu.alumno.hector.api_rest_mysql_futbol.srv.exception.AlumnoDuplicadoException;
import edu.alumno.hector.api_rest_mysql_futbol.srv.mapper.AlumnoMapper;
import edu.alumno.hector.api_rest_mysql_futbol.srv.repository.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Optional<AlumnoEdit> getAlumnoEditByDni(String dni) {
        Optional<AlumnoDb> alumnoDb=alumnoRepository.findById(dni);
        if (alumnoDb.isPresent())
            return Optional.of(AlumnoMapper.INSTANCE.alumnoDbToAlumnoEdit(alumnoDb.get()));
        else 
            return Optional.empty();
    }

    @Override
    public Optional<AlumnoInfo> getAlumnoInfoByDni(String dni) {
        Optional<AlumnoDb> alumnoDb=alumnoRepository.findById(dni);
        if (alumnoDb.isPresent())
            return Optional.of(AlumnoMapper.INSTANCE.alumnoDbToAlumnoInfo(alumnoDb.get()));
        else 
            return Optional.empty();
    }

    @Override
    public String deleteByDni(String dni,String usuarioQueHaceBorrado)  throws Exception{
        if (usuarioQueHaceBorrado==null) //Usuario no logeado
			    throw new Exception("Debe estar logeado para poder borrar el alumno");
        String resultado="";
        try {
            resultado=alumnoRepository.findById(dni)
            .map(a-> {
                    alumnoRepository.deleteById(dni);
                    return "Deleted";
                }).orElse("Not Deleted");           
            if (resultado.equals("Not Deleted")) //Ha habido algún error al intentar borrar
                throw new Exception();
            return resultado;// Si llega aquí no hay errores y devolvemos "Deleted"
        } catch (Exception e) {//Controlar cualquier error, pe BD no operativa
            throw new Exception("No se ha podido borrar el alumno con DNI '"+dni+"'");
        }       
    }

    @Override
    //Añadir un nuevo alumno a la BD y devolver el AlumnoEdit de la BD
    public AlumnoEdit add(AlumnoEdit alumnoEdit,String usuarioQueHaceAdd) throws Exception {
        if (usuarioQueHaceAdd==null) //Usuario no logeado
			throw new Exception("Debe estar logeado para poder añadir el alumno");
        Optional<AlumnoDb> alumnoDb=alumnoRepository.findById(alumnoEdit.getDni());
        if (alumnoDb.isPresent()) { //El alumno ya existe con ese DNI
			throw new AlumnoDuplicadoException(alumnoDb.get(),alumnoEdit);
		}else { //no existe y podemos añadirlo previa conversión de 'AlumnoEdit' a 'AlumnoDb'
            //primero configuramos usuario y fecha de inserción
            alumnoEdit.setUser(usuarioQueHaceAdd);
            alumnoEdit.setTs(new Date());
            try {//Controlamos posible error de acceso a BD para insertar
                return AlumnoMapper.INSTANCE.alumnoDbToAlumnoEdit(alumnoRepository
                .save(AlumnoMapper.INSTANCE.alumnoEditToAlumnoDb(alumnoEdit)));    
            } catch (Exception e) {
                throw new Exception("No se ha podido añadir el alumno con DNI '"+alumnoEdit.getDni()+"'");
            }
        }
    }

    @Override
    public Optional<AlumnoEdit> update(AlumnoEdit alumnoEditModificado,String usuarioQueHaceModificacion)  throws Exception {
        String errores="";
		if (usuarioQueHaceModificacion==null) { //Usuario no logeado
			errores="Debe estar logeado para poder modificar el alumno";
		} else { //Usuario logeado
            Optional<AlumnoDb> alumnoDb= alumnoRepository.findById(alumnoEditModificado.getDni());
            if (alumnoDb.isPresent()){//Existe alumno en la BD
                AlumnoEdit alumnoEditFromDb=AlumnoMapper.INSTANCE.alumnoDbToAlumnoEdit(alumnoDb.get());
                if (alumnoEditFromDb.sePuedeModificarUtilizando(alumnoEditModificado)) {
                    //actualizamos usuario y fecha modificación antes de mapear sobre Alumno
                    alumnoEditModificado.setUser(usuarioQueHaceModificacion);
                    alumnoEditModificado.setTs(new Date());
                    try {//Controlamos posible error de acceso a BD para modificar
                        AlumnoMapper.INSTANCE.updateAlumnoDbFromAlumnoEdit(alumnoEditModificado, alumnoDb.get());
                        return Optional.of(AlumnoMapper.INSTANCE
                                .alumnoDbToAlumnoEdit(alumnoRepository.save(alumnoDb.get())));
                    } catch (Exception e) {
                        throw new Exception("No se ha podido modificar el alumno con DNI '"+alumnoEditModificado.getDni()+"'");
                    }
                } else {//No se puede modificar el usuario
                    errores=alumnoEditFromDb.mensajeNoSePuedeModificar();
                }              
            } else {//No existe Alumno en la BD
                errores="Alumno con DNI '"+alumnoEditModificado.getDni()+"' no existe.";
            }
        }
        if (errores.length()>0) {//Hay errores
			throw new Exception(errores);
		}
        return Optional.empty();//En cualquier otro caso devolver Alumno vacio
    }

    @Override
    public PaginaDto<AlumnoList> findAllAlumnoList(Pageable paging){
        Page<AlumnoDb> paginaAlumnoDb=alumnoRepository.findAll(paging);
        return new PaginaDto<AlumnoList>(
            paginaAlumnoDb.getNumber(),//número de página solicitada
            paginaAlumnoDb.getSize(), //tamaño de la página
            paginaAlumnoDb.getTotalElements(),//total de elementos devueltos por la consulta sin paginación
            paginaAlumnoDb.getTotalPages(), //total páginas teniendo en cuenta el tamaño de cada página
            AlumnoMapper.INSTANCE.alumnosDbToAlumnosList(paginaAlumnoDb.getContent()),//lista de elementos
            paginaAlumnoDb.getSort()); //ordenación de la consulta
    }

    //De momento añadimos
	public List<String> listaInteresadoEn(){
		//Si fuera una lista más larga podríamos declararlo en la sección static de alumnos y darle valores
		// y aquí simplemente devolver la lista
		List < String > lista = new ArrayList < String > ();
        lista.add("Backend");
        lista.add("Frontend");
        return lista;	
	}
	
	public Map < String, String > listaGenero(){
		Map < String, String > lista = new HashMap < String, String > ();
        lista.put("H","Hombre");
        lista.put("M","Mujer");
        return lista;	
	}
	public Map < String, String > listaHorarios(){
		Map < String, String > lista = new HashMap < String, String > ();
        lista.put("M","Manyana");
        lista.put("T","Tarde");
        return lista;	
	}
	public Map < String, String > listaPais(){
		//Si fuera una lista más larga podríamos declararlo en la sección static de alumnos y darle valores
		// y aquí simplemente devolver la lista
		Map < String, String > lista = new HashMap < String, String > ();
	    lista.put("ES", "Espanya");
        lista.put("FR","Francia");
        lista.put("IT","Italia");
        lista.put("PT","Portugal");
        return lista;	
	}
}
