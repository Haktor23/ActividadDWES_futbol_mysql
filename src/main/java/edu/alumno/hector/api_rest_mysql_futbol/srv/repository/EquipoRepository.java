package edu.alumno.hector.api_rest_mysql_futbol.srv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.EquipoDb;

public interface EquipoRepository extends JpaRepository<EquipoDb, String> {
    
}
