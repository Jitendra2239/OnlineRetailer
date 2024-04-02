package com.jitendra.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitendra.model.Customer;
import com.jitendra.model.CustomerAddress;
import com.jitendra.service.CustomerService;

@WebMvcTest
class CustomerControllerTest2 {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
	private CustomerService service;
    private static ObjectMapper mapper = new ObjectMapper();

	@Test
	void testgetExample() throws Exception {
		Customer  customer =new Customer ();
		 customer.setCustomerId(1);
		 customer.setCustomerBillingAddres(null);;
		 customer.setCustomerName("Jitendra");
	       List<CustomerAddress>list = new ArrayList<>();                                  
	       CustomerAddress address=new CustomerAddress();                                          
	        address.setDoorNo(11);
	        address.setPinCode(845437);
	        address.setCity("noida");
	       
	        address.setLayout("moti");
	        address.setStreetName("jwahar");
	        list.add(address);
	        customer.setCustomerShipingAddres(list);
	        customer.setCustomerBillingAddres(list);
		   Mockito.when(service.searchCustomer((ArgumentMatchers.anyInt()))).thenReturn(customer);
		    mockMvc.perform(get("/searchCustomer/{id}",1)).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));
  
	}
	   @Test
	    public void testPostExample() throws Exception {
		   Customer  customer =new Customer ();
			 customer.setCustomerId(1);
			 
			 customer.setCustomerName("Jitendra");
		       List<CustomerAddress>list = new ArrayList<>();                                  
		       CustomerAddress address=new CustomerAddress();                                          
		        address.setDoorNo(11);
		        address.setPinCode(845437);
		        address.setCity("noida");
		        
		        address.setLayout("moti");
		        address.setStreetName("jwahar");
		        list.add(address);
		        customer.setCustomerBillingAddres(list);
		        customer.setCustomerShipingAddres(list);
			Mockito.when(service.addCustomer((ArgumentMatchers.any()))).thenReturn(customer);
			String json=mapper.writeValueAsString(customer);
			mockMvc.perform(post("/addCustomer").contentType(org.springframework.http.MediaType.APPLICATION_JSON).characterEncoding("utf-8")
					  .content(json).accept(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
			   .andExpect(jsonPath("$.cartId", Matchers.equalTo(1)));

	    }
	   
	   @Test
	    public void testPutExample() throws Exception {
		   Customer  customer =new Customer ();
			 customer.setCustomerId(1);
			 
			 customer.setCustomerName("Jitendra");
		       List<CustomerAddress>list = new ArrayList<>();                                  
		       CustomerAddress address=new CustomerAddress();                                          
		        address.setDoorNo(11);
		        address.setPinCode(845437);
		        address.setCity("noida");
		        
		        address.setLayout("moti");
		        address.setStreetName("jwahar");
		        list.add(address);
		        customer.setCustomerBillingAddres(list);
		        customer.setCustomerShipingAddres(list);
		        Mockito.when(service.updateCustomer((ArgumentMatchers.any()))).thenReturn(customer);
		    	String json=mapper.writeValueAsString(address);
				mockMvc.perform(put("/updateCustomer").contentType(org.springframework.http.MediaType.APPLICATION_JSON).characterEncoding("utf-8")
						  .content(json).accept(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
				 

  }
	   @Test
	    public void testDeleteExample() throws Exception {
		   Mockito.when(service.deleteCustomer((ArgumentMatchers.anyInt()))).thenReturn("address is deleted");
	        MvcResult requestResult = mockMvc.perform(delete("/deleteCustomer/{id}",1).param("addressId", "address is deleted"))
	                .andExpect(status().isNotFound()).andExpect(status().isNotFound()).andReturn();
	        String result = requestResult.getResponse().getContentAsString();
	     //
	    }


}
