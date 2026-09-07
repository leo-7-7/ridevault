package Ridevault.service;

import Ridevault.entity.Bike;
import Ridevault.entity.FuelRecord;
import Ridevault.repository.BikeRepository;
import Ridevault.repository.FuelRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelRecordService {

    private final FuelRecordRepository fuelRepository;
    private final BikeRepository bikeRepository;

    public FuelRecordService(
            FuelRecordRepository fuelRepository,
            BikeRepository bikeRepository) {

        this.fuelRepository = fuelRepository;
        this.bikeRepository = bikeRepository;
    }

    public FuelRecord addFuel(
            Long bikeId,
            FuelRecord fuelRecord) {

        Bike bike = bikeRepository.findById(bikeId)
                .orElseThrow(() ->
                        new RuntimeException("Bike not found"));

        fuelRecord.setBike(bike);

        return fuelRepository.save(fuelRecord);
    }

    public List<FuelRecord> getFuelRecords(Long bikeId) {
        return fuelRepository
                .findByBikeIdOrderByOdometerAsc(bikeId);
    }
}