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
import com.nfernandes.appointment_system.reservation.mapper.ReservationMapper;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;


    private final ReservationMapper reservationMapper;

    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper){
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody @Valid CreateReservationRequest request
            ){
        Reservation reservation = reservationService.createReservation(request.getClientId(), request.getWorkerId(), request.getServiceIds(),request.getStartTime());

        ReservationResponse response = reservationMapper.toResponse(reservation);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }



}
