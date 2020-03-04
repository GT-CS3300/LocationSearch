var curLat;
var curLong;

var searchMarkIcon = L.icon({iconUrl:'../assets/marker-icon.png',iconSize: [52, 52]});

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

	// Table initialization
	addRows($('.table-query').height(), $("#table-id").height(), $('tr:eq(1)').height());
	$('.table-query').height($('#table-id').height());
	$('#mapid').height($('#table-id').height());

	var markers = L.layerGroup();	
	$('#search').click(function() {
		var lat = $('#latitude').val();
		curLat = lat;
		var long = $('#longitude').val();
		curLong = long;
		var latVerify = !Number.isNaN(lat) && lat >= -90.0 && lat <= 90.0;
		var longVerify = !Number.isNaN(long) && long >= -180.0 && long <= 180.00;

		var rad = $('#radius').val();
		var radVerify = !Number.isNaN(rad) && rad >= 500 && rad <= 10000;

		var key = $('#keyword').val();
		var keyVerify = key.length > 0 && key.length < 12;

		if (latVerify && longVerify) {

			var request;
			if (!keyVerify && !radVerify) {
				request = {
					location: new google.maps.LatLng(lat,long),
					radius: '1000'
				};			
			} else if (radVerify && !keyVerify) {
				request = {
					location: new google.maps.LatLng(lat,long),
					radius: rad
				};			
			} else if (!radVerify && keyVerify) {
				request = {
					location: new google.maps.LatLng(lat,long),
					radius: '1000',
					keyword: key
				};			
			} else {
				request = {
					location: new google.maps.LatLng(lat,long),
					radius: rad,
					keyword: key
				};			
			}

			var googleMap = new google.maps.Map(document.getElementById('ignoreDiv'));
		  
			service = new google.maps.places.PlacesService(googleMap);
			service.nearbySearch(request, callback);

		} else {
			console.log("Regex test failed");
			$(".error-message").text("Your coordinates are invalid.");
			$(".error-message").css("display", "flex");
		}
	});

	function callback(results, status) {
		if (status == google.maps.places.PlacesServiceStatus.OK) {
			$(".error-message").css("display", "none");
			markers.clearLayers();
			var markerAr = [];
			var curLocation = L.marker([curLat,curLong], {icon: searchMarkIcon});
			markerAr.push(curLocation);
			var tableHTML = $(".key-row")[0].outerHTML;
			var rowCount = 1;
			$.each(results, function() {
				if (rowCount < $('tr').length) {
					var placeLat = this.geometry.location.lat();
					var placeLong = this.geometry.location.lng();
					var placeName = this.name;
					if (this.hasOwnProperty('opening_hours')) {
						var placeOpen = this.opening_hours.open_now;
					} else {
						var placeOpen = '-'
					}
					var placeAddress = this.vicinity;
					var marker = L.marker([placeLat, placeLong]);
					marker.bindPopup(placeName + "\n" + placeAddress);
					markerAr.push(marker);
					var tableRow = "<td>" + placeName + "</td> " + "<td>" + placeAddress + "</td> " + "<td>" + placeOpen + "</td> " + "<td>" + placeLat.toFixed(6) + "</td> " + "<td>" + placeLong.toFixed(6) + "</td> ";
					tableHTML += "<tr>"+tableRow+"</tr>";
					rowCount++;
				}
			});
			markers = L.layerGroup(markerAr).addTo(map);
			map.fitBounds(new L.featureGroup(markerAr).getBounds().pad(0.5));
			$("#table-id").html(tableHTML);
			addRows($('.table-query').height(), $("#table-id").height(), $('tr:eq(1)').height());
		} else if (status == google.maps.places.PlacesServiceStatus.ZERO_RESULTS) {
			console.log("Search had no results");
			$(".error-message").text("Search returned no nearby results.");
			$(".error-message").css("display", "flex");
			
		} else {
			console.log("Search failed");
			$(".error-message").text("Search failed to reach server, please try again.");
			$(".error-message").css("display", "flex");
		}
	}  
});


$(window).resize(function() {
	// Table Reinitialization on resize
	$("tr:nth-child(3)").nextAll().remove();
	$('.table-query').height($(window).height() * 0.8);
	addRows($('.table-query').height(), $("#table-id").height(), $('tr:eq(1)').height());
	$('.table-query').height($('#table-id').height());
	$('#mapid').height($('#table-id').height());

});

function addRows(maxHeight, curHeight, rowHeight) {
	while (maxHeight >= curHeight + rowHeight) {
		$("#table-id").append("<tr></tr>");
		curHeight += rowHeight;
	}
	// Zebra stripe the table whichever way makes the last row zebrastriped and visible
	if ($("tr").length % 2 == 0) {
		$("tr:odd").css("background-color", "#f5f5f5");
	} else {
		$("tr:even").not(":first").css("background-color", "#f5f5f5");
	}
}