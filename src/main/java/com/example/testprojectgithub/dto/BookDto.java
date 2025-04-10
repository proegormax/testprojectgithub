package com.example.testprojectgithub.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private Long id;
    private String vendorCode;
    private String title;
    private Integer publishYear;
    private String brand;
    private Integer stock;
    private BigDecimal price;
}
