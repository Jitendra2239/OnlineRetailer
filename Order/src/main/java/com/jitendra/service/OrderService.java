package com.jitendra.service;

import java.util.List;

import com.jitendra.dto.ItemResponse;
import com.jitendra.dto.OrderResponse;
import com.jitendra.model.LineItem;
import com.jitendra.model.OrderEntity;

public interface OrderService {
	//public LineItem addLineItem(LineItem item);
	//public String deleteLineItem(int id);
	//public LineItem  updateLineItem(LineItem item);
	//public LineItem searchLineItem(int id);
	public OrderEntity  addOrder(OrderEntity  order);
	public String deleteOrder(int id);
	public  OrderEntity   updateOrder(OrderEntity  order);
	public OrderEntity getOder(int id);
}
