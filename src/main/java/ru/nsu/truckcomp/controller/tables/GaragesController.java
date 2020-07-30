package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Garage;
import ru.nsu.truckcomp.repository.AreaRepository;
import ru.nsu.truckcomp.repository.GarageRepository;

import java.util.Map;

@Controller
public class GaragesController {
    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private GarageRepository garageRepository;

    @GetMapping("/garages")
    public String getGarages(Map<String, Object> model) {
        model.put("garages", garageRepository.findAll());
        model.put("areas", areaRepository.findAll());
        return "tables/garages";
    }

    @PostMapping("/addGarage")
    public String addGarage(@RequestParam Integer area,
                          Map<String, Object> model) {
        Garage garage = new Garage(areaRepository.findByAreaId(area));
        garageRepository.save(garage);
        getGarages(model);
        return "tables/garages";
    }

    // TODO: ошибка при попытке удаления записи, на которую существуют ссылки
    // TODO: запилить каскадное удаление походу...
    @PostMapping("/deleteGarage")
    public String deleteGarage(@RequestParam Integer garageId,
                             Map<String, Object> model) {
        garageRepository.delete(garageRepository.findByGarageId(garageId));
        getGarages(model);
        return "tables/garages";
    }

}