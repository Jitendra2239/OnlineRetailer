package com.jitendra.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jitendra.dto.InventryResponse;
import com.jitendra.exception.BadRequestException;
import com.jitendra.exception.InventryNotFoundException;
import com.jitendra.model.Inventry;
import com.jitendra.service.InventryService;
@RestController
@RequestMapping("/api")
public class InventryController {
	   int counter=1;
	@GetMapping("/get")
	public String hello()
	{
		return "hello";
	}
      
	  @Autowired
	  InventryService inventryService;
   @PostMapping("/addinventry")
	public ResponseEntity<InventryResponse> addLiInventry(@RequestBody Inventry inventry) {
		// TODO Auto-generated method stub
	
	    inventry.setInventryId(counter++);
	       if(inventry.getProductId()<0) {
		   throw new BadRequestException("Add Product Id") ;
	   }
	   if(inventry.getQuantity()<=0) {
		   throw new BadRequestException("Add producr Quantity") ;
	   }
	   Inventry inventry1=  inventryService.addLiInventry(inventry);
	   InventryResponse response =new InventryResponse();
	   response.setInventryId(inventry1.getInventryId());
	   response.setProductId(inventry1.getProductId());
	   response.setQuantity(inventry1.getQuantity());
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}


     @DeleteMapping("/deleteinventry/{id}")
	public ResponseEntity<String> deleteLInventry(@PathVariable int id) {
	 
    	 Inventry inventry = 	 inventryService.searchLiInventry(id);
         if(inventry==null) {
  		   throw new InventryNotFoundException("Item not Found!");
  	   }
	String s= inventryService.deleteLiInventry(id);
	 counter--;
	return new ResponseEntity<>(s,HttpStatus.OK);
	}

     @PutMapping("/updateinventry/{id}")
	public ResponseEntity<InventryResponse> updateLiInventry(@PathVariable int id,@RequestBody 	Inventry inventry) {
		// TODO Auto-generated method stub
    
    	Inventry inventry1 = 	 inventryService.searchLiInventry(id);
    	inventry1.setInventryId(inventry1.getInventryId());
    	inventry1.setProductId(inventry.getProductId());
    	inventry1.setQuantity(inventry.getQuantity());
    	Inventry inventry2= 	inventryService.updateLiInventry(inventry1 );
    	 InventryResponse response =new InventryResponse();
  	   response.setInventryId(inventry2.getInventryId());
  	   response.setProductId(inventry2.getProductId());
  	   response.setQuantity(inventry2.getQuantity());
  	  
 	return new ResponseEntity<>(response,HttpStatus.OK);
	}

    @GetMapping("/getinventry/{id}")
	public ResponseEntity< InventryResponse>  searchLiInventry(@PathVariable int id) {
	
    	  
		Inventry inventry = inventryService.searchLiInventry(id);
	     if(inventry==null) {
	  		   throw new InventryNotFoundException("Item not Found!");
	  	   }
		
    	  InventryResponse response =new InventryResponse();
   	   response.setInventryId(inventry.getInventryId());
   	  response.setProductId(inventry.getProductId());
   	     response.setQuantity(inventry.getQuantity());
   	   
    	return new ResponseEntity<>(response,HttpStatus.OK);
	 }
    @GetMapping("/inventry/{productid}")
	public ResponseEntity< InventryResponse>  getInventry(@PathVariable int productid) {
	
    	Inventry inventry = inventryService.getByProductId(productid);
		if(inventry==null)
			throw new InventryNotFoundException("");
		
    	  InventryResponse response =new InventryResponse();
   	   response.setInventryId(inventry.getInventryId());
   	   response.setProductId(inventry.getProductId());
   	   response.setQuantity(inventry.getQuantity());
    	return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
