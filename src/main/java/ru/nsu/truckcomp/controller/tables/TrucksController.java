package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nsu.truckcomp.model.PassengerTransport;
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

    @GetMapping("/trucks")
    public String getTrucks(Map<String, Object> model) {
        model.put("trucks", truckRepository.findAll());
        model.put("garages", garageRepository.findAll());
        return "tables/trucks";
    }

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
        getTrucks(model);
        return "tables/trucks";
    }

    @PostMapping("/deleteTruck")
    public String deleteTruck(@RequestParam Integer id,
                                           Map<String, Object> model) {
        transportRepository.delete(transportRepository.findById(id).get());
        getTrucks(model);
        return "tables/trucks";
    }

}
