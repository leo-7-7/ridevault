package Ridevault.controller;

import Ridevault.entity.ServiceRecord;
import Ridevault.service.ServiceRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServicePageController {

    private final ServiceRecordService serviceRecordService;

    public ServicePageController(ServiceRecordService serviceRecordService) {
        this.serviceRecordService = serviceRecordService;
    }

    @GetMapping("/add/{bikeId}")
    public String showAddServicePage(
            @PathVariable Long bikeId,
            Model model) {

        model.addAttribute("serviceRecord", new ServiceRecord());
        model.addAttribute("bikeId", bikeId);

        return "add-service";
    }

    @PostMapping("/add/{bikeId}")
    public String addServiceRecord(
            @PathVariable Long bikeId,
            @ModelAttribute("serviceRecord") ServiceRecord serviceRecord) {

        serviceRecordService.addService(bikeId, serviceRecord);

        return "redirect:/dashboard/" + bikeId;
    }
}
