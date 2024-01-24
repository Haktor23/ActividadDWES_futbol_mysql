package edu.alumno.hector.api_rest_mysql_futbol.srv.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.CiudadDb;





@Repository
public interface CiudadRepository extends JpaRepository<CiudadDb, Long> {
    //Filtrado por nombre usando equivalente a LIKE usando "Containing"
    List<CiudadDb> findByNombreContaining(String nombre, Sort sort);
    //Paginación
    Page<CiudadDb> findAll(Pageable pageable);
    Page<CiudadDb> findByNombreContaining(String nombre,Pageable pageable);

}
