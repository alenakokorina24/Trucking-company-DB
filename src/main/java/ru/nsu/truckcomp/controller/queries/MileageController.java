package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Mileage;
import ru.nsu.truckcomp.repository.RideRepository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MileageController {
    @Autowired
    private RideRepository rideRepository;

    private Pageable sorted;
    private int pageNumber;
    private final int ROWS_PER_PAGE = 12;

    private void updateModel(Map<String, Object> model, String transportInfo, List<Mileage> mileage, Object date) {
        model.put("mileage", mileage);
        model.put("transportInfo", transportInfo);
        model.put("date", date);
        model.put("hasPrev", pageNumber > 1);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", (pageNumber * ROWS_PER_PAGE) < mileage.size());
        model.put("next", pageNumber + 1);
    }

    private void getMileageByDay(Map<String, Object> model, Integer transport, String category, Date day) {
        List<Mileage> mileage;
        String transportInfo;
        if (transport != null) {
            mileage = rideRepository.getTransportDayMileage(transport, day, sorted);
            transportInfo = "АВТОМАШИНЫ №" + transport;
        } else {
            if (category.equals("пассажирский")) {
                mileage = rideRepository.getPassengerTransportDayMileage(day, sorted);
                transportInfo = "ПАССАЖИРСКОГО ТРАНСПОРТА";
            } else {
                mileage = rideRepository.getTruckDayMileage(day, sorted);
                transportInfo = "ГРУЗОВОГО ТРАНСПОРТА";
            }
        }
        updateModel(model, transportInfo, mileage, day);
     }

    private void getMileageByMonth(Map<String, Object> model, Integer transport, String category, String month) {
        List<Mileage> mileage;
        String transportInfo;
        if (transport != null) {
            mileage = rideRepository.getTransportMonthMileage(transport, month, sorted);
            transportInfo = "АВТОМАШИНЫ №" + transport;
        } else {
            if (category.equals("пассажирский")) {
                mileage = rideRepository.getPassengerTransportMonthMileage(month, sorted);
                transportInfo = "ПАССАЖИРСКОГО ТРАНСПОРТА";
            } else {
                mileage = rideRepository.getTruckMonthMileage(month, sorted);
                transportInfo = "ГРУЗОВОГО ТРАНСПОРТА";
            }
        }
        updateModel(model, transportInfo, mileage, month);
    }

    private void getMileageByYear(Map<String, Object> model, Integer transport, String category, Integer year) {
        List<Mileage> mileage;
        String transportInfo;
        if (transport != null) {
            mileage = rideRepository.getTransportYearMileage(transport, year, sorted);
            transportInfo = "АВТОМАШИНЫ №" + transport;
        } else {
            if (category.equals("пассажирский")) {
                mileage = rideRepository.getPassengerTransportYearMileage(year, sorted);
                transportInfo = "ПАССАЖИРСКОГО ТРАНСПОРТА";
            } else {
                mileage = rideRepository.getTruckYearMileage(year, sorted);
                transportInfo = "ГРУЗОВОГО ТРАНСПОРТА";
            }
        }
        updateModel(model, transportInfo, mileage, year);
    }

    @GetMapping("/mileage")
    public String getMileageInfo(@RequestParam String category,
                                 @RequestParam(required = false) Integer transport,
                                 @RequestParam String date,
                                 @RequestParam Date day,
                                 @RequestParam String month,
                                 @RequestParam Integer year,
                                 @RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                 Map<String, Object> model) {
        this.sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("id").ascending());
        this.pageNumber = pageNumber;
        switch (date) {
            case "day":
                getMileageByDay(model, transport, category, day);
                break;
            case "month":
                getMileageByMonth(model, transport, category, month);
                break;
            case "year":
                getMileageByYear(model, transport, category, year);
                break;
        }
        return "query/mileage";
    }
}
