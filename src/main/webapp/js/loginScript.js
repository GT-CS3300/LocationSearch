$(document).ready(function() {

	$("button").click(function() {
		var email = $("#email").val();
		var pwd = $("#password").val();
        var emailRegex = /^[A-Za-z0-9]+@[A-Za-z0-9]+\.[a-zA-Z]+$/
        var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
        if (emailRegex.test(email) && passwordRegex.test(pwd)) {
			$.ajax({
				type: 'GET',
				url: '/auth',
				data: {'Email': email,'Password':pwd },
				success: function(dataFromServer) {
                    console.log(dataFromServer);
                    console.log(dataFromServer.token);
					sessionStorage.LSToken = dataFromServer.token;
					
					if (dataFromServer.token) {
						window.location.assign("/html/locationSearchScreen.html"); 
						console.log("server data success");
					} else {
						console.log("server data failed");
					}
				},
				error: function() {
					console.log("Login failed");
					$(".error-message").text("Login failed to reach server, please try again.");
					$(".error-message").css("display", "flex");
				}
			});
        } else {
			console.log("Regex test failed");
			$(".error-message").text("Your email or password is invalid.");
			$(".error-message").css("display", "flex");
        }
    });
});
