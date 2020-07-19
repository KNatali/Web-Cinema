$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var naziv=$("#naziv").val();
	var adresa=$("#adresa").val();
	var brojCentrale=$("#broj").val();
	var eMail=$("#mail").val();
	var menadzer=$("#menadzer").val();
	
	var newBioskopJSON=formToJSON(naziv,adresa,brojCentrale,eMail,menadzer);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/add-bioskop",
		dataType:"json",
		contentType:"application/json",
		data:newBioskopJSON,
		success:function(){
			alert("Bioskop je uspjesno dodat");
			window.location.href="bioskopi.html";
		},
		error:function(data){
			alert("Greska,niste unijeli postojeci bioskop,salu i film");
			window.location.href="greskaDodajBioskop.html";
		}
		
	});
	
});

function formToJSON(naziv,adresa,broj,email,menadzer){
	return JSON.stringify({
		"naziv":naziv,
		"adresa":adresa,
		"brojCentrale":broj,
		"eMail":email,
		"menadzer":menadzer
	});
}