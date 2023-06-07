package com.musala.drone.controller;

import com.musala.drone.dto.request.DroneDto;
import com.musala.drone.dto.request.MedicationDto;
import com.musala.drone.dto.response.DroneResponseDto;
import com.musala.drone.dto.response.MedicationResponseDto;
import com.musala.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/drone")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping
    public ResponseEntity<DroneResponseDto> registerNewDrone(@RequestBody DroneDto droneDto) {
        DroneResponseDto droneResponseDto = this.droneService.registerNewDrone(droneDto);
        return new ResponseEntity<>(droneResponseDto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{droneId}/medication")
    public ResponseEntity<MedicationResponseDto> loadingDroneWithMedications(@PathVariable String droneId, @RequestBody MedicationDto medicationDto) {
        MedicationResponseDto medicationResponse = this.droneService.loadingDroneWithMedications(droneId, medicationDto);
        return new ResponseEntity<>(medicationResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{droneId}/medication")
    public ResponseEntity<List<MedicationResponseDto>> getMedicationsForADrone(@PathVariable String droneId) {
        List<MedicationResponseDto> medicationResponses = this.droneService.getMedicationsForADrone(droneId);
        return new ResponseEntity<>(medicationResponses, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DroneResponseDto>> getDronesAProperty(@RequestParam Map<String, String> reqParam) {
        List<DroneResponseDto> droneResponseList = this.droneService.getDronesByAProperty(reqParam);
        return new ResponseEntity<>(droneResponseList, HttpStatus.OK);
    }

}
