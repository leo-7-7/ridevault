package Ridevault.controller;

import Ridevault.dto.HealthResponse;
import Ridevault.entity.FuelRecord;
import Ridevault.entity.ServiceRecord;
import Ridevault.repository.FuelRecordRepository;
import Ridevault.repository.ServiceRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    private final FuelRecordRepository fuelRepository;
    private final ServiceRecordRepository serviceRepository;

    public HealthController(
            FuelRecordRepository fuelRepository,
            ServiceRecordRepository serviceRepository) {

        this.fuelRepository = fuelRepository;
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/bike/{bikeId}")
    public HealthResponse getHealthScore(
            @PathVariable Long bikeId) {

        List<FuelRecord> fuelRecords =
                fuelRepository.findByBikeIdOrderByOdometerAsc(bikeId);

        List<ServiceRecord> services =
                serviceRepository.findByBikeId(bikeId);

        // -------------------------
        // 1. Mileage Score
        // -------------------------

        double mileage = 0;

        if (fuelRecords.size() >= 2) {

            FuelRecord previous =
                    fuelRecords.get(fuelRecords.size() - 2);

            FuelRecord current =
                    fuelRecords.get(fuelRecords.size() - 1);

            double distance =
                    current.getOdometer() - previous.getOdometer();

            mileage = distance / current.getLiters();
        }

        int mileageScore;

        if (mileage >= 40) {
            mileageScore = 40;
        } else if (mileage >= 30) {
            mileageScore = 30;
        } else if (mileage >= 20) {
            mileageScore = 20;
        } else {
            mileageScore = 10;
        }

        // -------------------------
        // 2. Service Score
        // -------------------------

        int serviceScore;

        if (services.size() >= 3) {
            serviceScore = 30;
        } else if (services.size() == 2) {
            serviceScore = 20;
        } else if (services.size() == 1) {
            serviceScore = 10;
        } else {
            serviceScore = 0;
        }

        // -------------------------
        // 3. Maintenance Score
        // -------------------------

        int maintenanceScore = 0;

        if (!services.isEmpty()) {

            LocalDate lastService =
                    services.stream()
                            .map(ServiceRecord::getServiceDate)
                            .max(LocalDate::compareTo)
                            .orElse(LocalDate.now());

            long days =
                    ChronoUnit.DAYS.between(
                            lastService,
                            LocalDate.now());

            if (days <= 90) {
                maintenanceScore = 30;
            } else if (days <= 180) {
                maintenanceScore = 20;
            } else {
                maintenanceScore = 10;
            }
        }

        // -------------------------
        // 4. Final Health Score
        // -------------------------

        int healthScore =
                mileageScore +
                        serviceScore +
                        maintenanceScore;

        String status;

        if (healthScore >= 80) {
            status = "EXCELLENT";
        } else if (healthScore >= 60) {
            status = "GOOD";
        } else if (healthScore >= 40) {
            status = "NEEDS_ATTENTION";
        } else {
            status = "POOR";
        }

        return new HealthResponse(
                bikeId,
                mileage,
                serviceScore,
                maintenanceScore,
                mileageScore,
                healthScore,
                status
        );
    }
}
