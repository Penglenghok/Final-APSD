package com.example.demo.controller;

import com.example.demo.dto.SatelliteDTO;
import com.example.demo.model.Satellite;
import com.example.demo.service.SatelliteService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/satellites")
public class SatelliteController {

    private final SatelliteService service;

    public SatelliteController(SatelliteService service) {
        this.service = service;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSatellite(
            @PathVariable Long id,
            @RequestBody @Valid SatelliteDTO dto) {
        try {
            Satellite updated = this.service.updateSatellite(id, dto);
            return ResponseEntity.ok(updated);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }


}
