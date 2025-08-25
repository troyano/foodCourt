package com.pragma.foodcourt.infrastructure.out.jpa.mapper;

import com.pragma.foodcourt.domain.model.Category;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    ICategoryEntityMapper INSTANCE = Mappers.getMapper(ICategoryEntityMapper.class);
    CategoryEntity toEntity(Category model);
    Category toModel(CategoryEntity entity);
}
