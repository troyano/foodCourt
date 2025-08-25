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
public class Category {
    private Long id;
    private String code;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
}
