package com.mcn.shoop.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", length = 50)
    private String firstName;

    @Column(name="last_name", length = 50)
    private String lastName;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column(name="email", length = 50)
    private String email;

    @Column(name="phone_number", length = 50)
    private String phoneNumber;

    @Column(name="password", length = 50)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Address> address;

    @OneToMany(mappedBy = "user")
    private List<CardDetails> paymentCards;

}
