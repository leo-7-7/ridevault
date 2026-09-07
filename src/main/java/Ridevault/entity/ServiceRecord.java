package Ridevault.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "service_records")
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceType;

    private double cost;

    private double odometer;

    private LocalDate serviceDate;

    @ManyToOne
    @JoinColumn(name = "bike_id")
    private Bike bike;

    public ServiceRecord() {
    }

    public Long getId() {
        return id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
