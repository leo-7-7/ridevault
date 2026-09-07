package Ridevault.service;

import Ridevault.dto.DashboardResponse;
import Ridevault.entity.Bike;
import Ridevault.entity.FuelRecord;
import Ridevault.entity.ServiceRecord;
import Ridevault.repository.BikeRepository;
import Ridevault.repository.FuelRecordRepository;
import Ridevault.repository.ServiceRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DashboardService {

    private final BikeRepository bikeRepository;
    private final FuelRecordRepository fuelRecordRepository;
    private final ServiceRecordRepository serviceRecordRepository;

    public DashboardService(
            BikeRepository bikeRepository,
            FuelRecordRepository fuelRecordRepository,
            ServiceRecordRepository serviceRecordRepository) {

        this.bikeRepository = bikeRepository;
        this.fuelRecordRepository = fuelRecordRepository;
        this.serviceRecordRepository = serviceRecordRepository;
    }

    public DashboardResponse getDashboard(Long bikeId) {

        // 1. Get bike
        Bike bike = bikeRepository.findById(bikeId)
                .orElseThrow(() ->
                        new RuntimeException("Bike not found"));

        // 2. Get fuel records
        List<FuelRecord> fuelRecords =
                fuelRecordRepository.findByBikeIdOrderByOdometerAsc(bikeId);

        // 3. Get service records
        List<ServiceRecord> serviceRecords =
                serviceRecordRepository.findByBikeIdOrderByServiceDateDesc(bikeId);

        // 4. Calculate mileage
        double mileage = 0;

        if (fuelRecords.size() >= 2) {

            FuelRecord previous =
                    fuelRecords.get(fuelRecords.size() - 2);

            FuelRecord current =
                    fuelRecords.get(fuelRecords.size() - 1);

            double distance =
                    current.getOdometer() - previous.getOdometer();

            if (current.getLiters() > 0) {
                mileage = distance / current.getLiters();
            }
        }

        // 5. Calculate total fuel cost
        double totalFuelCost = fuelRecords.stream()
                .mapToDouble(FuelRecord::getCost)
                .sum();

        // 6. Calculate total service cost
        double totalServiceCost = serviceRecords.stream()
                .mapToDouble(ServiceRecord::getCost)
                .sum();

        // 7. Total records
        int totalServices = serviceRecords.size();
        int totalFuelRecords = fuelRecords.size();

        // 8. Calculate health score
        int healthScore = 0;

        // Mileage score
        if (mileage >= 40) {
            healthScore += 40;
        } else if (mileage >= 30) {
            healthScore += 30;
        } else if (mileage >= 20) {
            healthScore += 20;
        } else {
            healthScore += 10;
        }

        // Service frequency score
        if (totalServices >= 3) {
            healthScore += 30;
        } else if (totalServices == 2) {
            healthScore += 20;
        } else if (totalServices == 1) {
            healthScore += 10;
        }

        // Maintenance score
        int maintenanceScore = 0;

        if (!serviceRecords.isEmpty()) {

            ServiceRecord latestService =
                    serviceRecords.get(0);

            LocalDate serviceDate =
                    latestService.getServiceDate();

            if (serviceDate != null) {

                long daysSinceService =
                        ChronoUnit.DAYS.between(
                                serviceDate,
                                LocalDate.now());

                if (daysSinceService <= 90) {
                    maintenanceScore = 30;
                } else if (daysSinceService <= 180) {
                    maintenanceScore = 20;
                } else {
                    maintenanceScore = 10;
                }
            }

        } else {
            maintenanceScore = 0;
        }

        healthScore += maintenanceScore;

        // 9. Health status
        String healthStatus;

        if (healthScore >= 80) {
            healthStatus = "EXCELLENT";
        } else if (healthScore >= 60) {
            healthStatus = "GOOD";
        } else if (healthScore >= 40) {
            healthStatus = "NEEDS_ATTENTION";
        } else {
            healthStatus = "POOR";
        }

        // 10. Service due
        boolean serviceDue = false;
        String maintenanceMessage;

        if (serviceRecords.isEmpty()) {

            serviceDue = true;
            maintenanceMessage =
                    "No service records found. Schedule a service.";

        } else {

            ServiceRecord latestService =
                    serviceRecords.get(0);

            LocalDate serviceDate =
                    latestService.getServiceDate();

            if (serviceDate != null) {

                long daysSinceService =
                        ChronoUnit.DAYS.between(
                                serviceDate,
                                LocalDate.now());

                if (daysSinceService >= 180) {

                    serviceDue = true;

                    maintenanceMessage =
                            "Service is due. Last service was "
                                    + daysSinceService
                                    + " days ago.";

                } else if (daysSinceService >= 90) {

                    maintenanceMessage =
                            "Service recommended soon. Last service was "
                                    + daysSinceService
                                    + " days ago.";

                } else {

                    maintenanceMessage =
                            "Maintenance is up to date.";
                }

            } else {

                maintenanceMessage =
                        "Service date is not available.";
            }
        }

        // 11. Return dashboard data
        return new DashboardResponse(
                bike,
                mileage,
                totalFuelCost,
                totalServiceCost,
                totalServices,
                totalFuelRecords,
                healthScore,
                healthStatus,
                serviceDue,
                maintenanceMessage,
                serviceRecords
        );
    }
}