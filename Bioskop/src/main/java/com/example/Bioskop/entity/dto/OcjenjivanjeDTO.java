package com.example.Bioskop.entity.dto;

public class OcjenjivanjeDTO {
	private String korisnickoIme;
	private String lozinka;
	private String id;
	private Double ocjena;
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getOcjena() {
		return ocjena;
	}
	public void setOcjena(Double ocjena) {
		this.ocjena = ocjena;
	}
	
	
}
