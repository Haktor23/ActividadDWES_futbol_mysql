package edu.alumno.hector.api_rest_mysql_futbol.srv.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.JugadorDb;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorDb, String> {
    List <JugadorDb> findAll(Sort sort);
    List <JugadorDb> findByNombreContaining(String nombre,Sort sort);
    
    Page <JugadorDb> findAll(Pageable pageable);
    Page <JugadorDb> findByNombreContaining(String nombre,Pageable pageable);
}
