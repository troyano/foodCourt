package com.pragma.foodcourt.infrastructure.out.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "tax_id", nullable = false, unique = true)
    private Long taxId;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(nullable = false, length = 13)
    private String phone;

    @Column(name = "logo_url", nullable = false, length = 255)
    private String logoUrl;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
