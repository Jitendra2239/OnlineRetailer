package com.jitendra.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import com.jitendra.dto.ItemResponse;
import com.jitendra.dto.OrderResponse;
import com.jitendra.exception.BadRequestException;
import com.jitendra.exception.ItemNotFoundException;
import com.jitendra.exception.OrderNotFoundException;

import com.jitendra.model.LineItem;
import com.jitendra.model.OrderEntity;
import com.jitendra.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/item/{id}")
	public ResponseEntity<ItemResponse> addLineItem(@PathVariable int id ,@RequestBody LineItem item) {
		if (item.equals(" ")) {
			throw new BadRequestException("Bad Request");
		}
		OrderEntity order=   orderService.getOder(id);

		List<LineItem> list =  order.getItem();
		list.add(item);
		order.setItem(list);
		OrderEntity order1=  orderService.updateOrder(order);
	
		List<LineItem> item1 = order1.getItem();
		Iterator<LineItem> itr=item1.iterator();
		ItemResponse response1 = new ItemResponse();
		while(itr.hasNext()) {
		LineItem item2=	itr.next();
		if(item2.getProductName()==item.getProductName() && item2.getPrice()==item.getPrice()
				&& item2.getProductId()==item.getProductId() && item2.getQuantity()==item.getQuantity()) {
		
	    response1.setQuantity(item2.getQuantity());
		response1.setItemId(item2.getItemId());
		response1.setProducetName(item2.getProductName());
		response1.setProductId(item2.getProductId());
		response1.setPrice(item2.getPrice());
		}
		}
		return new ResponseEntity<>(response1, HttpStatus.CREATED);
	}

	@DeleteMapping("/item/{id}")
	public ResponseEntity<String> deleteLineItem(@PathVariable int id) {
		OrderEntity order=   orderService.getOder(id);
		order.setItem(null);
		OrderEntity order1=   orderService.updateOrder(order);
		  String s="some issue with deleting Address";
		  if(order.getItem()==null) {
	    	   s="Item  deleted from Order!";
	       }
	    	
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@PutMapping("/item{id}")
	public ResponseEntity<ItemResponse> updateLineItem(@PathVariable int id, @RequestBody LineItem item) {
		OrderEntity order=   orderService.getOder(id);

		List<LineItem> item1 = order.getItem();
		Iterator<LineItem> itr=item1.iterator();
		List<LineItem>ss=new ArrayList<>();
		while(itr.hasNext()) {
		LineItem item2=	itr.next();
		if(item2.getProductName()==item.getProductName() || item2.getPrice()==item.getPrice()
				|| item2.getProductId()==item.getProductId() || item2.getQuantity()==item.getQuantity()) {
		
			item2.setQuantity(item.getQuantity());
			item2.setItemId(item.getItemId());
			item2.setProductName(item.getProductName());
			item2.setProductId(item.getProductId());
			item2.setPrice(item.getPrice());
		
		}
		ss.add(item2);
		}
	    	order.setItem(ss);
		OrderEntity order1=   orderService.updateOrder(order);
		ItemResponse response1 = new ItemResponse();
		List<LineItem> item4 = order1.getItem();
		Iterator<LineItem> itr1=item4.iterator();
	
		while(itr1.hasNext()) {
		LineItem item2=	itr1.next();
		if(item2.getProductName()==item.getProductName() && item2.getPrice()==item.getPrice()
				&& item2.getProductId()==item.getProductId() && item2.getQuantity()==item.getQuantity()) {
		 response1.setItemId(item2.getItemId());
		response1.setProducetName(item2.getProductName());
		response1.setProductId(item2.getProductId());
		response1.setPrice(item2.getPrice());
		response1.setQuantity(item2.getQuantity());
		}
		}
		return new ResponseEntity<>(response1, HttpStatus.OK);
	}
	@GetMapping("/hello")
	public String hello() {
  return "Hello from getway!";
	}

	@GetMapping("/item/{id}")
	public ResponseEntity<List<LineItem>> getLineItem(@PathVariable int id) {

		OrderEntity order=   orderService.getOder(id);

		if (order.getItem() == null) {
			throw new ItemNotFoundException("Item not present");
		}
		List<LineItem> list=order.getItem();
		

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/order")
	public ResponseEntity<OrderResponse> addorder(@RequestBody OrderEntity order) {
		OrderEntity order1 = new OrderEntity();
	
		if (order.getItem().isEmpty() || order.getItem() == null) {
			throw new BadRequestException("add item in the cart");
		}
		
		List<LineItem> list = new ArrayList<>();
		LineItem item = new LineItem();
		 for (LineItem it : order.getItem()) {
           
		   item.setProductName(it.getProductName());
			item.setItemId(it.getItemId());
			item.setQuantity(it.getQuantity());
		
			item.setProductId(it.getProductId());
			item.setPrice(it.getPrice());
     System.out.println(item.getProductName());
			list.add(item);
		}
		order1.setItem(list);
		OrderEntity order2 = orderService.addOrder(order1);
		OrderResponse response = new OrderResponse();
	        	List<ItemResponse> list1 = new ArrayList<>();
	    	for (LineItem it : order2.getItem()) {
			ItemResponse response1 = new ItemResponse();
			response1.setItemId(it.getItemId());
			response1.setProducetName(it.getProductName());
			response1.setProductId(it.getProductId());
			response1.setQuantity(it.getQuantity());
			response1.setPrice(it.getPrice());

			list1.add(response1);
		}
		response.setItem(list1);

		response.setOrderId(order1.getOrderId());

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/order/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable int id) {
		OrderEntity order1 = orderService.getOder(id);
	
		String s1 = orderService.deleteOrder(id);
		return new ResponseEntity<>(s1, HttpStatus.OK);
	}

	@PutMapping("/order/{id}")
	public ResponseEntity<OrderResponse> updateOrder(@PathVariable int id, @RequestBody OrderEntity order) {
		OrderEntity order1 = orderService.getOder(id);
	

		List<LineItem> list = new ArrayList<>();
		for (LineItem item : order.getItem()) {
			LineItem item1 = new LineItem();
			item1.setItemId(item.getItemId());
			item1.setPrice(item.getPrice());
			item1.setProductId(item.getProductId());
			item1.setQuantity(item.getQuantity());
			item1.setProductName(item.getProductName());
	
			list.add(item1);
		}

		OrderEntity order2 = OrderEntity.builder().orderId(id).item(list).build();
		OrderEntity order3 = orderService.updateOrder(order2);
		OrderResponse response = new OrderResponse();
		List<ItemResponse> list1 = new ArrayList<>();
		for (LineItem it : order3.getItem()) {
			ItemResponse response1 = new ItemResponse();
			response1.setItemId(it.getItemId());
			response1.setProducetName(it.getProductName());
			response1.setProductId(it.getProductId());
			response1.setPrice(it.getPrice());
			response1.setQuantity(it.getQuantity());
			list1.add(response1);
		}
		response.setItem(list1);

		response.setOrderId(order3.getOrderId());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<OrderResponse> searchOrder(@PathVariable int id) {

		OrderEntity order1 = orderService.getOder(id);
		if (order1 == null) {
			throw new OrderNotFoundException("Order not Found");
		}
		OrderResponse response = new OrderResponse();

		List<ItemResponse> list = new ArrayList<>();
		for (LineItem it : order1.getItem()) {
			ItemResponse response1 = new ItemResponse();
			response1.setItemId(it.getItemId());
			response1.setProducetName(it.getProductName());
			response1.setProductId(it.getProductId());
			response1.setPrice(it.getPrice());
			response1.setQuantity(it.getQuantity());
			list.add(response1);
		}
		response.setItem(list);

		response.setOrderId(order1.getOrderId());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
