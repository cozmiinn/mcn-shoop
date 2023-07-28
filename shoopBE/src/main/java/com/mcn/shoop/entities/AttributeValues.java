
package com.mcn.shoop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "attribute_values")
public class AttributeValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String value;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name= "valuess",
            joinColumns = @JoinColumn(name = "attribute_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_values_id", referencedColumnName = "id"))
    @JsonIgnore
    private Attribute attribute;

//    @OneToMany(mappedBy = "attribute_values")
//    private List<AttributeValues> idValue;


}