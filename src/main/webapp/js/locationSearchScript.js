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
		var long = $('#longitude').val();
		var latVerify = !Number.isNaN(lat) && lat >= -90.0 && lat <= 90.0;
		var longVerify = !Number.isNaN(long) && long >= -180.0 && long <= 180.00;
		if (latVerify && longVerify) {
			map.setView([lat, long], 1000);
			markers.clearLayers();
			var markerAr = [];

			//var data=JSON.parse('{   "html_attributions" : [],   "results" : [      {         "geometry" : {            "location" : {               "lat" : -33.870775,               "lng" : 151.199025            }         },         "icon" : "http://maps.gstatic.com/mapfiles/place_api/icons/travel_agent-71.png",         "id" : "21a0b251c9b8392186142c798263e289fe45b4aa",         "name" : "Rhythmboat Cruises",         "opening_hours" : {            "open_now" : true         },         "photos" : [            {               "height" : 270,               "html_attributions" : [],               "photo_reference" : "CnRnAAAAF-LjFR1ZV93eawe1cU_3QNMCNmaGkowY7CnOf-kcNmPhNnPEG9W979jOuJJ1sGr75rhD5hqKzjD8vbMbSsRnq_Ni3ZIGfY6hKWmsOf3qHKJInkm4h55lzvLAXJVc-Rr4kI9O1tmIblblUpg2oqoq8RIQRMQJhFsTr5s9haxQ07EQHxoUO0ICubVFGYfJiMUPor1GnIWb5i8",               "width" : 519            }         ],         "place_id" : "ChIJyWEHuEmuEmsRm9hTkapTCrk",         "reference" : "CoQBdQAAAFSiijw5-cAV68xdf2O18pKIZ0seJh03u9h9wk_lEdG-cP1dWvp_QGS4SNCBMk_fB06YRsfMrNkINtPez22p5lRIlj5ty_HmcNwcl6GZXbD2RdXsVfLYlQwnZQcnu7ihkjZp_2gk1-fWXql3GQ8-1BEGwgCxG-eaSnIJIBPuIpihEhAY1WYdxPvOWsPnb2-nGb6QGhTipN0lgaLpQTnkcMeAIEvCsSa0Ww",         "types" : [ "travel_agency", "restaurant", "food", "establishment" ],         "vicinity" : "Pyrmont Bay Wharf Darling Dr, Sydney"      },      {         "geometry" : {            "location" : {               "lat" : -33.866891,               "lng" : 151.200814            }         },         "icon" : "http://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png",         "id" : "45a27fd8d56c56dc62afc9b49e1d850440d5c403",         "name" : "Private Charter Sydney Habour Cruise",         "photos" : [            {               "height" : 426,               "html_attributions" : [],               "photo_reference" : "CnRnAAAAL3n0Zu3U6fseyPl8URGKD49aGB2Wka7CKDZfamoGX2ZTLMBYgTUshjr-MXc0_O2BbvlUAZWtQTBHUVZ-5Sxb1-P-VX2Fx0sZF87q-9vUt19VDwQQmAX_mjQe7UWmU5lJGCOXSgxp2fu1b5VR_PF31RIQTKZLfqm8TA1eynnN4M1XShoU8adzJCcOWK0er14h8SqOIDZctvU",               "width" : 640            }         ],         "place_id" : "ChIJqwS6fjiuEmsRJAMiOY9MSms",         "reference" : "CpQBhgAAAFN27qR_t5oSDKPUzjQIeQa3lrRpFTm5alW3ZYbMFm8k10ETbISfK9S1nwcJVfrP-bjra7NSPuhaRulxoonSPQklDyB-xGvcJncq6qDXIUQ3hlI-bx4AxYckAOX74LkupHq7bcaREgrSBE-U6GbA1C3U7I-HnweO4IPtztSEcgW09y03v1hgHzL8xSDElmkQtRIQzLbyBfj3e0FhJzABXjM2QBoUE2EnL-DzWrzpgmMEulUBLGrtu2Y",         "types" : [ "restaurant", "food", "establishment" ],         "vicinity" : "Australia"      },      {         "geometry" : {            "location" : {               "lat" : -33.870943,               "lng" : 151.190311            }         },         "icon" : "http://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png",         "id" : "30bee58f819b6c47bd24151802f25ecf11df8943",         "name" : "Bucks Party Cruise",         "opening_hours" : {            "open_now" : true         },         "photos" : [            {               "height" : 600,               "html_attributions" : [],               "photo_reference" : "CnRnAAAA48AX5MsHIMiuipON_Lgh97hPiYDFkxx_vnaZQMOcvcQwYN92o33t5RwjRpOue5R47AjfMltntoz71hto40zqo7vFyxhDuuqhAChKGRQ5mdO5jv5CKWlzi182PICiOb37PiBtiFt7lSLe1SedoyrD-xIQD8xqSOaejWejYHCN4Ye2XBoUT3q2IXJQpMkmffJiBNftv8QSwF4",               "width" : 800            }         ],         "place_id" : "ChIJLfySpTOuEmsRsc_JfJtljdc",         "reference" : "CoQBdQAAANQSThnTekt-UokiTiX3oUFT6YDfdQJIG0ljlQnkLfWefcKmjxax0xmUpWjmpWdOsScl9zSyBNImmrTO9AE9DnWTdQ2hY7n-OOU4UgCfX7U0TE1Vf7jyODRISbK-u86TBJij0b2i7oUWq2bGr0cQSj8CV97U5q8SJR3AFDYi3ogqEhCMXjNLR1k8fiXTkG2BxGJmGhTqwE8C4grdjvJ0w5UsAVoOH7v8HQ",         "types" : [ "restaurant", "food", "establishment" ],         "vicinity" : "37 Bank St, Pyrmont"      },      {         "geometry" : {            "location" : {               "lat" : -33.867591,               "lng" : 151.201196            }         },         "icon" : "http://maps.gstatic.com/mapfiles/place_api/icons/travel_agent-71.png",         "id" : "a97f9fb468bcd26b68a23072a55af82d4b325e0d",         "name" : "Australian Cruise Group",         "opening_hours" : {            "open_now" : true         },         "photos" : [            {               "height" : 242,               "html_attributions" : [],               "photo_reference" : "CnRnAAAABjeoPQ7NUU3pDitV4Vs0BgP1FLhf_iCgStUZUr4ZuNqQnc5k43jbvjKC2hTGM8SrmdJYyOyxRO3D2yutoJwVC4Vp_dzckkjG35L6LfMm5sjrOr6uyOtr2PNCp1xQylx6vhdcpW8yZjBZCvVsjNajLBIQ-z4ttAMIc8EjEZV7LsoFgRoU6OrqxvKCnkJGb9F16W57iIV4LuM",               "width" : 200            }         ],         "place_id" : "ChIJrTLr-GyuEmsRBfy61i59si0",         "reference" : "CoQBeQAAAFvf12y8veSQMdIMmAXQmus1zqkgKQ-O2KEX0Kr47rIRTy6HNsyosVl0CjvEBulIu_cujrSOgICdcxNioFDHtAxXBhqeR-8xXtm52Bp0lVwnO3LzLFY3jeo8WrsyIwNE1kQlGuWA4xklpOknHJuRXSQJVheRlYijOHSgsBQ35mOcEhC5IpbpqCMe82yR136087wZGhSziPEbooYkHLn9e5njOTuBprcfVw",         "types" : [ "travel_agency", "restaurant", "food", "establishment" ],         "vicinity" : "32 The Promenade, King Street Wharf 5, Sydney"      }   ],   "status" : "OK"}');
			var data;
			$.ajax({
				type: 'POST',
				url: '/search/result',
				data: {'Latitude': lat,'Longitude':long, 'Radius': 13},
				success: function(dataFromServer) {
					data = JSON.parse(dataFromServer);
				},
				error: function() {
					console.log("Search failed");
					$(".error-message").text("Search failed to reach server, please try again.");
					$(".error-message").css("display", "flex");
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
			console.log("Regex test failed");
			$(".error-message").text("Your coordinates are invalid.");
			$(".error-message").css("display", "flex");
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