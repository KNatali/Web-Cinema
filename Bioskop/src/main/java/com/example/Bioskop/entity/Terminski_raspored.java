package com.example.Bioskop.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Terminski_raspored implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//svaki raspored je za po jedan film,dok jedan film moze imati vise raporeda
	@ManyToOne
	@JoinColumn(name="film_id")
	private Film film;
	
	@Column
	private Date datum_odrzavanja;
	@Column
	private String vrijeme_pocetka;
	@Column
	private int cijena;
	@Column
	private int broj_rezervacija;
	
	//persist u slucaju da obrisem terminski raspored,da mi se ne obrise ta sala iz baze
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	private Sala sala;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Date getDatum_odrzavanja() {
		return datum_odrzavanja;
	}
	public void setDatum_odrzavanja(Date datum_odrzavanja) {
		this.datum_odrzavanja = datum_odrzavanja;
	}
	public int getCijena() {
		return cijena;
	}
	public void setCijena(int cijena) {
		this.cijena = cijena;
	}
	public int getBroj_rezervacija() {
		return broj_rezervacija;
	}
	public void setBroj_rezervacija(int broj_rezervacija) {
		this.broj_rezervacija = broj_rezervacija;
	}
	public String getVrijeme_pocetka() {
		return vrijeme_pocetka;
	}
	public void setVrijeme_pocetka(String vrijeme_pocetka) {
		this.vrijeme_pocetka = vrijeme_pocetka;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	@Override
	public String toString() {
		return "Terminski raspored {id=" + id + ", naziv_filma=" + film.getNaziv()+ ", datum_odrzavanja=" + datum_odrzavanja
				+ ", pocetak=" + vrijeme_pocetka + ", cijena=" + cijena + ", broj_rezervacija=" + broj_rezervacija + "}";
	}
	
	
	
	
	

}
