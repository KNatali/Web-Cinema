package com.example.Bioskop.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Gledalac;
import com.example.Bioskop.entity.Ocjena;
import com.example.Bioskop.entity.Terminski_raspored;
import com.example.Bioskop.entity.dto.FilmDTO;
import com.example.Bioskop.entity.dto.OcjenjivanjeDTO;
import com.example.Bioskop.entity.dto.Terminski_rasporedDTO;
import com.example.Bioskop.service.FilmService;
import com.example.Bioskop.service.GledalacService;
import com.example.Bioskop.service.OcjenaService;
import com.example.Bioskop.service.Terminski_rasporedService;

@RestController
@RequestMapping(value="/api")
public class GledalacController {
	

	@Autowired
	private GledalacService gledalacService;
	@Autowired
	private Terminski_rasporedService terminski_rasporedService;
	@Autowired
	private FilmService filmService;
	@Autowired 
	private OcjenaService ocjenaService;
	@GetMapping(
			value="/gledalac-rezervisaneKarte/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Terminski_rasporedDTO>> rezervisaneKarte(@PathVariable(name="id")Long id){
		 Gledalac g=this.gledalacService.findOne(id);
		 Set<Terminski_raspored> karte=g.getRezervisani_filmovi();
		 List<Terminski_rasporedDTO> povratna=new ArrayList<>();
		for (Terminski_raspored t : karte) {
			Terminski_rasporedDTO tr=new Terminski_rasporedDTO();
			tr.setId(t.getId());
			tr.setBrojRezervacija(t.getBroj_rezervacija());
			tr.setNaziv(t.getFilm().getNaziv());
			tr.setCijena(t.getCijena());
			tr.setDatumOdrzavanja(t.getDatum_odrzavanja());
			tr.setSalaOznaka(t.getSala().getOznaka_sale());
			tr.setVrijemePocetka(t.getVrijeme_pocetka());
			tr.setBioskop(t.getSala().getBioskop().getNaziv());
			tr.setGledalacId(g.getId());
			povratna.add(tr);
		}
		return new ResponseEntity<>(povratna,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/gledalac-odgledaniFilmovi/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> odgledaniFilmovi(@PathVariable(name="id") Long id){
			Gledalac g=this.gledalacService.findOne(id);
			Set<Film> filmovi=g.getOdgledani_filmovi();
			List<FilmDTO> filmoviDTO=new ArrayList<>();
			
			for (Film f : filmovi) {
				FilmDTO fd=new FilmDTO();
				fd.setId(f.getId());
				fd.setNaziv(f.getNaziv());
				fd.setZanr(f.getZanr());
				fd.setOpis(f.getOpis());
				fd.setTrajanje(f.getTrajanje());
				fd.setSrednjaOcjena(f.getSrednja_ocjena());
				filmoviDTO.add(fd);
			}
			
			return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}

	
	@GetMapping(
			value="/gledalac-ocjenjeniFilmovi/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> ocjenjeniFilmovi(@PathVariable(name="id") Long id){
			Gledalac g=this.gledalacService.findOne(id);
			Set<Ocjena> filmovi=g.getOcjene();
			List<FilmDTO> filmoviDTO=new ArrayList<>();
			
			for (Ocjena f : filmovi) {
				FilmDTO fd=new FilmDTO();
				fd.setId(f.getFilm().getId());
				fd.setNaziv(f.getFilm().getNaziv());
				fd.setZanr(f.getFilm().getZanr());
				fd.setOpis(f.getFilm().getOpis());
				fd.setTrajanje(f.getFilm().getTrajanje());
				
				
				fd.setSrednjaOcjena(f.getOcjena());
						
				
				//fd.setSrednjaOcjena(f.getSrednja_ocjena());
				filmoviDTO.add(fd);
			}
			
			return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}
	
	
	@GetMapping(
			value="/gledalac-neocjenjeniFilmovi/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> neocjenjeniFilmovi(@PathVariable(name="id") Long id){
			Gledalac g=this.gledalacService.findOne(id);
			Set<Film> odgledani=g.getOdgledani_filmovi();
			Set<Ocjena> ocjene=g.getOcjene();
			Set<Film> ocjenjeni=new HashSet<>();
			Film film=new Film();
			for(Ocjena o:ocjene) {  //pravim listu ocjenjenih filmova
				film=o.getFilm();
				ocjenjeni.add(film);
			}
			List<FilmDTO> neocjenjeni=new ArrayList<>();
			for(Film f: odgledani) {
				if(!ocjenjeni.contains(f)) {
					FilmDTO fd=new FilmDTO();
					fd.setId(f.getId());
					fd.setNaziv(f.getNaziv());
					fd.setZanr(f.getZanr());
					fd.setOpis(f.getOpis());
					fd.setTrajanje(f.getTrajanje());
					fd.setSrednjaOcjena(f.getSrednja_ocjena());
					fd.setGledalacId(g.getId());
					neocjenjeni.add(fd);
				}
			}
			
			return new ResponseEntity<>(neocjenjeni,HttpStatus.OK);
		
	}
	
	@GetMapping(
			value="gledalac-otkaziRezervaciju/{id}/{value}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gledalac> otkazi(@PathVariable(name="id") Long id,@PathVariable(name="value") Long gledalacId){
			Terminski_raspored tr=this.terminski_rasporedService.findOne(id);
			//smanjenje broja rezervacija jer je otkazano 
			tr.setBroj_rezervacija(tr.getBroj_rezervacija()-1);
			if(tr==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Gledalac g=this.gledalacService.findOne(gledalacId);
			if(g==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Set<Terminski_raspored> rezervisani=g.getRezervisani_filmovi();
			rezervisani.remove(tr);
			this.gledalacService.save(g);
			Gledalac g1=new Gledalac();
			g1.setId(g.getId());
			
			return new ResponseEntity<>(g1,HttpStatus.OK);
			
	}
	
	@GetMapping(
			value="gledalac-potvrdiRezervaciju/{id}/{value}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gledalac> potvrdi(@PathVariable(name="id") Long id,@PathVariable(name="value") Long gledalacId){
			Terminski_raspored tr=this.terminski_rasporedService.findOne(id);
			//smanjenje broja rezervacija jer je otkazano 
			tr.setBroj_rezervacija(tr.getBroj_rezervacija()-1);
			if(tr==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Gledalac g=this.gledalacService.findOne(gledalacId);
			if(g==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Set<Terminski_raspored> rezervisani=g.getRezervisani_filmovi();
			rezervisani.remove(tr);
			Set<Film> odgledani=g.getOdgledani_filmovi();
			odgledani.add(tr.getFilm());
			this.gledalacService.save(g);
			Gledalac g1=new Gledalac();
			g1.setId(g.getId());
			
			return new ResponseEntity<>(g1,HttpStatus.OK);
			
	}
	
	@GetMapping(
			value="gledalac-ocijeniFilm/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Film> ocijeni(@PathVariable(name="id") Long id){
			Film f=this.filmService.findOne(id);
			Film f1=new Film();
			f1.setId(f.getId());
			
			return new ResponseEntity<>(f1,HttpStatus.OK);
			
	}
	@PostMapping(
			value="/ocjenjivanje",
			consumes = MediaType.APPLICATION_JSON_VALUE,     // tip podataka koje metoda može da primi
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OcjenjivanjeDTO> ocjenjivanje(@RequestBody OcjenjivanjeDTO o)throws Exception{
			Gledalac g=this.gledalacService.Find(o.getKorisnickoIme(), o.getLozinka());
			OcjenjivanjeDTO oc=new OcjenjivanjeDTO();
			oc.setId(o.getId().toString());
			
			if(g==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				Long id=Long.parseLong(o.getId());
				Film f=this.filmService.findOne(id);
				if(f==null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				Double ocjena=o.getOcjena();
				Double srednjaOcjena;
				if(f.getSrednja_ocjena()==0) {
					 srednjaOcjena=ocjena;
				}else {
					srednjaOcjena=(ocjena+f.getSrednja_ocjena())/2;
				}
				
				f.setSrednja_ocjena(srednjaOcjena);
				Set<Ocjena> ocjenjeni=g.getOcjene();
				Ocjena o1=new Ocjena();
				o1.setFilm(f);
				o1.setOcjena(ocjena);
				this.ocjenaService.save(o1);
				ocjenjeni.add(o1);
				this.gledalacService.save(g);
				
			}
			return new ResponseEntity<>(oc,HttpStatus.OK);
	}
			
	
	
}
