package com.example.Bioskop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bioskop.entity.Administrator;
import com.example.Bioskop.entity.Gledalac;
import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.service.AdministratorService;
import com.example.Bioskop.service.GledalacService;
import com.example.Bioskop.service.KorisnikService;
import com.example.Bioskop.service.MenadzerService;

@Controller
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	@Autowired
	private GledalacService gledalacService;
	@Autowired
	private MenadzerService menadzerService;
	@Autowired
	private AdministratorService administratorService;
	
	//pocetna stranica
	@GetMapping("/")
	public String welcome() {
		return "naslovna.html";
	}
	
	//za logovanje ,akcija je get-korisnik
	@GetMapping("/login")
	public String login(Model model) {
		Korisnik korisnik=new Korisnik();
		model.addAttribute("korisnik", korisnik);
		
		return "login.html";
	}
	
	//dodavanje novog korisnika,tj prikupljanje podataka
	@GetMapping("/register")
	public String register(Model model) {
		Korisnik korisnik=new Korisnik();
		model.addAttribute("korisnik",korisnik);
		return "register.html";
	}
	//cuvanje tih podataka prema odgovarajucoj ulozi
	//poziva se nakon sto registraciju popunim
	@PostMapping("/save-korisnik")
	public String save(@ModelAttribute Korisnik korisnik) {
		if(korisnik.getUloga().equalsIgnoreCase("Gledalac")) {
			this.gledalacService.registracija(korisnik);
			return "login.html";
		}else {
			this.menadzerService.registracija(korisnik);
			return "login.html";
		}
	
	}
	//nakon stp ga admin registruje
	@PostMapping("/save-menadzer")
	public String saveMenadzer(@ModelAttribute Menadzer menadzer) {
		menadzer.setAktivan(true);
		this.menadzerService.save(menadzer);
		return "naslovna.html";
	}
	
	//trazenje korisnika kada hoce da se prijavi
	//poziva e nakon sto se popuni login
	@GetMapping("/get-korisnik")
	public String find(@ModelAttribute Korisnik korisnik,Model model) {
		Gledalac g=this.gledalacService.Find(korisnik.getKorisnickoIme(),korisnik.getLozinka());
		Menadzer m=this.menadzerService.find(korisnik.getKorisnickoIme(),korisnik.getLozinka());
		Administrator a=this.administratorService.find(korisnik.getKorisnickoIme(), korisnik.getLozinka());
		
		model.addAttribute("gledalac",g);
		model.addAttribute("admin", a);
		model.addAttribute("menadzer", m);
		//gledam da li pripada nekoj frupi i vracam stranicu koja treba biti greska
		if(g!=null) {
			return "gledalacNaslovna.html";
		}
		else if(m!=null) {
			//ako je menadzer mora prvo da se vidi d ali je aktivan ili ne
			if(m.isAktivan()) {//ako jeste ide na svoj profil
				return "menadzerNaslovna.html";
			}else {
				return "greska.html";
			}
		}
		else if(a!=null) {
			return "adminNaslovna.html";
		}
		else {
			return "login.html";
		}
		
	}
	//popunjavanje za dodavanje menadzera,kod admina
	@GetMapping("/add-Menadzer")
	public String addMenadzerr(Model model) {
		Menadzer m=new Menadzer();
		model.addAttribute("menadzer",m);
		return "registerMenadzer.html";
	}
	
	//prikaz neaktivnih menadzera
	@GetMapping("/register-Menadzer")
	public String registerMenadzer(Model model) {
		//trazi sve menadzere koji su neaktivni
		List<Menadzer> menadzeri=this.menadzerService.findByAktivan(false);
		model.addAttribute("menadzeri",menadzeri);
		return "menadzeriNeaktivni.html";
	}
	
	//za aktiviranje korisnika kad se kklikne na tu tipku
	@GetMapping("/menadzeri/{id}")
	public String aktiviraj(@PathVariable(name="id") Long id,Model model) {
		Menadzer m=this.menadzerService.findOne(id);
		m.setAktivan(true);
		this.menadzerService.save(m);
		model.addAttribute("menader", m);
		return "redirect:/register-Menadzer";
		
	}
}
