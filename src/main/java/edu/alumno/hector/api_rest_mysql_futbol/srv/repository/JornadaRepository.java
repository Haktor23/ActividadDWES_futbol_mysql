package edu.alumno.hector.api_rest_mysql_futbol.srv.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.JornadaDb;

public interface JornadaRepository extends JpaRepository<JornadaDb,Long> {
    
}
