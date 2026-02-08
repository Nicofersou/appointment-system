package com.nfernandes.appointment_system.reservation;


import com.nfernandes.appointment_system.businessService.BusinessService;
import com.nfernandes.appointment_system.businessService.BusinessServiceRepository;
import com.nfernandes.appointment_system.person.client.Client;
import com.nfernandes.appointment_system.person.client.ClientRepository;
import com.nfernandes.appointment_system.person.worker.Worker;
import com.nfernandes.appointment_system.person.worker.WorkerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private BusinessServiceRepository businessServiceRepository;

    @Mock
    private WorkerRepository workerRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Set<Long> servicesIds = new HashSet<>();

    @Test
    void shouldCreateReservationSuccessfully() {

        Client client = new Client();
        client.setId(1L);

        Worker worker = new Worker();
        worker.setId(1L);
        worker.setWorkStartTime(LocalTime.of(9, 0));
        worker.setWorkEndTime(LocalTime.of(17, 0));

        servicesIds.add(1L);

        BusinessService service =
                new BusinessService("Corte", 30, BigDecimal.valueOf(13));
        worker.setServices(Set.of(service));


        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);

        when(clientRepository.findById(1L))
                .thenReturn(Optional.of(client));

        when(workerRepository.findById(1L))
                .thenReturn(Optional.of(worker));

        when(reservationRepository.save(any(Reservation.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(businessServiceRepository.findAllById(servicesIds))
                .thenReturn(List.of(service));

        Reservation saved = reservationService.createReservation(
                1L,
                1L,
                Set.of(1L),
                start
        );

        assertNotNull(saved);
        assertEquals(ReservationStatus.CREATED, saved.getStatus());

        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void shouldThrowExceptionWhenReservationOutsideWorkingHours(){
        Client client = new Client();
        Worker worker = new Worker();

        worker.setWorkStartTime(LocalTime.of(10,0));
        worker.setWorkEndTime(LocalTime.of(17,0));
        servicesIds.add(1L);



        BusinessService businessService = new BusinessService("Corte", 30, BigDecimal.valueOf(13));
        worker.setServices(Set.of(businessService));

        LocalDateTime start = LocalDateTime.of(2026,1, 5,9,0);
        LocalDateTime end = start.plusMinutes(30);

        when(clientRepository.findById(1L))
                .thenReturn(Optional.of(client));

        when(workerRepository.findById(1L))
                .thenReturn(Optional.of(worker));

        when(businessServiceRepository.findAllById(servicesIds))
                .thenReturn(List.of(businessService));

        assertThrows(IllegalArgumentException.class,
                () -> reservationService.createReservation(1L,1L,servicesIds,start));


    }


}
