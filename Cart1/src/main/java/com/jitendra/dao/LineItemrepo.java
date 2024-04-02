package com.jitendra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jitendra.modal.LineItem;



public interface LineItemrepo extends JpaRepository<LineItem,Integer>{

}
