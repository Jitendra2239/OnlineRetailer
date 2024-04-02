package com.jitendra.service;

import org.springframework.stereotype.Service;

import com.jitendra.model.Inventry;

public interface InventryService {
	public Inventry addLiInventry(Inventry  inventry );
	public String deleteLiInventry(int id);
	public Inventry  updateLiInventry(Inventry inventry );
	public Inventry  searchLiInventry(int id);
	 public Inventry getByProductId(int id);
}
