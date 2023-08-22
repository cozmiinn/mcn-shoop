package com.mcn.shoop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name= "product_variant")
public class ProductVariant{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "price", length = 10)
    private Float price;

    @Column(name = "available_quantity", length = 10)
    private int availableQuantity;

    @Column(name = "added_date", length = 10)
    private Date addedDate;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "base_product_id")
    @JsonIgnore
    private BaseProduct baseProduct;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "variants")
    private List<Attribute> attribute;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartEntry> cartEntries;
}
