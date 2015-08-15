
function submitForm(form) {
	// console.log(form);
	var success = true;
	if (form == "forgotPassword") {
		var email = $("#forgetEmail").val();
		// console.log("Email : " + email);
		var div = "#forgetEmailDiv";
		if (email == "" || !validateEmail(email)) {
			success = false;
			showErrorValidation(div, "Email ID is invalid / blank !");
		} else
			clearError(div);
	}
	if (form == "loginForm") {
		var email = $("#email").val();
		var password = $("#password").val();
		var div = "#emailDiv";

		if (email == "" || !validateEmail(email)) {
			success = false;
			showErrorValidation(div, "Email ID is Blank / Invalid !");
		} else
			clearError(div);

		div = "#passwordDiv";
		if (password == "" || password == null) {
			success = false;
			showErrorValidation(div, "Password is blank !");
		} else
			clearError(div);

	}
	if (form == "passwordForm") {
		var newPassword = $("#newPassword").val();
		var confirmPassword = $("#confirmPassword").val();

		if (newPassword.replace(" ", "").length == 0) {
			success = false;
			showErrorValidation("#passwordDiv", "Password is blank !");
		} else
			clearError("#passwordDiv");

		if (confirmPassword.replace(" ", "").length == 0) {
			success = false;
			showErrorValidation("#confirmPassDiv", "Password is blank !");
		} else
			clearError("#confirmPassDiv");

		if (newPassword != confirmPassword) {
			success = false;
			showErrorValidation("#confirmPassDiv", "Passwords does not match !");
			showErrorValidation("#passwordDiv", "Passwords does not match !");
		} else {
			clearError("#passwordDiv");
			clearError("#confirmPassDiv");
		}

	}

	if (success)
		$("#" + form).submit();
}

function validateEmail($email) {
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	return emailReg.test($email);
}

function clearError(div) {
	$(div).attr("class", $(div).attr("class").replace(" has-error", ""));
	div = div + " small#error";
	$(div).text("");
}

function showErrorValidation(div, message) {
	if ($(div).attr("class").indexOf("has-error") < 0) {
		$(div).attr("class", $(div).attr("class") + " has-error");
		div = div + " small#error";
		$(div).text(message);
	}
}
