package Ridevault.dto;

public class HealthResponse {

    private Long bikeId;
    private double mileage;
    private int serviceScore;
    private int maintenanceScore;
    private int mileageScore;
    private int healthScore;
    private String status;

    public HealthResponse(
            Long bikeId,
            double mileage,
            int serviceScore,
            int maintenanceScore,
            int mileageScore,
            int healthScore,
            String status) {

        this.bikeId = bikeId;
        this.mileage = mileage;
        this.serviceScore = serviceScore;
        this.maintenanceScore = maintenanceScore;
        this.mileageScore = mileageScore;
        this.healthScore = healthScore;
        this.status = status;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public double getMileage() {
        return mileage;
    }

    public int getServiceScore() {
        return serviceScore;
    }

    public int getMaintenanceScore() {
        return maintenanceScore;
    }

    public int getMileageScore() {
        return mileageScore;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public String getStatus() {
        return status;
    }
}
