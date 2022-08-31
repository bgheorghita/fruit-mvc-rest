package com.spring.fruitmvcrest.models;

import java.io.Serializable;
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

import lombok.Data;

@Data 
@Entity(name="Price")
public class Price implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(value = EnumType.STRING)
	private Currency currency; 
	private Float units;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="price")
	private List<Fruit> fruits = new ArrayList<>();
}
