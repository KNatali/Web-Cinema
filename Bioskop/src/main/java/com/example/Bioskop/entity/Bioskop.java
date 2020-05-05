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
public class Bioskop implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String broj_centrale;
	@Column
	private String e_mail;
	
	//jedan bioskop ima jednog menadzera
	//cascade je persist jer ne zelim da mi se obrise menadzer ako obrisem bioskop,posto je taj menadzer mozda zaduzen za jos neki bioskop
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Menadzer menadzer;
	
	//sale koje pripadaju tom bioskopu, dok jedna sala pripada samo jednom bioskopu
	//orphanRemoval je true jer ako se izbrise sala iz liste,treba se izbrisati i iz baze jer sala moze pripadati samo jednom bioskopu
	@OneToMany(mappedBy = "bioskop", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval=true)
    private Set<Sala> sale = new HashSet<>();
	
	//bioskop ima vise rasporeda
	@OneToMany(mappedBy="bioskop",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Raspored_filmova> rasporedi=new HashSet<>();
	

	
	
	


	public Set<Raspored_filmova> getRasporedi() {
		return rasporedi;
	}

	public void setRasporedi(Set<Raspored_filmova> rasporedi) {
		this.rasporedi = rasporedi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBroj_centrale() {
		return broj_centrale;
	}

	public void setBroj_centrale(String broj_centrale) {
		this.broj_centrale = broj_centrale;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public Menadzer getMenadzer() {
		return menadzer;
	}

	public void setMenadzer(Menadzer menadzer) {
		this.menadzer = menadzer;
	}

	public Set<Sala> getSale() {
		return sale;
	}

	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}
	
	@Override
	public String toString() {
		return "Bioskop {id=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", broj_centrale=" + broj_centrale
				+ ", e_mail=" + e_mail + "}";
	}
	
}
