package com.spring.fruitmvcrest.services;

import com.spring.fruitmvcrest.api.v1.models.PriceDTO;
import com.spring.fruitmvcrest.exceptions.ResourceNotFoundException;

public interface PriceService {
	 PriceDTO findPriceByFruitId(Long fruitId) throws ResourceNotFoundException;
	 PriceDTO findPriceById(Long priceId) throws ResourceNotFoundException;
}
