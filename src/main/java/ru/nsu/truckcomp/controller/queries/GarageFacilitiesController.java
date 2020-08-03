package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.repository.GarageRepository;
import ru.nsu.truckcomp.repository.PassTransportRepository;
import ru.nsu.truckcomp.repository.TruckRepository;

import java.util.Map;

@Controller
public class GarageFacilitiesController {
    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private PassTransportRepository passTransportRepository;

    @Autowired
    private GarageRepository garageRepository;

    @GetMapping("/garageFacilities")
    public String getGarageFacilities(@RequestParam(required = false) String category,
                                      Map<String, Object> model){
        if (category == null) {
            model.put("garages", garageRepository.findAll());
            category = "общая";
        } else if (category.equals("пассажирский")) {
            model.put("garages", passTransportRepository.getGarages());
        } else if (category.equals("грузовой")) {
            model.put("garages", truckRepository.getGarages());
        }
        model.put("category", category);
        return "query/garageFacilities";
    }
}
