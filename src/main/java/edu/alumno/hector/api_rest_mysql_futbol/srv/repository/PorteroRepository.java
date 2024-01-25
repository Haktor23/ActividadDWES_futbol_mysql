package edu.alumno.hector.api_rest_mysql_futbol.srv.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alumno.hector.api_rest_mysql_futbol.model.db.PorteroDb;

@Repository
public interface PorteroRepository extends JpaRepository<PorteroDb, String> {
    List <PorteroDb> findAll(Sort sort);
    
    Page <PorteroDb> findAll(Pageable pageable);
}
