package com.nfernandes.appointment_system.reservation.mapper;


import com.nfernandes.appointment_system.businessService.BusinessService;
import com.nfernandes.appointment_system.reservation.Reservation;
import com.nfernandes.appointment_system.reservation.dto.ReservationResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    public ReservationResponse toResponse (Reservation reservation){
        ReservationResponse response = new ReservationResponse();

        response.setId(reservation.getId());
        response.setStartTime(reservation.getStartDateTime());
        response.setEndTime(reservation.getEndDateTime());
        response.setClientId(reservation.getClient().getId());
        response.setWorkerId(reservation.getWorker().getId());

        response.setServiceIds(reservation.getServices().stream().map(BusinessService::getId).collect(Collectors.toSet()));

        return response;
    }
}
