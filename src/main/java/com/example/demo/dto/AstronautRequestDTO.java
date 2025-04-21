package com.example.demo.dto;

import com.example.demo.model.Satellite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AstronautRequestDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public List<Long> getSatelliteIds() {
        return satelliteIds;
    }

    public void setSatelliteIds(List<Long> satelliteIds) {
        this.satelliteIds = satelliteIds;
    }

    @Max(50)
    private Integer experienceYears;

    @NotNull(message = "Satellites list cannot be null")
    @NotEmpty
    private List<Long> satelliteIds;

}
