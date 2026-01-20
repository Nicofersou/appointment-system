package com.nfernandes.appointment_system.businessService;

import com.nfernandes.appointment_system.person.worker.Worker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "business_services")
@Getter
@Setter
public class BusinessService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private Integer durationInMinutes;

    @Column (nullable = false)
    private BigDecimal price;

    protected BusinessService() {
    }

    public BusinessService(String name, Integer durationInMinutes, BigDecimal price) {
        this.name = name;
        this.durationInMinutes = durationInMinutes;
        this.price = price;
    }



    @ManyToMany(mappedBy = "services")
    private Set<Worker> workers = new HashSet<>();



}
