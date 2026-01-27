package com.nfernandes.appointment_system.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findWorkerbyID(Long workerId);

    List<Reservation> findByWorkerIdAndStartDateTimeLessThanAndEndDateTimeGreaterThan(
            Long workerId,
            LocalDateTime end,
            LocalDateTime start
    );
}
