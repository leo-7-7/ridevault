package Ridevault.controller;

import Ridevault.entity.Bike;
import Ridevault.entity.FuelRecord;
import Ridevault.service.BikeService;
import Ridevault.service.FuelRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/fuel")
public class FuelHistoryPageController {

    private final FuelRecordService fuelRecordService;
    private final BikeService bikeService;

    public FuelHistoryPageController(
            FuelRecordService fuelRecordService,
            BikeService bikeService) {

        this.fuelRecordService = fuelRecordService;
        this.bikeService = bikeService;
    }

    @GetMapping("/history/{bikeId}")
    public String showFuelHistory(
            @PathVariable Long bikeId,
            Model model) {

        Bike bike = bikeService.getBike(bikeId);

        List<FuelRecord> fuelRecords =
                fuelRecordService.getFuelRecords(bikeId);

        // Show newest fuel record first
        Collections.reverse(fuelRecords);

        model.addAttribute("bike", bike);
        model.addAttribute("fuelRecords", fuelRecords);
        model.addAttribute("bikeId", bikeId);

        return "fuel-history";
    }
}