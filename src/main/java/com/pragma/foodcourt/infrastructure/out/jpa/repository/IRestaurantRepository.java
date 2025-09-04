package com.pragma.foodcourt.infrastructure.out.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pragma.foodcourt.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.Query;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

	boolean existsByTaxId(Long taxId);

	Optional<RestaurantEntity> findByTaxId(String taxId);

	@Query("SELECT r.createdBy FROM RestaurantEntity r WHERE r.id = :id")
	Optional<String> findCreatedBy(Long id);
}
