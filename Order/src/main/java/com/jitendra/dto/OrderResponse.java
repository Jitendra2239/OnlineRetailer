package com.jitendra.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jitendra.model.LineItem;
import com.jitendra.model.OrderEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
	private  int orderId;
   private  List<ItemResponse> item;
}
