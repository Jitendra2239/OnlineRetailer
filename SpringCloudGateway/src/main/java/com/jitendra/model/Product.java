package com.jitendra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	@JsonIgnore
	private int productId;
	private String productName;
	private String productDescrition;
	private int productPrice;
	private int quantity;
}
