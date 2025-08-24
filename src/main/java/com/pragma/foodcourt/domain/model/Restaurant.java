package com.pragma.foodcourt.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private String taxId;
    private String address;
    private String phone;
    private String logoUrl;
    private String createdBy;
    private LocalDateTime createdAt;
}
