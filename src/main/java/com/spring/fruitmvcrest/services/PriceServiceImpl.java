package com.spring.fruitmvcrest.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.fruitmvcrest.api.v1.mapper.PriceMapper;
import com.spring.fruitmvcrest.api.v1.models.PriceDTO;
import com.spring.fruitmvcrest.exceptions.ResourceNotFoundException;
import com.spring.fruitmvcrest.models.Fruit;
import com.spring.fruitmvcrest.repositories.FruitRepository;
import com.spring.fruitmvcrest.repositories.PriceRepository;

@Service
public class PriceServiceImpl implements PriceService{

	private final PriceRepository priceRepository;
	private final FruitRepository fruitRepository;
	private final PriceMapper priceMapper;
	
	public PriceServiceImpl(PriceRepository priceRepository, FruitRepository fruitRepository, PriceMapper priceMapper) {
		this.priceRepository = priceRepository;
		this.fruitRepository = fruitRepository;
		this.priceMapper = priceMapper;
	}

	@Override
	public PriceDTO findPriceById(Long priceId) throws ResourceNotFoundException {
		return priceMapper.priceToPriceDTO(priceRepository
				.findById(priceId)
				.orElseThrow(ResourceNotFoundException::new));
	}

	@Override
	public PriceDTO findPriceByFruitId(Long fruitId) throws ResourceNotFoundException {
	
		Optional<Fruit> fruitOptional = fruitRepository.findById(fruitId);

		if(fruitOptional.isPresent()) {
			return priceMapper.priceToPriceDTO(fruitOptional.get().getPrice());
		}
		else throw new ResourceNotFoundException();
	}

}
