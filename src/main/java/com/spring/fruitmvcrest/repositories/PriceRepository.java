package com.spring.fruitmvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fruitmvcrest.models.Price;

public interface PriceRepository extends JpaRepository<Price, Long>{

}
