package Ridevault.controller;

import Ridevault.entity.ServiceRecord;
import Ridevault.service.ServiceRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceRecordController {

    private final ServiceRecordService service;

    public ServiceRecordController(
            ServiceRecordService service) {

        this.service = service;
    }

    @PostMapping("/bike/{bikeId}")
    public ServiceRecord addService(
            @PathVariable Long bikeId,
            @RequestBody ServiceRecord serviceRecord) {

        return service.addService(bikeId, serviceRecord);
    }

    @GetMapping("/bike/{bikeId}")
    public List<ServiceRecord> getServices(
            @PathVariable Long bikeId) {

        return service.getServices(bikeId);
    }
}