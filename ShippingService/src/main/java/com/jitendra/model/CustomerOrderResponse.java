package com.jitendra.model;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrderResponse {
	private  int  orderId;
	private  List<LineItem>item;
	private int customerId;
	private String customerName;
	private String customerEmail;

	private List<CustomerAddress>  customerBillingAddres;

	private List<CustomerAddress>  customerShipingAddres;
}
