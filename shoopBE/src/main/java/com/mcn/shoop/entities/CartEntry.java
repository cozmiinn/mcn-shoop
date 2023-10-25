package com.mcn.shoop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cart_entry")
public class CartEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(mappedBy = "cartEntry")
    private ProductVariant variants;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price_per_piece")
    private double pricePerPiece;

    @Column(name = "total_price_per_entry")
    private double totalPricePerEntry;
}