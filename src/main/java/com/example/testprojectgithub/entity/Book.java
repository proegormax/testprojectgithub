package com.example.testprojectgithub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorCode;
    private String title;
    private Integer publishYear;
    private String brand;
    private Integer stock;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;
}
