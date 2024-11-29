package com.example.simulateur.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OllamaService {

    @Value("${ollama.api.url}")
    private String ollamaApiUrl;

    private final RestTemplate restTemplate;

    public OllamaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateScenario(String prompt) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("prompt", prompt);

        return restTemplate.postForObject(ollamaApiUrl, requestBody, String.class);
    }
}
