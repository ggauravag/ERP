/**
 * 
 */
var products = new Array();
var availProduct = new Array();
var expDetail = new Array();
var stockProducts = new Array(); // to have total products of a selected
// category from database- Stock module
var totalStockProducts = new Array(); // to maintain current no of products at
// AddStock page - Stock module
var customers, employees;
var customerDetails = new Array();

var firmSelect = new Array();

function Customer(id, name, email, mob, tin, house, line1, line2, city, state,
		pin, type) {
	this.id = id;
	this.name = name;
	this.email = email;
	this.mob = mob;
	this.tin = tin;
	this.house = house;
	this.line1 = line1;
	this.line2 = line2;
	this.city = city;
	this.state = state;
	this.pin = pin;
	this.type = type;
}

$("#inputState").change(
		function() {
			var selectState = $(this).val();
			$("#loaderImage").show();
			$.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				method : "post",
				data : {
					action : "getCities",
					state : selectState
				},
				success : function(data) {
					$("#loaderImage").hide();
					$("#inputCity").html("");
					for (var i = 0; i < data.city.length; i++) {
						$("#inputCity").append(
								"<option value='" + data.city[i] + "'>"
										+ data.city[i] + "</option>");
					}
				},
				error : function(data) {
					$("#loaderImage").hide();
					swal('Some Error : ' + data);
				}

			});
		});

function showDateTime(roleValue) {
	if (roleValue == "OPERATOR" || roleValue == "DBA") {
		$("#showDT").show();
	} else
		$("#showDT").hide();

}

function getSpecificTime(timeType) {
	if (timeType == "SPECIFIC") {
		$("#SEtime").show();
	} else {
		$("#SEtime").hide();
	}
}

$(document).ready(
		function() {
			$("#loaderImage").show();
			$.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				data : {
					action : "getUserName"
				},

				success : function(data) {
					$("#loaderImage").hide();
					if (data.session) {
						notify('Welcome back ' + data.name
								+ ' ! Last Log IP : ' + data.lastlog + '',
								'inverse');
					} else {
						notify('Your Session has expired ! Please Log In !',
								'inverse');
					}
				},
				error : function(data) {
					$("#loaderImage").hide();
					alert("Some Error while getting user information !");
				}
			});
		});

function Product() {
	this.name = "";
	this.price = 0;
	this.quantity = 0;
	this.total = 0;
	this.available = 0;
}

function stockproductDetails(id, cp, sp, qty, name) {
	this.id = id;
	this.sp = sp;
	this.cp = cp;
	this.qty = qty;
	this.name = name;
}

function currentStockProducts(name, qty, cp, sp, prodId) {
	this.name = name;
	this.qty = qty;
	this.cp = cp;
	this.sp = sp;
	this.prodId = prodId;
}

function SellProduct(id, name, sellPrice, qty) {
	this.id = id;
	this.name = name;
	this.sellPrice = sellPrice;
	this.qty = qty;
}

function Product(available, name, price, quantity, total, id) {
	this.id = id;
	this.name = name;
	this.price = price;
	this.quantity = quantity;
	this.total = total;
	this.available = available;
	console.log("Name : " + name + ", Quantity : " + quantity + ", Price : "
			+ price + ", Total : " + total + ", Available : " + available);
}

function showErrorValidation(div, message) {
	if ($(div).attr("class").indexOf("has-error") < 0) {
		$(div).attr("class", $(div).attr("class") + " has-error");
		div = div + " small#error";
		$(div).text(message);
	}
}

function todayDate() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; // January is 0!
	var yyyy = today.getFullYear();

	if (dd < 10) {
		dd = '0' + dd
	}

	if (mm < 10) {
		mm = '0' + mm
	}

	today = dd + '/' + mm + '/' + yyyy;
	return today;
}

function attendance(empId) {
	$("#loaderImage").show();
	$.ajax({

		url : $("#basePath").val() + "/ajaxServlet.do",

		data : {
			eid : empId,
			action : "getAttendanceDetails"
		},
		method : "post",
		error : function(data, status, errorThrown) {
			$("#loaderImage").hide();
			alert("Error : " + status + "," + errorThrown);
		},
		success : function(data) {
			$("#loaderImage").hide();
			if (data.result == true) {
				swal("Already Marked Attendance",
						"Please select user whose attendance is not marked",
						"error");
				document.getElementById("mkAtten").disabled = "disabled";
			} else {
				document.getElementById("mkAtten").disabled = false;
			}

		}
	});
}

$("#searchEmpDetails")
		.click(
				function(e) {

					var ipnm = $("#inputName").val();
					if (ipnm == null || ipnm == "") {
						swal("No data found.", "Name can't be blank !", "error");
						return;
					}
					var s = document.getElementById("viewEmpData");
					s.innerHTML = "";
					$("#loaderImage").show();
					$
							.ajax({

								url : $("#basePath").val() + "/ajaxServlet.do",

								data : {
									name : ipnm,
									action : "getEmpDetails"
								},
								method : "post",
								error : function(data, status, errorThrown) {
									$("#loaderImage").hide();
									alert("Error : " + status + ","
											+ errorThrown);
								},
								success : function(data) {
									$("#loaderImage").hide();
									var heading = document.createElement('div');
									heading
											.setAttribute("class",
													"card-header");

									var hd1 = document.createElement('h2');
									$(hd1).text("Employee Details");
									var hd2 = document.createElement('small');
									$(hd2)
											.text(
													"Please select the Employee To Mark Attendance");

									heading.appendChild(hd1);
									heading.appendChild(hd2);
									s.appendChild(heading);

									var dtbl = document.createElement('div');
									dtbl.setAttribute("class",
											"table-responsive");

									var table = document.createElement('table');
									table.setAttribute("class",
											"table table-condensed");

									var tbhead = document
											.createElement('thead');
									var tbhdrow = document.createElement('tr');

									var tbhdtd1 = document.createElement('td');
									$(tbhdtd1).text("Select");
									tbhdrow.appendChild(tbhdtd1);

									var tbhdtd2 = document.createElement('td');
									$(tbhdtd2).text("Employee ID");
									tbhdrow.appendChild(tbhdtd2);

									var tbhdtd3 = document.createElement('td');
									$(tbhdtd3).text("Name");
									tbhdrow.appendChild(tbhdtd3);

									var tbhdtd4 = document.createElement('td');
									$(tbhdtd4).text("Email");
									tbhdrow.appendChild(tbhdtd4);

									var tbhdtd5 = document.createElement('td');
									$(tbhdtd5).text("Mobile");
									tbhdrow.appendChild(tbhdtd5);

									var tbhdtd6 = document.createElement('td');
									$(tbhdtd6).text("Date of Joining");
									tbhdrow.appendChild(tbhdtd6);

									var tbhdtd7 = document.createElement('td');
									$(tbhdtd7).text("Salary");
									tbhdrow.appendChild(tbhdtd7);

									tbhead.appendChild(tbhdrow);
									table.appendChild(tbhead); // Header
									// inserted

									var EmpDetails = data.EmpDetails;

									var tbbody = document
											.createElement('tbody');
									var html = "";
									for (var i = 0; i < data.EmpDetails.length; i++) {

										var tbbdrw = document
												.createElement('tr');
										
										var input = document
												.createElement('input');
										input.setAttribute("type", "radio");
										input.setAttribute("name", "empid");
										input.setAttribute("value",
												EmpDetails[i].eid);
										input.setAttribute("onchange",
												"attendance(this.value)");
										var tbbdtd = document
												.createElement('td');
										tbbdtd.appendChild(input);
										tbbdrw.appendChild(tbbdtd);

										var tbbdtd1 = document
												.createElement('td');
										$(tbbdtd1).text(EmpDetails[i].eid);
										tbbdrw.appendChild(tbbdtd1);

										var tbbdtd2 = document
												.createElement('td');
										$(tbbdtd2)
												.text(EmpDetails[i].user.name);
										tbbdrw.appendChild(tbbdtd2);

										var tbbdtd3 = document
												.createElement('td');
										$(tbbdtd3).text(
												EmpDetails[i].user.mobile);
										tbbdrw.appendChild(tbbdtd3);

										var tbbdtd4 = document
												.createElement('td');
										$(tbbdtd4).text(
												EmpDetails[i].user.email);
										tbbdrw.appendChild(tbbdtd4);

										var tbbdtd5 = document
												.createElement('td');
										$(tbbdtd5).text(EmpDetails[i].doj);
										tbbdrw.appendChild(tbbdtd5);

										var tbbdtd6 = document
												.createElement('td');
										$(tbbdtd6).text(EmpDetails[i].salary);
										tbbdrw.appendChild(tbbdtd6);

										tbbody.appendChild(tbbdrw);
									}

									table.appendChild(tbbody);
									dtbl.appendChild(table);
									s.appendChild(dtbl);

									var n2 = document.createElement('label');
									n2.setAttribute("class",
											"col-sm-2 control-label");
									$(n2).text("Today's Date");

									var n3 = document.createElement('div');
									n3.setAttribute("class", "col-sm-4");

									var n4 = document.createElement('div');
									n4.setAttribute("class", "fg-line");

									var n5 = document.createElement('input');
									n5.setAttribute("type", "text");
									n5.setAttribute("class", "form-control");
									n5.setAttribute("name", "curdate");
									n5.setAttribute("readonly", "true");
									n5.setAttribute("value", todayDate());

									n4.appendChild(n5);
									n3.appendChild(n4);

									$("#todayDate").html(n2);
									$("#todayDate").html(n3);

									var n6 = document.createElement('div');
									n6.setAttribute("class", "col-sm-12");

									var n7 = document.createElement('button');
									n7.setAttribute("type", "submit");
									n7
											.setAttribute("class",
													"btn btn-primary btn-lg col-sm-6 col-sm-offset-3");
									n7.setAttribute("id", "mkAtten");
									n7.setAttribute("disabled", "disabled");
									$(n7).text("Mark Attendance");

									n6.appendChild(n7);
									$("#markAtten").html(n6);
								}
							});
				}

		);

$('#selectFirmDetails').click(
		function() {
			// alert("button clicked");
			var firmID = $("input[name='firmID']:checked").val();
			// alert('Value is : '+firmID);
			var selectFirm;
			for (var j = 0; j < firmSelect.length; j++) {
				if (firmSelect[j].id == firmID)
					selectFirm = firmSelect[j];
			}
			$('#modalPrint').modal('hide');
			$("#loaderImage").show();
			$.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				data : {
					firm : firmID,
					action : "setFirm",
					firmString : JSON.stringify(selectFirm)
				},
				method : "post",

				success : function(data) {
					// console.log("Result :"+data);
					$("#loaderImage").hide();
					if (data.Response == "Success") {

						window.open($("#basePath").val()
								+ "/PrintOrder.do?print=order", "", "");
					} else if (data.Response == "Error") {

						alert("Some error can't print Order Confirmation ! ");
					}
				},

				error : function(data) {
					$("#loaderImage").hide();
					alert("Some error ! " + data);
				}
			});
		});

function loadFirms() {
	console.log("Load Firms called");
	$("#loaderImage").show();
	$.ajax({
		url : $("#basePath").val() + "/ajaxServlet.do",
		data : {
			mobile : "'9460008990','9413333853'",
			action : "getFirmsDetails"
		},
		method : "post",

		success : function(data) {
			$("#loaderImage").hide();
			console.log("Success" + data);
			// alert("Success : "+data.firms[0].name);
			firmSelect = data.firms;
			$('#firmDiv').html("");
			var html = "<div class='table-responsive'>";
			html += "<table style='width:100%;border: 2px solid white;border-spacing: 15px;'>";
			for (var i = 0; i < data.firms.length; i++) {
				var firm = data.firms[i];
				
				html += "<tr style='border: 1px solid white'>";
				html += "<td width='2%' style='padding: 5px'><div class='radio m-b-15'><label><input type='radio' name='firmID' value='"+firm.id+"' ><i class='input-helper'></i></label></div></td>";
				html += "<td width='98%' style='padding: 5px'><strong>Name : </strong>" + firm.name + "<br><strong>Mobile : </strong>" + firm.mobile + "<br><strong>TIN : </strong>" + firm.tin + "<br><strong>Address : </strong>" + firm.address.houseNo + ", "
						+ firm.address.line1 + ", " + firm.address.line2 + ", "
						+ firm.address.city + " - " + firm.address.zip + ", "
						+ firm.address.state + "</td>";
				html += "</tr>";
				
				/*
				 * 
			
				f += "<div class='row'>";
				f += "<label class='radio radio-inline'>";
				f += "<input type='radio' name='firmID' value='" + firm.id
						+ "' class='form-control' />";
				f += "<i class='input-helper'></i>";
				f += "<p>" + firm.name + "</p>";
				f += "<label>" + firm.mobile + "</label>";
				f += "<label>" + firm.tin + "</label>";
				console.log(f);
				f += "<label>" + firm.address.houseNo + ", "
						+ firm.address.line1 + ", " + firm.address.line2 + ", "
						+ firm.address.city + " - " + firm.address.zip + ", "
						+ firm.address.state + "</label></label>";
				f += "<p></p></div>";
				$('#firmDiv').append(f);
				*/
			}
			html += "</table></div>";
			$('#firmDiv').html(html);
			$('#modalPrint').modal('show');
		},

		error : function(data) {
			$("#loaderImage").hide();
			console.log("Error" + data);
			alert("Error");
		}
	});
}

$("#loanForm")
		.submit(
				function() {

					var amount = $('#inputAmount').val();
					var tenure = $('#inputTenure').val();
					var installment = $('#inputInstallment').val();
					var interest = $('#inputInterest').val();
					var validity = /^[0-9]\d*$/;

					var success = true;
					var div = "#amountDiv";
					if (amount == "") {
						showErrorValidation(div, "Amount can't be blank !");
						success = false;
					} else
						clearError(div);
					if (!validity.test(amount)) {
						showErrorValidation(div,
								"Amount can take integer values only !");
						success = false;
					} else
						clearError(div);
					var div = "#tenureDiv";
					if (tenure == "") {
						showErrorValidation(div, "Tenure can't be blank !");
						success = false;
					} else
						clearError(div);
					if (!validity.test(tenure)) {
						showErrorValidation(div,
								"Tenure can take integer values only !");
						success = false;
					} else
						clearError(div);
					var div = "#monthlyDiv";
					if (installment == "") {
						showErrorValidation(div,
								"Monthly Installment can't be blank !");
						success = false;
					} else
						clearError(div);
					if (!validity.test(installment)) {
						showErrorValidation(div,
								"Monthly installement can take integer values only !");
						success = false;
					} else
						clearError(div);
					var div = "#interestDiv";
					if (interest == "") {
						showErrorValidation(div,
								"Interest rate can't be blank !");
						success = false;
					} else
						clearError(div);
					if (iNaN(Number(interest))) {
						showErrorValidation(div,
								"Interest can take numeric values only !");
						success = false;
					} else
						clearError(div);

					return success;
				});

$('#capitalForm').submit(function() {

	var amount = $('#inputAmount').val();
	var rate = $('#inputRate').val();
	var lender = $('#inputLender').val();
	var validity = /^[0-9]\d*$/;

	console.log("Lender : " + lender);

	var success = true;
	var div = "#amountDiv";
	if (amount == "") {
		showErrorValidation(div, "Amount can't be blank");
		success = false;
	} else
		clearError(div);
	if (!validity.test(amount)) {
		showErrorValidation(div, "Amount can take integer values only !");
		success = false;
	} else
		clearError(div);
	var div = "#rateDiv";
	if (rate == "") {
		showErrorValidation(div, "Interest Rate can't be blank !");
		success = false;
	} else
		clearError(div);
	if (isNaN(Number(rate))) {
		showErrorValidation(div, "Interest can take numeric values only !");
		success = false;
	} else
		clearError(div);
	var div = "#lenderDiv";

	if (lender == "") {
		console.log("showed error");
		showErrorValidation(div, "Input the lender name please !");
		success = false;
	} else
		clearError(div);
	if (validity.test(lender)) {
		showErrorValidation(div, "Name can't take integer values !");
		success = false;
	} else
		clearError(div);

	return success;
});

function notify(from, msg, align, icon, type, animIn, animOut) {
	$.growl({
		icon : icon,
		title : '',
		message : msg,
		url : ''
	}, {
		element : 'body',
		type : type,
		allow_dismiss : true,
		placement : {
			from : from,
			align : align
		},
		offset : {
			x : 20,
			y : 85
		},
		spacing : 10,
		z_index : 1031,
		delay : 2500,
		timer : 1000,
		url_target : '_blank',
		mouse_over : false,
		animate : {
			enter : animIn,
			exit : animOut
		},
		icon_type : 'class',
		template : '<div data-growl="container" class="alert" role="alert">'
				+ '<button type="button" class="close" data-growl="dismiss">'
				+ '<span aria-hidden="true">&times;</span>'
				+ '<span class="sr-only">Close</span>' + '</button>'
				+ '<span data-growl="icon"></span>'
				+ '<span data-growl="title"></span>'
				+ '<span data-growl="message"></span>'
				+ '<a href="#" data-growl="url"></a>' + '</div>'
	});
};

$('#submitBTN').click(function() {
	$('#buttonValue').val("submit");
});

$('#resetBTN').click(function() {
	$('#buttonValue').val("reset");
});

function clearError(div) {
	$(div).attr("class", $(div).attr("class").replace(" has-error", ""));
	div = div + " small#error";
	$(div).text("");
}

$('#custForm').submit(function() {
	var name = $('#inputName').val();
	var email = $('#inputEmail').val();
	var house = $('#inputHouse').val();
	var line1 = $('#inputAddress1').val();
	var line2 = $('#inputAddress2').val();
	var city = $('#inputCity').val();
	var state = $('#inputState').val();
	var mobile = $('#inputMobile').val();
	var pin = $('#inputPin').val();
	var num = $('#numProd').val();
	var type = $("input[name=\"custType\"]:checked").val();

	var mob = /^[7-9]{1}[0-9]{9}$/;

	console.log(city + ":" + state);

	var success = true;
	var div = "#nameDiv";
	if (name == "") {
		showErrorValidation(div, "Name can't be blank");
		success = false;
	} else
		clearError(div);
	var div = "#houseDiv";
	if (house == "") {
		showErrorValidation(div, "House/Shop No. can't be blank !");
		success = false;
	} else
		clearError(div);
	var div = "#addDiv";
	if (line1 == "") {
		showErrorValidation(div, "Address can't be blank !");
		success = false;
	} else
		clearError(div);

	var div = "#cityDiv";
	if (city == "Select City") {
		showErrorValidation(div, "Select a city please !");
		success = false;
	} else
		clearError(div);

	var div = "#stateDiv";
	if (state == "Select State") {
		showErrorValidation(div, "Select a state please !");
		success = false;
	} else
		clearError(div);

	var div = "#pinDiv";
	if (pin == "") {
		showErrorValidation(div, "Pin Code can't be blank !");
		success = false;
	} else
		clearError(div);
	
	if(type == "" || type == null || type == undefined)
	{
		success = false;
		alert("Please select Customer type first !");
	}

	var div = "#mobileDiv";
	if (mobile == "") {
		showErrorValidation(div, "Enter a mobile number please !");
		success = false;
	} else if (mobile.length < 10 || !mob.test(mobile)) {
		showErrorValidation(div, "Mobile number invalid !");
		success = false;
	} else
		clearError(div);

	var div = "#categoryDiv";
	if (num == "0") {
		showErrorValidation(div, "Select a product first.");
		success = false;
	} else
		clearError(div);

	return success;
}

);

function showComment(order_id) {
	var q = document.getElementById("fillComment");
	q.innerHTML = "";

	var head = document.createElement('div');
	head.setAttribute("class", "card-header");

	var hd1 = document.createElement('h2');
	$(hd1).text("Fill Details");
	var hd2 = document.createElement('small');
	$(hd2).text("Please describe the problem in the text below:-");

	head.appendChild(hd1);
	head.appendChild(hd2);
	q.appendChild(head);

	var e1 = document.createElement('div');
	e1.setAttribute("class", "form-group");

	var ee1 = document.createElement('label');
	ee1.setAttribute("class", "col-sm-offset-2 col-sm-3 control-label");
	$(ee1).text("Order Id");

	var indiv = document.createElement('div');
	indiv.setAttribute("class", "col-sm-5");
	var ind = document.createElement('div');
	ind.setAttribute("class", "fg-line");
	var ee2 = document.createElement('input');
	ee2.setAttribute("type", "text");
	ee2.setAttribute("disabled", "true");
	ee2.setAttribute("name", "orderid");
	ee2.setAttribute("class", "form-control");
	ee2.setAttribute("value", order_id);
	ind.appendChild(ee2);
	indiv.appendChild(ind);

	e1.appendChild(ee1);
	e1.appendChild(indiv);
	q.appendChild(e1);

	var e2 = document.createElement('div');
	e2.setAttribute("class", "form-group");

	var e3 = document.createElement('label');
	e3.setAttribute("class", "col-sm-offset-2 col-sm-3 control-label");
	$(e3).text("Complaint Description");

	var indiv1 = document.createElement('div');
	indiv1.setAttribute("class", "col-sm-5");
	var ind1 = document.createElement('div');
	ind1.setAttribute("class", "fg-line");
	var ee3 = document.createElement('textarea');
	ee3.setAttribute("name", "comment");
	ee3.setAttribute("class", "form-control");
	ee3.setAttribute("placeholder",
			"Enter the detailed description of the Problem occured");
	ee3.setAttribute("autocomplete", "off");
	ee3.setAttribute("rows", "4");
	ind1.appendChild(ee3);
	indiv1.appendChild(ind1);

	e2.appendChild(e3);
	e2.appendChild(indiv1);
	q.appendChild(e2);

	var e4 = document.createElement('div');
	e4.setAttribute("class", "form-group");
	var ee4 = document.createElement('div');
	ee4.setAttribute("class", "col-sm-offset-5");
	var e5 = document.createElement('button');
	e5.setAttribute("type", "button");
	e5.setAttribute("class", "btn btn-primary btn-lg col-sm-3");
	e5.setAttribute("id", "submitRequest");
	$(e5).text("Complaint >>>");
	ee4.appendChild(e5);
	e4.appendChild(ee4);
	q.appendChild(e4);

}

$("#SearchOrderbtn")
		.click(
				function(e) {
					var s = document.getElementById("fillOrderDetails");
					var p = document.getElementById("fillComment");

					var ipnm = $("#inputName").val();
					var ipmob = $("#inputMobile").val();
					var ipoid = $("#inputOrderId").val();
					if ((ipnm == "" || ipnm == null)
							&& (ipmob == "" || ipmob == null)
							&& (ipoid == "" || ipoid == null)) {
						alert("No data Found. Please Fill At Least One Category");
						return;
					}

					s.innerHTML = "";
					p.innerHTML = "";
					$("#loaderImage").show();
					$
							.ajax({

								url : $("#basePath").val() + "/ajaxServlet.do",

								data : {
									name : ipnm,
									order : ipoid,
									mobile : ipmob,
									action : "getOrderByNameIDMobile"
								},
								error : function(data) {
									$("#loaderImage").hide();
									alert("Error : " + data);
								},
								success : function(data) {
									$("#loaderImage").hide();
									var heading = document.createElement('div');
									heading
											.setAttribute("class",
													"card-header");

									var hd1 = document.createElement('h2');
									$(hd1).text("Order Details");
									var hd2 = document.createElement('small');
									$(hd2)
											.text(
													"Please select the order for complain");

									heading.appendChild(hd1);
									heading.appendChild(hd2);
									s.appendChild(heading);

									var dtbl = document.createElement('div');
									dtbl.setAttribute("class",
											"table-responsive");

									var table = document.createElement('table');
									table.setAttribute("class",
											"table table-condensed");

									var tbhead = document
											.createElement('thead');
									var tbhdrow = document.createElement('tr');

									var tbhdtd1 = document.createElement('td');
									$(tbhdtd1).text("Select");
									tbhdrow.appendChild(tbhdtd1);

									var tbhdtd2 = document.createElement('td');
									$(tbhdtd2).text("Order ID");
									tbhdrow.appendChild(tbhdtd2);

									var tbhdtd3 = document.createElement('td');
									$(tbhdtd3).text("Name");
									tbhdrow.appendChild(tbhdtd3);

									var tbhdtd4 = document.createElement('td');
									$(tbhdtd4).text("Email");
									tbhdrow.appendChild(tbhdtd4);

									var tbhdtd5 = document.createElement('td');
									$(tbhdtd5).text("Mobile");
									tbhdrow.appendChild(tbhdtd5);

									var tbhdtd6 = document.createElement('td');
									$(tbhdtd6).text("Amount");
									tbhdrow.appendChild(tbhdtd6);

									var tbhdtd7 = document.createElement('td');
									$(tbhdtd7).text("Date");
									tbhdrow.appendChild(tbhdtd7);

									var tbhdtd8 = document.createElement('td');
									$(tbhdtd8).text("Time");
									tbhdrow.appendChild(tbhdtd8);

									var tbhdtd9 = document.createElement('td');
									$(tbhdtd9).text("View Details");
									tbhdrow.appendChild(tbhdtd9);

									tbhead.appendChild(tbhdrow);
									table.appendChild(tbhead); // Header
									// inserted

									var orderdetails = data.orderdetails;

									var tbbody = document
											.createElement('tbody');

									for (var i = 0; i < data.orderdetails.length; i++) {

										var tbbdrw = document
												.createElement('tr');

										var input = document
												.createElement('input');
										input.setAttribute("type", "radio");
										input.setAttribute("name", "orderid");
										input.setAttribute("onchange",
												"showComment(this.value)");
										input.setAttribute("value",
												orderdetails[i].oid);
										var tbbdtd = document
												.createElement('td');
										tbbdtd.appendChild(input);
										tbbdrw.appendChild(tbbdtd);

										var tbbdtd1 = document
												.createElement('td');
										$(tbbdtd1).text(orderdetails[i].oid);
										tbbdrw.appendChild(tbbdtd1);

										var tbbdtd2 = document
												.createElement('td');
										$(tbbdtd2).text(
												orderdetails[i].Customer.name);
										tbbdrw.appendChild(tbbdtd2);

										var tbbdtd3 = document
												.createElement('td');
										$(tbbdtd3).text(
												orderdetails[i].Customer.email);
										tbbdrw.appendChild(tbbdtd3);

										var tbbdtd4 = document
												.createElement('td');
										$(tbbdtd4)
												.text(
														orderdetails[i].Customer.mobile);
										tbbdrw.appendChild(tbbdtd4);

										var tbbdtd5 = document
												.createElement('td');
										$(tbbdtd5).text(orderdetails[i].amount);
										tbbdrw.appendChild(tbbdtd5);

										var tbbdtd6 = document
												.createElement('td');
										$(tbbdtd6).text(orderdetails[i].date);
										tbbdrw.appendChild(tbbdtd6);

										var tbbdtd7 = document
												.createElement('td');
										$(tbbdtd7).text(orderdetails[i].time);
										tbbdrw.appendChild(tbbdtd7);

										var tbbdtd8 = document
												.createElement('td');
										var vdiv = document
												.createElement('div');
										vdiv.setAttribute("class",
												"row dialog col-sm-2");
										var vdetail = document
												.createElement('button');
										vdetail.setAttribute("id", "sa-basic");
										vdetail.setAttribute("class",
												"btn btn-info");
										vdetail.setAttribute("type", "button");
										vdetail.setAttribute("value",
												orderdetails[i].oid);
										vdetail
												.setAttribute(
														"onclick",
														"window.open('../../PrintOrder.do?print=view&oid="
																+ orderdetails[i].oid
																+ "',1,'width=300,height=300','resizable=yes','status=yes')");
										$(vdetail).text("Click here");
										vdiv.appendChild(vdetail);
										tbbdtd8.appendChild(vdiv);
										tbbdrw.appendChild(tbbdtd8);

										tbbody.appendChild(tbbdrw);
									}

									table.appendChild(tbbody);
									dtbl.appendChild(table);
									s.appendChild(dtbl);
								}
							});
				}

		);

function getQueryStringValue(key) {
	return unescape(window.location.search.replace(new RegExp(
			"^(?:.*[&\\?]" + escape(key).replace(/[\.\+\*]/g, "\\$&")
					+ "(?:\\=([^&]*))?)?.*$", "i"), "$1"));
}

$("#sendOrderDetails")
		.click(
				function() {
					$('#modalMobile').modal('hide');
					$("#loaderImage").show();
					$
							.ajax({
								url : $("#basePath").val() + "/ajaxServlet.do",
								data : {
									mobile : $("#sendMobiles").val(),
									email : $("#sendEmails").val(),
									action : "sendOrderDetails"
								},
								method : "post",
								error : function(data) {
									$("#loaderImage").hide();
									alert("Error : " + data);
								},
								success : function(data) {
									$("#loaderImage").hide();
									// alert("Success : " +
									// data.customers[0].name);
									if (data.status == "success") {
										swal(
												"SMS/Email Sent Successfully !",
												"Order details has been sent to the mobile and the email ids.",
												"success");
									} else {
										swal(
												"SMS Sent, but Email Delivery Failed !",
												"Order details has been sent to the mobile but email order confirmation requires to generate an order confirmation.",
												"error");
									}

								}
							});
				});

$('#selectCustomerBtn').click(
		function() {
			var cid = document.forms["custForm"].custID.value;
			$('#modalCustomer').modal('hide');
			for (var i = 0; i < customers.length; i++) {
				if (customers[i].id == cid) {
					// alert(customers[i].name);
					$('#inputName').val(customers[i].name + "");
					
					$('#inputEmail').val(customers[i].email + "");
					var address = customers[i].address;
					$('#inputHouse').val(address.house_no + "");
					$('#inputAddress1').val(address.line1);
					$('#inputAddress2').val(address.line2);
					$('#inputAddress1').val(address.line1);
					$('#inputState').val(address.state);

					if(customers[i].type == "MERCHANT")
					{
						$("#typeCustomer").attr("checked",false);
						$("#typeMerchant").attr("checked",true);
					}
					else if(customers[i].type == "CUSTOMER")
					{
						$("#typeMerchant").attr("checked",false);
						$("#typeCustomer").attr("checked",true);
					}
					//alert("Type is : "+customers[i].type);
					
					var selectState = address.state;
					$("#loaderImage").show();
					$.ajax({
						url : $("#basePath").val() + "/ajaxServlet.do",
						method : "post",
						data : {
							action : "getCities",
							state : selectState
						},
						success : function(data) {
							$("#loaderImage").hide();
							$("#inputCity").html("");
							for (var i = 0; i < data.city.length; i++) {
								$("#inputCity").append(
										"<option value='" + data.city[i] + "'>"
												+ data.city[i] + "</option>");
							}

							$("#inputCity").val(address.city);
						},
						error : function(data) {
							$("#loaderImage").hide();
							swal('Some Error : ' + data);
						}

					});

					$('#tin').val(customers[i].tin);
					$('#type').val(customers[i].type);
					$('#inputMobile').val(customers[i].mobile);
					$('#inputPin').val(address.zip);
					break;
				}

			}

			// console.log(cid);
		});

$("#searchCust").click(
		function(e) {
			console.log($("#inputName").val());
			var n = $("#inputName").val();

			if (n == "" || n == null) {
				return;
			}
			$("#loaderImage").show();
			$.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				data : {
					name : n,
					action : "getCustomerDetails"
				},
				error : function(data) {
					$("#loaderImage").hide();
					alert("Error : " + data);
				},
				success : function(data) {
					// alert("Success : " + data.customers[0].name);
					$("#loaderImage").hide();
					var div = document.getElementById('custList');
					div.innerHTML = "";
					// customers = new Array();
					// alert("Number of customers : "+data.customers.length);
					customers = data.customers;
					var html = "<table style='width:100%;border: 1px' >";
					for (var j = 0; j < data.customers.length; j+=2) {
						
						html += "<tr>";
						html += "<td><div class='radio m-b-15'>";
                        html += "<label>";
                        html += "<input type='radio' name='custID' id='custID' value='"+customers[j].id+"'>";
                        html += "<i class='input-helper'></i>";
                        var radioText = customers[j].name.replace('"', '');
                        html += radioText;
                        html += "</label></div></td><td>"+customers[j].mobile+"</td>";
                        
                        if((j+1) < data.customers.length)
                        {
                        	html += "<td><div class='radio m-b-15'>";
                            html += "<label>";
                            html += "<input type='radio' name='custID' id='custID' value='"+customers[j+1].id+"'>";
                            html += "<i class='input-helper'></i>";
                            var radioText = customers[j+1].name.replace('"', '');
                            html += radioText;
                            html += "</label></div></td><td>"+customers[j+1].mobile+"</td>";
                        }
                        else	
                        {
                        	html += "<td></td>";
                        }
                        
                        
                        html += "</tr>";
					}
					html += "</table>";
					$("#custList").html(html);
					/*for (var j = 0; j < data.customers.length; j++) {

						var label = document.createElement('label');
						label
								.setAttribute("class",
										"radio radio-inline m-r-20");

						var input = document.createElement('input');
						input.setAttribute("type", "radio");
						input.setAttribute("name", "custID");
						input.setAttribute("id", "custID");
						input.setAttribute("value", customers[j].id);

						var i = document.createElement('i');
						i.setAttribute("class", "input-helper");

						var p = document.createElement('p');
						p.setAttribute('style', 'margin-top: -7px');

						label.appendChild(input);
						label.appendChild(i);
						var radioText = customers[j].name.replace('"', '');
						var text = document.createTextNode(radioText + " - "
								+ customers[j].mobile);
						p.appendChild(text);
						label.appendChild(p);
			
						div.appendChild(label);
					}*/

					$('#modalCustomer').modal('show');

				}
			});
		});

$("#productDiv").change(function() {
	var id = parseInt($("#selectProduct").val());
	// console.log("The value is : "+id);
	for (var k = 0; k < availProduct.length; k++) {
		var product = availProduct[k];
		// console.log("The type is : "+typeof product);
		if (product.id == id) {
			$('#sellPrice').html("" + product.sellPrice);
			$('#avail').html("" + product.qty);
			// console.log("Product selected is : "+product.name);
			break;
		}

	}
});

function showProductByCategory(resp) {
	var length = resp.products.length;

	var select = document.createElement("select");
	select.setAttribute("class", "form-control");
	select.setAttribute("id", "selectProduct");
	select.setAttribute("data-live-search", "true");

	var opt = document.createElement("option");
	opt.setAttribute("value", "");
	opt.innerHTML = "Select Product";
	select.appendChild(opt);

	for (var i = 0; i < length; i++) {
		var prod = resp.products[i];
		availProduct[i] = new SellProduct(prod.id, prod.name, prod.sell_price,
				prod.quantity);
		var option = document.createElement("option");
		option.setAttribute("value", prod.id);
		option.innerText = prod.name;
		select.appendChild(option);
		console.log("Option appended : " + prod.name);
	}

	$("#productDiv").html("");
	$("#productDiv").append(select);
}

$('#selectCategory').change(
		function() {
			console.log("selected : " + $(this).val());
			// alert("getting products");

			if ($(this).val() == "") {
				showErrorValidation("#categoryDiv", "Select a Category.");
				var select = document.createElement("select");
				select.setAttribute("class", "selectpicker");
				select.setAttribute("id", "selectProduct");
				select.setAttribute("data-live-search", "true");

				var opt = document.createElement("option");
				opt.setAttribute("value", "");
				opt.innerHTML = "Select Product";
				select.appendChild(opt);
				$("#productDiv").html("");
				$("#productDiv").append(select);
				return;
			} else
				clearError("#categoryDiv");
			$("#loaderImage").show();
			$.get($("#basePath").val() + "/ajaxServlet.do", {
				action : 'getProductByCategory',
				catgId : $(this).val()
			}, function(response) {
				$("#loaderImage").hide();
				try {
					console.log("The length of response is : "
							+ response.products.length);
					showProductByCategory(response);
				} catch (err) {
					alert(err);
				}

			});
		});

function loadData(text) {
	console.log(text);
}

function showError(message) {
	console.log(message);
	alert(message);
}

function deleteProduct(index) {
	products.splice(index - 1, 1);
	var num = $("#numProd").val();
	try {
		num = parseInt(num);
		num--;
		$("#numProd").val("" + num);
	} catch (err) {
		alert("Exception : " + err.message);
	}
	console.log(products);
	displayProducts();
}

function addProduct() {
	var name = document.getElementById("selectProduct").value;
	var pName = "";
	for (var k = 0; k < availProduct.length; k++) {
		var product = availProduct[k];
		// console.log("The type is : "+typeof product);
		if (product.id == name) {
			pName = product.name;
			// console.log("Product selected is : "+product.name);
			break;
		}

	}
	pName = pName + " : " + name;
	var qty = document.getElementById("quantity").value;
	var price = document.getElementById("price").value;
	var avail = document.getElementById("avail").innerText;

	var success = true;
	if (name == "") {
		showErrorValidation("#productDiv", "No Product Selected !");
		success = false;
	} else
		clearError("#productDiv");
	if (qty == "" || isNaN(Number(qty))) {
		showErrorValidation("#quantityDiv", "No Quantity entered !");
		success = false;
	} else
		clearError("#quantityDiv");
	if (price == "" || price == null || isNaN(Number(price))) {
		showErrorValidation("#priceDiv", "No Price entered!");
		success = false;
	} else
		clearError("#priceDiv");
	if (avail == "") {
		showErrorValidation("#productDiv", "No Product available !");
		success = false;
	} else
		clearError("#productDiv");

	if (!success)
		return;

	qty = parseInt(qty);
	price = parseInt(price);
	avail = parseFloat(avail);
	/*
	 * if (avail < qty) { showErrorValidation("#productDiv", "Product quantity
	 * is greater than its availablity !"); success = false; } else
	 * clearError("#productDiv");
	 */
	if (!success)
		return;

	var num = $("#numProd").val();
	try {
		num = parseInt(num);
		num++;
		$("#numProd").val("" + num);
	} catch (err) {
		alert("Exception : " + err.message);
	}

	var product = new Product(avail, pName, price, qty, price * qty);
	products.push(product);
	displayProducts();
}

function displayProducts() {
	var length = products.length;
	var index;
	var mainForm = document.getElementById('products');
	mainForm.innerHTML = "";
	for (index = 0; index < length; index++) {
		var name = products[index].name;
		var price = products[index].price;
		var qty = products[index].quantity;
		var total = products[index].total;
		var available = products[index].available;
		showProduct(available, index + 1, name, price, qty, total);
	}
}

function showProduct(available, index, name, price, qty, total) {
	var productRow = document.createElement("div");
	productRow.setAttribute("class", "row");

	var formDiv = document.createElement("div");
	formDiv.setAttribute("class", "form-group");

	var nameLabel = document.createElement("label");
	nameLabel.setAttribute("class", "col-sm-2 control-label");
	nameLabel.setAttribute("for", "inputProduct");
	nameLabel.innerHTML = "Product Name";

	formDiv.appendChild(nameLabel);

	var textDiv = document.createElement('div');
	textDiv.setAttribute("class", "col-sm-8");

	var disableDiv = document.createElement("div");
	disableDiv.setAttribute("class", "fg-line disabled");

	var productName = document.createElement("input");
	productName.setAttribute("type", "text");
	productName.setAttribute("class", "form-control");
	productName.setAttribute("value", name);
	productName.setAttribute("readonly", "true");
	productName.setAttribute("name", "prodName");

	disableDiv.appendChild(productName);
	textDiv.appendChild(disableDiv);
	formDiv.appendChild(textDiv);

	productRow.appendChild(formDiv);

	var mainForm = document.getElementById('products');
	mainForm.appendChild(productRow);

	// products.push(productRow);

	// ----------- For product Name -------------//

	var rowDiv = document.createElement("div");
	rowDiv.setAttribute("class", "row");

	var nameLabel = document.createElement("label");
	nameLabel.setAttribute("for", "inputProduct");
	nameLabel.setAttribute("class", "col-sm-2 control-label");
	nameLabel.innerHTML = "Quantity";

	var qtyDiv = document.createElement("div");
	qtyDiv.setAttribute("class", "col-sm-3 form-group");

	var disableDiv = document.createElement("div");
	disableDiv.setAttribute("class", "fg-line disabled");

	var productQty = document.createElement("input");
	productQty.setAttribute("type", "text");
	productQty.setAttribute("class", "form-control");
	productQty.setAttribute("value", qty);
	productQty.setAttribute("readonly", "true");
	productQty.setAttribute("name", "prodQty");

	var availSpan = document.createElement("span");
	availSpan.setAttribute("class", "zmdi zmdi-shopping-basket form-control-feedback");

	var smallE = document.createElement("small");
	smallE.setAttribute("class", "help-block");
	smallE.innerHTML = "Quantity available : <label>" + available + "</label>";

	disableDiv.appendChild(productQty);
	qtyDiv.appendChild(disableDiv);
	qtyDiv.appendChild(availSpan);
	qtyDiv.appendChild(smallE);
	rowDiv.appendChild(nameLabel);
	rowDiv.appendChild(qtyDiv);

	// ------- For Quantity ------ //

	var nameLabel = document.createElement("label");
	nameLabel.setAttribute("for", "inputProduct");
	nameLabel.setAttribute("class", "col-sm-2 control-label");
	nameLabel.innerHTML = "Price";

	var qtyDiv = document.createElement("div");
	qtyDiv.setAttribute("class", "col-sm-3");

	var formDIV = document.createElement("div");
	formDIV.setAttribute("class", "form-group");

	var disableDiv = document.createElement("div");
	disableDiv.setAttribute("class", "fg-line disabled");

	var productQty = document.createElement("input");
	productQty.setAttribute("type", "text");
	productQty.setAttribute("class", "form-control");
	productQty.setAttribute("value", price);
	productQty.setAttribute("readonly", "true");
	productQty.setAttribute("name", "prodPrice");

	var availSpan = document.createElement("span");
	availSpan.setAttribute("class", "zmdi zmdi-money form-control-feedback");

	var smallE = document.createElement("small");
	smallE.setAttribute("class", "help-block");
	smallE.innerHTML = "Total Price : Rs. <label>" + total + "</label>";

	disableDiv.appendChild(productQty);
	formDIV.appendChild(disableDiv);
	formDIV.appendChild(availSpan);
	formDIV.appendChild(smallE);

	qtyDiv.appendChild(formDIV);
	rowDiv.appendChild(nameLabel);
	rowDiv.appendChild(qtyDiv);

	// -------- For Price Div --------//

	var formDiv = document.createElement('div');
	formDiv.setAttribute("class", "form-input");

	var innerDiv = document.createElement('div');
	innerDiv.setAttribute("class", "col-sm-2");

	var fgline = document.createElement("div");
	fgline.setAttribute("class", "fg-line");

	var closeButton = document.createElement('a');
	closeButton.setAttribute("class",
			"btn bgm-red btn-float waves-effect waves-button waves-float");
	closeButton.setAttribute("onClick", "deleteProduct(" + index + ")");
	closeButton.setAttribute("data-toggle", "tooltip");
	closeButton.setAttribute("data-placement", "right");
	closeButton.setAttribute("data-original-title", "Remove this product");
	closeButton.setAttribute("id", "ms-compose");

	var i = document.createElement("i");
	i.setAttribute("class", "zmdi zmdi-close");

	closeButton.appendChild(i);
	fgline.appendChild(closeButton);
	innerDiv.appendChild(fgline);
	formDiv.appendChild(innerDiv);
	rowDiv.appendChild(formDiv);
	// ------- For Cross Button ------//

	mainForm.appendChild(rowDiv);
	// products.push(rowDiv);

}

function notify(message, type) {
	$.growl({
		message : message
	}, {
		type : type,
		allow_dismiss : false,
		label : 'Cancel',
		className : 'btn-xs btn-inverse',
		placement : {
			from : 'top',
			align : 'right'
		},
		delay : 2500,
		animate : {
			enter : 'animated bounceIn',
			exit : 'animated bounceOut'
		},
		offset : {
			x : 20,
			y : 85
		}
	});
};

// ----------------- Add Expenditure ------------------//

function generate() {
	var selectedValue = document.getElementById("selectType").value;
	// alert(selectedValue);
	var dynamic = document.getElementById("dynamic");
	dynamic.innerHTML = "";

	var rowDiv = document.createElement("div");
	rowDiv.setAttribute("class", "row");

	var formDiv = document.createElement("div");
	formDiv.setAttribute("id", "dynamicDiv");

	var label = document.createElement("label");
	label.setAttribute("class", "col-sm-2 control-label");
	label.setAttribute("for", "dynamic1");

	var span = document.createElement("span");
	var colDiv = document.createElement("div");

	var lineDiv = document.createElement("div");
	lineDiv.setAttribute("class", "fg-line");

	var input = document.createElement("input");
	input.setAttribute("class", "form-control");
	input.setAttribute("id", "dynamic1");
	input.setAttribute("name", "dynamic1");
	input.setAttribute("type", "text");

	var small = document.createElement("small");
	small.setAttribute("class", "help-block");
	small.setAttribute("id", "error");

	if (selectedValue == "salary") {
		formDiv.setAttribute("class", "form-input");

		label.innerHTML = "Employee Name";

		colDiv.setAttribute("class", "col-sm-3 m-b-25");

		input.setAttribute("placeholder", "Enter Employee Name");
		input.setAttribute("onClick", "showEmployees()");

		span.setAttribute("class", "zmdi zmdi-account form-control-feedback");

		var formDiv1 = document.createElement("div");
		formDiv1.setAttribute("class", "form-input");
		formDiv1.setAttribute("id", "dynamicDiv1");

		var label1 = document.createElement("label");
		label1.setAttribute("class", "col-sm-2 control-label");
		label1.setAttribute("for", "dynamic2");
		label1.innerHTML = "Received By";

		var small1 = document.createElement("small");
		small1.setAttribute("class", "help-block");
		small1.setAttribute("id", "error");

		var span1 = document.createElement("span");
		span1.setAttribute("class", "zmdi zmdi-account form-control-feedback");

		var colDiv1 = document.createElement("div");
		colDiv1.setAttribute("class", "col-sm-3 m-b-25");

		var lineDiv1 = document.createElement("div");
		lineDiv1.setAttribute("class", "fg-line");

		var input1 = document.createElement("input");
		input1.setAttribute("class", "form-control");
		input1.setAttribute("id", "dynamic2");
		input1.setAttribute("name", "dynamic2");
		input1.setAttribute("type", "text");
		input1.setAttribute("placeholder", "Received By (person name)");

		var span1 = document.createElement("span");
		span1.setAttribute("class", "zmdi zmdi-account form-control-feedback");

		formDiv.appendChild(label);
		lineDiv.appendChild(input);
		colDiv.appendChild(lineDiv);
		colDiv.appendChild(span);
		colDiv.appendChild(small);
		formDiv.appendChild(colDiv);
		rowDiv.appendChild(formDiv);

		dynamic.appendChild(rowDiv);

		// for 2 textfield

		formDiv1.appendChild(label1);
		lineDiv1.appendChild(input1);
		colDiv1.appendChild(lineDiv1);
		colDiv1.appendChild(span1);
		colDiv1.appendChild(small1);
		formDiv1.appendChild(colDiv1);
		rowDiv.appendChild(formDiv1);

		dynamic.appendChild(rowDiv);

	} else if (selectedValue == "daily") {
		formDiv.setAttribute("class", "form-group");
		label.innerHTML = "Expenditure Details";
		colDiv.setAttribute("class", "col-sm-8 m-b-25");
		input.setAttribute("placeholder", "Enter Details");
		span.setAttribute("class", "zmdi zmdi-file-text form-control-feedback");

		formDiv.appendChild(label);
		lineDiv.appendChild(input);
		colDiv.appendChild(lineDiv);
		colDiv.appendChild(span);
		colDiv.appendChild(small);
		formDiv.appendChild(colDiv);

		dynamic.appendChild(formDiv);
	} else if (selectedValue == "loan" || selectedValue == "interest") {
		colDiv.setAttribute("class", "col-sm-6 m-b-25");
		formDiv.setAttribute("class", "form-group");

		var select = document.createElement("select");
		select.setAttribute("class", "form-control");
		select.setAttribute("name", "dynamic1");
		select.setAttribute("id", "dynamic1");

		if (selectedValue == "interest") {
			label.innerHTML = "Select Lender Detail";
		} else {
			label.innerHTML = "Select Loan Detail";
		}

		formDiv.appendChild(label);
		colDiv.appendChild(select);
		colDiv.appendChild(small);
		formDiv.appendChild(colDiv);
		dynamic.appendChild(formDiv);

		getExpenditureData(selectedValue);
	} else if (selectedValue == "purchase") {
		getPurchaseData();
	}

}

function getPurchaseData() {

	$("#loaderImage").show();

	$
			.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				data : {
					action : "getPurchaseData"
				},

				method : "post",

				success : function(data) {
					$("#loaderImage").hide();
					// alert(data.status);
					if (data.status == "success") {
						var html = "<div class='form-group'>"
						html += "<label class='control-label col-sm-2'>Select Purchase</label><div class='col-sm-8'>"
						html += "<select class='form-control' name='dynamic1'>";
						// alert("Number : " + data.purchases.length);
						for (var i = 0; i < data.purchases.length; i++) {
							html += "<option value='" + data.purchases[i].id
									+ "'>" + data.purchases[i].merchant + " - "
									+ data.purchases[i].date
									+ " || Amount :<b> "
									+ data.purchases[i].amount
									+ "</b> || Paid : "
									+ data.purchases[i].paid + "</option>";
						}
						html += "</select></div></div>";

						$("#dynamic").html(html);
					} else {
						swal("No purchases to show !");
					}
				},

				error : function(data) {
					$("#loaderImage").hide();
					alert("Error : " + data);
				}

			});

}

$("#expForm").submit(function() {

	var selectedType = $('#selectType').val();
	var amount = $('#amount').val();
	var selectedMode = $('#selectMode').val();
	var description = $('#inputDesc').val();
	var paid = $('#inputPaid').val();
	var dynamic1 = $('#dynamic1').val();
	var dynamic2 = $('#dynamic2').val();

	var success = true;
	var div = "#typeDiv";
	if (selectedType == "noSelect") {
		showErrorValidation(div, "Please select the type of Expenditure !");
		success = false;
	} else
		clearError(div);

	var div = "#amountDiv";
	if (amount == "") {
		showErrorValidation(div, "Please enter the amount !");
		success = false;
	} else
		clearError(div);

	var div = "#modeDiv";
	if (selectedMode == "noSelect") {
		showErrorValidation(div, "Please select the mode !");
		success = false;
	} else
		clearError(div);

	var div = "#descDiv";
	if (description == "") {
		showErrorValidation(div, "Description can't be empty !");
		success = false;
	} else
		clearError(div);

	var div = "#paidDiv";
	if (paid == "") {
		showErrorValidation(div, "Can't left empty. Enter the name please !");
		success = false;
	} else
		clearError(div);

	if (dynamic1 == "" || dynamic1 == "noSelect") {
		var div = "#dynamicDiv";
		if (dynamic1 == "" || dynamic1 == "noSelect") {
			showErrorValidation(div, "This field can't be empty !");
			success = false;
		} else
			clearError(div);
	}
	if (dynamic2 == "") {
		var div = "#dynamicDiv1";
		if (dynamic2 == "") {
			showErrorValidation(div, "The field can't be empty !");
			success = false;
		} else
			clearError(div);
	}

	return success;
});

function getExpenditureData(type) {
	$("#loaderImage").show();
	$.get($("#basePath").val() + "/ajaxServlet.do", {
		action : 'getExpenditureDetail',
		expType : type
	}, function(response) {
		$("#loaderImage").hide();
		try {
			if (response.type == "loan") {
				console.log("The length of Loan response is : "
						+ response.expDetail.length);
				showLoanData(response);
			} else {
				console.log("The length of Interest response is : "
						+ response.expDetail.length);
				showInterestData(response);
			}

		} catch (err) {
			alert(err);
		}

	});
}

function showLoanData(resp) {
	var length = resp.expDetail.length;

	var select = document.getElementById("dynamic1");

	for (var i = 0; i < length; i++) {
		var loan = resp.expDetail[i];
		var option = document.createElement("option");
		option.setAttribute("value", loan.id);
		option.innerText = "Amount : " + loan.amount + " , Installment : "
				+ loan.installment;
		select.appendChild(option);
		console.log("Option appended : " + loan.id);
	}
}

function showInterestData(resp) {
	var length = resp.expDetail.length;

	var select = document.getElementById("dynamic1");

	for (var i = 0; i < length; i++) {
		var interest = resp.expDetail[i];
		var option = document.createElement("option");
		option.setAttribute("value", interest.id);
		option.innerText = "Lender : " + interest.lender + " , Amount : "
				+ interest.amount;
		select.appendChild(option);
		console.log("Option appended : " + interest.id);
	}
}

function showEmployees() {
	$("#loaderImage").show();
	$.ajax({
		url : $("#basePath").val() + "/ajaxServlet.do",
		data : {
			action : "getEmployeeDetails"
		},
		error : function(data) {
			$("#loaderImage").hide();
			alert("Error : " + data);
		},
		success : function(data) {
			$("#loaderImage").hide();
			var div = document.getElementById("employeeList");
			div.innerHTML = "";

			employees = data.employees;
			var html = "<table style='width:100%'>";
			for (var j = 0; j < data.employees.length; j+=2) {

				html += "<tr>";
				html += "<td><div class='radio m-b-15'>";
                html += "<label>";
                html += "<input type='radio' name='inputName' value='"+employees[j].empId+"'>";
                html += "<i class='input-helper'></i>";
                var radioText = employees[j].name.replace('"', '');
                html += radioText;
                html += "</label></div></td><td>"+employees[j].mobile+"</td>";
                
                if((j+1) < data.employees.length)
                {
                	html += "<td><div class='radio m-b-15'>";
                    html += "<label>";
                    html += "<input type='radio' name='inputName' value='"+employees[j+1].empId+"'>";
                    html += "<i class='input-helper'></i>";
                    var radioText = employees[j+1].name.replace('"', '');
                    html += radioText;
                    html += "</label></div></td><td>"+employees[j+1].mobile+"</td>";
                }
                else	
                {
                	html += "<td></td>";
                }
				html += "</tr>";
				/*
				var label = document.createElement('label');
				label.setAttribute("class", "radio radio-inline m-r-20");

				var input = document.createElement('input');
				input.setAttribute("type", "radio");
				input.setAttribute("name", "inputName");
				input.setAttribute("value", employees[j].empId);

				var i = document.createElement('i');
				i.setAttribute("class", "input-helper");

				var p = document.createElement('p');
				p.setAttribute('style', 'margin-top: -7px');

				label.appendChild(input);
				label.appendChild(i);
				var radioText = employees[j].name;
				var text = document.createTextNode(radioText + " - "
						+ employees[j].mobile);
				p.appendChild(text);
				label.appendChild(p);

				div.appendChild(label);*/
			}
			html += "</table>";
			div.innerHTML = html;
			$('#modalEmployee').modal('show');
		}
	});
}

$('#selectEmployeeBtn').click(function() {
	var empId = document.forms["expForm"].inputName.value;
	$('#modalEmployee').modal('hide');
	for (var i = 0; i < employees.length; i++) {
		if (employees[i].empId == empId) {
			// alert(customers[i].name);
			$('#dynamic1').val(employees[i].name + "");
		}
	}
	// console.log(cid);
	document.getElementById("empId").value = empId;
});

// ----------------- Add Stock -----------------//

function checkStockForm() {
	var category = $('#selectCat').val();
	var product = $('#selectProd').val();
	var quantity = $('#quantity').val();
	var cp = $('#cp').val();
	var sp = $('#sp').val();

	var validity = /^[0-9]\d*$/;

	var success = true;
	var div = "#catDiv";
	if (category == "") {
		showErrorValidation(div, "Please select a product category !");
		success = false;
	} else
		clearError(div);

	var div = "#prodOuterDiv";
	if (product == "") {
		showErrorValidation(div, "Please select a product !");
		success = false;
	} else
		clearError(div);

	var div = "#qtyDiv";
	if (quantity == "") {
		showErrorValidation(div, "Please enter quantity of product !");
		success = false;
	} else
		clearError(div);
	if (!validity.test(quantity)) {
		showErrorValidation(div, "Quantity can take integer values only !");
		success = false;
	} else
		clearError(div);

	var div = "#cpDiv";
	if (cp == "") {
		showErrorValidation(div, "Cost Price can't be empty !");
		success = false;
	} else
		clearError(div);

	var div = "#spDiv";
	if (sp == "") {
		showErrorValidation(div, "Selling price can't be empty !");
		success = false;
	} else
		clearError(div);

	return success;
}

$("#stockForm")
		.submit(
				function() {

					var status = checkStockForm();

					if (status) {
						var num = $("#numProd").val();
						num = parseInt(num);
						if (num == 0) {
							swal({
								title : "Add product first !",
								text : "Click on the add button (bottom left) to add the product.",
								timer : 2000,
								showConfirmButton : false
							});
							return !status;
						}
					} else
						return status;
				});

function addMoreStock() {
	if (!checkStockForm())
		return;

	var num = $("#numProd").val();
	try {
		num = parseInt(num);
		num++;
		$("#numProd").val("" + num);
	} catch (err) {
		alert("Exception : " + err.message);
	}

	var prodId = document.getElementById("selectProd").value;
	var name = $("#selectProd option:selected").text();
	var qty = document.getElementById("quantity").value;
	var cp = document.getElementById("cp").value;
	var sp = document.getElementById("sp").value;

	console.log(name);
	console.log(qty);
	var product = new currentStockProducts(name, qty, cp, sp, prodId);
	totalStockProducts.push(product);

	displayStockProducts();
}

function displayStockProducts() {
	var length = totalStockProducts.length;
	var index;
	var mainForm = document.getElementById("product");
	mainForm.innerHTML = "";
	for (index = 0; index < length; index++) {
		var name = totalStockProducts[index].name;
		var qty = totalStockProducts[index].qty;
		var cp = totalStockProducts[index].cp;
		var sp = totalStockProducts[index].sp;
		var prodId = totalStockProducts[index].prodId;

		console.log(name);
		console.log(qty);
		showStockProduct(prodId, qty, name, cp, sp, index + 1);
	}
}

function deleteStockProduct(index) {
	totalStockProducts.splice(index - 1, 1);
	var num = $("#numProd").val();
	try {
		num = parseInt(num);
		num--;
		$("#numProd").val("" + num);
	} catch (err) {
		alert("Exception : " + err.message);
	}
	console.log(totalStockProducts);
	displayStockProducts();
}

function showStockProduct(prodId, qty, name, cp, sp, index) {
	console.log(qty);
	console.log(name);

	var mainForm = document.getElementById("product");

	// line

	var line = document.createElement("hr");

	// for product name ----

	var formDiv = document.createElement("div");
	formDiv.setAttribute("class", "form-group");

	var label = document.createElement("label");
	label.setAttribute("class", "col-sm-2 control-label");
	label.setAttribute("for", "selectProd");
	label.innerHTML = "Product Name";

	var colDiv = document.createElement("div");
	colDiv.setAttribute("class", "col-sm-8");

	var disableDiv = document.createElement("div");
	disableDiv.setAttribute("class", "fg-line");

	var productName = document.createElement("input");
	productName.setAttribute("type", "text");
	productName.setAttribute("class", "form-control");
	productName.setAttribute("readonly", "true");
	productName.setAttribute("value", name);
	productName.setAttribute("name", "inputProdName");

	formDiv.appendChild(label);
	disableDiv.appendChild(productName);
	colDiv.appendChild(disableDiv);
	formDiv.appendChild(colDiv);

	mainForm.appendChild(line);
	mainForm.appendChild(formDiv);

	// for quantity ----

	var formDiv = document.createElement("div");
	formDiv.setAttribute("class", "form-group");

	var label = document.createElement("label");
	label.setAttribute("class", "col-sm-2 control-label");
	label.setAttribute("for", "inputQuantity");
	label.innerHTML = "Quantity";

	var colDiv = document.createElement("div");
	colDiv.setAttribute("class", "col-sm-8");

	var disableDiv = document.createElement("div");
	disableDiv.setAttribute("class", "fg-line");

	var productQty = document.createElement("input");
	productQty.setAttribute("type", "text");
	productQty.setAttribute("class", "form-control");
	productQty.setAttribute("readonly", "true");
	productQty.setAttribute("value", qty);
	productQty.setAttribute("name", "inputProdQty");

	formDiv.appendChild(label);
	disableDiv.appendChild(productQty);
	colDiv.appendChild(disableDiv);
	formDiv.appendChild(colDiv);

	mainForm.appendChild(formDiv);

	// for cp and sp

	var rowDiv = document.createElement("div");
	rowDiv.setAttribute("class", "row");

	var formDiv = document.createElement("div");
	formDiv.setAttribute("class", "form-input");

	var label = document.createElement("label");
	label.setAttribute("class", "col-sm-2 control-label");
	label.setAttribute("for", "inputCP");
	label.innerHTML = "Cost Price";

	var colDiv = document.createElement("div");
	colDiv.setAttribute("class", "col-sm-3 m-b-25");

	var disableDiv = document.createElement("div");
	disableDiv.setAttribute("class", "fg-line disabled");

	var productCP = document.createElement("input");
	productCP.setAttribute("type", "text");
	productCP.setAttribute("class", "form-control");
	productCP.setAttribute("value", cp);
	productCP.setAttribute("readonly", "true");
	productCP.setAttribute("name", "prodCP");

	formDiv.appendChild(label);
	disableDiv.appendChild(productCP);
	colDiv.appendChild(disableDiv);
	formDiv.appendChild(colDiv);
	rowDiv.appendChild(formDiv);

	var formDiv = document.createElement("div");
	formDiv.setAttribute("class", "form-input");

	var label = document.createElement("label");
	label.setAttribute("class", "col-sm-2 control-label");
	label.setAttribute("for", "inputSP");
	label.innerHTML = "Selling Price";

	var colDiv = document.createElement("div");
	colDiv.setAttribute("class", "col-sm-3 m-b-25");

	var disableDiv = document.createElement("div");
	disableDiv.setAttribute("class", "fg-line disabled");

	var productSP = document.createElement("input");
	productSP.setAttribute("type", "text");
	productSP.setAttribute("class", "form-control");
	productSP.setAttribute("value", sp);
	productSP.setAttribute("readonly", "true");
	productSP.setAttribute("name", "prodSP");

	var hiddenInput = document.createElement("input");
	hiddenInput.setAttribute("type", "hidden");
	hiddenInput.setAttribute("name", "productId");
	hiddenInput.setAttribute("id", "productId");
	hiddenInput.setAttribute("value", prodId);

	formDiv.appendChild(label);
	formDiv.appendChild(hiddenInput);
	disableDiv.appendChild(productSP);
	colDiv.appendChild(disableDiv);
	formDiv.appendChild(colDiv);
	rowDiv.appendChild(formDiv);

	// for close button ----

	var formDiv = document.createElement('div');
	formDiv.setAttribute("class", "form-input");

	var innerDiv = document.createElement('div');
	innerDiv.setAttribute("class", "col-sm-2");

	var fgline = document.createElement("div");
	fgline.setAttribute("class", "fg-line");

	var closeButton = document.createElement('a');
	closeButton.setAttribute("class",
			"btn bgm-red btn-float waves-effect waves-button waves-float");
	closeButton.setAttribute("onClick", "deleteStockProduct(" + index + ")");
	closeButton.setAttribute("data-toggle", "tooltip");
	closeButton.setAttribute("data-placement", "right");
	closeButton.setAttribute("data-original-title", "Remove this product");
	closeButton.setAttribute("id", "ms-compose");

	var i = document.createElement("i");
	i.setAttribute("class", "zmdi zmdi-close");

	closeButton.appendChild(i);
	fgline.appendChild(closeButton);
	innerDiv.appendChild(fgline);
	formDiv.appendChild(innerDiv);
	rowDiv.appendChild(formDiv);

	mainForm.appendChild(rowDiv);
}

$('#selectCat').change(
		function() {
			console.log("selected : " + $(this).val());

			if ($(this).val() == "") {
				var select = document.createElement("select");
				select.setAttribute("class", "form-control");
				select.setAttribute("id", "selectProd");
				select.setAttribute("data-live-search", "true");

				var opt = document.createElement("option");
				opt.setAttribute("value", "");
				opt.innerHTML = "Select Product";
				select.appendChild(opt);
				$("#prodDiv").html("");
				$("#prodDiv").append(select);
			}
			$("#loaderImage").show();
			$.get($("#basePath").val() + "/ajaxServlet.do", {
				action : 'getProductByCategory',
				catgId : $(this).val()
			}, function(response) {
				$("#loaderImage").hide();
				try {
					console.log("The length of response is : "
							+ response.products.length);
					showProducts(response);
				} catch (err) {
					alert(err);
				}

			});
		});

function showProducts(resp) {
	var length = resp.products.length;

	var select = document.createElement("select");
	select.setAttribute("class", "form-control");
	select.setAttribute("id", "selectProd");
	select.setAttribute("data-live-search", "true");
	select.setAttribute("onchange", "stockDetails()");

	var opt = document.createElement("option");
	opt.setAttribute("value", "");
	opt.innerHTML = "Select Product";
	select.appendChild(opt);

	var small = document.createElement("small");
	small.setAttribute("id", "error");
	small.setAttribute("class", "help-block");

	for (var i = 0; i < length; i++) {
		var prod = resp.products[i];
		stockProducts[i] = new stockproductDetails(prod.id, prod.cost_price,
				prod.sell_price, prod.quantity, prod.name);
		var option = document.createElement("option");
		option.setAttribute("value", prod.id);
		option.innerText = prod.name;
		select.appendChild(option);
		console.log("Option appended : " + prod.name);
	}

	$("#prodDiv").html("");
	$("#prodDiv").append(select);
	$("#prodDiv").append(small);
}

function stockDetails() {
	// alert("hello");
	var prodId = document.getElementById("selectProd").value;
	var cp = "";
	var sp = "";
	var qty = "";
	for (var i = 0; i < stockProducts.length; i++) {
		if (stockProducts[i].id == prodId) {
			cp = stockProducts[i].cp;
			sp = stockProducts[i].sp;
			qty = stockProducts[i].qty;
			break;
		}
	}

	document.getElementById("avail").innerText = qty;
	document.getElementById("cp").value = cp;
	document.getElementById("sp").value = sp;
}

$('#addCatButton').click(function() {
	$('#modalCat').modal('show');

	$('#addCategory').click(function() {
		var success = true;
		var div = "#addCatDiv";
		if ($('#catName').val() == "") {
			showErrorValidation(div, "Enter the category name !");
			success = false;
		} else
			clearError(div);

		if (!success)
			return;
		$("#loaderImage").show();
		$.ajax({
			url : $("#basePath").val() + "/ajaxServlet.do",
			data : {
				action : "addCategory",
				catName : document.getElementById("catName").value
			},
			error : function(data) {
				$("#loaderImage").hide();
				alert("Error : " + data);
			},
			success : function(data) {
				$("#loaderImage").hide();
				$('#modalCat').modal('hide');
				/*
				 * notify("top",'A new Category has been added successfully.',
				 * "right", "fa fa-comments", "success", "animated flipInY",
				 * "animated flipOutY");
				 */
				swal({
					title : "Category added !",
					text : "The new Category has been added successfully.",
					type : "success",
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "Continue",
				}, function() {
					window.location.reload();
				});

			}
		});
	});
});

$('#addProdButton')
		.click(
				function() {
					var categoryId = $('#selectCat').val();
					var categoryName = $("#selectCat option:selected").text();

					if (categoryId == "") {
						swal({
							title : "Select Category !",
							text : "Select a category first to add a new product.",
							timer : 2000,
							showConfirmButton : false
						});
					} else {
						$('#modalProd').modal('show');

						document.getElementById("prodCat").value = "Product Category - "
								+ categoryName.toUpperCase();

						$('#addProduct')
								.click(
										function() {
											var validity = /^[0-9]\d*$/;

											var success = true;
											var div = "#addNameDiv";
											if ($('#prodName').val() == "") {
												showErrorValidation(div,
														"Enter the Product name !");
												success = false;
											} else
												clearError(div);

											var div = "#addQtyDiv";
											if ($('#prodQty').val() == "") {
												showErrorValidation(div,
														"Enter the Quantity of new Product !");
												success = false;
											} else
												clearError(div);
											if (!validity.test($('#prodQty')
													.val())) {
												showErrorValidation(div,
														"Quantity take integer values only !");
												success = false;
											} else
												clearError(div);

											var div = "#addSpDiv";
											if ($('#prodSp').val() == "") {
												showErrorValidation(div,
														"Enter the Selling price !");
												success = false;
											} else
												clearError(div);
											if (!validity.test($('#prodSp')
													.val())) {
												showErrorValidation(div,
														"Selling price can't take non-integer values !");
												success = false;
											} else
												clearError(div);

											var div = "#addCpDiv";
											if ($('#prodCp').val() == "") {
												showErrorValidation(div,
														"Enter the Cost Price !");
												success = false;
											} else
												clearError(div);
											if (!validity.test($('#prodCp')
													.val())) {
												showErrorValidation(div,
														"Cost price can't take non-integer values !");
												success = false;
											} else
												clearError(div);

											if (!success)
												return;

											var productName = $('#prodName')
													.val();
											var productQty = $('#prodQty')
													.val();
											var productSp = $('#prodSp').val();
											var productCp = $('#prodCp').val();
											$("#loaderImage").show();
											$
													.ajax({
														url : $("#basePath")
																.val()
																+ "/ajaxServlet.do",
														data : {
															action : "addNewProduct",
															catId : categoryId,
															prodName : productName,
															prodQty : productQty,
															prodSp : productSp,
															prodCp : productCp
														},
														error : function(data) {
															$("#loaderImage")
																	.hide();
															alert("Error : "
																	+ data);
														},
														success : function(data) {
															$("#loaderImage")
																	.hide();
															$('#modalProd')
																	.modal(
																			'hide');
															/*
															 * notify("top",'A
															 * new Category has
															 * been added
															 * successfully.',
															 * "right", "fa
															 * fa-comments",
															 * "success",
															 * "animated
															 * flipInY",
															 * "animated
															 * flipOutY");
															 */
															swal(
																	{
																		title : "Product added !",
																		text : "The new product ("
																				+ productName
																						.toUpperCase()
																				+ ") has been added successfully to category - "
																				+ categoryName
																						.toUpperCase(),
																		type : "success",
																		confirmButtonColor : "#DD6B55",
																		confirmButtonText : "Continue",
																	},
																	function() {
																		window.location
																				.reload();
																	});
														}
													});
										});
					}
				});

$('#merchantForm').submit(function() {
	var merchant = $('#selectMerchant').val();
	var amount = $('#amount').val();
	var selectedMode = $('#selectMode').val();
	var description = $('#inputDesc').val();
	var paid = $('#inputPaid').val();
	var current = $('#currentPayment').val();
	var validity = /^[0-9]\d*$/;
	
	var success = true;
	
	if($("#addPayment").checked)
	{
		var div = "#merchantDiv";
		if (merchant == "") {
			showErrorValidation(div, "Please select merchant name !");
			success = false;
		} else
			clearError(div);

		var div = "#amountDiv";
		if (amount == "") {
			showErrorValidation(div, "Please enter the amount !");
			success = false;
		} else
			clearError(div);
		if (!validity.test(amount)) {
			showErrorValidation(div, "Amount can take integer values only !");
			success = false;
		} else
			clearError(div);

		var div = "#currentDiv";
		if (current == "") {
			showErrorValidation(div, "Please select the mode !");
			success = false;
		} else
			clearError(div);

		var div = "#modeDiv";
		if (selectedMode == "") {
			showErrorValidation(div, "Please select the mode !");
			success = false;
		} else
			clearError(div);

		var div = "#descDiv";
		if (description == "") {
			showErrorValidation(div, "Description can't be empty !");
			success = false;
		} else
			clearError(div);

		var div = "#paidDiv";
		if (paid == "") {
			showErrorValidation(div, "Can't left empty. Enter the name please !");
			success = false;
		} else
			clearError(div);
	}

	return success;
});

$('#addMerchantButton')
		.click(
				function() {
					$('#modalMerchant').modal('show');

					$('#addMerchant')
							.click(
									function() {
										var mob = /^[7-9]{1}[0-9]{9}$/;

										var success = true;
										var div = "#nameDiv";
										if ($('#merchantName').val() == "") {
											showErrorValidation(div,
													"Please enter merchant name !");
											success = false;
										} else
											clearError(div);

										var div = "#mobileDiv";
										if ($('#mobile').val() == "") {
											showErrorValidation(div,
													"Please enter the mobile no !");
											success = false;
										} else
											clearError(div);
										if ($('#mobile').val().length < 10
												|| !mob
														.test($('#mobile')
																.val())) {
											showErrorValidation(div,
													"Invalid mobile no !");
											success = false;
										} else
											clearError(div);

										var div = "#emailDiv";
										if ($('#email').val() == "") {
											showErrorValidation(div,
													"Please enter email id !");
											success = false;
										} else
											clearError(div);

										var div = "#tinDiv";
										if ($('#tin').val() == "") {
											showErrorValidation(div,
													"Please enter tin no !");
											success = false;
										} else
											clearError(div);

										if (!success)
											return;

										var merchantName = $('#merchantName')
												.val();
										var mobile = $('#mobile').val();
										var email = $('#email').val();
										var tin = $('#tin').val();
										$("#loaderImage").show();
										$
												.ajax({
													url : $("#basePath").val()
															+ "/ajaxServlet.do",
													data : {
														action : "addNewMerchant",
														merchantName : merchantName,
														mobile : mobile,
														email : email,
														tin : tin,
														type : "MERCHANT"
													},
													error : function(data) {
														$("#loaderImage")
																.hide();
														alert("Error : " + data);
													},
													success : function(data) {
														$("#loaderImage")
																.hide();
														$('#modalMerchant')
																.modal('hide');

														swal(
																{
																	title : "Merchant added !",
																	text : "The new merchant (Name - "
																			+ merchantName
																					.toUpperCase()
																			+ ") has been added successfully.",
																	type : "success",
																	confirmButtonColor : "#DD6B55",
																	confirmButtonText : "Continue",
																},
																function() {
																	window.location
																			.reload();
																});
													}
												});

									});
				});

$('#searchCustomerButton')
		.click(
				function() {
					var s = document.getElementById("fillCustomerDetails");

					var ipname = $("#name").val();
					var ipmob = $("#mobile").val();
					if ((ipname == "" || ipname == null)
							&& (ipmob == "" || ipmob == null)) {
						swal(
								"No data found !",
								"Please enter name or mobile and click on search customer.",
								"warning");
						return;
					}

					s.innerHTML = "";

					$("#loaderImage").show();
					$
							.ajax({
								url : $("#basePath").val() + "/ajaxServlet.do",

								data : {
									name : ipname,
									mobile : ipmob,
									action : "getCustomerByNameMob"
								},
								error : function(data) {
									$("#loaderImage").hide();
									alert("Error : " + data);
								},
								success : function(data) {
									$("#loaderImage").hide();

									var heading = document.createElement('div');
									heading
											.setAttribute("class",
													"card-header");

									var hd1 = document.createElement('h2');
									$(hd1).text("Customer Details");
									var hd2 = document.createElement('small');
									$(hd2)
											.text(
													"Please select a customer to edit details");

									heading.appendChild(hd1);
									heading.appendChild(hd2);
									s.appendChild(heading);

									var dtbl = document.createElement('div');
									dtbl.setAttribute("class",
											"table-responsive");

									var table = document.createElement('table');
									table.setAttribute("class",
											"table table-condensed");

									var tbhead = document
											.createElement('thead');
									var tbhdrow = document.createElement('tr');

									var tbhdtd1 = document.createElement('td');
									$(tbhdtd1).text("Select");
									tbhdrow.appendChild(tbhdtd1);

									var tbhdtd2 = document.createElement('td');
									$(tbhdtd2).text("Name");
									tbhdrow.appendChild(tbhdtd2);

									var tbhdtd3 = document.createElement('td');
									$(tbhdtd3).text("Email");
									tbhdrow.appendChild(tbhdtd3);

									var tbhdtd4 = document.createElement('td');
									$(tbhdtd4).text("Mobile");
									tbhdrow.appendChild(tbhdtd4);

									var tbhdtd5 = document.createElement('td');
									$(tbhdtd5).text("Tin No.");
									tbhdrow.appendChild(tbhdtd5);

									var tbhdtd6 = document.createElement('td');
									$(tbhdtd6).text("Address");
									tbhdrow.appendChild(tbhdtd6);

									tbhead.appendChild(tbhdrow);
									table.appendChild(tbhead); // Header
									// inserted

									var custdetails = data.customers;

									var tbbody = document
											.createElement('tbody');

									for (var i = 0; i < data.customers.length; i++) {

										customerDetails[i] = new Customer(
												custdetails[i].id,
												custdetails[i].name,
												custdetails[i].email,
												custdetails[i].mobile,
												custdetails[i].tin,
												custdetails[i].address.house_no,
												custdetails[i].address.line1,
												custdetails[i].address.line2,
												custdetails[i].address.city,
												custdetails[i].address.state,
												custdetails[i].address.zip,
												custdetails[i].type);

										var tbbdrw = document
												.createElement('tr');

										var input = document
												.createElement('input');
										input.setAttribute("type", "radio");
										input.setAttribute("name",
												"radioButton");
										input.setAttribute("value",
												custdetails[i].id);
										input.setAttribute("onchange",
												"editDetails(this.value)");
										var tbbdtd = document
												.createElement('td');
										tbbdtd.appendChild(input);
										tbbdrw.appendChild(tbbdtd);

										var tbbdtd1 = document
												.createElement('td');
										$(tbbdtd1).text(custdetails[i].name);
										tbbdrw.appendChild(tbbdtd1);

										var tbbdtd2 = document
												.createElement('td');
										$(tbbdtd2).text(custdetails[i].email);
										tbbdrw.appendChild(tbbdtd2);

										var tbbdtd3 = document
												.createElement('td');
										$(tbbdtd3).text(custdetails[i].mobile);
										tbbdrw.appendChild(tbbdtd3);

										var tbbdtd4 = document
												.createElement('td');
										if (custdetails[i].tin == null)
											$(tbbdtd4).text(" - ");
										else
											$(tbbdtd4).text(custdetails[i].tin);
										tbbdrw.appendChild(tbbdtd4);

										var tbbdtd5 = document
												.createElement('td');
										$(tbbdtd5)
												.text(
														custdetails[i].address.house_no
																+ ", "
																+ custdetails[i].address.line1
																+ ", "
																+ custdetails[i].address.line2
																+ ", "
																+ custdetails[i].address.city
																+ ", "
																+ custdetails[i].address.state
																+ ", "
																+ custdetails[i].address.zip);
										tbbdrw.appendChild(tbbdtd5);

										tbbody.appendChild(tbbdrw);
									}

									table.appendChild(tbbody);
									dtbl.appendChild(table);
									s.appendChild(dtbl);
								}
							});
				});
function editDetails(customerId) {
	// console.log(customerId);
	document.getElementById("forTin").setAttribute("style", "display:none;");

	for (var i = 0; i < customerDetails.length; i++) {
		if (customerDetails[i].id == customerId) {
			$('#inputName').val(customerDetails[i].name);
			$('#inputEmail').val(customerDetails[i].email);
			$('#inputMobile').val(customerDetails[i].mob);
			if (customerDetails[i].type == 'MERCHANT') {
				$('#forTin').removeAttr("style");
				$('#inputTin').val(customerDetails[i].tin);
			}
			$('#inputHouse').val(customerDetails[i].house);
			$('#inputAddress1').val(customerDetails[i].line1);
			$('#inputAddress2').val(customerDetails[i].line2);

			$('#inputState').val(customerDetails[i].state);

			var selectState = customerDetails[i].state;
			var selectCity = customerDetails[i].city;
			$("#loaderImage").show();
			$.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				method : "post",
				data : {
					action : "getCities",
					state : selectState
				},
				success : function(data) {
					$("#loaderImage").hide();
					$("#inputCity").html("");
					for (var i = 0; i < data.city.length; i++) {
						$("#inputCity").append(
								"<option value='" + data.city[i] + "'>"
										+ data.city[i] + "</option>");
					}

					$("#inputCity").val(selectCity);

				},
				error : function(data) {
					$("#loaderImage").hide();
					swal('Some Error : ' + data);
				}

			});

			$('#inputPin').val(customerDetails[i].pin);
			$('#customerId').val(customerId);
			$('#customerType').val(customerDetails[i].type);
			break;
		}
	}
}

$('#editCustDetails').click(
		function() {
			if (document.getElementById("inputName").value == "") {
				swal("Select Customer !",
						"Select a customer first to edit details", "warning");
				return;
			}

			var add = $('#inputAddress2').val();
			var adds = add.split(",   ");
			add = add.replace(",   " + adds[adds.length - 1], "");
			add = add.replace(",   " + adds[adds.length - 2], "");

			console.log("add1 : " + add);
			$('#inputAddress2').val(add);

			$('#inputName').removeAttr("readonly");
			$('#inputEmail').removeAttr("readonly");
			$('#inputMobile').removeAttr("readonly");
			$('#inputTin').removeAttr("readonly");
			$('#inputHouse').removeAttr("readonly");
			$('#inputCity').removeAttr("disabled");
			$('#inputState').removeAttr("disabled");
			$('#inputAddress1').removeAttr("readonly");
			$('#inputAddress2').removeAttr("readonly");
			$('#inputPin').removeAttr("readonly");

			var button = $('#editCustDetails');
			button.removeAttr("id");
			button.attr("onclick", "saveDetails()");
			button.html("Save Details");
		});

function saveDetails() {
	var name = $('#inputName').val();
	var email = $('#inputEmail').val();
	var house = $('#inputHouse').val();
	var line1 = $('#inputAddress1').val();
	var city = $('#inputCity').val();
	var state = $('#inputState').val();
	var mobile = $('#inputMobile').val();
	var pin = $('#inputPin').val();

	var mob = /^[7-9]{1}[0-9]{9}$/;
	console.log("City : " + city + ", State : " + state);

	var success = true;
	var div = "#nameDiv";
	if (name == "") {
		showErrorValidation(div, "Name can't be blank");
		success = false;
	} else
		clearError(div);
	var div = "#emailDiv";

	var div = "#houseDiv";
	if (house == "") {
		showErrorValidation(div, "House/Shop No. can't be blank !");
		success = false;
	} else
		clearError(div);
	var div = "#addDiv1";
	if (line1 == "") {
		showErrorValidation(div, "Address can't be blank !");
		success = false;
	} else
		clearError(div);
	var div = "#cityDiv";
	if (city == "") {
		showErrorValidation(div, "Select a city please !");
		success = false;
	} else
		clearError(div);

	var div = "#stateDiv";
	if (state == "") {
		showErrorValidation(div, "Select a state please !");
		success = false;
	} else
		clearError(div);

	var div = "#pinDiv";
	if (pin == "") {
		showErrorValidation(div, "Pin Code can't be blank !");
		success = false;
	} else
		clearError(div);

	var div = "#mobileDiv";
	if (mobile == "") {
		showErrorValidation(div, "Enter a mobile number please !");
		success = false;
	} else if (mobile.length < 10 || !mob.test(mobile)) {
		showErrorValidation(div, "Mobile number invalid !");
		success = false;
	} else
		clearError(div);

	if (!success) {
		console.log("Error in validation of form");
		return;
	}

	document.getElementById("manageCusForm").submit();

}

$('#deleteCustDetails')
		.click(
				function() {
					if (document.getElementById("inputName").value == "") {
						swal("Select Customer !",
								"Select a customer first to delete details",
								"warning");
						return;
					}

					swal(
							{
								title : "Sure to delete customer!",
								text : "Are you sure you want to delete the customer and all its relevant data?",
								type : "warning",
								showCancelButton : true,
								confirmButtonColor : "#DD6B55",
								confirmButtonText : "Yes, delete it!",
								closeOnConfirm : true
							}, function() {
								$('#buttonType').val("deleteButton");
								document.getElementById("manageCusForm")
										.submit();
							});
				});

$('#selectCustomerModalBtn').click(function() {
	$('#modalCustomer').modal('hide');

	var custId = $('#custID').val();
	console.log(custId);

	$("#loaderImage").show();
	$.ajax({
		url : $("#basePath").val() + "/ajaxServlet.do",
		data : {
			customerId : custId,
			action : "getCustomerDetailsById"
		},
		error : function(data) {
			$("#loaderImage").hide();
			alert("Error : " + data);
		},
		success : function(data) {
			console.log("Success : " + data.customers[0].name);
			$("#loaderImage").hide();

			$('#inputName').val(data.customers[0].name);
			$('#inputEmail').val(data.customers[0].email);
			$('#inputMobile').val(data.customers[0].mobile);
			$('#inputHouse').val(data.customers[0].address.house_no);
			$('#inputAddress1').val(data.customers[0].address.line1);
			$('#inputAddress2').val(data.customers[0].address.line2);
			$('#city').val(data.customers[0].address.city);
			$('#state').val(data.customers[0].address.state);
			$('#inputPin').val(data.customers[0].address.zip);
			$('#customerId').val(custId);
		}
	});
});

$('#searchOrderButton1')
		.click(
				function() {
					var s = document.getElementById("fillProducts");

					var orderId = $("#orderId").val();

					if ((orderId == "" || orderId == null)) {
						swal("No data found !",
								"Please enter order Id and then search order.",
								"warning");
						return;
					}

					s.innerHTML = "";

					$("#loaderImage").show();
					$
							.ajax({
								url : $("#basePath").val() + "/ajaxServlet.do",

								data : {
									orderId : orderId,
									action : "getProductsByOrderId"
								},
								error : function(data) {
									$("#loaderImage").hide();
									alert("Error : " + data);
								},
								success : function(data) {
									$("#loaderImage").hide();

									$('#inputName')
											.val(
													data.orderdetails[0].customer.customerName);
									$('#inputEmail')
											.val(
													data.orderdetails[0].customer.email);
									$('#inputMobile')
											.val(
													data.orderdetails[0].customer.mobile);
									$('#inputHouse')
											.val(
													data.orderdetails[0].address.house_no);
									$('#inputAddress1').val(
											data.orderdetails[0].address.line1);
									$('#inputAddress2').val(
											data.orderdetails[0].address.line2);
									$('#city').val(
											data.orderdetails[0].address.city);
									$('#state').val(
											data.orderdetails[0].address.state);
									$('#inputPin').val(
											data.orderdetails[0].address.zip);
									$('#customerId')
											.val(
													data.orderdetails[0].customer.customerId);

									var heading = document.createElement('div');
									heading
											.setAttribute("class",
													"card-header");

									var hd1 = document.createElement('h2');
									$(hd1).text("Ordered Products");
									var hd2 = document.createElement('small');
									$(hd2)
											.text(
													"Here are products which are not yet shipped.");

									heading.appendChild(hd1);
									heading.appendChild(hd2);
									s.appendChild(heading);

									var dtbl = document.createElement('div');
									dtbl.setAttribute("class",
											"table-responsive");

									var table = document.createElement('table');
									table.setAttribute("class",
											"table table-condensed");

									var tbhead = document
											.createElement('thead');
									var tbhdrow = document.createElement('tr');

									var tbhdtd1 = document.createElement('td');
									$(tbhdtd1).text("Order Id");
									tbhdrow.appendChild(tbhdtd1);

									var tbhdtd2 = document.createElement('td');
									$(tbhdtd2).text("Product Category");
									tbhdrow.appendChild(tbhdtd2);

									var tbhdtd3 = document.createElement('td');
									$(tbhdtd3).text("Product Name");
									tbhdrow.appendChild(tbhdtd3);

									var tbhdtd4 = document.createElement('td');
									$(tbhdtd4).text("Quantity");
									tbhdrow.appendChild(tbhdtd4);

									var tbhdtd5 = document.createElement('td');
									$(tbhdtd5).text("Amount");
									tbhdrow.appendChild(tbhdtd5);

									var tbhdtd6 = document.createElement('td');
									$(tbhdtd6).text("Shipping Status");
									tbhdrow.appendChild(tbhdtd6);

									var tbhdtd7 = document.createElement('td');
									$(tbhdtd7).text("Delete Product");
									tbhdrow.appendChild(tbhdtd7);

									tbhead.appendChild(tbhdrow);
									table.appendChild(tbhead); // Header
									// inserted

									var orderdetails = data.orderdetails;

									var tbbody = document
											.createElement('tbody');

									for (var i = 0; i < orderdetails.length; i++) {

										var tbbdrw = document
												.createElement('tr');

										var tbbdtd1 = document
												.createElement('td');
										$(tbbdtd1)
												.text(
														orderdetails[i].orderitem.orderId);
										tbbdrw.appendChild(tbbdtd1);

										var tbbdtd2 = document
												.createElement('td');
										$(tbbdtd2)
												.text(
														orderdetails[i].product.categoryName);
										tbbdrw.appendChild(tbbdtd2);

										var tbbdtd3 = document
												.createElement('td');
										$(tbbdtd3)
												.text(
														orderdetails[i].product.productName);
										tbbdrw.appendChild(tbbdtd3);

										var tbbdtd4 = document
												.createElement('td');
										$(tbbdtd4)
												.text(
														orderdetails[i].orderitem.quantity);
										tbbdrw.appendChild(tbbdtd4);

										var tbbdtd5 = document
												.createElement('td');
										$(tbbdtd5)
												.text(
														orderdetails[i].orderitem.amount);
										tbbdrw.appendChild(tbbdtd5);
										
										if (orderdetails[i].orderitem.shipId == null || orderdetails[i].orderitem.shipId == 0) {
											var tbbdtd6 = document
													.createElement('td');
											$(tbbdtd6).text("Not Shipped");
											tbbdrw.appendChild(tbbdtd6);
											$('#shipId').val("0");

											var tbbdtd7 = document
													.createElement('td');
											var vdiv = document
													.createElement('div');
											vdiv.setAttribute("class",
													"row dialog col-sm-2");
											var vdetail = document
													.createElement('button');
											vdetail
													.setAttribute(
															"id",
															"deleteButton"
																	+ orderdetails[i].product.productId);
											vdetail.setAttribute("class",
													"btn btn-info");
											vdetail.setAttribute("type",
													"button");
											vdetail
													.setAttribute(
															"value",
															orderdetails[i].product.productId);
											vdetail
													.setAttribute(
															"onclick",
															"deleteOrderedProduct(this.value,"
																	+ orderdetails[i].orderitem.quantity
																	+ ","
																	+ orderdetails[i].orderitem.amount
																	+ ")");
											$(vdetail).text("Delete Product");
											vdiv.appendChild(vdetail);
											tbbdtd7.appendChild(vdiv);
											tbbdrw.appendChild(tbbdtd7);
										} else {
											var tbbdtd6 = document
													.createElement('td');
											$(tbbdtd6).text("Shipped");
											tbbdrw.appendChild(tbbdtd6);
											$('#shipId').val(orderdetails[i].orderitem.shipId);
										}

										tbbody.appendChild(tbbdrw);
									}

									table.appendChild(tbbody);
									dtbl.appendChild(table);
									s.appendChild(dtbl);
								}
							});
				});


function deleteOrderedProduct(productId, quantity, amount) {
	var orderId = $('#orderId').val();

	swal({
		title : "Delete Product ?",
		text : "Are you sure you want to delete this product.",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "Yes, delete it!",
		closeOnConfirm : true
	}, function() {

		$("#loaderImage").show();
		$.ajax({
			url : $("#basePath").val() + "/ajaxServlet.do",
			data : {
				action : "deleteOrderedProductById",
				productId : productId,
				orderId : orderId,
				prodAmount : amount,
				prodQuantity : quantity
			},
			error : function(data) {
				$("#loaderImage").hide();
				alert("Error : " + data);
			},
			success : function(data) {
				$("#loaderImage").hide();

				swal("Product Deleted !", "Product deleted successfully",
						"success");

				$('#deleteButton' + productId).text("Product Deleted");
				document.getElementById("deleteButton" + productId)
						.setAttribute("disabled", "");

			}
		});
	});
}

$('#editOrder').click(function() {
	var order = $('#orderId').val();
	var name = $('#inputName').val();
	var num = $("#numProd").val();
	num = parseInt(num);

	var success = true;
	var div = "#nameDiv";
	if (name == "") {
		showErrorValidation(div, "Customer Details can't be blank");
		success = false;
	} else
		clearError(div);
	var div = "#orderDiv";
	if (order == "") {
		showErrorValidation(div, "Please enter a order Id");
		success = false;
	} else
		clearError(div);

	if (!success) {
		console.log("Error in validation of form");
		return;
	}

	document.getElementById("editOrderForm").submit();

});

$('#delOrder')
		.click(
				function() {
					var name = $('#inputName').val();
					var shipId = $('#shipId').val();
					console.log(shipId);
					if (name == "") {
						swal(
								"Search order first !",
								"Either this order id does not exists OR The products corresponding to this id is already shipped OR You had not searched for order.",
								"warning");
						return;
					}
					if (shipId != "0") {
						swal(
								"Order already shipped !",
								"The product/products corresponding to this particular Order Id is already shipped.",
								"warning");
						$('#shipId').val("0");
						return;
					}

					$('#deleteOrder').val('delete');
					document.getElementById("editOrderForm").submit();
				});
