/**
 * New node file
 */

function showPurchase(purchaseId) {
	$("#loaderImage").show();
	$.ajax({
		url : $("#basePath").val() + "/ajaxServlet.do",
		data : {
			action : "getPurchaseById",
			pid : purchaseId
		},
		success : function(data) {
			$("#loaderImage").hide();
			$("#displayName").val(data.merchant);
			$("#displayMobile").val(data.mobile);
			$("#displayEmail").val(data.email);
			$("#displayTin").val(data.tin);
			$("#productDetails").html("");

			for (var i = 0; i < data.products.length; i++) {
				var item = data.products[i];
				// alert("Product id is : "+item.product_id);
				var app = "<div class=\"form-group\">";
				app += "<div class=\"col-sm-2 col-sm-offset-1\">";
				app += "<label class=\"control-label\"><strong>" + item.name
						+ "";

				app += "</div>";
				app += "<div class=\"col-sm-2 col-sm-offset-1\">";
				app += "<label class=\"control-label\"><strong>"
						+ item.rate + "";
				app += "</strong></label>";
				app += "</div>";
				app += "<div class=\"col-sm-2 col-sm-offset-1\">";
				app += "<label class=\"control-label\"><strong>" + item.quantity
						+ "</strong></label></div>";
				app += "<div class=\"col-sm-2 col-sm-offset-1\">";
				app += "<label class=\"control-label\"><strong>" + item.amount;
				app += "</strong></label>";
				app += "</div>";
				app += "</div>";
				$("#productDetails").append(app);
			}

			$("#paymentDetails").html("");
			$("#total").text(data.amount);
			var paid = 0;
			for (var i = 0; i < data.payments.length; i++) {
				var payment = data.payments[i];
				paid += payment.amount;
				var html = "<tr>";
				html += "<td>" + payment.id + "</td>";
				html += "<td>" + payment.date + "</td>";
				html += "<td>" + payment.amount + "</td>";
				html += "<td>" + payment.mode + "</td>";
				html += "<td>" + payment.paidBy + "</td>";

				html += "<td>" + payment.description + "</td>";

				html += "</tr>";
				$("#paymentDetails").append(html);
			}
			$("#paid").text(paid);
			$("#due").text(data.amount - paid);

		},
		error : function(data) {
			$("#loaderImage").hide();
			alert("");
		}
	});
}

$("#searchPurchaseButton").click(function() {
	var purchaseID = $("#inputPurchaseID").val();
	var fromDate = $("#inputFromDate").val();
	var toDate = $("#inputToDate").val();
	var merchantName = $("#inputMerchantName").val();

	// alert(purchaseID+":"+fromDate+"-"+toDate+":"+merchantName);
	$("#loaderImage").show();
	$.ajax({
		url : $("#basePath").val() + "/ajaxServlet.do",
		data : {
			action : "getPurchaseDetail",
			pid : purchaseID,
			to : toDate,
			from : fromDate,
			name : merchantName
		},

		success : function(data) {
			$("#loaderImage").hide();
			var s = document.getElementById("fillPurchaseDetails");
			s.innerHTML = "";
			var heading = document.createElement('div');
			heading.setAttribute("class", "card-header");

			var hd1 = document.createElement('h2');
			$(hd1).text("Purchase Details");
			var hd2 = document.createElement('small');
			$(hd2).text("Please select the purchase to edit");

			heading.appendChild(hd1);
			heading.appendChild(hd2);
			s.appendChild(heading);

			var dtbl = document.createElement('div');
			dtbl.setAttribute("class", "table-responsive");

			var table = document.createElement('table');
			table.setAttribute("class", "table table-condensed");

			var tbhead = document.createElement('thead');
			var tbhdrow = document.createElement('tr');

			var tbhdtd1 = document.createElement('td');
			$(tbhdtd1).text("Select");
			tbhdrow.appendChild(tbhdtd1);

			var tbhdtd2 = document.createElement('td');
			$(tbhdtd2).text("Purchase ID");
			tbhdrow.appendChild(tbhdtd2);

			var tbhdtd3 = document.createElement('td');
			$(tbhdtd3).text("Merchant Name");
			tbhdrow.appendChild(tbhdtd3);

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

			orderdetails = data.purchases;

			var tbbody = document.createElement('tbody');

			for (var i = 0; i < data.purchases.length; i++) {
				console.log("ID : "+i);
				var tbbdrw = document.createElement('tr');

				var input = document.createElement('input');
				input.setAttribute("type", "radio");
				input.setAttribute("name", "purchaseID");
				input.setAttribute("onchange", "showPurchase(this.value)");
				input.setAttribute("value", orderdetails[i].id);
				var tbbdtd = document.createElement('td');
				tbbdtd.appendChild(input);
				tbbdrw.appendChild(tbbdtd);

				var tbbdtd1 = document.createElement('td');
				$(tbbdtd1).text(orderdetails[i].id);
				tbbdrw.appendChild(tbbdtd1);

				var tbbdtd2 = document.createElement('td');
				$(tbbdtd2).text(orderdetails[i].merchant);
				tbbdrw.appendChild(tbbdtd2);

				var tbbdtd3 = document.createElement('td');
				$(tbbdtd3).text(orderdetails[i].mobile);
				tbbdrw.appendChild(tbbdtd3);

				var tbbdtd4 = document.createElement('td');
				$(tbbdtd4).text(orderdetails[i].amount);
				tbbdrw.appendChild(tbbdtd4);

				var tbbdtd6 = document.createElement('td');
				$(tbbdtd6).text(orderdetails[i].date);
				tbbdrw.appendChild(tbbdtd6);

				var tbbdtd8 = document.createElement('td');
				var vdiv = document.createElement('div');
				vdiv.setAttribute("class", "row dialog col-sm-2");
				/*
				 * var vdetail = document .createElement('button');
				 * vdetail.setAttribute("id", "sa-basic");
				 * vdetail.setAttribute("class", "btn btn-info");
				 * vdetail.setAttribute("type", "button");
				 * vdetail.setAttribute("value", orderdetails[i].oid); vdetail
				 * .setAttribute( "onclick", "window.open('" + $("#basePath")
				 * .val() + "/PrintOrder.do?print=view&oid=" +
				 * orderdetails[i].oid +
				 * "',1,'width=300,height=300','resizable=yes','status=yes')");
				 * $(vdetail).text("Click here"); vdiv.appendChild(vdetail);
				 */
				tbbdtd8.appendChild(vdiv);
				tbbdrw.appendChild(tbbdtd8);
				tbbody.appendChild(tbbdrw);
			}

			table.appendChild(tbbody);
			dtbl.appendChild(table);
			s.appendChild(dtbl);

		},

		error : function(data) {
			$("#loaderImage").hide();
		}

	});

});