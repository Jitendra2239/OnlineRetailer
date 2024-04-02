package com.jitendra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitendra.dao.CartRepo;
import com.jitendra.dao.LineItemrepo;
import com.jitendra.modal.CartEntity;
import com.jitendra.modal.LineItem;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepo repo;
	@Autowired
	private LineItemrepo repo1;

	@Override
	public String deleteCart(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		return "Deleted";
	}

	@Override
	public CartEntity updateCart(CartEntity entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public CartEntity getCart(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	public CartEntity addcart(CartEntity entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public LineItem getItem(int id) {
		// TODO Auto-generated method stub
		return repo1.findById(id).orElse(null);
	}

	@Override
	public String deleteItem(int id) {
		// TODO Auto-generated method stub
		repo1.deleteById(id);
		return "Deleted";
	}

	@Override
	public LineItem updateItem(LineItem item) {
		// TODO Auto-generated method stub
		return repo1.save(item);
	}

	@Override
	public LineItem addItem(LineItem item) {
		// TODO Auto-generated method stub
		return repo1.save(item);
	}

}
