package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/rides")
    public String getRides(Map<String, Object> model) {
        model.put("rides", rideRepository.findAll());
        model.put("routes", routeRepository.findAll());
        model.put("transport", transportRepository.findAll());
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
        getRides(model);
        return "tables/rides";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteRide")
    public String deleteRide(@RequestParam Integer rideId,
                             Map<String, Object> model) {
        rideRepository.delete(rideRepository.findByRideId(rideId));
        getRides(model);
        return "tables/rides";
    }

}
