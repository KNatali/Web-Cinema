$(document).ready(function(){
	
	
	$.ajax({
		type:"GET",
		url:"http://localhost:3050/api/filmovi",
		dataType:"json",
		success:function(data){
			
			
			console.log("SUCESS:",data);
			
			for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<a href='#' class='btnSlika' id=" + data[i]['id'] + "><img class=\"card-img-top\" src='images/"+data[i]['naziv']+".jpg' alt='Movie'></a>";
				col+="<div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4>";
				col+="<p class=\"card-text\"><b>Žanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocjena:</b><td>"+data[i]['srednjaOcjena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#table').append(col);
			}
			
		},
		error:function(data){
			console.log("ERROR:",data);
		}
	});
});



$(document).on('click', '.sortNaziv', function () {           
    var employeesDiv = $("#table");                    
    employeesDiv.hide();   
    $("#sortiraniOcjena").hide();
    $("#sortiraniTrajanje").hide();


    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/sortNaziv", 
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<a class='btnSlika' id=" + data[i]['id'] + "><img class=\"card-img-top\" src='images/"+data[i]['naziv']+".jpg'></a>";
				col+="<div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4>";
				col+="<p class=\"card-text\"><b>Žanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocjena:</b><td>"+data[i]['srednjaOcjena']+"</td></p>";
				
				col+="</div></div></div>";
				
				$('#sortirani').append(col);
				$('#sortirani').show();
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});


$(document).on('click', '.sortOcjena', function () {            
    var employeesDiv = $("#table");                      
    employeesDiv.hide(); 
    $("#sortirani").hide();// sakrij taj element
    $("#sortiraniTrajanje").hide();


    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/sortOcjena",  
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<a class='btnSlika' id=" + data[i]['id'] + "><img class=\"card-img-top\" src='images/"+data[i]['naziv']+".jpg'></a>";
				col+="<div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4>";
				col+="<p class=\"card-text\"><b>Žanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocjena:</b><td>"+data[i]['srednjaOcjena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#sortiraniOcjena').append(col);
				$('#sortiraniOcjena').show();
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});

$(document).on('click', '.sortTrajanje', function () {             
    var employeesDiv = $("#table");                     
    employeesDiv.hide();       
    $("#sortirani").hide();
    $("#sortiraniOcjena").hide();

    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/sortTrajanje",  
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<a class='btnSlika' id=" + data[i]['id'] + "><img class=\"card-img-top\" src='images/"+data[i]['naziv']+".jpg'></a>";
				col+="<div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4>";
				col+="<p class=\"card-text\"><b>Žanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocjena:</b><td>"+data[i]['srednjaOcjena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#sortiraniTrajanje').append(col);
				$('#sortiraniTrajanje').show();
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});

//izabere se odabrani film
$(document).on('click', '.btnSlika', function () {           
	$(".top").hide();
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
        	
        	
                                  
           
        },
        error: function (data) {
        	alert("Za izabrani film jos uvijek nema projekcija");
        	window.location.href="filmovi.html";
            console.log("ERROR : ", data);
        }
    });
});



$(document).on('click', '.btnRezervisi', function () {            
	$("#lista").hide();
	//$("#filmPrikaz").hide();
	$("#kartica1").hide();
        $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/rezervisi/" + this.id,  
        dataType: "json",
        success: function (data) {
        	
        	
        		
        		var red="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
	             red+="<input type='text' class='form-control' id='podatak' placeholder='Izabrnai id' value="+data['id']+"  disabled='disabled'></div>"
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
			window.location.href="filmovi.html";
			
			
		},
		error:function(data){
			
			alert("Greska! Korisnik sa unijetim podacima je nepostojeci ili za datu projekciju nema vise slobodnih mjesta. Pokusajte opet!!");
			window.location.href="filmovi.html";
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
