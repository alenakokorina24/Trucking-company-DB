package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Route;
import ru.nsu.truckcomp.repository.RouteRepository;

import java.util.Map;

@Controller
public class RoutesController {
    @Autowired
    private RouteRepository routeRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/routes")
    public String getRoutes(Map<String, Object> model,
                            @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        long count = routeRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROWS_PER_PAGE) < count;
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("driverId").ascending());
        model.put("routes", routeRepository.findAll(sorted));
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);
        return "tables/routes";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addRoute")
    public String addArea(@RequestParam Integer length,
                          Map<String, Object> model) {
        Route route = new Route(length);
        routeRepository.save(route);
        getRoutes(model, 1);
        return "tables/routes";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteRoute")
    public String deleteArea(@RequestParam Integer routeId,
                             Map<String, Object> model) {
        routeRepository.delete(routeRepository.findByRouteId(routeId));
        getRoutes(model, 1);
        return "tables/routes";
    }

}