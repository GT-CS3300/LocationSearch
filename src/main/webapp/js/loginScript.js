$(document).ready(function() {
	$("button").click(function() {
		var email = $("#email").val();
		var pwd = $("#password").val();
        var emailRegex = /^A-Za-z0-9@(?:[0-9a-zA-Z-]+.)+[a-zA-Z]{2,9}$/
        var passwordRegex = /^(?=.\d)(?=.[a-z])(?=.[A-Z])(?=.[a-zA-Z]).{8,}$/
        if (emailRegex.test(email) && passwordRegex.test(pwd)) {
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
        } else {

        }

    });
});
