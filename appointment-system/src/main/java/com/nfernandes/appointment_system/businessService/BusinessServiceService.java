package com.nfernandes.appointment_system.businessService;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.nfernandes.appointment_system.businessService.BusinessServiceRepository;

import java.math.BigDecimal;

@Service
@Transactional
public class BusinessServiceService {

    private final BusinessServiceRepository repository;

    public BusinessServiceService(BusinessServiceRepository repository) {
        this.repository = repository;
    }

    public BusinessService create(String name, Integer duration, BigDecimal price) {
        BusinessService service = new BusinessService(name, duration, price);
        return repository.save(service);
    }


}
