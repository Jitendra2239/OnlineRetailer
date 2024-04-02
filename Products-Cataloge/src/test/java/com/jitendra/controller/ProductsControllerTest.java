package com.jitendra.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.jitendra.model.Products;
import com.jitendra.service.ProductsService;

@WebMvcTest
class ProductsControllerTest {

	   @Autowired
	    private MockMvc mockMvc;
	    @MockBean
		private ProductsService service;
	    private static ObjectMapper mapper = new ObjectMapper();
	    Products products;
		 @BeforeEach                           
		    void setUp() {  
	
			 products=  Products.builder().productId(1).productDescrition("charing").productPrice(200).build();
		    }
		@Test
		void testgetExample() throws Exception {
			
		
			   Mockito.when(service.searchProducts((ArgumentMatchers.anyInt()))).thenReturn(products);
			    mockMvc.perform(get("/product/{id}",1)).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));
	  
		}
		   @Test
		    public void testPostExample() throws Exception {
			
				Mockito.when(service.addProducts((ArgumentMatchers.any()))).thenReturn(products);
				String json=mapper.writeValueAsString(products);
				mockMvc.perform(post("/addcart").contentType(org.springframework.http.MediaType.APPLICATION_JSON).characterEncoding("utf-8")
						  .content(json).accept(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				   .andExpect(jsonPath("$.cartId", Matchers.equalTo(1)));

		    }
		   
		   @Test
		    public void testPutExample() throws Exception {
			
			        Mockito.when(service.updateProducts((ArgumentMatchers.any()))).thenReturn(products);
			    	String json=mapper.writeValueAsString(products);
					mockMvc.perform(put("/updatecart").contentType(org.springframework.http.MediaType.APPLICATION_JSON).characterEncoding("utf-8")
							  .content(json).accept(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
					 

	  }
		   @Test
		    public void testDeleteExample() throws Exception {
			   Mockito.when(service.deletProducts((ArgumentMatchers.anyInt()))).thenReturn("Item is deleted");
		        MvcResult requestResult = mockMvc.perform(delete("/deletecart/{id}",1).param("itemId", "Item is deleted"))
		                .andExpect(status().isNotFound()).andExpect(status().isNotFound()).andReturn();
		        String result = requestResult.getResponse().getContentAsString();
		     //
		    }

}
