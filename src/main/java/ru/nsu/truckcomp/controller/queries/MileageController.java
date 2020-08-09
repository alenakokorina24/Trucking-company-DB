package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.repository.RideRepository;

import java.sql.Date;
import java.util.Map;

@Controller
public class MileageController {
    @Autowired
    private RideRepository rideRepository;

    private void getMileageByDay(Map<String, Object> model, Integer transport, String category, Date day) {
        if (transport != null) {
            model.put("mileage", rideRepository.getTransportDayMileage(transport, day));
        } else if (category != null) {
            if (category.equals("пассажирский")) {
                model.put("mileage", rideRepository.getPassengerTransportDayMileage(day));
            } else {
                model.put("mileage", rideRepository.getTruckDayMileage(day));
            }
        }
     }

    private void getMileageByMonth(Map<String, Object> model, Integer transport, String category, String month) {
        if (transport != null) {
            model.put("mileage", rideRepository.getTransportMonthMileage(transport, month));
        } else if (category != null) {
            if (category.equals("пассажирский")) {
                model.put("mileage", rideRepository.getPassengerTransportMonthMileage(month));
            } else {
                model.put("mileage", rideRepository.getTruckMonthMileage(month));
            }
        }
    }

    private void getMileageByYear(Map<String, Object> model, Integer transport, String category, Integer year) {
        if (transport != null) {
            model.put("mileage", rideRepository.getTransportYearMileage(transport, year));
        } else if (category != null) {
            if (category.equals("пассажирский")) {
                model.put("mileage", rideRepository.getPassengerTransportYearMileage(year));
            } else {
                model.put("mileage", rideRepository.getTruckYearMileage(year));
            }
        }
    }

    @GetMapping("/mileage")
    public String getMileageInfo(@RequestParam(required = false) String category,
                                 @RequestParam(required = false) Integer transport,
                                 @RequestParam Boolean dayChecked,
                                 @RequestParam Boolean monthChecked,
                                 @RequestParam Boolean yearChecked,
                                 @RequestParam Date day,
                                 @RequestParam String month,
                                 @RequestParam Integer year,
                                 Map<String, Object> model) {
        if (dayChecked) {
            getMileageByDay(model, transport, category, day);
        } else if (monthChecked) {
            getMileageByMonth(model, transport, category, month);
        } else if (yearChecked) {
            getMileageByYear(model, transport, category, year);
        }
        return "query/mileage";
    }
}
