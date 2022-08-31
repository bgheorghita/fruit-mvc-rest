package com.spring.fruitmvcrest.api.v1.models;

import java.util.ArrayList;
import java.util.List;

import com.spring.fruitmvcrest.models.Currency;
import com.spring.fruitmvcrest.models.Fruit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PriceDTO {
	private Long id;
	private Currency currency;
	private Float units;
	private List<Fruit> fruits = new ArrayList<>();
}
