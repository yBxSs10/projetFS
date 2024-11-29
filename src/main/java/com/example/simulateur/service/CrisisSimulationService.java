package com.example.simulateur.service;

import com.example.simulateur.entity.CompteRendu;
import com.example.simulateur.repository.CompteRenduRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CrisisSimulationService {

    private final OllamaService ollamaService;
    private final CompteRenduRepository compteRenduRepository;

    public CrisisSimulationService(OllamaService ollamaService, CompteRenduRepository compteRenduRepository) {
        this.ollamaService = ollamaService;
        this.compteRenduRepository = compteRenduRepository;
    }

    public CompteRendu simulateCrisis(String prompt, Long userId) {
        String scenario = ollamaService.generateScenario(prompt);

        CompteRendu compteRendu = new CompteRendu();
        compteRendu.setContenu(scenario);
        compteRendu.setDateCreation(LocalDateTime.now());
        compteRendu.setCitoyenId(userId);

        return compteRenduRepository.save(compteRendu);
    }

    public String readCrisisScenario(String scenario) {
        // Implémente la logique pour lire le scénario de crise
        return "Contenu du scénario de crise pour : " + scenario;
    }

}


