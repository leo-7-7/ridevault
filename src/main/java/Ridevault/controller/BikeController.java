package Ridevault.controller;

import Ridevault.entity.Bike;
import Ridevault.service.BikeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    @PostMapping
    public Bike addBike(@Valid @RequestBody Bike bike) {
        return bikeService.addBike(bike);
    }

    @GetMapping("/{id}")
    public Bike getBike(@PathVariable Long id) {
        return bikeService.getBike(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBike(@PathVariable Long id) {
        bikeService.deleteBike(id);
    }
}