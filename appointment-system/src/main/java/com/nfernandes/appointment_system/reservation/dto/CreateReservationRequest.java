package com.nfernandes.appointment_system.reservation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
public class CreateReservationRequest {

    @NotNull
    private Long ClientId;

    @NotNull
    private Long workerId;

    @NotEmpty
    private Set<Long> serviceIds;

    @NotNull
    private LocalDateTime startTime;
}
