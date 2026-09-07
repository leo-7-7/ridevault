package Ridevault.dto;

import Ridevault.entity.Bike;
import Ridevault.entity.ServiceRecord;

import java.util.List;

public class DashboardResponse {

    private Bike bike;

    private double mileage;
    private double totalFuelCost;
    private double totalServiceCost;

    private int totalServices;
    private int totalFuelRecords;

    private int healthScore;
    private String healthStatus;

    private boolean serviceDue;
    private String maintenanceMessage;

    private List<ServiceRecord> serviceHistory;

    public DashboardResponse(
            Bike bike,
            double mileage,
            double totalFuelCost,
            double totalServiceCost,
            int totalServices,
            int totalFuelRecords,
            int healthScore,
            String healthStatus,
            boolean serviceDue,
            String maintenanceMessage,
            List<ServiceRecord> serviceHistory) {

        this.bike = bike;
        this.mileage = mileage;
        this.totalFuelCost = totalFuelCost;
        this.totalServiceCost = totalServiceCost;
        this.totalServices = totalServices;
        this.totalFuelRecords = totalFuelRecords;
        this.healthScore = healthScore;
        this.healthStatus = healthStatus;
        this.serviceDue = serviceDue;
        this.maintenanceMessage = maintenanceMessage;
        this.serviceHistory = serviceHistory;
    }

    public Bike getBike() {
        return bike;
    }

    public double getMileage() {
        return mileage;
    }

    public double getTotalFuelCost() {
        return totalFuelCost;
    }

    public double getTotalServiceCost() {
        return totalServiceCost;
    }

    public int getTotalServices() {
        return totalServices;
    }

    public int getTotalFuelRecords() {
        return totalFuelRecords;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public boolean isServiceDue() {
        return serviceDue;
    }

    public String getMaintenanceMessage() {
        return maintenanceMessage;
    }

    public List<ServiceRecord> getServiceHistory() {
        return serviceHistory;
    }
}