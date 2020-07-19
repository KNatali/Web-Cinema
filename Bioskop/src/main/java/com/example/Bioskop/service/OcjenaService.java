package com.example.Bioskop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Ocjena;
import com.example.Bioskop.repository.OcjenaRepository;

@Service
public class OcjenaService {

	@Autowired
	private OcjenaRepository ocjenaRepository;
	
	public Ocjena save(Ocjena o) {
		return this.ocjenaRepository.save(o);
	}
}
