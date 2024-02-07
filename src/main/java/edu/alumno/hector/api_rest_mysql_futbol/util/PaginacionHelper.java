package edu.alumno.hector.api_rest_mysql_futbol.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.ui.ModelMap;

public class PaginacionHelper {

    public static Sort ConfiguraOrdenaciones(String[] sort) {
        List<Order> criteriosOrdenacion = new ArrayList<Order>();// Crear sorts (ordenación de los datos)
        if (sort[0].contains(",")) { // Hay más de un criterio de ordenación
            for (String criterioOrdenacion : sort) {// Tenemos un vector de ordenaciones en 'sort' y debemos leerlos
                String[] orden = criterioOrdenacion.split(",");
                if (orden.length > 1)
                    criteriosOrdenacion.add(new Order(Direction.fromString(orden[1]), orden[0]));
                else // por defecto asc si no se dice nada
                    criteriosOrdenacion.add(new Order(Direction.fromString("asc"), orden[0]));
            }
        } else // Solo hay un criterio de ordenación:El primer elemento es la dirección y el
               // segundo el campo
            criteriosOrdenacion.add(new Order(Direction.fromString(sort[1]), sort[0]));
        return Sort.by(criteriosOrdenacion);
    }

    public static Pageable ConfiguraPaginacion(int numPage, int pageSize, String[] sort) {        
        // El primer criterio de ordenacion siempre deberá de contener el
        // orden(asc,desc)
        Sort sorts = ConfiguraOrdenaciones(sort);
        // Crear solicitud de página nº 'numPage' de tamaño 'pageSize' utilizando el orden
        // 'sorts'
        Pageable paging = PageRequest.of(numPage, pageSize, sorts);
        return paging;
    }

    public static String ConfiguraUrlConOrdenacion(String url,String[] sort, int pageSize){
        String urlPagina = url + "?size=" + pageSize;// preparamos url para paginación
        if (sort[0].contains(",")) { // Hay más de un criterio de ordenación
            // Tenemos un vector de ordenaciones en 'sort' y debemos leerlos
            for (String criterioOrdenacion : sort) {
                String[] orden = criterioOrdenacion.split(",");
                if (orden.length > 1)
                    urlPagina += "&sort=" + orden[0] + "," + orden[1];
                else // por defecto asc si no se dice nada
                    urlPagina += "&sort=" + orden[0] + ",asc";
            }
        } else { // Solo hay un criterio de ordenación: El primer elemento es la dirección y el
                 // segundo el campo
            urlPagina += "&sort=" + sort[0] + "," + sort[1];
        }
        return urlPagina;

    }
    public static ModelMap ConfiguraPaginaEnModel(ModelMap model, String url, String[] sort, int currentPage,
            int pageSize, long totalItems, int totalPages) {
        // Añadimos los datos de la página al modelo y configuramos urlPage en base a la ordenación
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        if (!sort[0].contains(",")) {// Si es solo un atributo orden podemos mostrarlo en el front
            // Solo hay un criterio de ordenación: El primer elemento es la dirección y el segundo el campo
            model.addAttribute("orderBy", sort[0] + "," + sort[1]); 
            
        }
        model.addAttribute("urlPage", ConfiguraUrlConOrdenacion(url,sort, pageSize));
        return model;
    }
}

