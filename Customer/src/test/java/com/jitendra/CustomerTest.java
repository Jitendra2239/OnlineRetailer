package com.jitendra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.jitendra.model.Customer;
@ExtendWith(MockitoExtension.class)
public class CustomerTest {

	@Mock
	private Customer customer;
	@Test
	public void testSetCustomerName() {
	customer.setCustomerName("jitendra");;
	}
	@Test
	public void testGetCustomerName() {
		customer.setCustomerName("jitendra");;
		customer.setCustomerName("jitendra");;
		assertEquals("jitendra", customer.getCustomerName());
	}
}
