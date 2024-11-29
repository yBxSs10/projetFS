package com.example.simulateur.controller;

import com.example.simulateur.service.CrisisSimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/crisis-simulation")
@RequiredArgsConstructor
public class ollamacontroller {

    private final CrisisSimulationService crisisSimulationService;
    private final OllamaChatModel chatModel;

    @PostMapping(path = "/simulate")
    public String simulateCrisis(@RequestParam String scenario) {
        // Lire le prompt spécifique à la gestion de crise
        String prompt = crisisSimulationService.readCrisisScenario(scenario);

        // Créer les messages pour l'IA
        List<Message> messages = new ArrayList<>();
        messages.add(new SystemMessage("<start_of_turn>" + prompt + "<end_of_turn>"));
        messages.add(new UserMessage("<start_of_turn>" + scenario + "<end_of_turn>"));

        // Créer le prompt à envoyer à Ollama
        Prompt promptToSend = new Prompt(messages);

        // Lancer l'appel à Ollama pour obtenir une réponse
        Flux<ChatResponse> chatResponses = chatModel.stream(promptToSend);

        // Récupérer et retourner la réponse de l'IA
        String message = Objects.requireNonNull(chatResponses.collectList().block()).stream()
                .map(response -> response.getResult().getOutput().getContent())
                .collect(Collectors.joining());

        return message;
    }
}
