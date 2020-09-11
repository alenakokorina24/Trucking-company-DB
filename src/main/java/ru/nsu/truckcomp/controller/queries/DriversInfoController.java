package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Driver;
import ru.nsu.truckcomp.repository.DriverRepository;

import java.util.List;
import java.util.Map;

@Controller
public class DriversInfoController {
    @Autowired
    private DriverRepository driverRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/driversInfo")
    public String getDriversInfo(@RequestParam(required = false) Integer area,
                                 @RequestParam(required = false) Integer transport,
                                 @RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                 Map<String, Object> model) {
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("empId").ascending());
        List<Driver> drivers;
        if (area != null && transport != null) {
            drivers = driverRepository.findByTransportIdAndBrigade_AreaAreaId(transport, area, sorted);
        } else if (area != null) {
            drivers = driverRepository.findByBrigade_AreaAreaId(area, sorted);
        } else if (transport != null) {
            drivers = driverRepository.findByTransportId(transport, sorted);
        } else {
            drivers = driverRepository.findAll(sorted);
        }
        model.put("drivers", drivers);
        model.put("driversNum", drivers.size());
        model.put("hasPrev", pageNumber > 1);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", (pageNumber * ROWS_PER_PAGE) < drivers.size());
        model.put("next", pageNumber + 1);
        return "query/driversInfo";
    }
}
