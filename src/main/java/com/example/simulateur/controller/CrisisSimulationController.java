package com.example.simulateur.controller;

import com.example.simulateur.entity.CompteRendu;
import com.example.simulateur.service.CrisisSimulationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crisis")
public class CrisisSimulationController {

    private final CrisisSimulationService crisisSimulationService;

    public CrisisSimulationController(CrisisSimulationService crisisSimulationService) {
        this.crisisSimulationService = crisisSimulationService;
    }

    @PostMapping("/simulate")
    public CompteRendu simulateCrisis(@RequestBody String prompt, @RequestParam Long userId) {
        return crisisSimulationService.simulateCrisis(prompt, userId);
    }
}
