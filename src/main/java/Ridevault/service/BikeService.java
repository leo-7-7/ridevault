package Ridevault.service;

import Ridevault.exception.BikeNotFoundException;
import Ridevault.entity.Bike;
import Ridevault.repository.BikeRepository;
import Ridevault.repository.FuelRecordRepository;
import Ridevault.repository.ServiceRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;
    private final FuelRecordRepository fuelRecordRepository;
    private final ServiceRecordRepository serviceRecordRepository;

    public BikeService(
            BikeRepository bikeRepository,
            FuelRecordRepository fuelRecordRepository,
            ServiceRecordRepository serviceRecordRepository) {

        this.bikeRepository = bikeRepository;
        this.fuelRecordRepository = fuelRecordRepository;
        this.serviceRecordRepository = serviceRecordRepository;
    }

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public Bike addBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public Bike getBike(Long id) {
        return bikeRepository.findById(id)
                .orElseThrow(() -> new BikeNotFoundException(id));
    }

    public void deleteBike(Long id) {

        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new BikeNotFoundException(id));

        // Delete all service records belonging to this bike
        serviceRecordRepository.deleteAll(
                serviceRecordRepository.findByBikeId(id)
        );

        // Delete all fuel records belonging to this bike
        fuelRecordRepository.deleteAll(
                fuelRecordRepository.findByBikeId(id)
        );

        // Finally delete the bike
        bikeRepository.delete(bike);
    }
}