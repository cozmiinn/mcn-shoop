package com.mcn.shoop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="street_line", length = 50)
    private String streetLine;

    @Column(name="postal_code", length = 50)
    private String postalCode;

    @Column(name="city", length = 50)
    private String city;

    @Column(name="county", length = 50)
    private String county;

    @Column(name = "country", length = 50)
    private String country;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
