package com.example.Bioskop.entity.dto;

public class FilmDTO {
	private Long id;
	private String naziv;
	private String opis;
	private String zanr;
	private int trajanje;
	private Double srednjaOcjena;
	private Long gledalacId;
	
	
public FilmDTO() {}
	
	
	public FilmDTO(Long id, String naziv, String opis, String zanr, int trajanje, Double srednjaOcjena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.zanr = zanr;
		this.trajanje = trajanje;
		this.srednjaOcjena = srednjaOcjena;
	}
	
	
	public Long getGledalacId() {
		return gledalacId;
	}
	public void setGledalacId(Long gledalacId) {
		this.gledalacId = gledalacId;
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
	public int getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	public Double getSrednjaOcjena() {
		return srednjaOcjena;
	}
	public void setSrednjaOcjena(Double srednjaOcjena) {
		this.srednjaOcjena = srednjaOcjena;
	}
	
	
}
