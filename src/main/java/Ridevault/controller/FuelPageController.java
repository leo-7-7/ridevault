package Ridevault.controller;

import Ridevault.entity.FuelRecord;
import Ridevault.service.FuelRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fuel")
public class FuelPageController {

    private final FuelRecordService fuelRecordService;

    public FuelPageController(FuelRecordService fuelRecordService) {
        this.fuelRecordService = fuelRecordService;
    }

    @GetMapping("/add/{bikeId}")
    public String showAddFuelPage(
            @PathVariable Long bikeId,
            Model model) {

        model.addAttribute("fuelRecord", new FuelRecord());
        model.addAttribute("bikeId", bikeId);

        return "add-fuel";
    }

    @PostMapping("/add/{bikeId}")
    public String addFuelRecord(
            @PathVariable Long bikeId,
            @ModelAttribute("fuelRecord") FuelRecord fuelRecord) {

        fuelRecordService.addFuel(bikeId, fuelRecord);

        return "redirect:/dashboard/" + bikeId;
    }
}