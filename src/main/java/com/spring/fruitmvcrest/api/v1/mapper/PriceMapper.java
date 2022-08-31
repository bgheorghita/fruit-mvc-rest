package com.spring.fruitmvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.spring.fruitmvcrest.api.v1.models.PriceDTO;
import com.spring.fruitmvcrest.models.Price;

@Mapper
public interface PriceMapper {
	PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
	
	PriceDTO priceToPriceDTO(Price price);
	Price priceDTOToPrice(PriceDTO priceDTO);

}
