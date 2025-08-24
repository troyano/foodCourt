package com.pragma.foodcourt.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pragma.foodcourt.infrastructure.out.jpa.entity.ObjectEntity;

public interface IObjectRepository extends JpaRepository<ObjectEntity, Long> {

}