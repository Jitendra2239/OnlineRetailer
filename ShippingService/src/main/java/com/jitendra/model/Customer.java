package com.jitendra.model;


import java.io.Serializable;
import java.util.List;




import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Customer implements Serializable{

     
	private int customerId;
	private String customerName;
	private String customerEmail;

	private List<CustomerAddress>  customerBillingAddres;

	private List<CustomerAddress>  customerShipingAddres;

}