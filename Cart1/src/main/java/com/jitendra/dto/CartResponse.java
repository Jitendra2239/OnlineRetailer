package com.jitendra.dto;

import java.util.List;

import com.jitendra.modal.LineItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
	private  int cartId;
	   private  List<ItemResponse> item;
}
