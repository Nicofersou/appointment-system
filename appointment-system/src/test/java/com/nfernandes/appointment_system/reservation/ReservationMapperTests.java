package com.nfernandes.appointment_system.reservation;


import com.nfernandes.appointment_system.businessService.BusinessService;
import com.nfernandes.appointment_system.person.client.Client;
import com.nfernandes.appointment_system.person.worker.Worker;
import com.nfernandes.appointment_system.reservation.dto.ReservationResponse;
import com.nfernandes.appointment_system.reservation.mapper.ReservationMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReservationMapperTests {

    private final ReservationMapper reservationMapper = new ReservationMapper();

    @Test
    void shouldMapReservationToResponseSuccessfully() {

        // GIVEN
        Client client = new Client();
        client.setId(1L);

        Worker worker = new Worker();
        worker.setId(2L);

        BusinessService service = new BusinessService("CorteSR7", 30, BigDecimal.valueOf(15));
        service.setId(5L);

        Reservation reservation = new Reservation();
        reservation.setId(11L);
        reservation.setClient(client);
        reservation.setWorker(worker);
        reservation.setStartDateTime(LocalDateTime.of(2026, 1, 30, 10, 0));
        reservation.setEndDateTime(LocalDateTime.of(2026, 1, 30, 11, 0));
        reservation.setStatus(ReservationStatus.CREATED);
        reservation.setServices(Set.of(service));

        // WHEN
        ReservationResponse response = reservationMapper.toResponse(reservation);

        // THEN
        assertNotNull(response);
        assertEquals(11L, response.getId());
        assertEquals(reservation.getStartDateTime(), response.getStartTime());
        assertEquals(reservation.getEndDateTime(), response.getEndTime());
        assertEquals(1L, response.getClientId());
        assertEquals(2L, response.getWorkerId());
        assertEquals(Set.of(5L), response.getServiceIds());
    }
}