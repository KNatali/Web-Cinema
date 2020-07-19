$(document).ready(function(){
	var loginKartica=$("#kartica").hide();

	
	
	var korisnickoIme=window.localStorage.getItem("korisnickoIme");
	var lozinka=window.localStorage.getItem("lozinka");
	
	var newKorisnikJSON=formToJSON(korisnickoIme,lozinka);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/get-korisnik",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(data){
			console.log("SUCCESS: ",data);
			window.localStorage.setItem("korisnickoIme",data['korisnickoIme']);
			window.localStorage.setItem("lozinka",data['lozinka']);
			
			$("#ime").append(data['ime']);
			$("#korisnicko").append(data['korisnickoIme']);
			$('#loz').append(data['lozinka']);
			$('#name').append(data['ime']);
			$('#prezime').append(data['prezime']);
			$('#telefon').append(data['kontaktTelefon']);
			$('#email').append(data['email']);
			$('#datum').append(data['datumRodjenja']);
			$('#role').append(data['uloga']);
			
			
		
			  var profil=$("#profil1").removeClass("d-none").show();
			  if(data['uloga']=="Administrator"){
				  var pod=$("#admin").removeClass("d-none").show();
			  }else if(data['uloga']=="Menadzer"){
				  var m="<button class='btnBioskopi btn-outline-danger btn-lg btn-block' id="+data['id']+">Pregled bioskopa</button>";
				  m+="<a href='FilmAdd.html'  style='text-decoration : none'><button class='btnDodajFilm btn-outline-danger btn-lg btn-block' id="+data['id']+">Dodavanje filma</button>";
					 
				   $("#menadzer").append(m);
				   $("#menadzer").removeClass("d-none").show();
			  }
			  else{
				  var rez="<button class='btnRezervisani btn-outline-danger btn-lg btn-block' id="+data['id']+">Rezervisane karte</button>";
				   rez+="<button  class='btnSvi btn-outline-danger btn-lg btn-block' id="+data['id']+">Svi odgledani filmovi</button>";
				   rez+="<button class='btnNeocjenjeni btn-outline-danger btn-lg btn-block' id="+data['id']+">Neocjenjeni filmovi</button>";
				   rez+="<button class='btnOcjenjeni btn-outline-danger btn-lg btn-block' id="+data['id']+">Ocjenjeni filmovi</button>";
				  
				 $("#gledalac").append(rez);
				  $("#gledalac").removeClass("d-none").show();
			  }
			
		},
		error:function(data){
			var loginKartica=$("#kartica").show();
			alert("Greska! Korisnik sa unijetim podacima je neposotjeći");
		}
	});
	
});

function formToJSON(korisnickoIme,lozinka){
	return JSON.stringify({
		"korisnickoIme":korisnickoIme,
		"lozinka":lozinka
		
	});
}


$(document).on('click', '.btnRezervisani', function () {         
    $("#odgledaniFilmovi").hide();
    $("#ocjenjeniFilmovi").hide();
    $("#ocjenjivanje").hide();
    $("#neocjenjeniFilmovi").hide();
    $(".sakrij").empty();

    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/gledalac-rezervisaneKarte/" + this.id,  
        dataType: "json",
        success: function (data) {
        
        	for(i=0;i<data.length;i++){
        		var row="<tr class='sakrij'>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['datumOdrzavanja']+"</td>";
        		row+="<td>"+data[i]['vrijemePocetka']+"</td>";
        		row+="<td>"+data[i]['cijena']+"</td>";
        		row+="<td>"+data[i]['brojRezervacija']+"</td>";
        		row+="<td>"+data[i]['bioskop']+"</td>";
        		row+="<td>"+data[i]['salaOznaka']+"</td>";
        		
        		
        		 var btn = "<button class='btnOtkazi btn btn-danger' value="+data[i]['gledalacId']+" id= " + data[i]['id']+ ">Otkaži</button>";
	              row += "<td>" + btn + "</td>"; 

	              var btn1 = "<button class='btnPotvrdi btn btn-danger' value="+data[i]['gledalacId']+" id= " + data[i]['id']+ ">Potvrdi kupovinu</button>";
		          row += "<td>" + btn1 + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela').append(row);
	              $("#listaKarte").removeClass("d-none").show();
	              
        	}                          
           
        },
        error: function (data) {
        	alert("Neuspjesno, pokusajte opet");
            console.log("ERROR : ", data);
        }
    });
});


$(document).on('click', '.btnSvi', function () {           
	  $("#listaKarte").hide();
	  $("#ocjenjeniFilmovi").hide();
	  $("#neocjenjeniFilmovi").hide();
	  $("#ocjenjivanje").hide();
	  $(".sakrij").empty();

 
    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/gledalac-odgledaniFilmovi/" + this.id,  
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
        		var row="<tr class='sakrij'>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['zanr']+"</td>";
        		row+="<td>"+data[i]['opis']+"</td>";
        		row+="<td>"+data[i]['trajanje']+"</td>";
        		row+="<td>"+data[i]['srednjaOcjena']+"</td>";
        		
        		
        		
	              row+="</tr>";
	              
	              $('#tabela1').append(row);
	              
	              $("#odgledaniFilmovi").removeClass("d-none").show();
	              
	              
        	}                          
           
        },
        error: function (data) {
        	alert("Neuspjesno, pokusajte opet");
            console.log("ERROR : ", data);
        }
    });
});


$(document).on('click', '.btnOcjenjeni', function () {           
	  $("#listaKarte").hide();
	  $("#odgledaniFilmovi").hide();
	  $("#neocjenjeniFilmovi").hide();
	  $(".sakrij").empty();
	 

  $.ajax({
      type: "GET",
      url: "http://localhost:3050/api/gledalac-ocjenjeniFilmovi/" + this.id,  
      dataType: "json",
      success: function (data) {
     
      	for(i=0;i<data.length;i++){
      		var row="<tr class='sakrij'>";
      		row+="<td>"+data[i]['naziv']+"</td>";
      		row+="<td>"+data[i]['zanr']+"</td>";
      		row+="<td>"+data[i]['opis']+"</td>";
      		row+="<td>"+data[i]['trajanje']+"</td>";
      		row+="<td>"+data[i]['srednjaOcjena']+"</td>";
      		
      		
      		
	              row+="</tr>";
	              
	              $('#tabela2').append(row);
	              
	              $("#ocjenjeniFilmovi").removeClass("d-none").show();
	              
      	}                          
         
      },
      error: function (data) {
      	alert("Neuspjesno, pokusajte opet");
          console.log("ERROR : ", data);
      }
  });
});


$(document).on('click', '.btnNeocjenjeni', function () {           
	  $("#listaKarte").hide();
	  $("#odgledaniFilmovi").hide();
	  $("#ocjenjeniFilmovi").hide();
	  $("#ocjenjivanje").hide();
	  $(".sakrij").empty();


$.ajax({
    type: "GET",
    url: "http://localhost:3050/api/gledalac-neocjenjeniFilmovi/" + this.id, 
    dataType: "json",
    success: function (data) {
    	for(i=0;i<data.length;i++){
    		var row="<tr class='sakrij'>";
    		row+="<td>"+data[i]['naziv']+"</td>";
    		row+="<td>"+data[i]['zanr']+"</td>";
    		row+="<td>"+data[i]['opis']+"</td>";
    		row+="<td>"+data[i]['trajanje']+"</td>";
    		row+="<td>"+data[i]['srednjaOcjena']+"</td>";
    		

   		 var btn = "<button class='btnOcijeni btn btn-danger' value="+data[i]['gledalacId']+"  id = " + data[i]['id'] + ">Ocijeni</button>";
             row += "<td>" + btn + "</td>"; 
             row+="</tr>";
             
	              
	              $('#tabela3').append(row);
	              
	              $("#neocjenjeniFilmovi").removeClass("d-none").show();
	              
    	}                          
       
    },
    error: function (data) {
    	alert("Neuspjesno, pokusajte opet");
        console.log("ERROR : ", data);
    }
});
});


$(document).on('click', '.btnOtkazi', function () {        

	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/api/gledalac-otkaziRezervaciju/" + this.id+"/"+this.value,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Uspjesno uklonjena rezervacija");
		     window.location.href="profilGledalac.html";
			 
			
		   
		},
		error: function (data) {
			alert("Neuspjesno, pokusajte opet");
			 window.location.href="login.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '.btnPotvrdi', function () {        

	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/api/gledalac-potvrdiRezervaciju/" + this.id+"/"+this.value,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Uspjesno kupljena karta");
		     window.location.href="profilGledalac.html";
			 
			
		   
		},
		error: function (data) {
			alert("Neuspjesno, pokusajte opet");
			 window.location.href="login.html";
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '.btnOcijeni', function () {        

	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/api/gledalac-ocijeniFilm/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			//alert("Uspjesno ocijenjen film");
			var red="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='podatakOcjena' placeholder='Izabrnai id' value="+data['id']+"  disabled='disabled'></div>"
             $('#filmic').append(red);
             $("#ocjenjivanje").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Neuspjesno, pokusajte opet");
			 window.location.href="profilGledalac.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '#ocijeniFilm', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	$("#kartica1").hide();
	
	var korisnickoIme=$("#korisnickoImeOcjena").val();
	var lozinka=$("#lozinkaOcjena").val();
	var ocjena=$("#ocjenaOcjena").val();
	var id=$("#podatakOcjena").val();
   
	var newKorisnikJSON=formToJSONOcjena(korisnickoIme,lozinka,ocjena,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/ocjenjivanje",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(data){
			alert("Uspjesno");
			window.location.href="profilGledalac.html";
			
			
		},
		error:function(data){
			
			alert("Greska! Korisnik sa unijetim podacima je nepostojeci ili za datu projekciju nema vise slobodnih mjesta. Pokusajte opet!!");
			window.location.href="filmovi.html";
        }
    });
});

function formToJSONOcjena(korisnickoIme,lozinka,ocjena,id){
	return JSON.stringify({
		"korisnickoIme":korisnickoIme,
		"lozinka":lozinka,
		"ocjena":ocjena,
		"id":id
		
	});
}

