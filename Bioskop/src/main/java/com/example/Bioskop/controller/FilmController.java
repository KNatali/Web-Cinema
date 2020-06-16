package com.example.Bioskop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Terminski_raspored;
import com.example.Bioskop.service.FilmService;

@Controller
public class FilmController {

	@Autowired
	private FilmService filmService;
	
	
	
	
	@GetMapping("/filmovi")
	public String getFilmovi(Model model) {
		List<Film> filmoviList=this.filmService.findAll();
		model.addAttribute("filmovi",filmoviList);
		
		return "filmovi.html";
	}
	
	
	//uzimanje svih podataka koji se unesu polja i smjestaju u jedan film sa tim poljima
	//Bice posebna stranica sa tim poljima
	@GetMapping("/pretraga")
	public String pretraga(Model model) {
		Film film=new Film();
		model.addAttribute("film", film);
		return "pretraga.html";
	}
	//iz tog modela koji je stavljen u Film uzimam podatke i po njima trazim listu svih filmova
	//pretraga po kriterijumima,moze jedan a i vise istovremeno
	@GetMapping("/film-pretraga")
	public String find(@ModelAttribute Film film,Model model) {
		List<Film> filmoviList=this.filmService.findByKriterijumi(film.getNaziv(), film.getZanr(), film.getOpis(),film.getSrednja_ocjena());
		model.addAttribute("filmoviNaziv",filmoviList);
		return "filmoviNaziv.html";
	}
	
	
	@GetMapping("/filmovi/{id}")
	public String getFilm(@PathVariable(name="id") Long id,Model model) {
		//da dobijem samo taj film
		Film f=this.filmService.findOne(id);
		List<Terminski_raspored> projekcije=f.getProjekcije();
		model.addAttribute("projekcije", projekcije);
		
		
		
		return "film.html";
	}
	
	
}
