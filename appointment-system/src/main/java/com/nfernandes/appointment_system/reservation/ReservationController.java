package com.nfernandes.appointment_system.reservation;


import com.nfernandes.appointment_system.reservation.dto.CreateReservationRequest;
import com.nfernandes.appointment_system.reservation.dto.ReservationResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody @Valid CreateReservationRequest request
            ){
        Reservation reservation = reservationService.createReservation(request.getClientId(), request.getWorkerId(), request.getServiceIds(),request.getStartTime());

        ReservationResponse response = mapToResponse(reservation);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }

    private ReservationResponse mapToResponse(Reservation reservation){
        ReservationResponse response = new ReservationResponse();
        response.setId(reservation.getId());
        response.setClientId(reservation.getClient().getId());
        response.setWorkerId(reservation.getWorker().getId());
        response.setServiceIds(reservation.getServices().stream().map(service -> service.getId()).collect(Collectors.toSet()));
        response.setStartTime(reservation.getStartDateTime());
        response.setEndTime(reservation.getEndDateTime());
        return response;

    }

}
