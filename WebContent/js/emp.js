var EmpDetails = "";

$("#searchEmployeeDetails").click(
		function(e) {
			EmpDetails = "";
			var n = $("#inputName").val();

			if (n == "" || n == null) {
				swal("No Data Found", "Please Enter correct name !", "error");
				return;
			}
			$("#loaderImage").show();
			$.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				data : {
					name : n,
					action : "getEmployeesDetails"
				},
				error : function(data) {
					$("#loaderImage").hide();
					alert("Error : " + data);
				},
				success : function(data) {
					$("#loaderImage").hide();
					var div = document.getElementById('empList');
					div.innerHTML = "";

					EmpDetails = data.EmpDetails;

					for (var j = 0; j < data.EmpDetails.length; j++) {

						var label = document.createElement('label');
						label
								.setAttribute("class",
										"radio radio-inline m-r-20");

						var input = document.createElement('input');
						input.setAttribute("type", "radio");
						input.setAttribute("name", "empID");
						input.setAttribute("value", EmpDetails[j].eid);

						var i = document.createElement('i');
						i.setAttribute("class", "input-helper");

						var p = document.createElement('p');
						p.setAttribute('style', 'margin-top: -7px');

						label.appendChild(input);
						label.appendChild(i);
						var text = document
								.createTextNode(EmpDetails[j].user.name + "-"
										+ EmpDetails[j].user.mobile);
						p.appendChild(text);

						label.appendChild(p);

						div.appendChild(label);
					}

					$('#modalEmp').modal('show');

				}
			});
		});

function addID() {

	var idnum = document.getElementById('numIDProof').value;
	idnum++;
	document.getElementById('numIDProof').value = idnum;

	document.getElementById("p" + idnum).style.display = "initial";

}

$("#selectEmpBtn")
		.click(
				function(e) {
					var eid = document.forms["ManempForm"].empID.value;
					$('#modalEmp').modal('hide');
					for (var i = 0; i < EmpDetails.length; i++) {
						if (EmpDetails[i].eid == eid) {

							$("#inputName").val(EmpDetails[i].user.name);
							$("#inputEmpID").val(EmpDetails[i].eid);
							$("#inputEmpEmail").val(EmpDetails[i].user.email);
							$("#inputEmpRole").val(EmpDetails[i].role);
							$("#inputEmpDoj").val(EmpDetails[i].doj);
							$("#inputEmpMobile").val(EmpDetails[i].user.mobile);
							$("#inputEmpSal").val(EmpDetails[i].salary);
							$("#inputEmpHouse").val(
									EmpDetails[i].address.house_no);
							$("#inputEmpAddress1").val(
									EmpDetails[i].address.line1);
							$("#inputEmpAddress2").val(
									EmpDetails[i].address.line2);
							$("#inputCity").append(
									"<option value='"
											+ EmpDetails[i].address.city
											+ "' selected>"
											+ EmpDetails[i].address.city
											+ "</option>");
							$("#inputState").val(EmpDetails[i].address.state);
							$("#inputEmpZip").val(EmpDetails[i].address.zip);

						}
					}

				});

$("#ContactForm").submit(function() {
	var mobile = $("#inputMobile").val();

	var success = true;
	var div = "#mobileDiv";
	if (mobile == "") {
		showErrorValidation(div, "Mobile can't be blank !");
		success = false;
	} else
		clearError(div);
	return success;
});

$("#PassForm").submit(function() {
	var pass = $("inputPass").val();
	var cpass = $("inputConfPass").val();
	var success = true;
	var div = "#passDiv";
	if (pass == "") {
		showErrorValidation(div, "Password can't be blank !");
		success = false;
	} else
		clearError(div);
	var div = "#cpassDiv";
	if (cpass == "") {
		showErrorValidation(div, "Confirm Password can't be blank !");
		success = false;
	} else
		clearError(div);
	if (cpass != pass) {
		showErrorValidation(div, "Confirm Password must match Password !");
		success = false;
	} else
		clearError(div);
	return success;
});

$("#proNameForm").submit(function() {
	var fname = $("#inputFname").val();
	var lname = $("#inputLname").val();
	var success = true;
	var div = "#fnameDiv";
	if (fname == "") {
		showErrorValidation(div, "Name can't be blank !");
		success = false;
	} else
		clearError(div);
	var div = "#lnameDiv";
	if (lname == "") {
		showErrorValidation(div, "Name can't be blank !");
		success = false;
	} else
		clearError(div);
	return success;
});
