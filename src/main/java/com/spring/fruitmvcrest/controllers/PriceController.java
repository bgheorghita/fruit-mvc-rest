package com.spring.fruitmvcrest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.fruitmvcrest.api.v1.models.PriceDTO;
import com.spring.fruitmvcrest.exceptions.ResourceNotFoundException;
import com.spring.fruitmvcrest.services.PriceService;

@RestController
@RequestMapping(PriceController.URL_BASE)
public class PriceController {
	public final static String URL_BASE = "/api/v1/prices";
	
	private final PriceService priceService;
	
	public PriceController(PriceService priceService) {
		this.priceService = priceService;
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PriceDTO getPriceById(@PathVariable Long id) throws ResourceNotFoundException {
		return priceService.findPriceById(id);
	}
}
