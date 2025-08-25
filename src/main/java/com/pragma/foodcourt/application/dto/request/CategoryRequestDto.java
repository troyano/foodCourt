package com.pragma.foodcourt.application.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDto {
    @NotBlank
    @Size(max = 5, message = "The code cannot exceed 5 characters")
    private String code;
    @Size(max = 255, message = "The description cannot exceed 255 characters")
    private String description;
    @NotBlank
    @Size(max = 150, message = "The createdBy cannot exceed 150 characters")
    private String createdBy;
}
