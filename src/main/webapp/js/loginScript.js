$(document).ready(function() {
	$("button").click(function() {
		var email = $("#email").val();
		var pwd = $("#password").val();
        var emailRegex = /^A-Za-z0-9@(?:[0-9a-zA-Z-]+.)+[a-zA-Z]{2,9}$/
        var passwordRegex = /^(?=.\d)(?=.[a-z])(?=.[A-Z])(?=.[a-zA-Z]).{8,}$/
        if (emailRegex.test(email) && passwordRegex.test(pwd)) {
            $.ajax({
                type: 'POST',
                url: '/hello/postItLocalSearch',
                data: {'Email': email,'Password':pwd},
                success: function(dataFromServer) {
                    var result = JSON.parse(dataFromServer);
                    console.log("Server has succeeded.");
                },
                error: function() {
                    console.log("Server has failed.");
                }
            });
        } else {

        }

    });
});
