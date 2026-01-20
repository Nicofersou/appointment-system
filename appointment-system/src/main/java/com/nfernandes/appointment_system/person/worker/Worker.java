package com.nfernandes.appointment_system.person.worker;

import com.nfernandes.appointment_system.businessService.BusinessService;
import com.nfernandes.appointment_system.person.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "Workers")
@Getter
@Setter
public class Worker extends Person {
    private LocalTime workStartTime;
    private LocalTime workEndTime;

    private boolean active = true;

    @ManyToMany
    @JoinTable(
            name = "worker_services",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<BusinessService> services = new HashSet<>();

    public void addService(BusinessService service) {
        services.add(service);
    }

    public void removeService(BusinessService service) {
        services.remove(service);
    }

}
