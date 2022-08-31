package com.spring.fruitmvcrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.fruitmvcrest.models.Fruit;
import com.spring.fruitmvcrest.repositories.FruitRepository;

@Component
public class Bootstrap implements CommandLineRunner{
		
	private final FruitRepository fruitRepository;
	
	public Bootstrap(FruitRepository fruitRepository) {
		this.fruitRepository = fruitRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Fruit apple = new Fruit();
		apple.setId(1L);
		apple.setName("Apple");
		
		Fruit orange = new Fruit();
		orange.setId(2L);
		orange.setName("Orange");
		
		Fruit avocado = new Fruit();
		avocado.setId(3L);
		avocado.setName("Avocado");
		
		fruitRepository.save(apple);
		fruitRepository.save(orange);
		fruitRepository.save(avocado);
		
		System.out.println("Data Loaded");
	}

}
