package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Route;
import ru.nsu.truckcomp.repository.RouteRepository;

import javax.annotation.security.RolesAllowed;
import java.util.Map;

@Controller
public class RoutesController {
    @Autowired
    private RouteRepository routeRepository;

    @GetMapping("/routes")
    public String getRoutes(Map<String, Object> model) {
        model.put("routes", routeRepository.findAll());
        return "tables/routes";
    }

//    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/addRoute")
    public String addArea(@RequestParam Integer length,
                          Map<String, Object> model) {
        Route route = new Route(length);
        routeRepository.save(route);
        getRoutes(model);
        return "tables/routes";
    }

//    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/deleteRoute")
    public String deleteArea(@RequestParam Integer routeId,
                             Map<String, Object> model) {
        routeRepository.delete(routeRepository.findByRouteId(routeId));
        getRoutes(model);
        return "tables/routes";
    }

}