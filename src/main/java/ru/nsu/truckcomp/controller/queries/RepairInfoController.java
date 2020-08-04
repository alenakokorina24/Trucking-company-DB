package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.RepairList;
import ru.nsu.truckcomp.repository.RepairListRepository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class RepairInfoController {
    @Autowired
    private RepairListRepository repairListRepository;

    private void updateModel(Map<String, Object> model, List<RepairList> repairs, String transportInfo) {
        model.put("repairList", repairs);
        model.put("transportInfo", transportInfo);
        model.put("repairsNum", repairs.size());
    }

    private void getRepairsByCategory(Map<String, Object> model, String category, Date start, Date end) {
        List<RepairList> repairs;
        String transportInfo;

        if (category.equals("пассажирский")) {
            repairs = repairListRepository.getPassengerTransportRepairs(start, end);
            transportInfo = "ПАССАЖИРСКОГО ТРАНСПОРТА";
        } else {
            repairs = repairListRepository.getTruckRepairs(start, end);
            transportInfo = "ГРУЗОВОГО ТРАНСПОРТА";
        }
        updateModel(model, repairs, transportInfo);
    }

    private void getRepairsByBrandAndCategory(Map<String, Object> model, String brand, String category, Date start, Date end) {
        List<RepairList> repairs;
        String transportInfo;

        if (category.equals("пассажирский")) {
            repairs = repairListRepository.getPassengerTransportRepairsWithBrand(brand, start, end);
            transportInfo = "ПАССАЖИРСКОГО ТРАНСПОРТА МАРКИ " + brand;
        } else {
            repairs = repairListRepository.getTruckRepairsWithBrand(brand, start, end);
            transportInfo = "ГРУЗОВОГО ТРАНСПОРТА МАРКИ " + brand;
        }
        updateModel(model, repairs, transportInfo);
    }

    private void getRepairsByBrand(Map<String, Object> model, String brand, Date start, Date end) {
        List<RepairList> repairs = repairListRepository.findByTransport_BrandAndReceivedAfterAndReturnedBefore(brand, start, end);
        updateModel(model, repairs, "АВТОМОБИЛЕЙ МАРКИ " + brand);
    }

    private void getRepairsById(Map<String, Object> model, Integer transport, Date start, Date end) {
        List<RepairList> repairs = repairListRepository.findByTransport_IdAndReceivedAfterAndReturnedBefore(transport, start, end);
        updateModel(model, repairs, "АВТОМАШИНЫ №" + transport);
    }

    private void getRepairs(Map<String, Object> model) {
        List<RepairList> repairs = repairListRepository.findAll();
        updateModel(model, repairs, "ВСЕГО ТРАНСПОРТА");
    }

    @GetMapping("/repairInfo")
    public String getAutoParkInfo(@RequestParam(required = false) String category,
                                  @RequestParam(required = false) String brand,
                                  @RequestParam(required = false) Integer transport,
                                  @RequestParam(required = false) Date start,
                                  @RequestParam(required = false) Date end,
                                  Map<String, Object> model) {
        if (transport != null) {
            getRepairsById(model, transport, start, end);
        } else if (category != null && brand != null) {
            getRepairsByBrandAndCategory(model, brand, category, start, end);
        } else if (brand != null) {
            getRepairsByBrand(model, brand, start, end);
        } else if (category != null) {
            getRepairsByCategory(model, category, start, end);
        } else {
            getRepairs(model);
        }
        return "query/repairInfo";
    }
}
