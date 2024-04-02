package com.jitendra.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.jitendra.dao.CustomerRepo;
import com.jitendra.model.Customer;
import com.jitendra.model.CustomerAddress;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@Mock
	private CustomerRepo repo;
	
	@InjectMocks
	private CustomerServiceImpl service;

	private Customer customer;
	private CustomerAddress  customerAddress;

	@BeforeEach
	public void setup() {
		// employeeRepository = Mockito.mock(EmployeeRepository.class);
		// employeeService = new EmployeeServiceImpl(employeeRepository);
		 customer=  Customer.builder()
				 .customerBillingAddres(null).customerEmail("jite@qwer")
				 .customerId(1)
				 .customerName("jitendra").build();
		 customerAddress=CustomerAddress.builder().city("moti").doorNo(11)
				 .layout("ccccc").pinCode(845478).build();
		List<CustomerAddress > list = new ArrayList<>();
		list.add(customerAddress);
	}

	@DisplayName("JUnit test for save method")
	@Test
	void testAddLineItem() {
		given(repo.save(customer)).willReturn(customer);
		Customer customer1 = service.addCustomer(customer);
		assertThat(customer1).isNotNull();
	}

	@DisplayName("JUnit test for search By Id method")
	@Test
	void testsearchCustomer() {
		given(repo.findById(1)).willReturn(Optional.of(customer));
		Customer customer1= service.searchCustomer(customer.getCustomerId());
		assertThat(customer1.getCustomerId()).isEqualTo(1);
	}

	@DisplayName("JUnit test for delete By Id method")
	@Test
	void testdeleteCustomer() {
		willDoNothing().given(repo).deleteById(1);
		
		service.deleteCustomer(1);
		verify(repo, times(1)).deleteById(1);
	}
	
	
	@DisplayName("JUnit test for update method")
	@Test
	void testUpdateLineItem() {
		given(repo.save(customer)).willReturn(customer);
		customer.setCustomerShipingAddres(null);   
		Customer customer1 = service.updateCustomer(customer);
	    assertThat(customer1.getCustomerShipingAddres()).isEqualTo(null);
	}

}
