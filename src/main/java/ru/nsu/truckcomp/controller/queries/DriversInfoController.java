package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/driversInfo")
    public String getDriversInfo(@RequestParam(required = false) Integer area,
                                 @RequestParam(required = false) Integer transport,
                                 Map<String, Object> model) {
        List<Driver> drivers;
        if (area != null && transport != null) {
            drivers = driverRepository.findByTransportIdAndBrigade_AreaAreaId(transport, area);
        } else if (area != null) {
            drivers = driverRepository.findByBrigade_AreaAreaId(area);
        } else if (transport != null) {
            drivers = driverRepository.findByTransportId(transport);
        } else {
            drivers = driverRepository.findAll();
        }

        model.put("drivers", drivers);
        model.put("driversNum", drivers.size());

        return "query/driversInfo";
    }
}
