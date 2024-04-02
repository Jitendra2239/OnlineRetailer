package com.jitendra.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jitendra.model.Products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsResponse {
	private int productId;
	private String productName;
	private String productDescrition;
	private int productPrice;
}
