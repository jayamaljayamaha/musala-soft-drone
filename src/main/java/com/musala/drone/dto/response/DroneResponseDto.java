package com.musala.drone.dto.response;

import com.musala.drone.entity.Drone;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DroneResponseDto {
    private boolean isSuccess;
    private Drone drone;
}
