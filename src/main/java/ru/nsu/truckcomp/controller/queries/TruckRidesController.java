package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.repository.RideRepository;

import java.sql.Date;
import java.util.Map;

@Controller
public class TruckRidesController {
    @Autowired
    private RideRepository rideRepository;

    @GetMapping("/truckRides")
    public String getTruckRides(@RequestParam(required = false) Date start,
                                  @RequestParam(required = false) Date end,
                                  Map<String, Object> model){
        model.put("rides", rideRepository.getTruckRides(start, end));
        model.put("start", start);
        model.put("end", end);
        return "query/truckRides";
    }
}
