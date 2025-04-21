package com.example.demo.service;

import com.example.demo.dto.SatelliteDTO;
import com.example.demo.model.Satellite;
import com.example.demo.repository.SatelliteRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatelliteService {

    private final SatelliteRepository repo;

    public SatelliteService(SatelliteRepository repo) {
        this.repo = repo;
    }

    public List<Satellite> getSattelites(List<Long> ids){
        return  this.repo.findAllById(ids);
    }

    public SatelliteRepository getRepo() {
        return repo;
    }

    public Satellite updateSatellite(Long id, SatelliteDTO dto) throws BadRequestException {
        Satellite satellite = this.repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Satellite not found with id: " + id));
        if (satellite.isDecommissioned()) {
            throw new BadRequestException("Cannot update a decommissioned satellite.");
        }
        satellite.setName(dto.getName());
        satellite.setLaunchDate(dto.getLaunchDate());
        satellite.setOrbitType(dto.getOrbitType());
        satellite.setDecommissioned(dto.isDecommissioned());
        return this.repo.save(satellite);
    }
}
