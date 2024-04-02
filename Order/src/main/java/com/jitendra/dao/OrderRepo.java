package com.jitendra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jitendra.model.LineItem;
import com.jitendra.model.OrderEntity;

public interface OrderRepo extends JpaRepository<OrderEntity, Integer>{

}
