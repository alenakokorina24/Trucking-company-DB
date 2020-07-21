package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Ride;
import ru.nsu.truckcomp.model.Transport;
import ru.nsu.truckcomp.repository.RideRepository;
import ru.nsu.truckcomp.repository.TransportRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Mileage {
    @Autowired
    private RideRepository rideRepository;

    @GetMapping("/mileage")
    public String getMileageInfo(@RequestParam(required = false) String category,
                                 @RequestParam(required = false) Integer transport,
                                 @RequestParam(required = false) Date day,
                                 @RequestParam(required = false) String month,
                                 @RequestParam(required = false) Integer year,
                                 Map<String, Object> model) {
        Iterable<Ride> rides;
        if (transport != null) {
            rides = rideRepository.findByDateAndTransport_Id(day, transport);
        } else if (category != null) {

        } else {
            rides = rideRepository.findAll();
        }



        return "query/mileage";
    }
}
