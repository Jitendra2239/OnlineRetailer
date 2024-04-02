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

import javax.net.ssl.SSLEngineResult.Status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitendra.modal.CartEntity;
import com.jitendra.modal.LineItem;
import com.jitendra.service.CartService;

import io.swagger.v3.oas.models.media.MediaType;
@WebMvcTest
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
	private CartService service;
    private static ObjectMapper mapper = new ObjectMapper();
	 @BeforeEach                           
	    void setUp() {  
		 CartEntity  entity=new CartEntity();
		 entity.setCartId(1);
		
	       List<LineItem>list = new ArrayList<>();                                  
            LineItem item=new LineItem();                                          
	        item.setItemId(1);
	        item.setPrice(100);
	        item.setProductName("prodcuts1");
	        item.setProductId(2);
	        entity.setItem(list);
	    }
	@Test
	void testgetExample() throws Exception {
		 LineItem item=new LineItem();                                          
	        item.setItemId(1);
	        item.setPrice(100);
	        item.setProductName("prodcuts1");
	        item.setProductId(2);
		   Mockito.when(service.getItem((ArgumentMatchers.anyInt()))).thenReturn(item);
		    mockMvc.perform(get("/getItem/{id}",1)).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));
  
	}
	   @Test
	    public void testPostExample() throws Exception {
		   CartEntity  entity=new CartEntity();
			 entity.setCartId(1);
			
		       List<LineItem>list = new ArrayList<>();                                  
	            LineItem item=new LineItem();                                          
		        item.setItemId(1);
		        item.setPrice(100);
		        item.setProductName("prodcuts1");
		        item.setProductId(2);
		        list.add(item);
		        entity.setItem(list);
			Mockito.when(service.addcart((ArgumentMatchers.any()))).thenReturn(entity);
			String json=mapper.writeValueAsString(entity);
			mockMvc.perform(post("/cart").contentType(org.springframework.http.MediaType.APPLICATION_JSON).characterEncoding("utf-8")
					  .content(json).accept(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
			   .andExpect(jsonPath("$.CartId", Matchers.equalTo(1)));

	    }
	   
	   @Test
	    public void testPutExample() throws Exception {
		   CartEntity  entity=new CartEntity();
			 entity.setCartId(1);
			
		      List<LineItem>list = new ArrayList<>();                                  
	            LineItem item=new LineItem();                                          
		        item.setItemId(1);
		        item.setPrice(1000);
		        item.setProductName("prodcuts1");
		        item.setProductId(2);
		        list.add(item);
		        entity.setItem(list);
		        Mockito.when(service.updateCart((ArgumentMatchers.any()))).thenReturn(entity);
		    	String json=mapper.writeValueAsString(item);
				mockMvc.perform(put("/updatecart").contentType(org.springframework.http.MediaType.APPLICATION_JSON).characterEncoding("utf-8")
						  .content(json).accept(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
				 

  }
	   @Test
	    public void testDeleteExample() throws Exception {
		   Mockito.when(service.deleteItem((ArgumentMatchers.anyInt()))).thenReturn("Item is deleted");
	        MvcResult requestResult = mockMvc.perform(delete("/deletecart/{id}",1).param("itemId", "Item is deleted"))
	                .andExpect(status().isNotFound()).andExpect(status().isNotFound()).andReturn();
	        String result = requestResult.getResponse().getContentAsString();
	     //
	    }

}
