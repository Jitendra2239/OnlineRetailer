package com.jitendra.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jitendra.model.Inventry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventryResponse {
	 private int inventryId;
	 private int productId;
	 private int quantity;
}
