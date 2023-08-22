package com.mcn.shoop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart")
    private List<User> user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartEntry> cartEntries;

    @Column(name = "total_price")
    private double totalPrice;
}
