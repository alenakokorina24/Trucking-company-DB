package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Garage;
import ru.nsu.truckcomp.model.RepairList;
import ru.nsu.truckcomp.repository.GarageRepository;
import ru.nsu.truckcomp.repository.PassTransportRepository;
import ru.nsu.truckcomp.repository.TruckRepository;

import java.util.List;
import java.util.Map;

@Controller
public class GarageFacilitiesController {
    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private PassTransportRepository passTransportRepository;

    @Autowired
    private GarageRepository garageRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/garageFacilities")
    public String getGarageFacilities(@RequestParam(required = false) String category,
                                      @RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                      Map<String, Object> model){
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("garageId").ascending());
        List<Garage> garages;
        if (category == null) {
            garages = garageRepository.findAll(sorted);
            category = "общая";
        } else if (category.equals("пассажирский")) {
            garages = passTransportRepository.getGarages(sorted);
        } else {
            garages = truckRepository.getGarages(sorted);
        }
        model.put("category", category);
        model.put("hasPrev", pageNumber > 1);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", (pageNumber * ROWS_PER_PAGE) < garages.size());
        model.put("next", pageNumber + 1);
        return "query/garageFacilities";
    }
}
