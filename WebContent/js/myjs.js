/**
 * 
 */
var products = new Array();
var availProduct = new Array();
var customers;

var firmSelect = new Array();

function Product() {
	this.name = "";
	this.price = 0;
	this.quantity = 0;
	this.total = 0;
	this.available = 0;
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


$('#selectFirmDetails').click(
   function(){
	   //alert("button clicked");
	   var firmID = $("input[name='firmID']:checked").val();
	   //alert('Value is : '+firmID);
	   var selectFirm;
	   for(var j = 0; j < firmSelect.length; j++)
		{
		   	if(firmSelect[j].id == firmID)
		   		selectFirm = firmSelect[j];
		}
	   
	   $.ajax(
		{
			url : "../ajaxServlet.do",
			data : {
				firm : firmID,
				action : "setFirm",
				firmString : JSON.stringify(selectFirm)
			},
			method : "post",
			
			success : function(data){
				//console.log("Result :"+data);
				if(data.Response == "Success")
				{
					$('#modalPrint').modal('hide');
					window.open("../PrintOrder.do?print=order","","");
				}
				else if(data.Response == "Error")
				{
					$('#modalPrint').modal('hide');
					alert("Some error can't print Order Confirmation ! ");
				}	
			},
			
			error : function(data){
			    alert("Some error ! "+data);	
			}
		}	   
	   );
   }		
);

function loadFirms()
{
	console.log("Load Firms called");
	$.ajax(
			{
				url : "../ajaxServlet.do",
				data : {
					mobile : "'9460008990','9413333853'",
					action : "getFirmsDetails"
				},
				method : "post",
				
				success : function(data){
					console.log("Success"+data);
					// alert("Success : "+data.firms[0].name);
					firmSelect = data.firms;
					$('#firmDiv').html("");
					for(var i = 0; i < data.firms.length; i++)
					{
						var firm = data.firms[i];
						var f = "";
						f += "<div class='row'>";
						f += "<label class='radio radio-inline'>"; 
						f += "<input type='radio' name='firmID' value='"+firm.id+"' class='form-control' />"; 
						f += "<i class='input-helper'></i>";
						f += "<p>"+firm.name+"</p>";
						f += "<label>"+firm.mobile+"</label>";
						f += "<label>"+firm.tin+"</label>";
						console.log(f);
						f += "<label>"+firm.address.houseNo+", "+firm.address.line1+", "+firm.address.line2+", "+firm.address.city+" - "+firm.address.zip+", "+firm.address.state+"</label></label>";
						f += "<p></p></div>";
						$('#firmDiv').append(f);
					}
					$('#modalPrint').modal('show');
				},
				
				error : function(data){
					console.log("Error"+data);
					alert("Error");
				}
			});
}


$("#loanForm").submit(function() {
	
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
		showErrorValidation(div, "Amount can take integer values only !");
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
		showErrorValidation(div, "Tenure can take integer values only !");
		success = false;
	} else
		clearError(div);
	var div = "#monthlyDiv";
	if (installment == "") {
		showErrorValidation(div, "Monthly Installment can't be blank !");
		success = false;
	} else
		clearError(div);
	if (!validity.test(installment)) {
		showErrorValidation(div, "Monthly installement can take integer values only !");
		success = false;
	} else
		clearError(div);
	var div = "#interestDiv";
	if (interest == "") {
		showErrorValidation(div, "Interest rate can't be blank !");
		success = false;
	} else
		clearError(div);
	if (!validity.test(interest)) {
		showErrorValidation(div, "Interest can take integer values only !");
		success = false;
	} else
		clearError(div);
	
	return success;
}
);

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
	if (!validity.test(rate)) {
		showErrorValidation(div, "Interest can take integer values only !");
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
}
);

function notify(from, msg, align, icon, type, animIn, animOut){
    $.growl({
        icon: icon,
        title: '',
        message: msg,
        url: ''
    },{
            element: 'body',
            type: type,
            allow_dismiss: true,
            placement: {
                    from: from,
                    align: align
            },
            offset: {
                x: 20,
                y: 85
            },
            spacing: 10,
            z_index: 1031,
            delay: 2500,
            timer: 1000,
            url_target: '_blank',
            mouse_over: false,
            animate: {
                    enter: animIn,
                    exit: animOut
            },
            icon_type: 'class',
            template: '<div data-growl="container" class="alert" role="alert">' +
                            '<button type="button" class="close" data-growl="dismiss">' +
                                '<span aria-hidden="true">&times;</span>' +
                                '<span class="sr-only">Close</span>' +
                            '</button>' +
                            '<span data-growl="icon"></span>' +
                            '<span data-growl="title"></span>' +
                            '<span data-growl="message"></span>' +
                            '<a href="#" data-growl="url"></a>' +
                        '</div>'
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


function showComment(order_id)
{
	var q = document.getElementById("fillComment");
	q.innerHTML="";

	var head = document.createElement('div');
	head.setAttribute("class","card-header");
	
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
	ee2.setAttribute("class","form-control");
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
	ee3.setAttribute("class","form-control");
	ee3.setAttribute("placeholder", "Enter the detailed description of the Problem occured");
	ee3.setAttribute("autocomplete","off");
	ee3.setAttribute("rows","4");
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


$("#SearchOrderbtn").click(function(e)
		{
	var s = document.getElementById("fillOrderDetails");
	var p = document.getElementById("fillComment");
	
	var ipnm=$("#inputName").val();
	var ipmob=$("#inputMobile").val();
	var ipoid=$("#inputOrderId").val();
	if((ipnm=="" || ipnm == null) && (ipmob=="" || ipmob == null) && (ipoid=="" || ipoid == null))
	{
		alert("No data Found. Please Fill At Least One Category");
	    return;
	}
	
	s.innerHTML="";
	p.innerHTML="";
	$.ajax({
		
		    url : "../../ajaxServlet.do",
		    
		    data : 
		    {
			name : ipnm,
			order : ipoid,
			mobile : ipmob,
			action : "getOrderByNameIDMobile"
	     	},
	     	error : function(data) {
				alert("Error : " + data);
			},
			success : function(data) {
				
				var heading = document.createElement('div');
				heading.setAttribute("class","card-header");
				
				var hd1 = document.createElement('h2');
				$(hd1).text("Order Details");
				var hd2 = document.createElement('small');
				$(hd2).text("Please select the order for complain");
				
				
				heading.appendChild(hd1);
				heading.appendChild(hd2);
				s.appendChild(heading);
		 
				
				var dtbl = document.createElement('div');
				dtbl.setAttribute("class","table-responsive");
			
			    var table = document.createElement('table');
			    table.setAttribute("class", "table table-condensed");
			
			    var tbhead = document.createElement('thead');
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
			    table.appendChild(tbhead);  // Header inserted
			    
			    
			    
			    var orderdetails = data.orderdetails;
			    
			    var tbbody = document.createElement('tbody');
			    
			    for(var i=0;i<data.orderdetails.length;i++)
				{
			    
			    var tbbdrw = document.createElement('tr');
			    	    
			    
					    var input = document.createElement('input');
					    input.setAttribute("type", "radio");
					    input.setAttribute("name", "orderid");					   
					    input.setAttribute("onchange", "showComment(this.value)");
					    input.setAttribute("value", orderdetails[i].oid);			    
					    var tbbdtd = document.createElement('td');
					    tbbdtd.appendChild(input);
					    tbbdrw.appendChild(tbbdtd);
			    
			    	    var tbbdtd1 = document.createElement('td');
					    $(tbbdtd1).text(orderdetails[i].oid);
					    tbbdrw.appendChild(tbbdtd1);
					    
					    var tbbdtd2 = document.createElement('td');
					    $(tbbdtd2).text(orderdetails[i].Customer.name);
					    tbbdrw.appendChild(tbbdtd2);
					    
					    var tbbdtd3 = document.createElement('td');
					    $(tbbdtd3).text(orderdetails[i].Customer.email);
					    tbbdrw.appendChild(tbbdtd3);
					    
					    
					    var tbbdtd4 = document.createElement('td');
					    $(tbbdtd4).text(orderdetails[i].Customer.mobile);
					    tbbdrw.appendChild(tbbdtd4);
					    
					    var tbbdtd5 = document.createElement('td');
					    $(tbbdtd5).text(orderdetails[i].amount);
					    tbbdrw.appendChild(tbbdtd5);
					    
					    var tbbdtd6 = document.createElement('td');
					    $(tbbdtd6).text(orderdetails[i].date);
					    tbbdrw.appendChild(tbbdtd6);
					    
					    var tbbdtd7 = document.createElement('td');
					    $(tbbdtd7).text(orderdetails[i].time);
					    tbbdrw.appendChild(tbbdtd7);
					    
					    var tbbdtd8 = document.createElement('td');
					    var vdiv = document.createElement('div');
					    vdiv.setAttribute("class", "row dialog col-sm-2");
					    var vdetail = document.createElement('button');
					    vdetail.setAttribute("id", "sa-basic");
					    vdetail.setAttribute("class", "btn btn-info");
					    vdetail.setAttribute("type", "button");
					    vdetail.setAttribute("value", orderdetails[i].oid);
					    vdetail.setAttribute("onclick", "window.open('../../PrintOrder.do?print=view&oid="+orderdetails[i].oid+"',1,'width=300,height=300','resizable=yes','status=yes')");
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

function getQueryStringValue (key) {  
	  return unescape(window.location.search.replace(new RegExp("^(?:.*[&\\?]" + escape(key).replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));  
	}


$("#sendOrderDetails").click(
		function() {
			$.ajax({
				url : "../ajaxServlet.do",
				data : {
					mobile : $("#sendMobiles").val(),
					email : $("#sendEmails").val(),
					action : "sendOrderDetails"
				},
				method: "post",
				error : function(data) {
					alert("Error : " + data);
				},
				success : function(data) {
					// alert("Success : " + data.customers[0].name);
					$('#modalMobile').modal('hide');
					swal("SMS/Email Sent Successfully !", "Order details has been sent to the mobile and the email ids.", "success");
				}
			});
		});

$('#selectCustomerBtn').click(function() {
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
			$('#inputCity').val(address.city);
			$('#inputState').val(address.state);
			$('#tin').val(customers[i].tin);
			$('#type').val(customers[i].type);
			$('#inputMobile').val(customers[i].mobile);
			$('#inputPin').val(address.zip);

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

			$.ajax({
				url : "../ajaxServlet.do",
				data : {
					name : n,
					action : "getCustomerDetails"
				},
				error : function(data) {
					alert("Error : " + data);
				},
				success : function(data) {
					// alert("Success : " + data.customers[0].name);

					var div = document.getElementById('custList');
					div.innerHTML = "";
					// customers = new Array();
					// alert("Number of customers : "+data.customers.length);
					customers = data.customers;

					for (var j = 0; j < data.customers.length; j++) {

						var label = document.createElement('label');
						label
								.setAttribute("class",
										"radio radio-inline m-r-20");

						var input = document.createElement('input');
						input.setAttribute("type", "radio");
						input.setAttribute("name", "custID");
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
					}

					$('#modalCustomer').modal('show');

				}
			});
		}

);

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
	select.setAttribute("class", "selectpicker form-control");
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
				select.setAttribute("class", "selectpicker form-control");
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

			$.get("../ajaxServlet.do", {
				action : 'getProductByCategory',
				catgId : $(this).val()
			}, function(response) {
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
	if (qty == "") {
		showErrorValidation("#quantityDiv", "No Quantity entered !");
		success = false;
	} else
		clearError("#quantityDiv");
	if (price == "" || price == null) {
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

	if (avail < qty) {
		showErrorValidation("#productDiv",
				"Product quantity is greater than its availablity !");
		success = false;
	} else
		clearError("#productDiv");

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
	availSpan.setAttribute("class", "md-shopping-basket form-control-feedback");

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
	availSpan.setAttribute("class", "md-attach-money form-control-feedback");

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
	i.setAttribute("class", "md md-close");

	closeButton.appendChild(i);
	fgline.appendChild(closeButton);
	innerDiv.appendChild(fgline);
	formDiv.appendChild(innerDiv);
	rowDiv.appendChild(formDiv);
	// ------- For Cross Button ------//

	mainForm.appendChild(rowDiv);
	// products.push(rowDiv);

}
