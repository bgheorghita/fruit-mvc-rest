package com.spring.fruitmvcrest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.fruitmvcrest.api.v1.mapper.FruitMapper;
import com.spring.fruitmvcrest.api.v1.models.FruitDTO;
import com.spring.fruitmvcrest.exceptions.ResourceNotFoundException;
import com.spring.fruitmvcrest.models.Fruit;
import com.spring.fruitmvcrest.repositories.FruitRepository;

@Service
public class FruitServiceImpl implements FruitService{

	private final FruitRepository fruitRepository;
	private final FruitMapper fruitMapper;
	
	public FruitServiceImpl(FruitRepository fruitRepository, FruitMapper fruitMapper) {
		this.fruitRepository = fruitRepository;
		this.fruitMapper = fruitMapper;
	}

	@Override
	public List<FruitDTO> getAllFruits() {
		List<FruitDTO> fruits = new ArrayList<>();
		fruitRepository.findAll()
					   .stream()
					   .forEach(fruit -> fruits.add(fruitMapper.fruitToFruitDTO(fruit)));
		return fruits;
	}

	@Override
	public FruitDTO getFruitById(Long fruitId) throws ResourceNotFoundException {
		return fruitMapper.fruitToFruitDTO(fruitRepository
				.findById(fruitId)
				.orElseThrow(ResourceNotFoundException::new));
	}

	@Override
	public FruitDTO createNewFruit(FruitDTO fruit) {
		return saveAndReturnDTO(fruitMapper.fruitDTOToFruit(fruit));
	}
	
	@Override
	public FruitDTO updateFruit(Long id, FruitDTO fruit) {
		Fruit savedFruit = fruitMapper.fruitDTOToFruit(fruit);
		savedFruit.setId(id);
		return saveAndReturnDTO(savedFruit);
	}
	
	private FruitDTO saveAndReturnDTO(Fruit fruit) {
		Fruit savedFruit = fruitRepository.save(fruit);
		return fruitMapper.fruitToFruitDTO(savedFruit);
	}

	@Override
	public FruitDTO patchFruit(Long id, FruitDTO fruit) throws ResourceNotFoundException {
		Optional<Fruit> newFruitOptional = fruitRepository.findById(id);
		
		if(newFruitOptional.isPresent()) {
			
			Fruit newFruit = newFruitOptional.get();
			
			newFruit.setId(id);

			if(fruit.getName() != null) {
				newFruit.setName(fruit.getName());;
			}
			
			return saveAndReturnDTO(newFruit);
		}
		
		throw new ResourceNotFoundException();
	}

	@Override
	public void deleteFruitById(Long fruitId) {
		fruitRepository.deleteById(fruitId);
	}
}
