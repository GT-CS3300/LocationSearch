$(document).ready(function() {
	$("#login").click(function() {
		var email = $("#email").val();
		var pwd = $("#password").val();
		$.ajax({
			type: 'POST',
			url: '/hello/postItLocalSearch',
			data: {'Email': email,'Password':pwd },
			success: function(dataFromServer) {
				var result = JSON.parse(dataFromServer);
				$("#firstDivID").text(result);
				alert('Just got back from server side!! with '+ dataFromServer)
			},
			error: function() {
				alert('Something bad happened in our server !!')
			}
		});
    });
});
    