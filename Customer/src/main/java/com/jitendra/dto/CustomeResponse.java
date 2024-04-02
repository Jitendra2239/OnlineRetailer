package com.jitendra.dto;

import java.util.List;

import com.jitendra.model.Customer;
import com.jitendra.model.CustomerAddress;

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
public class CustomeResponse {
	private int customerId;
	private String customerName;
	private String customerEmail;
	private List<CustomerAddressResponse > customerBillingAddres;
	private List<CustomerAddressResponse > customerShipingAddres;
	
  
}
