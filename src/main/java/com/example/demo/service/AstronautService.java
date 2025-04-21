package com.example.demo.service;


import com.example.demo.dto.AstronautRequestDTO;
import com.example.demo.dto.SatelliteDTO;
import com.example.demo.exception.DecommissionedSatelliteException;
import com.example.demo.model.Astronaut;
import com.example.demo.model.Satellite;
import com.example.demo.repository.AstronautRepository;
import com.example.demo.repository.SatelliteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AstronautService {
    private final AstronautRepository astronautRepository;
    private final SatelliteRepository satelliteRepository;

    public AstronautService(AstronautRepository astronautRepository, SatelliteRepository satelliteRepository) {
        this.astronautRepository = astronautRepository;
        this.satelliteRepository = satelliteRepository;
    }

    public Astronaut createAstronaut(AstronautRequestDTO dto) {
        Set<Satellite> satellites = new HashSet<>();

        for (Long id : dto.getSatelliteIds()) {
            Satellite satellite = satelliteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Satellite not found with id: " + id));

            if (satellite.isDecommissioned()) {
                throw new DecommissionedSatelliteException("Satellite " + satellite.getName() + " is decommissioned");
            }
            satellites.add(satellite);
        }

        Astronaut astronaut = new Astronaut();
        astronaut.setFirstName(dto.getFirstName());
        astronaut.setLastName(dto.getLastName());
        astronaut.setExperienceYears(dto.getExperienceYears());
        astronaut.setSatellites(satellites);

        return astronautRepository.save(astronaut);
    }

    public List<Astronaut> getAllSorted(String sortField, String sortOrder) {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return astronautRepository.findAll(Sort.by(direction, sortField));
    }

}
