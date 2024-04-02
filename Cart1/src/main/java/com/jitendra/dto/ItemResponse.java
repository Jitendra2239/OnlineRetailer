package com.jitendra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponse {
	private  int itemId;
	private  int productId;
	private  int quantity;
	private  int price;
	private  String producetName;
}