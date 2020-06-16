package com.example.Bioskop.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Sala implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int kapacitet;
	@Column
	private String oznaka_sale;
	
	//sala pripada jednom bioskopu
	@ManyToOne(fetch = FetchType.EAGER)
	private Bioskop bioskop;
	
	//sala ima vise terminskih rasporeda,ako obrisem salu,obrisace mi se  teminski raspored koji je u njoj
	@OneToMany(mappedBy="sala",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Terminski_raspored> projekcije=new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getOznaka_sale() {
		return oznaka_sale;
	}

	public void setOznaka_sale(String oznaka_sale) {
		this.oznaka_sale = oznaka_sale;
	}

	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}

	public Set<Terminski_raspored> getProjekcije() {
		return projekcije;
	}

	public void setProjekcije(Set<Terminski_raspored> projekcije) {
		this.projekcije = projekcije;
	}
	
	@Override
	public String toString() {
		return "Sala {id=" + id + ", kapacitet=" + kapacitet + ", oznaka_sale=" + oznaka_sale + ",bioskop="+bioskop+"}";
	}
	
}
