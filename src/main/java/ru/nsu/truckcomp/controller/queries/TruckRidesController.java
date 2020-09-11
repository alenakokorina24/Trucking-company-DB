package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Ride;
import ru.nsu.truckcomp.repository.RideRepository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class TruckRidesController {
    @Autowired
    private RideRepository rideRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/truckRides")
    public String getTruckRides(@RequestParam(required = false) Date start,
                                @RequestParam(required = false) Date end,
                                @RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                Map<String, Object> model){
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("id").ascending());
        List<Ride> rides = rideRepository.getTruckRides(start, end, sorted);
        model.put("hasPrev", pageNumber > 1);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", (pageNumber * ROWS_PER_PAGE) < rides.size());
        model.put("next", pageNumber + 1);
        model.put("rides", rides);
        model.put("start", start);
        model.put("end", end);
        return "query/truckRides";
    }
}
