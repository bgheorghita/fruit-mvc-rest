package com.spring.fruitmvcrest.api.v1.models;

import com.spring.fruitmvcrest.models.Price;

import lombok.Data;

@Data
public class FruitDTO {
	private Long id;
	private String name;
	private Price price;
}
