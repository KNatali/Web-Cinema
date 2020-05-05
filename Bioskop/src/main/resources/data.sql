INSERT INTO ADMINISTRATOR (korisnicko_ime,lozinka,ime,prezime,uloga,aktivan) VALUES ('natali','246516','Natalija','Krsmanovic','Administrator',true);

INSERT INTO MENADZER (korisnicko_ime,lozinka,ime,prezime,uloga,aktivan) VALUES ('nikola12','skfckh','Nikola','Nikolic','Menadzer',true);

INSERT INTO GLEDALAC (korisnicko_ime,lozinka,ime,prezime,uloga) VALUES ('ANA345','ANI565','Ana','Antic','Gledalac');
INSERT INTO GLEDALAC (korisnicko_ime,lozinka,ime,prezime,uloga) VALUES ('milosm','12nnn12','Milos','Milakovic','Gledalac');
INSERT INTO GLEDALAC (korisnicko_ime,lozinka,ime,prezime,uloga) VALUES ('KingmArko','aggsh','Marko','Markovic','Gledalac');

INSERT INTO BIOSKOP (naziv,adresa,broj_centrale,menadzer_id) VALUES ('Cineplexx Palas','Trg Krajine bb','+387 51 211 732',1);
INSERT INTO BIOSKOP (naziv,adresa,broj_centrale,menadzer_id) VALUES ('CineStar','Bulevar srpske vojske','+387 51 255 024',1);

INSERT INTO FILM(naziv,opis,zanr,trajanje) VALUES ('Us','Misteriozna desavanja iza ogledala','horor',90);
INSERT INTO FILM(naziv,opis,zanr,trajanje) VALUES ('Love Rosie','romanticna prica jednog para','romatika',115);
INSERT INTO FILM(naziv,opis,zanr,trajanje) VALUES ('Prestige','film sa puno inzvarednih trikova i plot tvistova','misterija',120);

INSERT INTO SALA(kapacitet,oznaka_sale,bioskop_id) VALUES (200,'A2',1);
INSERT INTO SALA(kapacitet,oznaka_sale,bioskop_id) VALUES (250,'A1',1);
INSERT INTO SALA(kapacitet,oznaka_sale,bioskop_id) VALUES (100,'A3',2);

INSERT INTO OCJENA (film_id,ocjena) VALUES (1,8);
INSERT INTO OCJENA (film_id,ocjena) VALUES (2,9);
INSERT INTO OCJENA (film_id,ocjena) VALUES (3,10);
INSERT INTO OCJENA (film_id,ocjena) VALUES (1,9);

INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (1,1);
INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (1,2);
INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (2,1);
INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (3,3);

INSERT INTO LISTA_REZERVISANIH_FILMOVA (gledalac_id,film_id) VALUES (1,3);
INSERT INTO LISTA_REZERVISANIH_FILMOVA (gledalac_id,film_id) VALUES (2,3);
INSERT INTO LISTA_REZERVISANIH_FILMOVA (gledalac_id,film_id) VALUES (3,1);

INSERT INTO LISTA_OCJENJENIH_FILMOVA (gledalac_id,ocjena_id) VALUES (1,1);
INSERT INTO LISTA_OCJENJENIH_FILMOVA (gledalac_id,ocjena_id) VALUES (2,4);
INSERT INTO LISTA_OCJENJENIH_FILMOVA (gledalac_id,ocjena_id) VALUES (3,3);

INSERT INTO TERMINSKI_RASPORED (film_id,datum_odrzavanja,vrijeme_pocetka,cijena,broj_rezervacija,sala_id) VALUES (1,'2020-05-03','18:00',280,50,1);
INSERT INTO TERMINSKI_RASPORED (film_id,datum_odrzavanja,vrijeme_pocetka,cijena,broj_rezervacija,sala_id) VALUES (2,'2020-06-10','20:00',280,10,1);
INSERT INTO TERMINSKI_RASPORED (film_id,datum_odrzavanja,vrijeme_pocetka,cijena,broj_rezervacija,sala_id) VALUES (3,'2020-05-15','21:30',250,85,2);
INSERT INTO TERMINSKI_RASPORED (film_id,datum_odrzavanja,vrijeme_pocetka,cijena,broj_rezervacija,sala_id) VALUES (1,'2020-06-22','20:45',200,100,3);
INSERT INTO TERMINSKI_RASPORED (film_id,datum_odrzavanja,vrijeme_pocetka,cijena,broj_rezervacija,sala_id) VALUES (2,'2020-06-22','15:45',200,70,3);

INSERT INTO RASPORED_FILMOVA (bioskop_id,terminski_raspored_id) VALUES (1,1);
INSERT INTO RASPORED_FILMOVA (bioskop_id,terminski_raspored_id) VALUES (1,2);
INSERT INTO RASPORED_FILMOVA (bioskop_id,terminski_raspored_id) VALUES (1,3);
INSERT INTO RASPORED_FILMOVA (bioskop_id,terminski_raspored_id) VALUES (2,4);
INSERT INTO RASPORED_FILMOVA (bioskop_id,terminski_raspored_id) VALUES (2,5);







