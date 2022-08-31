package com.spring.fruitmvcrest.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.fruitmvcrest.api.v1.models.FruitDTO;
import com.spring.fruitmvcrest.exceptions.ResourceNotFoundException;
import com.spring.fruitmvcrest.services.FruitService;


class FruitControllerTest {
	
	@Mock
	FruitService fruitService;
	
	@InjectMocks
	FruitController controller;
	
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new APIExceptionHandlerController())
				.build();
	}

	@Test
	void testGetAllFruits() throws Exception {
		List <FruitDTO> fruits = Arrays.asList(new FruitDTO (), new FruitDTO());
		
		when(fruitService.getAllFruits()).thenReturn(fruits);

		mockMvc.perform(get(FruitController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	void testGetFruitById() throws Exception {
		FruitDTO orange = new FruitDTO();
		orange.setId(1L);
		orange.setName("Orange");
		
		when(fruitService.getFruitById(1L)).thenReturn(orange);
		
		mockMvc.perform(get(FruitController.BASE_URL+"/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Orange")));
		
	}
	
	@Test
	void testGetFruitByIdShouldReturnResourceNotFound() throws Exception {
		when(fruitService.getFruitById(anyLong())).thenThrow(ResourceNotFoundException.class);
		
		mockMvc.perform(get(FruitController.BASE_URL+"/20")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound())
			      .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
			      .andExpect(result -> assertEquals(result.getResponse().getContentAsString(), APIExceptionHandlerController.RESOURCE_NOT_FOUND_MSG));
	}
	
	@Test
	void testAddNewFruit() throws Exception {
		FruitDTO orange = new FruitDTO();
		orange.setId(1L);
		orange.setName("Orange");
		
		FruitDTO returnedOrange = new FruitDTO();
		returnedOrange.setId(orange.getId());
		returnedOrange.setName(orange.getName());
		
		when(fruitService.createNewFruit(orange)).thenReturn(returnedOrange);
		
		mockMvc.perform(post(FruitController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(orange)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", equalTo(1)))
				.andExpect(jsonPath("$.name", equalTo("Orange")));
	}
	
	@Test
	void testUpdateFruit() throws JsonProcessingException, Exception {
		FruitDTO orange = new FruitDTO();
		orange.setId(1L);
		orange.setName("Orange");
		
		FruitDTO lemon = new FruitDTO();
		lemon.setId(2L);
		lemon.setName("Lemon");
		
		orange.setId(lemon.getId());
		orange.setName(lemon.getName());
		
		when(fruitService.updateFruit(anyLong(), any(FruitDTO.class))).thenReturn(orange);
		
		mockMvc.perform(put(FruitController.BASE_URL+"/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(lemon)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", equalTo(lemon.getId() == null ? null : lemon.getId().intValue())))
				.andExpect(jsonPath("$.name", equalTo(lemon.getName())));
		
		
	}
	
	@Test
	void testPatchFruit() throws JsonProcessingException, Exception {
		FruitDTO orange = new FruitDTO();
		orange.setId(1L);
		orange.setName("Orange");
		
		FruitDTO newOrange = new FruitDTO();
		newOrange.setId(2L);
		newOrange.setName("Lemon");
		
		orange.setId(newOrange.getId());
		orange.setName(newOrange.getName());
		
		when(fruitService.patchFruit(anyLong(), any(FruitDTO.class))).thenReturn(orange);
		
		mockMvc.perform(patch(FruitController.BASE_URL+"/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(newOrange)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", equalTo(newOrange.getId() == null ? null : newOrange.getId().intValue())))
				.andExpect(jsonPath("$.name", equalTo(newOrange.getName())));
		
		verify(fruitService, times(1)).patchFruit(1L, newOrange);
	}
	
	@Test
	void testDeleteFruitById() throws Exception {
		mockMvc.perform(delete(FruitController.BASE_URL+"/1")
				.contentType(MediaType.APPLICATION_JSON));
		
		verify(fruitService, times(1)).deleteFruitById(1L);
	}

}
