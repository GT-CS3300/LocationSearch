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

	var LatLongRegex = /^[-+]?([1-8]?\d(.\d+)?|90(.0+)?),\s*[-+]?(180(.0+)?|((1[0-7]\d)|([1-9]?\d))(.\d+)?)$/;
	var markers = L.layerGroup();
	$('#search').click(function() {
		var lat = $('#latitude').val();
		var long = $('#longitude').val();
		var latLong = lat + ', ' + long;
		if (LatLongRegex.test(latLong)) {
			map.setView([lat, long], 13);
			markers.clearLayers();
			var markerAr = [];

			var data;
			$.ajax({
				type: 'POST',
				url: '/search/result',
				data: {'Latitude': lat,'Longitude':long, 'Radius': 15},
				success: function(dataFromServer) {
					data = JSON.parse(dataFromServer);
				},
				error: function() {
					console.log("Search failed");
				}
			});

			var tableHTML = $(".key-row")[0].outerHTML;
			$.each(data.results, function() {
				var placeLat = this.geometry.location.lat;
				var placeLong = this.geometry.location.lng;
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
				var tableRow = "<td>" + placeName + "</td> " + "<td>" + placeAddress + "</td> " + "<td>" + placeOpen + "</td> " + "<td>" + placeLat + "</td> " + "<td>" + placeLong + "</td> ";
				tableHTML += "<tr>"+tableRow+"</tr>";
			});
			markers = L.layerGroup(markerAr).addTo(map);
			$("#table-id").html(tableHTML);
			addRows($('.table-query').height(), $("#table-id").height(), $('tr:eq(1)').height());
		} else {
			console.log("Invalid Latitude Longitude");
		}
	});
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