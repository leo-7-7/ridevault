package Ridevault.controller;

import Ridevault.dto.DashboardResponse;
import Ridevault.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/bike/{bikeId}")
    public DashboardResponse getDashboard(
            @PathVariable Long bikeId) {

        return dashboardService.getDashboard(bikeId);
    }
}