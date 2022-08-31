package com.spring.fruitmvcrest.api.v1.models;

import com.spring.fruitmvcrest.models.Price;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FruitDTO {
	private Long id;
	private String name;
	private Price price;
}
