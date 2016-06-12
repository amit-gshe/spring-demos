package com.example.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@ManyToOne
	Good good;
}
