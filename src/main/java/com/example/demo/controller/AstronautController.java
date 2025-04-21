package com.example.demo.controller;

import com.example.demo.dto.AstronautRequestDTO;
import com.example.demo.model.Astronaut;
import com.example.demo.model.Satellite;
import com.example.demo.service.AstronautService;
import com.example.demo.service.SatelliteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/astronaut")
public class AstronautController {
    public AstronautController(AstronautService astronautService) {
        this.astronautService = astronautService;
    }

    private final AstronautService astronautService;

    @PostMapping
    public ResponseEntity<Astronaut> createAstronaut(@RequestBody @Valid AstronautRequestDTO dto) {
        Astronaut created = astronautService.createAstronaut(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Astronaut>> getAstronautsSorted(
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order
    ) {
        List<Astronaut> astronauts = astronautService.getAllSorted(sort, order);
        return ResponseEntity.ok(astronauts);
    }

}
