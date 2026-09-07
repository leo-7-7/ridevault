package Ridevault.dto;

import java.time.LocalDate;

public class MaintenanceResponse {

    private Long bikeId;
    private LocalDate lastServiceDate;
    private long daysSinceService;
    private boolean serviceDue;
    private String message;

    public MaintenanceResponse(
            Long bikeId,
            LocalDate lastServiceDate,
            long daysSinceService,
            boolean serviceDue,
            String message) {

        this.bikeId = bikeId;
        this.lastServiceDate = lastServiceDate;
        this.daysSinceService = daysSinceService;
        this.serviceDue = serviceDue;
        this.message = message;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public LocalDate getLastServiceDate() {
        return lastServiceDate;
    }

    public long getDaysSinceService() {
        return daysSinceService;
    }

    public boolean isServiceDue() {
        return serviceDue;
    }

    public String getMessage() {
        return message;
    }
}