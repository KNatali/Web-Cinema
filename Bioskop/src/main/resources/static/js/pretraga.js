$(document).on("submit","form",function(event){
	event.preventDefault();
	$("#kartica").hide();
	$("#kartica1").hide();
	
	var naziv=$("#naziv").val();
	var zanr=$("#zanr").val();
	var opis=$("#opis").val();
	var srednjaOcjena=$("#srednja_ocjena").val();
	var cijena=$("#cijena").val();
	var datumOdrzavanja=$("#datum").val();
	
	
	var newFilmJSON=formToJSON(naziv,zanr,opis,srednjaOcjena,cijena,datumOdrzavanja);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/filmovi/pretraga",
		dataType:"json",
		contentType:"application/json",
		data:newFilmJSON,
		success:function(data){
			for(i=0;i<data.length;i++){
				
				
				
				
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<div class=\"card mb-2\">";
				col+="<a href='#' class='btnSlika' id=" + data[i]['id'] + "><img class=\"card-img-top\" src='images/"+data[i]['naziv']+".jpg' alt='movie'></a>";
				col+="<div class=\"card-body\" id='filmKartica'> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4>";
				col+="<p class=\"card-text\"><b>Žanr:</b>  <td>"+data[i]['zanr']+"</td>";
	
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocjena:</b><td>"+data[i]['srednjaOcjena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#table').append(col);
				$("#lista").removeClass("d-none").show();
			}
			
		},
		error:function(data){
			alert("Nema pronadjenih filmova");
			window.location.href="pretraga.html";
			
		}
		
	});
	
});

function formToJSON(naziv,zanr,opis,srednjaOcjena,cijena,datumOdrzavanja){
	return JSON.stringify({
		"naziv":naziv,
		"zanr":zanr,
		"opis":opis,
		"srednjaOcjena":srednjaOcjena,
		"cijena":cijena,
		"datumOdrzavanja":datumOdrzavanja
	});
}

$(document).on('click', '.btnSlika', function () {           
	$("#lista").hide();
	$("#kartica1").hide();

    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/" + this.id,  
        dataType: "json",
        success: function (data) {
        	

			var div="<div class='mc-xl-5 ml-4'><p><strong>Naziv: </strong><span>"+data[0]['naziv']+"</span></p></div>";
			div+="<div class='mc-xl-5 ml-4'><p><strong>Zanr: </strong><span>"+data[0]['zanr']+"</span></p></div>";
			div+="<div class='mc-xl-5 ml-4'><p><strong>Trajanje: </strong><span>"+data[0]['trajanje']+" min</span></p></div>";
			div+="<div class='mc-xl-5 ml-4'><p><strong>Srednja ocjena: </strong><span>"+data[0]['srednjaOcjena']+"</span></p></div>";
			div+="<div class='mc-xl-5 ml-4'><p><strong>Opis: </strong><span>"+data[0]['opis']+"</span></p></div>";
			$('#podaciFilm').append(div);
        	
        	for(i=0;i<data.length;i++){
        		var row="<tr>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['datumOdrzavanja']+"</td>";
        		row+="<td>"+data[i]['vrijemePocetka']+"</td>";
        		row+="<td>"+data[i]['cijena']+"</td>";
        		row+="<td>"+data[i]['brojRezervacija']+"</td>";
        		row+="<td>"+data[i]['bioskop']+"</td>";
        		row+="<td>"+data[i]['salaOznaka']+"</td>";
        		
        		
        		 var btn = "<button class='btnRezervisi btn btn-danger' id = " + data[i]['id'] + ">Rezerviši</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela').append(row);
	              $("#filmPrikaz").removeClass("d-none").show();
	              
        	}
        	 //window.location.href="index.html";
        	
                                  
           
        },
        error: function (data) {
        	alert("Za izabrani film jos uvijek nema projekcija");
        	window.location.href="pretraga.html";
            console.log("ERROR : ", data);
        }
    });
});


$(document).on('click', '.btnRezervisi', function () {            
	$("#lista").hide();
	
	$("#filmic").empty();
	$("#kartica1").hide();
        $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/rezervisi/" + this.id,  
        dataType: "json",
        success: function (data) {
        	
        		
        		var red="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
	             red+="<input type='text' class='form-control' id='podatak' placeholder='Izabrnai id' value="+data['id']+"></div>"
	              $('#filmic').append(red);
	              $("#kartica1").removeClass("d-none").show();
	              
        	
       
        	
                                  
           
        },
        error: function (data) {
        	alert("Neuspjesno, pokusajte opet");
            console.log("ERROR : ", data);
        }
    });
});

$(document).on('click', '#rezervacija', function () {         
	event.preventDefault();
	$("#kartica1").hide();
	
	var korisnickoIme=$("#korisnickoIme").val();
	var lozinka=$("#lozinka").val();
	var id=$("#podatak").val();
   
	var newKorisnikJSON=formToJSON1(korisnickoIme,lozinka,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/filmovi/rezervacija",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(data){
			alert("Uspjesno");
			
			
			
		},
		error:function(data){
			
			alert("Greska! Gledalac sa unijetim podacima je neposotjeći");
			window.location.href="pretraga.html";
        }
    });
});

function formToJSON1(korisnickoIme,lozinka,id){
	return JSON.stringify({
		"korisnickoIme":korisnickoIme,
		"lozinka":lozinka,
		"id":id
		
	});
}