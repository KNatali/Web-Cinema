package com.example.Bioskop.entity.dto;

import java.util.Date;

public class FilmDTOPretraga {
	private Long id;
	private String naziv;
	private String opis;
	private String zanr;
	private Double srednjaOcjena;
	private int cijena;
	private Date datumOdrzavanja;
	
	public FilmDTOPretraga(Long id, String naziv, String opis, String zanr, Double srednjaOcjena, int cijena,
			Date datumOdrzavanja) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.zanr = zanr;
		this.srednjaOcjena = srednjaOcjena;
		this.cijena = cijena;
		this.datumOdrzavanja = datumOdrzavanja;
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
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getZanr() {
		return zanr;
	}
	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	public Double getSrednjaOcjena() {
		return srednjaOcjena;
	}
	public void setSrednjaOcjena(Double srednjaOcjena) {
		this.srednjaOcjena = srednjaOcjena;
	}
	public int getCijena() {
		return cijena;
	}
	public void setCijena(int cijena) {
		this.cijena = cijena;
	}
	public Date getDatumOdrzavanja() {
		return datumOdrzavanja;
	}
	public void setDatumOdrzavanja(Date datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}
	
	

}
