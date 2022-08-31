package com.spring.fruitmvcrest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.fruitmvcrest.api.v1.models.FruitDTO;
import com.spring.fruitmvcrest.exceptions.ResourceNotFoundException;
import com.spring.fruitmvcrest.services.FruitService;

@RestController
@RequestMapping(FruitController.BASE_URL)
public class FruitController {
	
	public final static String BASE_URL = "/api/v1/fruits";
	private final FruitService fruitService;
	
	public FruitController(FruitService fruitService) {
		this.fruitService = fruitService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<FruitDTO> getAllFruits() {
		return fruitService.getAllFruits();
	}
	
	@GetMapping("/{fruitId}")
	@ResponseStatus(HttpStatus.OK)
	public FruitDTO getFruitById(@PathVariable Long fruitId) throws ResourceNotFoundException {
		return fruitService.getFruitById(fruitId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FruitDTO createNewFruit(@RequestBody FruitDTO fruit) {
		return fruitService.createNewFruit(fruit);
	}
	
	@PutMapping("/{fruitId}")
	@ResponseStatus(HttpStatus.OK)
	public FruitDTO updateFruit(@PathVariable Long fruitId, @RequestBody FruitDTO fruit) {
		return fruitService.updateFruit(fruitId, fruit);
	}
	
	@PatchMapping("/{fruitId}")
	@ResponseStatus(HttpStatus.OK)
	public FruitDTO patchFruit(@PathVariable Long fruitId, @RequestBody FruitDTO fruit) {
		return fruitService.patchFruit(fruitId, fruit);
	}
	
	@DeleteMapping("/{fruitId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteFruitById(@PathVariable Long fruitId) {
		fruitService.deleteFruitById(fruitId);
	}
}














