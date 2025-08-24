package com.pragma.foodcourt.domain.model;

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
    private Long taxId;
    private String address;
    private String phone;
    private String logoUrl;
    private Long createdBy;
    private java.time.LocalDateTime createdAt;
}
