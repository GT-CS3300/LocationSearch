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
                'dataType': 'json',
                processData: false,
                'contentType': 'application/json',
                'data':JSON.stringify({
                    "email":email,
                    "password":pwd
                 }),
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
