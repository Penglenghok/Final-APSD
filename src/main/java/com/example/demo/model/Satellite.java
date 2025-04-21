package com.example.demo.model;


import com.example.demo.enums.OrbitType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Satellite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public OrbitType getOrbitType() {
        return orbitType;
    }

    public void setOrbitType(OrbitType orbitType) {
        this.orbitType = orbitType;
    }

    public boolean isDecommissioned() {
        return decommissioned;
    }

    public void setDecommissioned(boolean decommissioned) {
        this.decommissioned = decommissioned;
    }

    public Set<Astronaut> getAstronauts() {
        return astronauts;
    }

    public void setAstronauts(Set<Astronaut> astronauts) {
        this.astronauts = astronauts;
    }

    @NotNull
    private LocalDate launchDate;

    @Enumerated(EnumType.STRING)
    private OrbitType orbitType;

    private boolean decommissioned;

    @ManyToMany(mappedBy = "satellites")
    @JsonBackReference
    private Set<Astronaut> astronauts = new HashSet<>();

}
