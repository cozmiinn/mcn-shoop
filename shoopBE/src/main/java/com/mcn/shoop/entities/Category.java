//package com.mcn.shoop.entities;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@Table(name = "category")
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "category_name", length = 50)
//    private String categoryName;
//
//    @OneToMany(mappedBy = "category")
//    private List<Subcategory> subcategories;
//}
