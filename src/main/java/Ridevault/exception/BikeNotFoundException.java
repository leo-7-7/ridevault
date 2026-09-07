package Ridevault.exception;

public class BikeNotFoundException extends RuntimeException {

    public BikeNotFoundException(Long bikeId) {
        super("Bike with ID " + bikeId + " not found");
    }
}