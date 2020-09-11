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

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/garages")
    public String getGarages(Map<String, Object> model,
                             @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        long count = garageRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROWS_PER_PAGE) < count;
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("garageId").ascending());
        model.put("garages", garageRepository.findAll(sorted));
        model.put("areas", areaRepository.findAll());
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);
        return "tables/garages";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addGarage")
    public String addGarage(@RequestParam Integer area,
                          Map<String, Object> model) {
        Garage garage = new Garage(areaRepository.findByAreaId(area));
        garageRepository.save(garage);
        getGarages(model, 1);
        return "tables/garages";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteGarage")
    public String deleteGarage(@RequestParam Integer garageId,
                             Map<String, Object> model) {
        garageRepository.delete(garageRepository.findByGarageId(garageId));
        getGarages(model, 1);
        return "tables/garages";
    }

}