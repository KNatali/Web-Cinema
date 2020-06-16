package com.example.Bioskop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bioskop.entity.Bioskop;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.service.BioskopService;
import com.example.Bioskop.service.MenadzerService;

@Controller
public class BioskopController {
	
	@Autowired
	private BioskopService bioskopService;
	@Autowired
	private MenadzerService menadzerService;
	
	//trazi sve bioskope i izlistava ih
	@GetMapping("/bioskopi")
	public String getBioskopi(Model model) {
		List<Bioskop> bioskopi=this.bioskopService.findAll();
		model.addAttribute("bioskopi", bioskopi);
		return "bioskopi.html";
	}
	
	//admin poziva za dodavanje bioskopa
	@GetMapping("/bioskop-dodaj")
	public String addBioskop(Model model) {
		Bioskop bioskop=new Bioskop();
		model.addAttribute("bioskop", bioskop);
		return "bioskopAdd.html";
	}

	//sacuvava podatk eod bioskopa koje smo unijeli u polja
	@PostMapping("/save-bioskop")
	public String save(@ModelAttribute Bioskop bioskop) {
		//Menadzer m=bioskop.getMenadzer();
		String korisnik=bioskop.getMenadzer().getKorisnickoIme();
		Menadzer m=this.menadzerService.findKorisnickoIme(korisnik);
		//gleda d ali je unijeti menadzer postojeci
		if(m==null) {
			return "greskaDodajBioskop.html";
		}
		else {
			Bioskop b=new Bioskop();
			b.setNaziv(bioskop.getNaziv());
			b.setAdresa(bioskop.getAdresa());
			b.setBrojCentrale(bioskop.getBrojCentrale());
			b.setEMail(bioskop.getEMail());
			b.setMenadzer(m);
			this.bioskopService.save(b);
			return "redirect:/bioskopi";
		}
		
	}
}
