package com.example.Bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.Film;

public interface FilmRepository extends JpaRepository<Film,Long>{

	List<Film> findAllByNazivIgnoreCase(String naziv);
	
	//istovremena pretraga:Naziv,zanr,opis,ocjena,cijena,vrijeme projekcije
	
	//???KKAo da stavim vrijeme i datum i cijenu ako je razlicita za razlicite bioskope
	
	List<Film> findByNazivIgnoreCaseAndZanrIgnoreCaseAndOpisIgnoreCaseAndSrednjaOcjena(String naziv,String zanr,String opis,Double o);
	
	List<Film> findBySrednjaOcjenaBetween(Double ol,Double oh);
	

}
