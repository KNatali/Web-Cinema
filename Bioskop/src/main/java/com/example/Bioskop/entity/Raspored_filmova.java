package com.example.Bioskop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Raspored_filmova implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//raspored ima jedan bioskop,dok bioskop moze biti u vise rasporeda
	@ManyToOne(fetch=FetchType.EAGER)
	private Bioskop bioskop;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Terminski_raspored terminski_raspored;

}
