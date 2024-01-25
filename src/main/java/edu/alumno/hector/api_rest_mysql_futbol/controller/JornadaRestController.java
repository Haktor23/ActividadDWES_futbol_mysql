package edu.alumno.hector.api_rest_mysql_futbol.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.hector.api_rest_mysql_futbol.model.dto.JornadaList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;
import edu.alumno.hector.api_rest_mysql_futbol.srv.JornadaService;

@RestController
@RequestMapping("/api/v1/")
public class JornadaRestController {
       
 private final JornadaService jornadaService;

 public JornadaRestController(JornadaService jornadaService){
    this.jornadaService=jornadaService;
}

@GetMapping("/jornadas")
    public ResponseEntity <Map<String,Object>> getAllJornadas(
        @RequestParam(defaultValue =  "0") int page,
        @RequestParam(defaultValue =  "3") int size,
        @RequestParam(defaultValue =  "num,asc") String[] sort) {
            
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
        PaginaDto<JornadaList> paginaJornadasList=jornadaService.findAll(paging);

        List<JornadaList> jornadas = paginaJornadasList.getContent();
        Map<String, Object> response = new HashMap();

        response.put("data", jornadas);
        response.put("currentPage", paginaJornadasList.getNumber());
        response.put("pageSize", paginaJornadasList.getSize());
        response.put("totalItems", paginaJornadasList.getTotalElements());
        response.put("totalPages", paginaJornadasList.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);

       } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
       
    }

}
