package com.nfernandes.appointment_system.reservation;


import com.nfernandes.appointment_system.businessService.BusinessService;
import com.nfernandes.appointment_system.person.client.Client;
import com.nfernandes.appointment_system.person.worker.Worker;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha y hora de inicio de la reserva
    @Column(nullable = false)
    private LocalDateTime startDateTime;

    // Fecha y hora de fin (calculada según servicios)
    @Column(nullable = false)
    private LocalDateTime endDateTime;

    // Estado actual de la reserva
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    // Cliente que realiza la reserva
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    // Peluquero que realizará el servicio
    @ManyToOne(optional = false)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    // Servicios contratados en la reserva
    @ManyToMany
    @JoinTable(
            name = "reservation_services",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<BusinessService> services = new HashSet<>();
}