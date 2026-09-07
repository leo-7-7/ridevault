package Ridevault.repository;

import Ridevault.entity.FuelRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuelRecordRepository
        extends JpaRepository<FuelRecord, Long> {

    List<FuelRecord> findByBikeIdOrderByOdometerAsc(Long bikeId);
    List<FuelRecord> findByBikeId(Long bikeId);
}
