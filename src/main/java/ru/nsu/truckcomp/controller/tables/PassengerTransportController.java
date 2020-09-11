package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.PassengerTransport;
import ru.nsu.truckcomp.repository.GarageRepository;
import ru.nsu.truckcomp.repository.PassTransportRepository;
import ru.nsu.truckcomp.repository.TransportRepository;

import java.sql.Date;
import java.util.Map;

// контроллер Spring MVC
@Controller
public class PassengerTransportController {
    // спринг сам найдёт нужный бин и подставит в значение поля
    @Autowired
    private PassTransportRepository passTransportRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private GarageRepository garageRepository;

    @GetMapping("/passengerTransport")
    public String getPassengerTransport(Map<String, Object> model) {
        model.put("passengerTransport", passTransportRepository.findAll());
        model.put("garages", garageRepository.findAll());
        return "tables/passengerTransport";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addPassengerTransport")
    public String addPassengerTransport(@RequestParam String brand,
                                        @RequestParam Date acquirementDate,
                                        @RequestParam Date decommissionDate,
                                        @RequestParam Integer garage,
                                        @RequestParam Integer capacity,
                                        @RequestParam String type,
                                        Map<String, Object> model) {
        PassengerTransport passengerTransport = new PassengerTransport(garageRepository.findByGarageId(garage), brand, acquirementDate, decommissionDate, type, capacity);
        passTransportRepository.save(passengerTransport);
        getPassengerTransport(model);
        return "tables/passengerTransport";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deletePassengerTransport")
    public String deletePassengerTransport(@RequestParam Integer id,
                             Map<String, Object> model) {
        transportRepository.delete(transportRepository.findById(id).get());
        getPassengerTransport(model);
        return "tables/passengerTransport";
    }

}