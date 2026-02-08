package com.nfernandes.appointment_system.reservation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
public class ReservationResponse {

    private Long id;
    private Long clientId;
    private Long workerId;
    private Set<Long> serviceIds;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
