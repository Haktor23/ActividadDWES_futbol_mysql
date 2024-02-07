package edu.alumno.hector.api_rest_mysql_futbol.srv.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.AlumnoDb;





@Repository
public interface AlumnoRepository extends JpaRepository<AlumnoDb, String> {
    
    Page<AlumnoDb> findAll(Pageable pageable);
}