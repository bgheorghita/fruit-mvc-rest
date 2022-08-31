package com.spring.fruitmvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.spring.fruitmvcrest.api.v1.models.FruitDTO;
import com.spring.fruitmvcrest.models.Fruit;

@Mapper
public interface FruitMapper {
	
	FruitMapper INSTANCE = Mappers.getMapper(FruitMapper.class);
	
	FruitDTO fruitToFruitDTO(Fruit fruit);
	Fruit fruitDTOToFruit(FruitDTO fruitDTO);
	
}
