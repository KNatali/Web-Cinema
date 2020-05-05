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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Film implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	@Column
	private String opis;
	@Column
	private String zanr;
	@Column
	private int trajanje;
	@Column
	private double srednja_ocjena;
	
	//druga strana veze
	@ManyToMany(mappedBy="odgledani_filmovi")
	private Set<Gledalac> gledaoci_koji_su_odgledali_film=new HashSet<>();
	
	@ManyToMany(mappedBy="rezervisani_filmovi")
	private Set<Gledalac> gledaoci_koji_su_rezervisali_film=new HashSet<>();

	//druga strana veze,film ima vise ocjena
	@OneToMany(mappedBy="film",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Ocjena> ocjene=new HashSet<>();
	
	
	
	
	public Set<Ocjena> getOcjene() {
		return ocjene;
	}

	public void setOcjene(Set<Ocjena> ocjene) {
		this.ocjene = ocjene;
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

	public double getSrednja_ocjena() {
		return srednja_ocjena;
	}

	public void setSrednja_ocjena(double srednja_ocjena) {
		this.srednja_ocjena = srednja_ocjena;
	}

	public Set<Gledalac> getGledaoci_koji_su_odgledali_film() {
		return gledaoci_koji_su_odgledali_film;
	}

	public void setGledaoci_koji_su_odgledali_film(Set<Gledalac> gledaoci_koji_su_odgledali_film) {
		this.gledaoci_koji_su_odgledali_film = gledaoci_koji_su_odgledali_film;
	}

	public Set<Gledalac> getGledaoci_koji_su_rezervisali_film() {
		return gledaoci_koji_su_rezervisali_film;
	}

	public void setGledaoci_koji_su_rezervisali_film(Set<Gledalac> gledaoci_koji_su_rezervisali_film) {
		this.gledaoci_koji_su_rezervisali_film = gledaoci_koji_su_rezervisali_film;
	}
	
	@Override
	public String toString() {
		return "Film {id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", zanr=" + zanr + ", trajanje=" + trajanje
				+ ", srednja_ocena=" + srednja_ocjena + "}";
	}
	
	
	

}
