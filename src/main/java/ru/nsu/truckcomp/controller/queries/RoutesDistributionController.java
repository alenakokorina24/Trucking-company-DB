package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.repository.RideRepository;

import java.util.Map;

@Controller
public class RoutesDistributionController {
    @Autowired
    private RideRepository rideRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/routesDistribution")
    public String getRoutesInfo(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                Map<String, Object> model) {
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("rideId").ascending());
        model.put("routes", rideRepository.getRoutesDistribution(sorted));
        return "query/routesDistribution";
    }
}
