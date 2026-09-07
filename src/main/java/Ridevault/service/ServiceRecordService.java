package Ridevault.service;

import Ridevault.entity.Bike;
import Ridevault.entity.ServiceRecord;
import Ridevault.repository.BikeRepository;
import Ridevault.repository.ServiceRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRepository;
    private final BikeRepository bikeRepository;

    public ServiceRecordService(
            ServiceRecordRepository serviceRepository,
            BikeRepository bikeRepository) {

        this.serviceRepository = serviceRepository;
        this.bikeRepository = bikeRepository;
    }

    public ServiceRecord addService(
            Long bikeId,
            ServiceRecord serviceRecord) {

        Bike bike = bikeRepository.findById(bikeId)
                .orElseThrow(() ->
                        new RuntimeException("Bike not found"));

        serviceRecord.setBike(bike);

        return serviceRepository.save(serviceRecord);
    }

    public List<ServiceRecord> getServices(Long bikeId) {
        return serviceRepository.findByBikeId(bikeId);
    }
}