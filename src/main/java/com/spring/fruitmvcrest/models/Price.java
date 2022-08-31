package com.spring.fruitmvcrest.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity(name="Price")
public class Price{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(value = EnumType.STRING)
	private Currency currency; 
	private Float units;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="price")
	@JsonManagedReference
	private List<Fruit> fruits = new ArrayList<>();
}
 	