package com.musala.drone.util;

import com.musala.drone.common.enums.DroneModel;
import com.musala.drone.common.enums.DroneState;
import com.musala.drone.exception.DataInvalidException;
import com.musala.drone.exception.DroneConstraintViolationException;
import lombok.experimental.UtilityClass;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ValidationUtils {

    public void validateModel(String model){
        if(Arrays.stream(DroneModel.values()).map(DroneModel::getValue).noneMatch(value -> value.equals(model))) {
            throw new DataInvalidException("Drone model value is not acceptable", "drone_model");
        }
    }

    public void validateDroneState(String state){
        if(Arrays.stream(DroneState.values()).map(DroneState::name).noneMatch(value -> value.equals(state))) {
            throw new DataInvalidException("Drone state value is not acceptable", "drone_state");
        }
    }

    public void validateDroneState(DroneState droneState, List<DroneState> requiredStates){
        if(!requiredStates.contains(droneState)) {
            throw new DroneConstraintViolationException(MessageFormat.format("Drone is not in {0} state", requiredStates.toString()), "state");
        }
    }

    public void validateWeightOfMedication(Integer droneMaxWeight, Integer droneExistingWeight, Integer medicationWeight){
        if(droneExistingWeight + medicationWeight > droneMaxWeight) {
            throw new DroneConstraintViolationException("Drone max weight will overflow", "weight");
        }
    }
}
