package com.spring.fruitmvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fruitmvcrest.models.Fruit;

public interface FruitRepository extends JpaRepository<Fruit, Long>{

}
