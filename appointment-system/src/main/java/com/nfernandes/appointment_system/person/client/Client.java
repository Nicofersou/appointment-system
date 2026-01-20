package com.nfernandes.appointment_system.person.client;
import com.nfernandes.appointment_system.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Clients")
@Getter
@Setter
public class Client extends Person {

    private boolean active = true;

}
