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


function addPanel()
{
	$("#panelDiv").append($("#example").html());
}

$("#payOpen").click(function(){
	window.open("payment/payOrder.jsp", "_self", "", "");
});

$("#generateReceipt").click(function() {
	loadFirms();
});

$("#processPayment").click(function(){
	window
	.open(
			$("#basePath")
					.val()
					+ "/operator/payment/payOrder.jsp",
			"", "");
});

var isFromSideButton = false;
var paymentID = 0;
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


function showCompleteOrder(orderID)
{
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
				for(var i = 0; i < data.shipments.length;i++)
				{
					var shipment = data.shipments[i];
					
					var html = "<div class=\"panel panel-collapse\">";
					html += "<div class=\"panel-heading\" role=\"tab\" id=\"heading"+i+"\">";
					html += "<h4 class=\"panel-title\">";
					html += "<a data-toggle=\"collapse\" data-parent=\"#accordion\"";
					html += "href=\"#collapse"+i+"\" aria-expanded=\"false\"";
					html += "aria-controls=\"collapse"+i+"\"> Shipment ID #"+shipment.id+"";
					html += "&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;"+shipment.medium+"";
					html += "&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;"+shipment.mediumNumber+"&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;"+shipment.mediumName+"";
					html += "&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;"+shipment.contact+"&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;"+shipment.time+"";
					html += "</a></h4></div>";
					html += "<div id=\"collapse"+i+"\" class=\"collapse\" role=\"tabpanel\"";
					html += "aria-labelledby=\"heading"+i+"\">";
					html += "<div class=\"panel-body\">";
					html += "<div class=\"row\">";
					html += "<label class=\"control-label col-sm-4\"><strong>Product Name";
					html += "</strong></label> <label class=\"control-label col-sm-4\"><strong>Shipped Quantity";
					html += "</strong></label></div>";
					for(var j = 0; j < shipment.products.length; j++)
					{
						var prod = shipment.products[j];
						html += "<div class=\"row\"><label class=\"control-label col-sm-4\">"+prod.name+"</label> <label class=\"control-label col-sm-4\">"+prod.quantity+"</label></div>";
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
			alert("Error : "+err);
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

										if (isFromSideButton == false) {

											if ($("#generateReceipt").attr(
													"type") != null
													&& $("#generateReceipt")
															.attr("type") != "undefined") {
												window
														.open(
																$("#basePath")
																		.val()
																		+ "/PrintOrder.do?print=receipt",
																"", "");
											} else {
												window
														.open(
																$("#basePath")
																		.val()
																		+ "/PrintOrder.do?print=challan",
																"", "");
											}
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
			for (var i = 0; i < data.firms.length; i++) {
				var firm = data.firms[i];
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
			}
			$('#modalFirm').modal('show');
		},

		error : function(data) {
			$("#loaderImage").hide();
			console.log("Error" + data);
			alert("Error");
		}
	});
}

$("#sendReceiptDetails").click(
		
	
		function(){
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
					swal(
							"SMS/Email Sent Successfully !",
							"Payment Receipt has been sent to the mobile and the email ids.",
							"success");
				}
			});
		}
);



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
									
									swal(
											"SMS/Email Sent Successfully !",
											"Shipment details has been sent to the mobile and the email ids.",
											"success");
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

					if ((ipnm == "" || ipnm == null)
							&& (ipmob == "" || ipmob == null)
							&& (ipoid == "" || ipoid == null)) {
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
										if($("#page").val() == "viewOrder")
										{
											input
											.setAttribute("onchange",
													"showCompleteOrder(this.value)");
										}
										else
										{
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

					if ((ipnm == "" || ipnm == null)
							&& (ipmob == "" || ipmob == null)
							&& (ipoid == "" || ipoid == null)) {
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