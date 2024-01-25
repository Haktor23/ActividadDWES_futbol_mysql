package edu.alumno.hector.api_rest_mysql_futbol.model.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JornadaList implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long num;
    private Date fecha;
}
