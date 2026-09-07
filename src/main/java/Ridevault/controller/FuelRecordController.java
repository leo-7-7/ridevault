package Ridevault.controller;

import Ridevault.entity.FuelRecord;
import Ridevault.service.FuelRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuel")
public class FuelRecordController {

    private final FuelRecordService fuelService;

    public FuelRecordController(
            FuelRecordService fuelService) {

        this.fuelService = fuelService;
    }

    @PostMapping("/bike/{bikeId}")
    public FuelRecord addFuel(
            @PathVariable Long bikeId,
            @RequestBody FuelRecord fuelRecord) {

        return fuelService.addFuel(bikeId, fuelRecord);
    }

    @GetMapping("/bike/{bikeId}")
    public List<FuelRecord> getFuelRecords(
            @PathVariable Long bikeId) {

        return fuelService.getFuelRecords(bikeId);
    }
}