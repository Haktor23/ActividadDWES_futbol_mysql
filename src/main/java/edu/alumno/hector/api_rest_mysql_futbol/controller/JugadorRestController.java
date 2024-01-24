package edu.alumno.hector.api_rest_mysql_futbol.controller;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;

import edu.alumno.hector.api_rest_mysql_futbol.model.dto.JugadorList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;
import edu.alumno.hector.api_rest_mysql_futbol.srv.JugadorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/")
public class JugadorRestController {

private final JugadorService jugadorService;

public JugadorRestController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }
    @GetMapping("/jugadores")
    public ResponseEntity <Map<String,Object>> getAllJugadores(
        @RequestParam(defaultValue =  "0") int page,
        @RequestParam(defaultValue =  "3") int size,
        @RequestParam(defaultValue =  "id,asc") String[] sort) {
            
       try {
        List<Order> criteriosOrdenacion= new ArrayList<Order>();
        if (sort[0].contains(",")) {
            for (String criterioOrdenacion : sort) {
                String[] orden = criterioOrdenacion.split(",");
                if (orden.length > 1) 
                    criteriosOrdenacion.add(new Order(Direction.fromString(orden[1]),orden[0])); 
                       else 
                       criteriosOrdenacion.add(new Order(Direction.fromString("asc"),orden[0]));
                   
            }
        }else{
            criteriosOrdenacion.add(new Order(Direction.fromString(sort[1]),sort[0]));
        }
        Sort sorts= Sort.by(criteriosOrdenacion);

        Pageable paging = PageRequest.of(page,size,sorts);
        PaginaDto<JugadorList> paginaJugadoresList=jugadorService.findAll(paging);

        List<JugadorList> jugadores = paginaJugadoresList.getContent();
        Map<String, Object> response = new HashMap();

        response.put("data", jugadores);
        response.put("currentPage", paginaJugadoresList.getNumber());
        response.put("pageSize", paginaJugadoresList.getSize());
        response.put("totalItems", paginaJugadoresList.getTotalElements());
        response.put("totalPages", paginaJugadoresList.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);

       } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
       
    }

}
