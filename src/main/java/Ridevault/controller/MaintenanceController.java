package Ridevault.controller;

import Ridevault.dto.MaintenanceResponse;
import Ridevault.entity.ServiceRecord;
import Ridevault.repository.ServiceRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final ServiceRecordRepository serviceRepository;

    public MaintenanceController(
            ServiceRecordRepository serviceRepository) {

        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/bike/{bikeId}")
    public MaintenanceResponse checkMaintenance(
            @PathVariable Long bikeId) {

        List<ServiceRecord> services =
                serviceRepository.findByBikeId(bikeId);

        // No service history
        if (services.isEmpty()) {

            return new MaintenanceResponse(
                    bikeId,
                    null,
                    0,
                    true,
                    "No service history found. Service recommended."
            );
        }

        // Find latest service
        LocalDate lastServiceDate =
                services.stream()
                        .map(ServiceRecord::getServiceDate)
                        .max(LocalDate::compareTo)
                        .orElse(LocalDate.now());

        long daysSinceService =
                ChronoUnit.DAYS.between(
                        lastServiceDate,
                        LocalDate.now()
                );

        boolean serviceDue =
                daysSinceService >= 180;

        String message;

        if (serviceDue) {
            message = "Service is due. Please service your bike.";
        } else {
            message = "Bike service is not due yet.";
        }

        return new MaintenanceResponse(
                bikeId,
                lastServiceDate,
                daysSinceService,
                serviceDue,
                message
        );
    }
}