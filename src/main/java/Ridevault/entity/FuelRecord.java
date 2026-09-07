package Ridevault.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "fuel_records")
public class FuelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double liters;

    private double cost;

    private double odometer;

    private LocalDate fuelDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bike_id")
    private Bike bike;

    public FuelRecord() {
    }

    public Long getId() {
        return id;
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public LocalDate getFuelDate() {
        return fuelDate;
    }

    public void setFuelDate(LocalDate fuelDate) {
        this.fuelDate = fuelDate;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
