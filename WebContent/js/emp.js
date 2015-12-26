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

function getEmpAttendance(emp)
{
	
	if($("#startDate").val() == "" || $("#endDate").val() == ""){
		swal({
			title : "No Date Entered !",
			text : "Please enter start date or end date.",
			timer : 2000,
			showConfirmButton : false
		});
		$(emp).removeAttr('checked');
		return;
	}
	
	var va = emp.value.split(" ");
	var salary = va[1]/30;
	var empId = va[0];
	
	$("#loaderImage").show();
	$.ajax({
		url : $("#basePath").val() + "/ajaxServlet.do",
		data : {
			empId : empId,
			startDate : $("#startDate").val(),
			endDate : $("#endDate").val(),
			action : "getEmployeeAttendance"
		},
		error : function(data) {
			$("#loaderImage").hide();
			alert("Error : " + data.attendance);
		},
		success : function(data) {
			$("#loaderImage").hide();
			
			var tbbody = document.getElementById("tableBody");
			tbbody.innerHTML = "";
			
			for(var i = 0; i < data.attendance.length; i++)
			{
				var tbbdrw = document.createElement('tr');
				
				var tbbdtd1 = document.createElement('td');
				$(tbbdtd1).text(i+1);
				tbbdrw.appendChild(tbbdtd1);
				
				var tbbdtd2 = document.createElement('td');
				$(tbbdtd2).text(data.attendance[i].presentDate);
				tbbdrw.appendChild(tbbdtd2);
				
				var tbbdtd3 = document.createElement('td');
				$(tbbdtd3).text("Present");
				tbbdrw.appendChild(tbbdtd3);
				
				var span = document.createElement('span');
				
				var tbbdtd4 = document.createElement('td');
				if(data.attendance[i].halfDay == 0)
				{
					span.setAttribute("class", "md-clear");
					tbbdtd4.appendChild(span);
				}
				else
				{
					//$(tbbdtd4).text("Yes");
					span.setAttribute("class", "md-done");
					tbbdtd4.appendChild(span);
				}
				tbbdrw.appendChild(tbbdtd4);
				tbbody.appendChild(tbbdrw);
			}
			
			$("#attendance").text(data.attendance.length);
			$("#salary").text("Rs. " + salary*i);
		}
	});
}