package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nsu.truckcomp.model.Truck;
import ru.nsu.truckcomp.repository.*;

import java.sql.Date;
import java.util.Map;

@Controller
public class TrucksController {
    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private GarageRepository garageRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/trucks")
    public String getTrucks(Map<String, Object> model,
                            @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        long count = truckRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROWS_PER_PAGE) < count;
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("id").ascending());
        model.put("trucks", truckRepository.findAll(sorted));
        model.put("garages", garageRepository.findAll());
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);
        return "tables/trucks";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addTruck")
    public String addTruck(@RequestParam String brand,
                           @RequestParam Date acquirementDate,
                           @RequestParam Date decommissionDate,
                           @RequestParam Integer garage,
                           @RequestParam String carcassType,
                           @RequestParam Integer carryingCapacity,
                           @RequestParam Integer weight,
                           Map<String, Object> model) {
        Truck truck = new Truck(garageRepository.findByGarageId(garage), brand, acquirementDate, decommissionDate, carryingCapacity, weight, carcassType);
        truckRepository.save(truck);
        getTrucks(model, 1);
        return "tables/trucks";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteTruck")
    public String deleteTruck(@RequestParam Integer id,
                                           Map<String, Object> model) {
        transportRepository.delete(transportRepository.findById(id).get());
        getTrucks(model, 1);
        return "tables/trucks";
    }

}
