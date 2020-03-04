$(document).ready(function() {
    $("#password").focus(function() {
        $(".help-message").fadeIn(200);
    });

    $("#password").focusout(function() {
        $(".help-message").fadeOut(200);
    });

    $("button").click(function() {
        var emailRegex = /^[A-Za-z0-9]+@[A-Za-z0-9]+\.[a-zA-Z]+$/
        var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;

        var email = $("#email").val();
        var pwd = $("#password").val();
        var pwdConfirm = $("#password-confirmation").val();
        var fName = $("#firstName").val();
        var lName = $("#lastName").val();
        if (emailRegex.test(email) && passwordRegex.test(pwd) && pwd == pwdConfirm) {
            $.ajax({
                type: 'POST',
                url: '/auth',
                data: {'email': email,'password':pwd},
                success: function(dataFromServer) {
					var result = JSON.parse(dataFromServer);
					sessionStorage.LSToken = "TokenFailure";
					
					if (dataFromServer == "true") {
						window.location.assign("/html/locationSearchScreen.html"); 
						console.log("server data success");
						console.log(result);
						console.log(dataFromServer);
					} else {
						console.log("server data failed");
						console.log(result);
						console.log(dataFromServer);
					}
                },
                error: function() {
					console.log("Signup failed");
					$(".error-message").text("Signup failed to reach server, please try again.");
					$(".error-message").css("display", "flex");
                }
            });
        } else {
			console.log("Regex test failed");
			$(".error-message").text("Your information does not match our requirements.");
			$(".error-message").css("display", "flex");
        }
    });
});
