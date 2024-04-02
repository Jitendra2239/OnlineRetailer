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
import com.jitendra.model.Inventry;
import com.jitendra.service.InventryService;

@WebMvcTest
class InventryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
	private InventryService service;
    private static ObjectMapper mapper = new ObjectMapper();
    private Inventry inventry;
	 @BeforeEach                           
	    void setUp() {  
		 Inventry inventry=new Inventry();
		 inventry= Inventry.builder().inventryId(1).productId(2).quantity(200).build();
	    }
	@Test
	void testgetExample() throws Exception {
		
	
		   Mockito.when(service.searchLiInventry((ArgumentMatchers.anyInt()))).thenReturn(inventry);
		    mockMvc.perform(get("/getinventry/{id}",1)).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));
  
	}
	   @Test
	    public void testPostExample() throws Exception {
		
			Mockito.when(service.addLiInventry((ArgumentMatchers.any()))).thenReturn(inventry);
			String json=mapper.writeValueAsString(inventry);
			mockMvc.perform(post("/addinventry").contentType(org.springframework.http.MediaType.APPLICATION_JSON).characterEncoding("utf-8")
					  .content(json).accept(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
			   .andExpect(jsonPath("$.cartId", Matchers.equalTo(1)));

	    }
	   
	   @Test
	    public void testPutExample() throws Exception {
		
		        Mockito.when(service.updateLiInventry((ArgumentMatchers.any()))).thenReturn(inventry);
		    	String json=mapper.writeValueAsString(inventry);
				mockMvc.perform(put("/updateinventry/{id}",1).contentType(org.springframework.http.MediaType.APPLICATION_JSON).characterEncoding("utf-8")
						  .content(json).accept(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
				 

  }
	   @Test
	    public void testDeleteExample() throws Exception {
		   Mockito.when(service.deleteLiInventry((ArgumentMatchers.anyInt()))).thenReturn("Item is deleted");
	        MvcResult requestResult = mockMvc.perform(delete("/deleteinventry/{id}",1).param("itemId", "Item is deleted"))
	                .andExpect(status().isNotFound()).andExpect(status().isNotFound()).andReturn();
	        String result = requestResult.getResponse().getContentAsString();
	     //
	    }

}