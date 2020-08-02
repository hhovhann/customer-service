package com.hhovhann.customerservice.entity;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Data
@Entity(name = "Address")
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Enumerated(EnumType.STRING)
    private AddressType typeOfAddress;

    @NonNull
    private String city;

    @NonNull
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @Override
    public boolean equals(Object current) {
        if (this == current) return true;
        if (!(current instanceof Address)) return false;
        return id != null && id.equals(((Address) current).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
