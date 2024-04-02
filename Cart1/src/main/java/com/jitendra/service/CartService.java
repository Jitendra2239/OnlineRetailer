package com.jitendra.service;

import org.springframework.stereotype.Service;

import com.jitendra.modal.CartEntity;
import com.jitendra.modal.LineItem;


@Service
public interface CartService {
	public  CartEntity addcart( CartEntity entity);
	public  LineItem addItem(LineItem item);
	public String deleteItem(int id);
	public String deleteCart(int id);
	public  CartEntity updateCart(CartEntity entity);
	public  LineItem updateItem(LineItem item);
	public CartEntity getCart(int id);
	public LineItem getItem(int id);
}
