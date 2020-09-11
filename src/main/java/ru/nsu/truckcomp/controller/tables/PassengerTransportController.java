package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

@Controller
public class PassengerTransportController {
    // спринг сам найдёт нужный бин и подставит в значение поля
    @Autowired
    private PassTransportRepository passTransportRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private GarageRepository garageRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/passengerTransport")
    public String getPassengerTransport(Map<String, Object> model,
                                        @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        long count = passTransportRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROWS_PER_PAGE) < count;
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("id").ascending());
        model.put("passengerTransport", passTransportRepository.findAll(sorted));
        model.put("garages", garageRepository.findAll());
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);
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
        getPassengerTransport(model, 1);
        return "tables/passengerTransport";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deletePassengerTransport")
    public String deletePassengerTransport(@RequestParam Integer id,
                             Map<String, Object> model) {
        transportRepository.delete(transportRepository.findById(id).get());
        getPassengerTransport(model, 1);
        return "tables/passengerTransport";
    }

}