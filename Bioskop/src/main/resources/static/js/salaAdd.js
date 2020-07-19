$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var oznakaSale=$("#oznakaSale").val();
	var kapacitet=$("#kapacitet").val();
	var bioskop=$("#bioskop").val();
	
	
	var newSalaJSON=formToJSON(oznakaSale,kapacitet,bioskop);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/add-sala",
		dataType:"json",
		contentType:"application/json",
		data:newSalaJSON,
		success:function(){
			alert("Sala je uspjesno dodata");
			window.location.href="profilMenadzer.html";
		},
		error:function(data){
			alert("Greska");
			window.location.href="greskaDodajSalu.html";
		}
		
	});
	
});

function formToJSON(oznakaSale,kapacitet,bioskop){
	return JSON.stringify({
		"oznakaSale":oznakaSale,
		"kapacitet":kapacitet,
		"bioskop":bioskop
		
	});
}