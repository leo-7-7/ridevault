package Ridevault.controller;

import Ridevault.dto.DashboardResponse;
import Ridevault.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardPageController {

    private final DashboardService dashboardService;

    public DashboardPageController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{bikeId}")
    public String dashboard(
            @PathVariable Long bikeId,
            Model model) {

        DashboardResponse dashboard =
                dashboardService.getDashboard(bikeId);

        model.addAttribute("dashboard", dashboard);

        return "dashboard";
    }
}