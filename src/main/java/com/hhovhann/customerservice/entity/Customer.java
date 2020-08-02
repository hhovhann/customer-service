package com.hhovhann.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity(name = "Customer")
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 1, max = 10)
    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @Size(min = 1, max = 10)
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @Size(min = 9, max = 12)
    @NotBlank(message = "Phone Number is mandatory")
    private String phoneNumber;

    @Size(min = 6, max = 36)
    @NotBlank(message = "Email is mandatory")
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonManagedReference
    private List<Address> addresses;

    public void addAddress(Address address) {
        this.addresses.add(address);
        address.setCustomer(this);
    }

    public void removeAddress(Address address) {
        this.addresses.remove(address);
        address.setCustomer(null);
    }
}
