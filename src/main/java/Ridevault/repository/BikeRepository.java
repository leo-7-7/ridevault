package Ridevault.repository;

import Ridevault.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {

}