$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var bioskop=$("#nazivBioskopa").val();
	var film=$("#nazivFilma").val();
	var datum=$("#datumOdrzavanja").val();
	var pocetak=$("#vrijemePocetka").val();
	var cijena=$("#cijena").val();
	var sala=$("#oznakaSale").val();
	
	var newRasporedJSON=formToJSON(bioskop,film,datum,pocetak,cijena,sala);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/add-raspored",
		dataType:"json",
		contentType:"application/json",
		data:newRasporedJSON,
		success:function(){
			alert("Uspjesno je dodat novi raspored");
			 window.location.href="profilMenadzer.html";
		
		},
		error:function(data){
			alert("Greska");
			
		}
	});
});

function formToJSON(bioskop,film,datum,pocetak,cijena,sala){
	return JSON.stringify({
		"bioskop":bioskop,
		"naziv":film,
		"datumOdrzavanja":datum,
		"vrijemePocetka":pocetak,
		"cijena":cijena,
		"salaOznaka":sala
	});
}