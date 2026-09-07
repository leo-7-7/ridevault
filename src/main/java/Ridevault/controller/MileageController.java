package Ridevault.controller;
import Ridevault.dto.MileageResponse;
import Ridevault.entity.FuelRecord;
import Ridevault.repository.FuelRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mileage")
public class MileageController {

    private final FuelRecordRepository fuelRepository;

    public MileageController(FuelRecordRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    @GetMapping("/bike/{bikeId}")
    public MileageResponse calculateMileage(@PathVariable Long bikeId) {

        List<FuelRecord> records =
                fuelRepository.findByBikeIdOrderByOdometerAsc(bikeId);

        if (records.size() < 2) {
            throw new RuntimeException(
                    "At least two fuel records are required"
            );
        }

        FuelRecord previous = records.get(records.size() - 2);
        FuelRecord current = records.get(records.size() - 1);

        double distance =
                current.getOdometer() - previous.getOdometer();

        double fuelUsed = current.getLiters();

        double mileage = distance / fuelUsed;

        return new MileageResponse(
                bikeId,
                distance,
                fuelUsed,
                mileage,
                "km/L"
        );
    }
}