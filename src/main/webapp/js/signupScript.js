$(document).ready(function() {
    $("button").click(function() {
        var emailRegex = /^A-Za-z0-9@(?:[0-9a-zA-Z-]+.)+[a-zA-Z]{2,9}$/
        var passwordRegex = /^(?=.\d)(?=.[a-z])(?=.[A-Z])(?=.[a-zA-Z]).{8,}$/

        var email = $("#email").val();
        var pwd = $("#password").val();
        var fName = $("#firstName").val();
        var lName = $("#lastName").val();
        if (emailRegex.test(email) && passwordRegex.test(pwd)) {
            $.ajax({
                type: 'POST',
                url: '/signup/store',
                data: {'Email': email,'Password':pwd, 'Firstname': "Mahdi", 'Lastname':  "Rooz"},
                success: function(dataFromServer) {
                    var result = JSON.parse(dataFromServer);
                    
                    if (dataFromServer == "true") {
						window.location.assign("locationSearchScreen.html"); 
                    } else {

                    }
                },
                error: function() {
					console.log("Signup failed");
                }
            });
        } else {

        }
    });
});
