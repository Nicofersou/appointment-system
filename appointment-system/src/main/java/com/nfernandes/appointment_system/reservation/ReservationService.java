package com.nfernandes.appointment_system.reservation;

import com.nfernandes.appointment_system.businessService.BusinessService;
import com.nfernandes.appointment_system.businessService.BusinessServiceRepository;
import com.nfernandes.appointment_system.person.client.Client;
import com.nfernandes.appointment_system.person.client.ClientRepository;
import com.nfernandes.appointment_system.person.worker.Worker;
import com.nfernandes.appointment_system.person.worker.WorkerRepository;
import com.nfernandes.appointment_system.reservation.ReservationRepository;
import com.nfernandes.appointment_system.reservation.ReservationStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ClientRepository clientRepository;
    private WorkerRepository workerRepository;
    private BusinessServiceRepository businessServiceRepository;

    public Reservation createReservation(Long clientId,Long workerId,Set<Long> serviceIds,LocalDateTime startDateTime){

        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client Not found"));
        Worker worker = workerRepository.findById(workerId).orElseThrow(() -> new EntityNotFoundException("Worker not found"));
        Set<BusinessService> services = Set.copyOf(businessServiceRepository.findAllById(serviceIds));
         if(!worker.isActive()){
             throw new IllegalStateException("Worker is not active");
         }

         if(!worker.getServices().containsAll(services)){
             throw new IllegalArgumentException("Worker does not offer all selected services");
         }


         int totalDurationSMinutes =  services.stream()
                 .mapToInt(BusinessService::getDurationInMinutes)
                 .sum();
        LocalDateTime endDateTime = startDateTime.plusMinutes(totalDurationSMinutes);


        if (startDateTime.toLocalTime().isBefore(worker.getWorkStartTime()) || endDateTime.toLocalTime().isAfter(worker.getWorkEndTime())){
            throw new IllegalArgumentException("Reservation out of worker schedule");
        }

        boolean hasOverLaps = reservationRepository.findByWorkerIdAndStartDateTimeLessThanAndEndDateTimeGreaterThan(workerId,endDateTime, startDateTime).isEmpty();

        if (!hasOverLaps){
            throw new IllegalArgumentException("Worker already has a reservation in this time slot");
        }


        Reservation reservation = Reservation.builder().client(client).worker(worker).services(services).startDateTime(startDateTime).endDateTime(endDateTime).status(ReservationStatus.CREATED).build();
        return reservationRepository.save(reservation);
    }


}
