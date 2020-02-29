$(document).ready(function() {
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
				
				// Format results from back-end into table
				
			},
			error: function() {
				alert('Shite happened in our server !!')
			}
		});
	});
});
