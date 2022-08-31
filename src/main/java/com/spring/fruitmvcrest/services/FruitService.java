package com.spring.fruitmvcrest.services;

import java.util.List;

import com.spring.fruitmvcrest.api.v1.models.FruitDTO;
import com.spring.fruitmvcrest.exceptions.ResourceNotFoundException;

public interface FruitService {
	List<FruitDTO> getAllFruits();
	FruitDTO getFruitById(Long fruitId) throws ResourceNotFoundException;
	FruitDTO createNewFruit(FruitDTO fruit);
	FruitDTO updateFruit(Long id, FruitDTO fruit);
	FruitDTO patchFruit(Long id, FruitDTO fruit);
	void deleteFruitById(Long fruitId);
}
