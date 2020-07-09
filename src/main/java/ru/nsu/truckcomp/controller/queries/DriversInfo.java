package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.repository.DriverRepository;

import java.util.Map;

@Controller
public class DriversInfo {
    @Autowired
    private DriverRepository driverRepository;

    @GetMapping("/drivers")
    public String getDriversInfo(@RequestParam(required = false) Integer area,
                                 @RequestParam(required = false) Integer transport,
                                 Map<String, Object> model) {
        if (area != null && transport != null) {
            model.put("drivers", driverRepository.findByTransportIdAndBrigade_AreaAreaId(transport, area));
        } else if (area != null) {
            model.put("drivers", driverRepository.findByBrigade_AreaAreaId(area));
        } else if (transport != null) {
            model.put("drivers", driverRepository.findByTransportId(transport));
        } else {
            model.put("drivers", driverRepository.findAll());
        }
        return "query/drivers";
    }
}
