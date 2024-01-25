package edu.alumno.hector.api_rest_mysql_futbol.model.db;

import java.util.Date;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name= "jornadas")
public class JornadaDb {
    @Id
    private Long num;
    private Date fecha;
}
