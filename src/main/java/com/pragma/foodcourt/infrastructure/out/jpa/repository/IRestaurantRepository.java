package com.pragma.foodcourt.infrastructure.out.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pragma.foodcourt.infrastructure.out.jpa.entity.RestaurantEntity;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

	boolean existsByTaxId(Long taxId);

	Optional<RestaurantEntity> findByTaxId(String taxId);
}
