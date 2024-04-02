package com.jitendra.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jitendra.dto.CartResponse;
import com.jitendra.dto.ItemResponse;

import com.jitendra.exception.BadRequestException;
import com.jitendra.exception.ItemNotFoundException;
import com.jitendra.modal.CartEntity;
import com.jitendra.modal.LineItem;

import com.jitendra.service.CartService;
@RestController
@RequestMapping("/api")
public class CartController {
@Autowired
private CartService service;


@PostMapping("/cart")
public ResponseEntity<CartResponse> addCart(@RequestBody CartEntity entity) {
	CartResponse response=new CartResponse();
	CartEntity entity2 ;
//	if (entity.getItem().isEmpty() || entity.getItem() == null) {
//		throw new BadRequestException("add item in the cart");
//	}
 	  if(entity.getItem() == null) {
	 entity2 = service.addcart(entity);
	      response.setCartId(entity2.getCartId());
	}
	    else {
	
	    CartEntity entity1=new CartEntity();
	
	List<LineItem> list = new ArrayList<>();
	LineItem item = new LineItem();
	for (LineItem it : entity.getItem()) {
 
		item.setItemId(it.getItemId());
		item.setProductName(it.getProductName());
		item.setProductId(it.getProductId());
		item.setPrice(it.getPrice());
        item.setQuantity(it.getQuantity());
		item.setCart(entity1);
		list.add(item);
	}
	entity1.setItem(list);
     entity2 = service.addcart(entity1);
	
	List<ItemResponse> list1=new ArrayList<>();
	
	response.setCartId(entity2.getCartId());
	ItemResponse response1 = new ItemResponse();
	for (LineItem it : entity2.getItem()) {
	
		response1.setItemId(it.getItemId());
		response1.setProducetName(it.getProductName());
		response1.setProductId(it.getProductId());
		response1.setPrice(it.getPrice());
		response1.setQuantity(it.getQuantity());
		list1.add(response1);
	}
	response.setItem(list1);
	}
	return new ResponseEntity<>(response, HttpStatus.CREATED);
}

       @DeleteMapping("deletecart/{id}")
	  public ResponseEntity<String> deleteCart(@PathVariable int id) {
    	CartEntity entity=    service.getCart(id);
    	   String s1;
    	   if(entity==null)
    	   {
    		   throw new ItemNotFoundException("id: "+ id);  
    		
    	   }
    	   else {
    	 s1=service.deleteCart(id);
    	   }
		return new ResponseEntity<>(s1,HttpStatus.OK);
	}

	@PutMapping("/updatecart/{id}")
	public ResponseEntity<CartResponse> updateCart(@PathVariable int id, @RequestBody CartEntity entity) {

		CartEntity entity1 = service.getCart(id);

	  	   if(entity1==null)
	  	   {
	  		   throw new ItemNotFoundException("");  
	  		
	  	   }
	  	   int k=0;
		LineItem item1 = new LineItem();
		List<LineItem> list = new ArrayList<>();
		for (LineItem item : entity.getItem()) {
			LineItem it=service.getItem(entity1.getItem().get(k).getItemId());
      		it.setItemId(entity1.getItem().get(k++).getItemId());
			it.setPrice(item.getPrice());
			it.setProductId(item.getProductId());
			it.setQuantity(item.getQuantity());
			it.setProductName(item.getProductName());
	        service.updateItem(it);
			list.add(it);
		}

		CartEntity cart = CartEntity.builder().cartId(id).item(list).build();
		  

		CartResponse response = new CartResponse();
		List<ItemResponse> list1 = new ArrayList<>();
		ItemResponse response1 = new ItemResponse();
		for (LineItem it : cart.getItem()) {
		
			response1.setItemId(it.getItemId());
			response1.setProducetName(it.getProductName());
			response1.setProductId(it.getProductId());
			response1.setPrice(it.getPrice());
			response1.setQuantity(it.getQuantity());
			list1.add(response1);
		}
		response.setItem(list1);

		response.setCartId(cart.getCartId());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    @GetMapping("/getcart/{id}")
	public ResponseEntity< CartResponse>getCart(@PathVariable int id) {
    	 CartResponse response =new CartResponse();
    	 List<ItemResponse>list=new ArrayList<>();
    	CartEntity entity1=   service.getCart(id);

  	   if(entity1==null)
  	   {
  		   throw new ItemNotFoundException("");  
  	
  	   }
  	   else {
  		
  	 response.setCartId(entity1.getCartId());
  		 
  		 ItemResponse res=new ItemResponse();
  		 for(LineItem it:entity1.getItem()) {
  			res.setItemId(it.getItemId());
  			res.setPrice(it.getPrice());
  			res.setProductId(it.getProductId());
  			res.setQuantity(it.getQuantity());
  			res.setProducetName(it.getProductName());
  			list.add(res); 
  		 }
  		 }
  	
  	  response.setItem(list);
  	 
  	return new ResponseEntity<>(response,HttpStatus.OK);
	}
    @GetMapping("/getitem/{id}")
 	public ResponseEntity<ItemResponse>getItem(@PathVariable int id) {
    	 ItemResponse res=new ItemResponse();
     	LineItem it=   service.getItem(id);

   	   if(it==null)
   	   {
   		   throw new ItemNotFoundException("");  
   		
   	   }
   	res.setItemId(it.getItemId());
		res.setPrice(it.getPrice());
		res.setProductId(it.getProductId());
		res.setQuantity(it.getQuantity());
		res.setProducetName(it.getProductName());
	
   	return new ResponseEntity<>(res,HttpStatus.OK);
 	}
   
    @PostMapping("/additem/{id}")
    public ResponseEntity<LineItem> addLineItem(@PathVariable int id,@RequestBody LineItem item) {

    	CartEntity entity1 = service.getCart(id);

	  	   if(entity1==null)
	  	   {
	  		   throw new ItemNotFoundException("");  
	  		
	  	   }
	  	   int k=0;
		LineItem item1 = new LineItem();
		List<LineItem> list = new ArrayList<>();
         list.add(item);
         item.setCart(entity1);
         entity1.setItem(list);
		CartEntity cart = CartEntity.builder().cartId(id).item(list).build();
	


	        service.updateCart(entity1);
			
	

    	return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    @DeleteMapping("deleteitem/{id}")
	 public ResponseEntity<String> deleteLineItem(@PathVariable int id) {
    	LineItem item=    service.getItem(id);
    	   String s1;
    	   if(item==null)
    	   {
    		   throw new ItemNotFoundException("id: "+ id);  
    		
    	         }
    	   else {
    	 s1=service.deleteItem(id);
    	   }
		return new ResponseEntity<>(s1,HttpStatus.OK);
	}

	@PutMapping("/updateitem/{id}")
	public ResponseEntity<LineItem> updateLineItem(@PathVariable int id, @RequestBody LineItem item) {

		
	   LineItem it = service.getItem(id);
		
		if (it == null) {
			throw new ItemNotFoundException("");

		}
		CartEntity cart = CartEntity.builder().cartId(id).build();
		List<LineItem> list = new ArrayList<>();
		it.setItemId(id);
		// it.setCart(cart);
		it.setPrice(item.getPrice());
		it.setProductId(item.getProductId());
		it.setQuantity(item.getQuantity());
		it.setProductName(item.getProductName());

		list.add(it);
		cart.setItem(list);
		service.updateItem(it);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
    
}
