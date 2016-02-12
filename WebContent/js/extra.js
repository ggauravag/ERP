/**
 * New node file
 */

var orderdetails;



$("#searchOrderButton").click(function() {
	var orderID = $("#inputOrderID").val();
});

$("#sendShipment").click(function() {
	$('#modalMobile').modal('show');
});

$("#sendReceipt").click(function() {
	$('#modalMobile').modal('show');
});

$("#generateChallan").click(function() {
	loadFirms();
});

$("#addCityButton").click(function(){
	$("#modalAddCity").modal('show');
});

$("#addNewCity").click(function(){
	var newCity = $("#newCityName").val();
	var stateName = $("#stateToModify").val();
	
	$("#loaderImage").show();
	$.ajax({
		
		url : $("#basePath").val() + "/ajaxServlet.do",
		method : "post",
		data : {
			state : stateName,
			city : newCity,
			action : "addCityInState"
		},
		success : function(data){
			$("#loaderImage").hide();
			if(data.status)
			{
				if($('#inputState').val() === stateName)
					$('#inputCity').append('<option>'+newCity+'</option>');
				$("#modalAddCity").modal('hide');
				swal("City Added","New City "+newCity+" has been added to state : "+stateName,"success");
			}
			else
			{
				$("#errorInCityAdd").html(data.message);
			}
		},
		error : function(data){
			$("#loaderImage").hide();
			alert("Something went wrong");
		}
		
	});
});

$("#stateToModify").change(function(){
	var city = $("#newCityName").val();
	if(city.length != 0)
		$("#addNewCity").removeAttr('disabled');
	else
		$("#addNewCity").attr('disabled',true);
});

$("#newCityName").keyup(function(event){
	//console.log(this.value);
	var words = this.value.split(' ');
	for(var i = 0; i < words.length; i++)
	{
		words[i] = words[i].charAt(0).toUpperCase() + words[i].substr(1,words[i].length - 1).toLowerCase();
	}
	this.value = words.join(' ');
	var state = $("#stateToModify").val();
	//console.log("State : "+state);
	if(state === "Select State")
	{
		$("#addNewCity").attr('disabled',true);
	}
	else
	{
		$("#addNewCity").removeAttr('disabled');
	}
	
});

function addPanel() {
	$("#panelDiv").append($("#example").html());
}

$("#generateInvoice").click(function() {
	var order = $("input[name=orderID]:checked").val();
	if (order == null || order == "") {
		swal("Please select an order !");
		return;
	}

	$("#printType").val("invoice");
	loadFirms();
});

$("#generateOrderConfirm").click(function() {
	var order = $("input[name=orderID]:checked").val();
	if (order == null || order == "") {
		swal("Please select an order !");
		return;
	}
	$("#printType").val("orderPrint");
	loadFirms();
});

$("input[name=vatApplicable]").change(function() {
	if (this.value == "true") {
		swal("Please enter the VAT Percentage !");
		$("#vatDiv").show();
		$("#inputVatPercent").prop('disabled', false);
	} else {
		$("#vatDiv").hide();
		$("#inputVatPercent").prop('disabled', true);
		swal("No VAT Applicable now !");
	}
});

$("#payOpen").click(function() {
	window.open("payment/payOrder.jsp", "_self", "", "");
});

$("#generateReceipt").click(function() {
	loadFirms();
});

$("#processPayment").click(
		function() {
			window.open(
					$("#basePath").val() + "/operator/payment/payOrder.jsp",
					"", "");
		});

var isFromSideButton = false;
var shipChallanID = 0;
var orderChallanID = 0;
var isChallan = false;
var paymentID = 0;
$("#empForm").submit(
		function() {
			var fname = $("#inputFname").val();
			var lname = $("#inputSname").val();
			var email = $("#inputEmail").val();
			var mobile = $("#inputMobile").val();
			var house_no = $("#inputHouse").val();
			var ln1 = $("#inputAddress1").val();
			var ln2 = $("#inputAddress2").val();
			var cty = $("#inputCity").val();
			var stat = $("#inputState").val();
			var pin = $("#inputPin").val();
			var role = $("#inputRole").val();
			var sal = $("#inputSalary").val();
			var doj = $("#inputDoj").val();

			var success = true;
			var div = "#nameDiv";
			if (fname == "" || lname == "") {
				showErrorValidation(div, "Name can't be blank !");
				success = false;
			} else
				clearError(div);
			div = "#emailDiv";
			if ((role == "OPERATOR" || role == "DBA")
					&& (email == null || email == "")) {
				showErrorValidation(div, "Email can't be blank !");
				success = false;
			} else
				clearError(div);
			div = "#mobileDiv";
			if (mobile == null || mobile == "") {
				showErrorValidation(div, "Mobile can't be blank !");
				success = false;
			} else
				clearError(div);
			div = "#houseDiv";
			if (house_no == null || house_no == "") {
				showErrorValidation(div, "House No. can't be blank !");
				success = false;
			} else
				clearError(div);
			div = "#line1Div";
			if (ln1 == null || ln1 == "") {
				showErrorValidation(div, "Address Line1 can't be blank !");
				success = false;
			} else
				clearError(div);
			div = "#line2Div";
			if (ln2 == null || ln2 == "") {
				showErrorValidation(div, "Address Line2 can't be blank !");
				success = false;
			} else
				clearError(div);
			div = "#cityDiv";
			if (cty == null || cty == "") {
				showErrorValidation(div, "Please select the City !");
				success = false;
			} else
				clearError(div);
			div = "#stateDiv";
			if (stat == null || stat == "") {
				showErrorValidation(div, "Please select the State !");
				success = false;
			} else
				clearError(div);
			div = "#pinDiv";
			if (pin == null || pin == "") {
				showErrorValidation(div, "Pin Number can't be blank !");
				success = false;
			} else
				clearError(div);
			div = "#roleDiv";
			if (role == null || role == "") {
				showErrorValidation(div, "Please select the Role !");
				success = false;
			} else
				clearError(div);
			div = "#salaryDiv";
			if (sal == null || sal == "") {
				showErrorValidation(div, "Salary can't be blank !");
				success = false;
			} else
				clearError(div);
			div = "#dojDiv";
			if (doj == null || doj == "") {
				showErrorValidation(div, "Date of Joining can't be blank !");
				success = false;
			} else
				clearError(div);
			return success;
		});
$("#payForm").submit(function() {

	var amount = $('#inputAmount').val();
	var orderID = $('input[name=orderID]:checked').val();
	var medium = $('#selectMedium').val();
	var paidBy = $("#inputPaidBy").val();
	var validity = /^[0-9]\d*$/;

	console.log(amount + ":" + orderID + ":" + medium + ":" + paidBy);

	var success = true;
	var div = "#amountDiv";
	if (amount == "") {
		showErrorValidation(div, "Amount can't be blank !");
		success = false;
	} else
		clearError(div);
	if (!validity.test(amount)) {
		showErrorValidation(div, "Amount can take integer values only !");
		success = false;
	} else
		clearError(div);

	// console.log("Status is : "+success);

	var div = "#paidByDiv";
	if (paidBy == "") {
		showErrorValidation(div, "Someone has to pay, enter name !");
		success = false;
	} else
		clearError(div);

	// console.log("Status is : "+success);

	var div = "#mediumDiv";
	if (medium == "Select Payment Mode") {
		showErrorValidation(div, "Please select a payment mode !");
		success = false;
	} else
		clearError(div);

	// console.log("Status is : "+success);

	var div = "#orderDiv";
	if (orderID == "undefined" || orderID == "" || orderID == null) {
		showErrorValidation(div, "Please select an order first !");
		success = false;
	} else
		clearError(div);

	// console.log("Status is : "+success);

	return success;
});

function clearError(div) {
	$(div).attr("class", $(div).attr("class").replace(" has-error", ""));
	div = div + " small#error > font";
	$(div).text("");
}

function showErrorValidation(div, message) {
	console.log(div + " - " + message);
	if ($(div).attr("class").indexOf("has-error") < 0) {
		$(div).attr("class", $(div).attr("class") + " has-error");
		div = div + " small#error > font";
		$(div).text(message);
	}
}

function printChallanBySide(shipID, orderID) {
	isFromSideButton = true;
	isChallan = true;
	shipChallanID = shipID;
	orderChallanID = orderID;
	loadFirms();
}

var isInitialized = false;

var orderTotal = 0;
var discount = 0;
var shipCharge = 0;

function getContent()
{
	var html = "<table><tr><td><b>Order Amount :&nbsp;</b></td><td>(+)</td><td>&nbsp;<span class=\"WebRupee\">&#x20B9;</span> "+orderTotal+"</td></tr><tr><td><b>Discount :&nbsp;</b></td><td>(-)</td><td>&nbsp;<span class=\"WebRupee\">&#x20B9;</span> "+discount+"</td></tr><tr><td><b>Shipping Charge :&nbsp;</b></td><td>(+)</td><td>&nbsp;<span class=\"WebRupee\">&#x20B9;</span> "+shipCharge+"</td></tr></table>";
	return html;
}


function showCompleteOrder(orderID) {
	
	selectedOrder = null;
	var value = $("input[name=orderID]:checked").val();
	for (var i = 0; i < orderdetails.length; i++) {
		if (orderdetails[i].oid == value) {
			selectedOrder = orderdetails[i];
			$("#orderID").val(orderdetails[i].oid);
			break;
		}
	}

	$("#selectName").val(selectedOrder.Customer.name);
	$("#itemJSON").val(JSON.stringify(selectedOrder.items));
	$("#selectEmail").val(selectedOrder.Customer.email);
	$("#selectMobile").val(selectedOrder.Customer.mobile);
	$("#productDetails").html("");
	for (var j = 0; j < selectedOrder.items.length; j++) {
		var item = selectedOrder.items[j];
		// alert("Product id is : "+item.product_id);
		var app = "<div class=\"form-group\">";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_name
				+ "";

		app += "</div>";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_amount
				+ "";
		app += "</strong></label>";
		app += "</div>";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_qty
				+ "</strong></label></div>";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_amount
				* item.product_qty + "";
		app += "</strong></label>";
		app += "</div>";
		app += "</div>";
		$("#productDetails").append(app);
	}

	
	$("#loaderImage").show();
	$
			.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				data : {
					action : "getPaymentDetails",
					orderID : value
				},
				success : function(data) {
					$("#loaderImage").hide();
					if (data.status == "success") {
						$("#paymentDetails").html("");
						var total = selectedOrder.amount + data.shippingCharge - data.discountAmount;
						//'<b>Order Amount : </b>'+ selectedOrder.amount +'</br><b>Discount (-): </b>'+ data.discountAmount +'<br/><b>Shipping Charge (+): </b>'+ data.shippingCharge +''
						
						orderTotal = selectedOrder.amount;
						shipCharge = data.shippingCharge;
						discount = data.discountAmount;
						
						if(!isInitialized)
						{
							$('[data-toggle="amount-popover"]').popover({
						        'placement': 'top',
						        'template':'<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div><div class="popover-content"></div></a></div></div>',
						        'html': 'true',
						        'content': function (){return getContent()},
						        'animation': true,
						        'title':'Total Amount Breakup',
						        'container': 'body'
							});
							isInitialized = true;
						}
						
						$("#total").text(total);
						var paid = 0;
						for (var i = 0; i < data.payments.length; i++) {
							var payment = data.payments[i];
							paid += payment.amount;
							var html = "<tr>";
							html += "<td>" + payment.id + "</td>";
							html += "<td>" + payment.datetime + "</td>";
							html += "<td>" + payment.amount + "</td>";
							html += "<td>" + payment.mode.split(";")[0]
									+ "</td>";
							html += "<td>" + payment.paidBy + "</td>";
							html += "<td>" + payment.orderID + "</td>";
							html += "<td>" + payment.type + "</td>";
							html += "<td><button type='button' class='btn bgm-indigo waves-effect waves-button waves-float' onclick='printReceipt("
									+ payment.id + ")'>Generate</button></td>";
							html += "</tr>";
							$("#paymentDetails").append(html);
						}
						$("#paid").text(paid);
						$("#discount").text(data.discountAmount);
						$("#shippingCharge").text(data.shippingCharge);
						$("#due").text(total - paid);
					} else {
						swal("Error in fetching Payment Details");
					}

				},
				error : function(err) {
					$("#loaderImage").hide();
				}
			});

	$("#loaderImage").show();
	$
			.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				data : {
					action : "getShipmentDetails",
					orderID : value
				},
				success : function(data) {
					$("#loaderImage").hide();
					$("#panelDiv").html("");
					if (data.status == "success") {
						for (var i = 0; i < data.shipments.length; i++) {
							var shipment = data.shipments[i];

							var html = "<div class=\"panel panel-collapse\">";
							html += "<div class=\"panel-heading\" role=\"tab\" id=\"heading"
									+ i + "\">";
							html += "<h4 class=\"panel-title\">";
							html += "<a data-toggle=\"collapse\" data-parent=\"#accordion\"";
							html += "href=\"#collapse" + i
									+ "\" aria-expanded=\"false\"";
							html += "aria-controls=\"collapse" + i
									+ "\"> Shipment ID #" + shipment.id + "";
							html += "&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;"
									+ shipment.medium + "";
							html += "&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;"
									+ shipment.mediumNumber
									+ "&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;"
									+ shipment.mediumName + "";
							html += "&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;"
									+ shipment.contact
									+ "&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;"
									+ shipment.time + "";
							html += "</a></h4></div>";
							html += "<div id=\"collapse" + i
									+ "\" class=\"collapse\" role=\"tabpanel\"";
							html += "aria-labelledby=\"heading" + i + "\">";
							html += "<div class=\"panel-body\">";
							html += "<div class=\"row\">";
							html += "<label class=\"control-label col-sm-3\"><strong>Product Name";
							html += "</strong></label> <label class=\"control-label col-sm-3\"><strong>Shipped Quantity";
							html += "</strong></label><button type='button' class='col-sm-offset-2 btn bgm-deeporange waves-effect waves-button waves-float' onclick='printChallanBySide("
									+ shipment.id
									+ ","
									+ value
									+ ");'>Generate Challan</button></div>";
							for (var j = 0; j < shipment.products.length; j++) {
								var prod = shipment.products[j];
								html += "<div class=\"row\"><label class=\"control-label col-sm-3\">"
										+ prod.name
										+ "</label> <label class=\"control-label text-left col-sm-3\">"
										+ prod.quantity + "</label></div>";
							}
							html += "</div></div></div>";

							$("#panelDiv").append(html);
						}

					} else {
						swal("Error in fetching Shipment Details");
					}

				},
				error : function(err) {
					$("#loaderImage").hide();
					alert("Error : " + err);
				}
			});

}

$('#selectFirmID')
		.click(
				function() {
					// alert("button clicked");
					var firmID = $("input[name='firmID']:checked").val();
					// alert('Value is : '+firmID);
					var selectFirm;
					for (var j = 0; j < firmSelect.length; j++) {
						if (firmSelect[j].id == firmID)
							selectFirm = firmSelect[j];
					}

					$("#loaderImage").show();

					$
							.ajax({
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
										$('#modalFirm').modal('hide');
										// alert("Is From Side Button :
										// "+isFromSideButton);
										if (isFromSideButton == false) {

											if ($("#printDocForm").attr(
													"method") != undefined) {
												$("#printDocForm").submit();
											} else if ($("#generateReceipt")
													.attr("type") != null
													&& $("#generateReceipt")
															.attr("type") != undefined) {

												window
														.open(
																$("#basePath")
																		.val()
																		+ "/PrintOrder.do?print=receipt",
																"", "");
											} else if($("#letterTextField").attr("type") != null && $("#letterTextField").attr("type") != undefined)
											{
												//alert("From Letter Head");
												$("#letterTextField").val($(".note-editable").html());
												console.log("HTML is : "+$(".note-editable").html());
												$("#letterForm").submit();
											}
											else {

												window
														.open(
																$("#basePath")
																		.val()
																		+ "/PrintOrder.do?print=challan",
																"", "");
											}
										} else {

											if (isChallan) {
												window
														.open(
																$("#basePath")
																		.val()
																		+ "/PrintOrder.do?print=challan&shipID="
																		+ shipChallanID
																		+ "&orderID="
																		+ orderChallanID,
																"_blank");
												isChallan = false;
											} else {
												window
														.open(
																$("#basePath")
																		.val()
																		+ "/PrintOrder.do?print=receipt&payID="
																		+ paymentID
																		+ "",
																"_blank");
											}
											isFromSideButton = false;
											// alert("Is From Side Button :
											// "+isFromSideButton);
										}

									} else if (data.Response == "Error") {
										$('#modalFirm').modal('hide');
										alert("Some error can't print Challan ! ");
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
			console.log("Success" + data);
			$("#loaderImage").hide();
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
				var f = "";
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
			$('#modalFirm').modal('show');
		},

		error : function(data) {
			$("#loaderImage").hide();
			console.log("Error" + data);
			alert("Error");
		}
	});
}

$("#sendReceiptDetails")
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
									action : "sendReceiptDetails"
								},
								method : "post",
								error : function(data) {
									$("#loaderImage").hide();
									alert("Error : " + data);
								},
								success : function(data) {
									// alert("Success : " +
									// data.customers[0].name);
									$("#loaderImage").hide();
									
									if(data.status == "success")
									{
									swal(
											"SMS/Email Sent Successfully !",
											"Payment Receipt has been sent to the mobile and the email ids.",
											"success");
									}
									else
									{
										swal(
												"SMS Sent, but Email delivery Failed !",
												"Payment Receipt has been sent to the mobile but the Email delivery failed as it requires to generate a receipt first.",
												"error");
									}
									
								}
							});
				});

$("#sendShipmentDetails")
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
									action : "sendShipmentDetails"
								},
								method : "post",
								error : function(data) {
									$("#loaderImage").hide();
									alert("Error : " + data);
								},
								success : function(data) {
									// alert("Success : " +
									// data.customers[0].name);
									$("#loaderImage").hide();
									
									if(data.status == "success")
									{
										swal(
												"SMS/Email Sent Successfully !",
												"Shipment details has been sent to the mobile and the email ids.",
												"success");
									}
									else
									{
										swal(
												"SMS Sent, but Email Delivery Failed !",
												"Shipment details has been sent to the mobile but the email need to generate a Challan first.",
												"error");
									}

									
								}
							});
				});

function checkStock(checkbox) {
	var prodID = $(checkbox).val();

	if (!checkbox.checked)
		return;

	// console.log("Product ID is : " + prodID);
	// alert("Order ID is : " + selectedOrder.oid);
	var qty = null;
	for (var i = 0; i < selectedOrder.items.length; i++) {
		var item = selectedOrder.items[i];
		if (item.product_id == prodID) {
			qty = item.product_qty;
			break;
		}

	}
	$("#loaderImage").show();
	$
			.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				data : {
					action : "checkStock",
					productID : prodID,
					prodQty : qty
				},
				method : "post",
				success : function(data) {
					$("#loaderImage").hide();
					if (data.status == "success") {
						if (data.quantity < qty) {
							swal(""
									+ data.name
									+ " is currently "
									+ data.quantity
									+ " in stock which is less than ordered quantity ( "
									+ qty + " )");
							$(checkbox).removeAttr("checked");
						} else {
							swal(data.name + " is currently " + data.quantity
									+ " in qty. It will be delivered !");
						}

					} else {
						swal("Unable to check available product inventory.");
					}

				},
				error : function(data) {
					$("#loaderImage").hide();
					swal("Unable to check available product inventory.");
				}
			});

}

function printReceipt(payID) {
	isFromSideButton = true;
	paymentID = payID;
	loadFirms();
}

function showProductsDisplay(value) {

	selectedOrder = null;
	for (var i = 0; i < orderdetails.length; i++) {
		if (orderdetails[i].oid == value) {
			selectedOrder = orderdetails[i];
			$("#orderID").val(orderdetails[i].oid);
			break;
		}
	}

	$("#selectName").val(selectedOrder.Customer.name);
	$("#itemJSON").val(JSON.stringify(selectedOrder.items));
	$("#selectEmail").val(selectedOrder.Customer.email);
	$("#selectMobile").val(selectedOrder.Customer.mobile);
	$("#productDetails").html("");
	for (var j = 0; j < selectedOrder.items.length; j++) {
		var item = selectedOrder.items[j];
		// alert("Product id is : "+item.product_id);
		var app = "<div class=\"form-group\">";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_name
				+ "";

		app += "</div>";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_amount
				+ "";
		app += "</strong></label>";
		app += "</div>";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_qty
				+ "</strong></label></div>";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_amount
				* item.product_qty + "";
		app += "</strong></label>";
		app += "</div>";
		app += "</div>";
		$("#productDetails").append(app);
	}

	$("#total").text(selectedOrder.amount);
	$("#loaderImage").show();
	$
			.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				data : {
					action : "getPaymentDetails",
					orderID : value
				},
				success : function(data) {
					$("#loaderImage").hide();
					if (data.status == "success") {
						$("#paymentDetails").html("");
						var paid = 0;
						for (var i = 0; i < data.payments.length; i++) {
							var payment = data.payments[i];
							paid += payment.amount;
							var html = "<tr>";
							html += "<td>" + payment.id + "</td>";
							html += "<td>" + payment.datetime + "</td>";
							html += "<td>" + payment.amount + "</td>";
							html += "<td>" + payment.mode.split(";")[0]
									+ "</td>";
							html += "<td>" + payment.paidBy + "</td>";
							html += "<td>" + payment.orderID + "</td>";
							html += "<td>" + payment.type + "</td>";
							html += "<td><button type='button' class='btn bgm-indigo waves-effect waves-button waves-float' onclick='printReceipt("
									+ payment.id + ")'>Generate</button></td>";
							html += "</tr>";
							$("#paymentDetails").append(html);
						}
						$("#paid").text(paid);
						$("#due").text(selectedOrder.amount - paid);
					} else {
						swal("Error in fetching Payment Details");
					}

				},
				error : function(err) {
					$("#loaderImage").hide();
				}
			});
}

function getPaymentDetails(orderID) {

}

function validateReturn(cbox, Rqty, qty) {
	var cid = $(cbox).attr("id");
	cid = cid.replace("returnProd", "");
	//alert("Function : "+cid+",,,"+$("#returnQty" + cid).val());
	try {
		var returnQty = Number($("#returnQty" + cid).val());
		//alert("Returned Qty : " + returnQty + ", available quantity :" +(qty - Rqty));
		if (returnQty == 0) {
			$("#returnDiv > small#error > font").text(
					"Return Quantity should be greater than zero.");
			return false;
		} else if (returnQty > (qty - Rqty)) {
			$("#returnDiv > small#error > font").text(
					"Return Quantity is greater than purchased Quantity");
			return false;
		} else {
			var rate = Number($("#prodRate" + cid).text());
			var amount = Number($("#returnAmount").val());
			if($(cbox).is(":checked"))
			{
				//alert("Unchecked");
				$("#returnQty" + cid).attr("readonly",true);
				amount += rate * returnQty;
			}
			else
			{
				//alert("checked");
				$("#returnQty" + cid).attr("readonly",false);
				amount -= rate * returnQty;
			}
			
			
			//alert(", DIV is : "+"#prodRate"+cid+",,,"+$("#prodRate" + cid).html());
			
			//alert("Rate : "+rate+", Amount : "+amount+", ReturnQty : "+returnQty);
			
			$("#returnAmount").val(amount);
			
			return true;
		}

	} catch (err) {
		alert(err.message);
		return false;
	}

}

function showCompleteReturn(value) {
	selectedOrder = null;
	for (var i = 0; i < orderdetails.length; i++) {
		if (orderdetails[i].oid == value) {
			selectedOrder = orderdetails[i];
			$("#orderID").val(orderdetails[i].oid);
			break;
		}
	}

	$("#selectName").val(selectedOrder.Customer.name);
	$("#itemJSON").val(JSON.stringify(selectedOrder.items));
	$("#selectEmail").val(selectedOrder.Customer.email);
	$("#selectMobile").val(selectedOrder.Customer.mobile);
	$("#productDetails").html("");
	for (var j = 0; j < selectedOrder.items.length; j++) {
		var item = selectedOrder.items[j];
		// alert("Product id is : "+item.product_id);
		var app = "<div class=\"form-group\">";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_name
				+ "";

		app += "</div>";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_amount
				+ "";
		app += "</strong></label>";
		app += "</div>";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_qty
				+ "</strong></label></div>";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_amount
				* item.product_qty + "";
		app += "</strong></label>";
		app += "</div>";
		app += "</div>";
		$("#productDetails").append(app);
	}

	$("#total").text(selectedOrder.amount);
	$("#loaderImage").show();
	$
			.ajax({
				url : $("#basePath").val() + "/ajaxServlet.do",
				data : {
					action : "getReturnDetails",
					orderID : value
				},
				success : function(data) {
					$("#loaderImage").hide();
					if (data.status == "success") {
						$("#returnDetails").html("");
						$("#returnAmount").val(0);
						var i = 0;
						var name = "";
						for (; i < data.returns.length; i++) {
							var returnObj = data.returns[i];

							var html = "<div class='form-group'>";
							if(name != returnObj.productName)
							{
								html += "<div class='col-sm-2'>"
									+ returnObj.productName + "<input type='hidden' name='prodID' value='"+returnObj.productID+"' /></div>";
							html += "<div class='col-sm-2' id='prodRate"+i+"'>" + returnObj.rate
									+ "</div>";
							html += "<div class='col-sm-2'>"
									+ returnObj.quantity + "</div>";	
							}
							else
							{
								html += "<div class='col-sm-2'></div><div class='col-sm-2'></div><div class='col-sm-2'></div>";
							}
							
							

							if (returnObj.isReturned) {
								html += "<div class='col-sm-2'>"
										+ returnObj.returnTime + " ####  "
										+ returnObj.returnQuantity + "</div>";
								
								if (name != returnObj.productName && returnObj.returnQuantity < returnObj.quantity) {
									html += "<div class='col-sm-2'><input type='text' placeholder='Enter Qty.' name='returnQty' id='returnQty"
											+ i
											+ "' class='form-control' /></div>";
									html += "<div class='col-sm-2'><input type='checkbox' value='true' id='returnProd"
											+ i
											+ "' name='returnProd' onclick='return validateReturn(this,"
											+ returnObj.returnQuantity
											+ ","
											+ returnObj.quantity
											+ ");' /></div>";
								} else {
									html += "<div class='col-sm-2'></div>";
									html += "<div class='col-sm-2'></div>";
								}
							} else {
								html += "<div class='col-sm-2'></div>";
								html += "<div class='col-sm-2'><input type='text' placeholder='Enter Qty.' id='returnQty"
										+ i
										+ "' name='returnQty' class='form-control' /></div>";
								html += "<div class='col-sm-2'><input type='checkbox' value='true' id='returnProd"
										+ i
										+ "' onclick='return validateReturn(this,0,"
										+ (returnObj.quantity)
										+ ");' name='returnProd'  /></div>";
							}
							html += "</div>";
							$("#returnDetails").append(html);
							
							name = returnObj.productName;
						}
						$("#numProd").val(i);
					} else {
						swal("No Returns to show ! Error in fetching details !");
					}

				},
				error : function(err) {
					$("#loaderImage").hide();
				}
			});

}

function showProducts(value) {
	// alert("product : "+value);
	selectedOrder = null;
	for (var i = 0; i < orderdetails.length; i++) {
		if (orderdetails[i].oid == value) {
			selectedOrder = orderdetails[i];
			$("#orderID").val(orderdetails[i].oid);
			break;
		}
	}

	$("#selectName").val(selectedOrder.Customer.name);
	$("#itemJSON").val(JSON.stringify(selectedOrder.items));
	$("#selectEmail").val(selectedOrder.Customer.email);
	$("#selectMobile").val(selectedOrder.Customer.mobile);
	$("#productDetails").html("");
	for (var j = 0; j < selectedOrder.items.length; j++) {
		var item = selectedOrder.items[j];
		// alert("Product id is : "+item.product_id);
		var app = "<div class=\"form-group\">";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		if (item.ship_id == 0) {
			app += "<input type='checkbox' onchange='return checkStock(this)' name='prodID' value='"
					+ item.product_id + "' />";
		} else {
			app += "<input type='checkbox' name='prodID' value='"
					+ item.product_id + "' disabled checked/>";
		}
		app += "</div>";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_name
				+ "";
		app += "</strong></label>";
		app += "</div>";
		app += "<div class=\"col-sm-2 col-sm-offset-1\">";
		app += "<label class=\"control-label\"><strong>" + item.product_qty
				+ "</strong></label>";
		app += "</div></div>";
		$("#productDetails").append(app);
	}
}

$("#searchOrderBtn")
		.click(
				function(e) {

					var s = document.getElementById("fillOrderDetails");

					var ipnm = $("#inputName").val();
					var ipmob = $("#inputMobile").val();
					var ipoid = $("#inputOrderID").val();
					var toDate = $("#inputToDate").val();
					var fromDate = $("#inputFromDate").val();
					
					
					if ((ipnm == "" || ipnm == null)
							&& (ipmob == "" || ipmob == null)
							&& (ipoid == "" || ipoid == null) && (toDate == "" && fromDate == "")) {
						alert("No data Found. Please Fill At Least One Category");
						return;
					}

					s.innerHTML = "";
					$("#loaderImage").show();
					$
							.ajax({

								url : $("#basePath").val() + "/ajaxServlet.do",

								data : {
									name : ipnm,
									order : ipoid,
									mobile : ipmob,
									to : toDate,
									from : fromDate,
									action : "getOrder"
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
									$(tbhdtd8).text("Status");
									tbhdrow.appendChild(tbhdtd8);

									var tbhdtd9 = document.createElement('td');
									$(tbhdtd9).text("View Details");
									tbhdrow.appendChild(tbhdtd9);

									tbhead.appendChild(tbhdrow);
									table.appendChild(tbhead); // Header
									// inserted

									orderdetails = data.orderdetails;

									var tbbody = document
											.createElement('tbody');

									for (var i = 0; i < data.orderdetails.length; i++) {

										var tbbdrw = document
												.createElement('tr');

										var input = document
												.createElement('input');
										input.setAttribute("type", "radio");
										input.setAttribute("name", "orderID");
										if ($("#page").val() == "viewOrder") {
											input
													.setAttribute("onchange",
															"showCompleteOrder(this.value)");
										} else if ($("#page").val() == "return") {
											input
													.setAttribute("onchange",
															"showCompleteReturn(this.value)");
										} else {
											input
													.setAttribute("onchange",
															"showProductsDisplay(this.value)");
										}

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
										if(orderdetails[i].status == "Shipped")
										{
											$(tbbdtd7).html("<b style='color:green'>"+orderdetails[i].status+"</b>");
										}
										else
											$(tbbdtd7).html("<b style='color:blue'>"+orderdetails[i].status+"</b>");
										
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
														"window.open('"
																+ $("#basePath")
																		.val()
																+ "/PrintOrder.do?print=view&oid="
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

$("#searchOrderButton")
		.click(
				function(e) {
					var s = document.getElementById("fillOrderDetails");

					var ipnm = $("#inputName").val();
					var ipmob = $("#inputMobile").val();
					var ipoid = $("#inputOrderID").val();
					var toDate = $("#inputToDate").val();
					var fromDate = $("#inputFromDate").val();
					
					
					if ((ipnm == "" || ipnm == null)
							&& (ipmob == "" || ipmob == null)
							&& (ipoid == "" || ipoid == null) && (toDate == "" && fromDate == "")) {
						alert("No data Found. Please Fill At Least One Category");
						return;
					}

					s.innerHTML = "";
					$("#loaderImage").show();
					$
							.ajax({

								url : $("#basePath").val() + "/ajaxServlet.do",

								data : {
									name : ipnm,
									order : ipoid,
									mobile : ipmob,
									to : toDate,
									from : fromDate,
									action : "getOrder"
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

									var tbhdtd9 = document.createElement('td');
									$(tbhdtd9).text("View Details");
									tbhdrow.appendChild(tbhdtd9);

									tbhead.appendChild(tbhdrow);
									table.appendChild(tbhead); // Header
									// inserted

									orderdetails = data.orderdetails;

									var tbbody = document
											.createElement('tbody');

									for (var i = 0; i < data.orderdetails.length; i++) {

										var tbbdrw = document
												.createElement('tr');

										var input = document
												.createElement('input');
										input.setAttribute("type", "radio");
										input.setAttribute("name", "orderID");
										input.setAttribute("onchange",
												"showProducts(this.value)");
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
														"window.open('"
																+ $("#basePath")
																		.val()
																+ "/PrintOrder.do?print=view&oid="
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