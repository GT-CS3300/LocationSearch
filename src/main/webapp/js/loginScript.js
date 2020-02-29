$(document).ready(function() {
	$("#login").click(function() {
		var email = $("#email").val();
		var pwd = $("#password").val();
		$.ajax({
			type: 'POST',
			url: '/login/verify',
			data: {'Email': email,'Password':pwd },
			success: function(dataFromServer) {
				var result = JSON.parse(dataFromServer);
				$("#firstDivID").text(result);
				alert('Just got back from server side!! with '+ dataFromServer)
				
				if (dataFromServer == "true") {
					alert('Proceed to the locationSearchScreen page if login is good')
					window.location.href = "locationSearchScreen.html"; // get this to work plz
				} else {
					alert('Wrong login credentials')
				}
			},
			error: function() {
				alert('Shite happened in our server !!')
			}
		});
    });
});
    