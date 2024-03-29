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
import ru.nsu.truckcomp.model.Ride;
import ru.nsu.truckcomp.repository.RideRepository;
import ru.nsu.truckcomp.repository.RouteRepository;
import ru.nsu.truckcomp.repository.TransportRepository;

import java.sql.Date;
import java.util.Map;

@Controller
public class RidesController {
    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private TransportRepository transportRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/rides")
    public String getRides(Map<String, Object> model,
                           @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        long count = rideRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROWS_PER_PAGE) < count;
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("rideId").ascending());
        model.put("rides", rideRepository.findAll(sorted));
        model.put("routes", routeRepository.findAll());
        model.put("transport", transportRepository.findAll());
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);
        return "tables/rides";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addRide")
    public String addRide(@RequestParam Integer route,
                          @RequestParam Date date,
                          @RequestParam Integer transport,
                          Map<String, Object> model) {
        Ride ride = new Ride(routeRepository.findByRouteId(route), transportRepository.findById(transport).get(), date);
        rideRepository.save(ride);
        getRides(model, 1);
        return "tables/rides";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteRide")
    public String deleteRide(@RequestParam Integer rideId,
                             Map<String, Object> model) {
        rideRepository.delete(rideRepository.findByRideId(rideId));
        getRides(model, 1);
        return "tables/rides";
    }

}
