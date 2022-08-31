package com.spring.fruitmvcrest.services;

import com.spring.fruitmvcrest.api.v1.models.PriceDTO;

public interface PriceService {
	 PriceDTO findPriceByFruitId(Long fruitId);
}
