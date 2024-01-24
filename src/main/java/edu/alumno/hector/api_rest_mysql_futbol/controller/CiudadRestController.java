package edu.alumno.hector.api_rest_mysql_futbol.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.hector.api_rest_mysql_futbol.model.dto.CiudadInfo;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.CiudadList;
import edu.alumno.hector.api_rest_mysql_futbol.model.dto.PaginaDto;
import edu.alumno.hector.api_rest_mysql_futbol.srv.CiudadService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/")
public class CiudadRestController {

    private final CiudadService ciudadService;

    public CiudadRestController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }

    }



    @GetMapping("/ciudades/orden/{direccionOrden}")
    public Collection<CiudadList> getCiudadesListOrderByName(@PathVariable("direccionOrden") String direccionOrden) {
        return ciudadService.findAllCiudadList(Sort.by(Direction.fromString(direccionOrden), "nombre"));
    }

    @GetMapping("/ciudades/{id}/info")
    public ResponseEntity<CiudadInfo> getCiudadInfoById(@PathVariable(value = "id") Long id) throws RuntimeException {
        Optional<CiudadInfo> ciudadInfo = ciudadService.getCiudadInfoById(id);
        if (ciudadInfo.isPresent())
            return ResponseEntity.ok().body(ciudadInfo.get());
        else
            throw new ResourceNotFoundException("Ciudad not found on :: " + id);

    }

    @GetMapping("/ciudades/nombre/{nombre}/orden/{orden}")
    public Collection<CiudadList> getCiudadesByNombresContainingListOrderByName(@PathVariable("nombre") String nombre,
            @PathVariable("orden") String orden) {
        return ciudadService.findByNombreContraining(nombre, Sort.by(Direction.fromString(orden), "nombre"));
    }


    @GetMapping("/ciudades")
    public ResponseEntity<Map<String, Object>> getAllCiudades(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        try {
            List<Order> criteriosOrdenacionList = new ArrayList<Order>();
            if (sort[0].contains(",")) {
                for (String criteriosOrdenacion : sort) {
                    String[] orden = criteriosOrdenacion.split(",");
                    if (orden.length > 1) {
                        criteriosOrdenacionList.add(new Order(Direction.fromString(orden[1]), orden[0]));
                    } else {
                        criteriosOrdenacionList.add(new Order(Direction.fromString("asc"), orden[0]));
                    }
                }
            } else {
                criteriosOrdenacionList.add(new Order(Direction.fromString(sort[1]), sort[0]));
            }

            Sort sorts = Sort.by(criteriosOrdenacionList);

            Pageable paging = PageRequest.of(page, size, sorts);

            PaginaDto<CiudadList> paginaCiudadesList;

            if (nombre == null) {
                paginaCiudadesList = ciudadService.findAllPageCiudadList(paging);
            } else {
                paginaCiudadesList = ciudadService.findByNombreContaining(nombre, paging);
            }

List<CiudadList> ciudades = paginaCiudadesList.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("data", ciudades);
            response.put("currentPage", paginaCiudadesList.getNumber());
            response.put("pageSize", paginaCiudadesList.getSize());
            response.put("totalItems", paginaCiudadesList.getTotalElements());
            response.put("totalPages", paginaCiudadesList.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
/*  Sort sorts= Sort.by(criteriosOrdenacion);
        Pageable paging = PageRequest.of(page,size,sorts);
        PaginaDto<CiudadList> paginaCiudadesList=ciudadService.findAll(paging);

        List<CiudadList> ciudades = paginaCiudadesList.getContent();
        Map<String, Object> response = new HashMap();

        response.put("data", ciudades);
        response.put("currentPage", paginaCiudadesList.getNumber());
        response.put("pageSize", paginaCiudadesList.getSize());
        response.put("totalItems", paginaCiudadesList.getTotalElements());
        response.put("totalPages", paginaCiudadesList.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);

       } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       } */
