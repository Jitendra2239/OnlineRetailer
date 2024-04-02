package com.jitendra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jitendra.modal.CartEntity;
import com.jitendra.modal.LineItem;



public interface CartRepo extends JpaRepository<CartEntity,Integer> {

}
