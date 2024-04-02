package com.jitendra.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitendra.dao.InventryRepo;
import com.jitendra.exception.InventryNotFoundException;
import com.jitendra.model.Inventry;

@Service
public class InventryServiceImpl implements InventryService {

	@Autowired
	 private InventryRepo inventryRepo;
	@Override
	public Inventry addLiInventry(Inventry  inventry ) {
		// TODO Auto-generated method stub
	return 	inventryRepo.save(inventry );
	
	}

	@Override
	public String deleteLiInventry(int id) {
		// TODO Auto-generated method stub
		inventryRepo.deleteById(id);;
		return "Deleted";
	}

	@Override
	public Inventry  updateLiInventry(Inventry  inventry ) {
		// TODO Auto-generated method stub
	return	inventryRepo.save(inventry);
	
	}

	@Override
	public Inventry searchLiInventry(int id) {
		// TODO Auto-generated method stub
	return	inventryRepo.findById(id).orElse(null);
	
	}

	@Override
	public Inventry getByProductId(int id) {
		// TODO Auto-generated method stub
		return inventryRepo.findByProductId(id);
	}

}
