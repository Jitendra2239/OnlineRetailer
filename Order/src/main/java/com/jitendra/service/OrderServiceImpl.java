package com.jitendra.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jitendra.dao.OrderRepo;
import com.jitendra.dto.OrderResponse;
import com.jitendra.exception.NoSuchCustomerExistsException;
import com.jitendra.model.LineItem;
import com.jitendra.model.OrderEntity;

import io.swagger.v3.oas.annotations.servers.Server;
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepo orderRepo;

	@Override
	public OrderEntity  addOrder(OrderEntity  order) {
		// TODO Auto-generated method stub

		return orderRepo.save(order);
	}

	@Override
	public String deleteOrder(int id) {
		// TODO Auto-generated method stub
		orderRepo.deleteById(id);
		return "Oder Deleted";
	}

	@Override
	public OrderEntity updateOrder(OrderEntity  order) {
		// TODO Auto-generated method stub
		return 	orderRepo.save(order);
		
	}

	@Override
	public  OrderEntity getOder(int id) {
		// TODO Auto-generated method stub
		 OrderEntity order=	orderRepo.findById(id).orElseThrow(()->new NoSuchCustomerExistsException("try with diffrent Id!"));;
		return order;
	}

}
