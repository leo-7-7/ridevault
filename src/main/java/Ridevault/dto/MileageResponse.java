package Ridevault.dto;

public class MileageResponse {

    private Long bikeId;
    private double distance;
    private double fuelUsed;
    private double mileage;
    private String unit;

    public MileageResponse(
            Long bikeId,
            double distance,
            double fuelUsed,
            double mileage,
            String unit) {

        this.bikeId = bikeId;
        this.distance = distance;
        this.fuelUsed = fuelUsed;
        this.mileage = mileage;
        this.unit = unit;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public double getDistance() {
        return distance;
    }

    public double getFuelUsed() {
        return fuelUsed;
    }

    public double getMileage() {
        return mileage;
    }

    public String getUnit() {
        return unit;
    }
}