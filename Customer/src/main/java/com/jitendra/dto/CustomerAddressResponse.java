package com.jitendra.dto;

import java.util.List;

import com.jitendra.model.CustomerAddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddressResponse {
private int doorNo;
	private int customerId;
	private int pinCode;
	private String layout;
	private String city;
	private String streetName;
}
