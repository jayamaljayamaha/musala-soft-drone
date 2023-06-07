package com.musala.drone.service;

import com.musala.drone.common.enums.DroneState;
import com.musala.drone.dto.request.DroneDto;
import com.musala.drone.dto.request.MedicationDto;
import com.musala.drone.dto.response.DroneResponseDto;
import com.musala.drone.dto.response.MedicationResponseDto;
import com.musala.drone.entity.Drone;
import com.musala.drone.entity.Medication;
import com.musala.drone.exception.DataInvalidException;
import com.musala.drone.exception.DroneConstraintViolationException;
import com.musala.drone.repository.DroneRepository;
import com.musala.drone.repository.MedicationRepository;
import com.musala.drone.util.ValidationUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class DroneService {

    @Autowired
    private Validator validator;

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    public DroneResponseDto registerNewDrone(DroneDto droneDto) {
        Set<ConstraintViolation<DroneDto>> violations = validator.validate(droneDto);
        if(violations.isEmpty()) {
            ValidationUtils.validateModel(droneDto.getModel());
            ValidationUtils.validateDroneState(droneDto.getState());
            Drone drone = Drone.builder().serialNumber(droneDto.getSerialNumber())
                    .model(droneDto.getModel())
                    .state(droneDto.getState())
                    .batteryCapacity(droneDto.getBatteryCapacity())
                    .weightCapacity(droneDto.getWeightLimit())
                    .build();
            Drone savedDrone = droneRepository.save(drone);
            return DroneResponseDto.builder().drone(savedDrone).isSuccess(true).build();
        } else  {
            List<ConstraintViolation<DroneDto>> violationList = violations.stream().toList();
            throw new DataInvalidException(violationList.get(0).getMessage(), violationList.get(0).getPropertyPath().toString());
        }
    }

    public MedicationResponseDto loadingDroneWithMedications(String droneId, MedicationDto medicationDto) {
        Set<ConstraintViolation<MedicationDto>> violations = validator.validate(medicationDto);
        if(violations.isEmpty()) {
            Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new DataInvalidException("No drone exist for the droneId", "drone_id"));
            List<Integer> existingWeights = this.medicationRepository.getAllWeightsByDroneSerialNumber(droneId);
            ValidationUtils.validateWeightOfMedication(drone.getWeightCapacity(),
                    existingWeights.stream().mapToInt(Integer::intValue).sum(), medicationDto.getWeight());
            ValidationUtils.validateDroneState(DroneState.valueOf(drone.getState()), List.of(DroneState.IDLE, DroneState.LOADED));
            drone = saveDroneState(drone, DroneState.LOADING);
            Medication medication = Medication.builder()
                    .code(medicationDto.getCode())
                    .name(medicationDto.getName())
                    .weight(medicationDto.getWeight())
                    .image(medicationDto.getImage())
                    .drone(drone)
                    .build();
            Medication loadedMedication = this.medicationRepository.save(medication);
            saveDroneState(drone, DroneState.LOADED);
            return MedicationResponseDto.builder().medication(loadedMedication).isSuccess(true).build();
        } else  {
            List<ConstraintViolation<MedicationDto>> violationList = violations.stream().toList();
            throw new DataInvalidException(violationList.get(0).getMessage(), violationList.get(0).getPropertyPath().toString());
        }
    }

    private Drone saveDroneState(Drone drone, DroneState droneState){
        drone.setState(droneState.name());
        return droneRepository.save(drone);
    }

    public List<MedicationResponseDto> getMedicationsForADrone(String droneId) {
        List<Medication> existingWeights = this.medicationRepository.getAllByDroneSerialNumber(droneId);
        return existingWeights.stream().map(medication -> MedicationResponseDto.builder().isSuccess(true).medication(medication).build()).toList();
    }

    public List<DroneResponseDto> getDronesByAProperty(Map<String, String> reqParam) {
        if(reqParam.containsKey("availableFor")){
            return this.getDronesByAvailableFor(reqParam.get("availableFor"));
        } else if(reqParam.containsKey("batteryCapacity")) {
            return List.of(this.getDronesByBatteryLevel("batteryCapacity"));
        }
        return List.of();
    }

    private List<DroneResponseDto> getDronesByAvailableFor(String availableFor) {
        Set<Drone> dronesAvailableForLoading = this.droneRepository.getDronesByStatuses(Set.of(DroneState.IDLE.name(), DroneState.LOADED.name()));
        return dronesAvailableForLoading.stream().map(drone -> DroneResponseDto.builder().drone(drone).isSuccess(true).build()).toList();
    }

    private DroneResponseDto getDronesByBatteryLevel(String batteryCapacity) {
        try {
            Integer capacity = Integer.parseInt(batteryCapacity);
            Optional<Drone> droneOpt = this.droneRepository.findDroneByBatteryCapacity(capacity);
            return droneOpt.isPresent() ? DroneResponseDto.builder().drone(droneOpt.get()).isSuccess(true).build() : DroneResponseDto.builder().build();
        } catch (NumberFormatException exception) {
            throw new DroneConstraintViolationException(exception.getMessage(), "batteryCapacity");
        }
    }

}
