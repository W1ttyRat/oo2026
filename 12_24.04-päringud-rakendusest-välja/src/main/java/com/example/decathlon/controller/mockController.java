package com.example.decathlon.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/external")
public class mockController {

    private final RestTemplate restTemplate = new RestTemplate();

    // :Judges
    // :Locations
    private final String MOCKAPI_URL = "https://69fca59230ad0a6fd1bff158.mockapi.io";

    @GetMapping("/judges")
    public Object[] getJudges() {
        String url = MOCKAPI_URL + "/Judges";
        return restTemplate.getForObject(url, Object[].class);
    }

    @GetMapping("/locations")
    public Object[] getLocations() {
        String url = MOCKAPI_URL + "/Locations";
        return restTemplate.getForObject(url, Object[].class);
    }
}
