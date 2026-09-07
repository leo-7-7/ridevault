package Ridevault.controller;

import Ridevault.entity.Bike;
import Ridevault.service.BikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bikes")
public class BikePageController {

    private final BikeService bikeService;

    public BikePageController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping
    public String showBikes(Model model) {
        model.addAttribute("bikes", bikeService.getAllBikes());
        return "bikes";
    }

    @GetMapping("/add")
    public String showAddBikeForm(Model model) {
        model.addAttribute("bike", new Bike());
        return "add-bike";
    }

    @PostMapping("/add")
    public String addBike(@ModelAttribute("bike") Bike bike) {
        bikeService.addBike(bike);
        return "redirect:/bikes";
    }
}