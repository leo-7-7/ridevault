package Ridevault.repository;

import Ridevault.entity.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRecordRepository
        extends JpaRepository<ServiceRecord, Long> {

    List<ServiceRecord> findByBikeId(Long bikeId);

    List<ServiceRecord> findByBikeIdOrderByServiceDateDesc(Long bikeId);
}