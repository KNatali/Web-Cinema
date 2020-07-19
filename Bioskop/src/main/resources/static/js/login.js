$(document).on("submit","form",function(event){
	event.preventDefault();
	var loginKartica=$("#kartica").hide();

	
		
	var korisnickoIme=$("#korisnickoIme").val();
	var lozinka=$("#lozinka").val();
	
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
			
			if(data['uloga']=="Gledalac"){
				window.location.href="profilGledalac.html";
			}else if(data['uloga']=="Menadzer"){
				window.location.href="profilMenadzer.html";
			}else{
				window.location.href="profilAdmin.html";
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




