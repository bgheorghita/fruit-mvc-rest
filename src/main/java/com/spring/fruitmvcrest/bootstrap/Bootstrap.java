package com.spring.fruitmvcrest.bootstrap;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.fruitmvcrest.models.Currency;
import com.spring.fruitmvcrest.models.Fruit;
import com.spring.fruitmvcrest.models.Price;
import com.spring.fruitmvcrest.repositories.FruitRepository;
import com.spring.fruitmvcrest.repositories.PriceRepository;

@Component
public class Bootstrap implements CommandLineRunner{
		
	private final FruitRepository fruitRepository;
	private final PriceRepository priceRepository;
	
	public Bootstrap(FruitRepository fruitRepository, PriceRepository priceRepository) {
		this.fruitRepository = fruitRepository;
		this.priceRepository = priceRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Price applePrice = new Price();
		applePrice.setCurrency(Currency.EUR);
		applePrice.setId(1L);
		applePrice.setUnits(10F);
		priceRepository.save(applePrice);
		
		Price orangePrice = new Price();
		orangePrice.setCurrency(Currency.EUR);
		orangePrice.setId(2L);
		orangePrice.setUnits(20F);
		priceRepository.save(orangePrice);
		
		Price avocadoPrice = new Price();
		avocadoPrice.setCurrency(Currency.USD);
		avocadoPrice.setId(3L);
		avocadoPrice.setUnits(30F);
		priceRepository.save(avocadoPrice);
		
		Fruit apple = new Fruit();
		apple.setId(1L);
		apple.setName("Apple");
		apple.setPrice(applePrice);
	    applePrice.setFruits(Arrays.asList(apple));
		
		Fruit orange = new Fruit();
		orange.setId(2L);
		orange.setName("Orange");
		orange.setPrice(orangePrice);
		
		Fruit avocado = new Fruit();
		avocado.setId(3L);
		avocado.setName("Avocado");
		avocado.setPrice(avocadoPrice);
		avocadoPrice.setFruits(Arrays.asList(avocado));
		
		Fruit lemon = new Fruit();
		lemon.setId(4L);
		lemon.setName("Lemon");
		lemon.setPrice(orangePrice);
		orangePrice.setFruits(Arrays.asList(orange, lemon));
		
		fruitRepository.save(apple);
		fruitRepository.save(orange);
		fruitRepository.save(avocado);
		fruitRepository.save(lemon);
		
		System.out.println("Data Loaded");
	}

}
