$(document).ready(function() {
	$("#login").click(function() {
		var email = $("#email").val();
		var pwd = $("#password").val();
		$.ajax({
			type: 'POST',
			url: '/login/verify',
			data: {'Email': email, 'Password': pwd},
			success: function(dataFromServer) {
				var result = JSON.parse(dataFromServer);
				
				$("#firstDivID").text(result);
				alert('Just got back from server side!! with '+ dataFromServer)
				
				if (dataFromServer == "true") {
					alert('Proceed to the locationSearchScreen page if login is good')
					window.location.href = "locationSearchScreen.html";
				} else {
					alert('Wrong login credentials')
				}
			},
			error: function() {
				alert('Shite happened in our server !!')
			}
		});
    });
	
	$("#signup").click(function() {
		var email = $("#username").val();
		var pwd = $("#password").val();
		var firstname = $("#firstname").val();
		var lastname = $("#lastname").val();
		$.ajax({
			type: 'POST',
			url: '/signup/store',
			data: {'Email': email, 'Password': pwd, 'Firstname': firstname, 'Lastname': lastname},
			success: function(dataFromServer) {
				var result = JSON.parse(dataFromServer);
				
				$("#firstDivID").text(result);
				alert('Just got back from server side!! with '+ dataFromServer)
				
				if (dataFromServer == "true") {
					alert('Proceed to the locationSearchScreen page if signup is good')
					window.location.href = "locationSearchScreen.html";
				} else {
					alert('Bad signup')
				}
			},
			error: function() {
				alert('Shite happened in our server !!')
			}
		});
    });

	// Map initalization for LocationSearchPage
	var map = L.map('mapid').setView([33.7490, -84.3880], 13);

	L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox/streets-v11',
		tileSize: 512,
		zoomOffset: -1
	}).addTo(map);

	var popup = L.popup();

	function onMapClick(e) {
		popup
			.setLatLng(e.latlng)
			.setContent("You clicked the map at " + e.latlng.toString())
			.openOn(map);
	}

	map.on('click', onMapClick);

	$('#search').click(function() {
		var lat = $('#latitude').val();
		var long = $('#longitude').val();
		var radius = $('#radius').val();
		
		// Make sure input values are checked before passed onto back-end
		
		map.setView([lat, long], 13);
		
		$.ajax({
			type: 'POST',
			url: '/search/result',
			data: {'Longitude': long, 'Latitude': lat, 'Radius': radius},
			success: function(dataFromServer) {
				var result = JSON.parse(dataFromServer);
				
				$("#firstDivID").text(result);
				alert('Just got back from server side!! with '+ dataFromServer)
				
			},
			error: function() {
				alert('Shite happened in our server !!')
			}
		});
	});
});
