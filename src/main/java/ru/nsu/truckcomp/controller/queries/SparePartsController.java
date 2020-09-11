package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.RepairList;
import ru.nsu.truckcomp.repository.RepairListRepository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class SparePartsController {
    @Autowired
    private RepairListRepository repairListRepository;

    private Pageable sorted;
    private int pageNumber;
    private final int ROWS_PER_PAGE = 12;

    private void updateModel(Map<String, Object> model, List<RepairList> repairs, String sparePart, String transportInfo) {
        model.put("repairs", repairs);
        model.put("sparePartsNum", repairs.size());
        model.put("sparePart", sparePart);
        model.put("transportInfo", transportInfo);
        model.put("hasPrev", pageNumber > 1);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", (pageNumber * ROWS_PER_PAGE) < repairs.size());
        model.put("next", pageNumber + 1);
    }

    private void getSparePartsById(Map<String, Object> model, String sparePart, int id, Date start, Date end) {
        List<RepairList> repairs = repairListRepository.findBySparePartAndTransport_IdAndReceivedAfterAndReturnedBefore(sparePart, id, start, end, sorted);
        updateModel(model, repairs, sparePart, "АВТОМАШИНЫ №" + id);
    }

    private void getSparePartsByBrand(Map<String, Object> model, String sparePart, String brand, Date start, Date end) {
        List<RepairList> repairs = repairListRepository.findBySparePartAndTransport_BrandAndReceivedAfterAndReturnedBefore(sparePart, brand, start, end, sorted);
        updateModel(model, repairs, sparePart, "МАРКИ " + brand);
    }

    private void getSparePartsByCategory(Map<String, Object> model, String category, String sparePart, Date start, Date end) {
        List<RepairList> repairs;
        String transportInfo;

        if (category.equals("пассажирский")) {
            repairs = repairListRepository.getPassengerTransportSpareParts(start, end, sparePart, sorted);
            transportInfo = "ПАССАЖИРСКОГО ТРАНСПОРТА";
        } else {
            repairs = repairListRepository.getTruckSpareParts(start, end, sparePart, sorted);
            transportInfo = "ГРУЗОВОГО ТРАНСПОРТА";
        }
        updateModel(model, repairs, sparePart, transportInfo);
    }

    private void getSparePartsByBrandAndCategory(Map<String, Object> model, String brand, String category, String sparePart, Date start, Date end) {
        List<RepairList> repairs;
        String transportInfo;

        if (category.equals("пассажирский")) {
            repairs = repairListRepository.getPassengerTransportSparePartsWithBrand(brand, start, end, sparePart, sorted);
            transportInfo = "ПАССАЖИРСКОГО ТРАНСПОРТА";
        } else {
            repairs = repairListRepository.getTruckSparePartsWithBrand(brand, start, end, sparePart, sorted);
            transportInfo = "ГРУЗОВОГО ТРАНСПОРТА";
        }
        updateModel(model, repairs, sparePart, transportInfo);
    }

    private void getSpareParts(Map<String, Object> model, String sparePart, Date start, Date end) {
        List<RepairList> repairs = repairListRepository.findBySparePartAndReceivedAfterAndReturnedBefore(sparePart, start, end, sorted);
        updateModel(model, repairs, sparePart, "ВСЕГО ТРАНСПОРТА");
    }

    @GetMapping("/spareParts")
    public String getSparePartsInfo(@RequestParam String sparePart,
                                    @RequestParam(required = false) String category,
                                    @RequestParam(required = false) String brand,
                                    @RequestParam(required = false) Integer transport,
                                    @RequestParam(required = false) Date start,
                                    @RequestParam(required = false) Date end,
                                    @RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                    Map<String, Object> model) {
        this.sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("repId").ascending());
        this.pageNumber = pageNumber;
        if (transport != null) {
            getSparePartsById(model, sparePart, transport, start, end);
        } else if (category != null && brand != null) {
            getSparePartsByBrandAndCategory(model, brand, category, sparePart, start, end);
        } else if (brand != null) {
            getSparePartsByBrand(model, sparePart, brand, start, end);
        } else if (category != null) {
            getSparePartsByCategory(model, category, sparePart, start, end);
        } else {
            getSpareParts(model, sparePart, start, end);
       }
        return "query/spareParts";
    }
}