/**
 * 
 */
var products = new Array();
var availProduct = new Array();
var customers;

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
		showErrorValidation(div, "Select a product first.")
		success = false;
	} else
		clearError(div);

	return success;
}

);

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

			$('#inputMobile').val(customers[i].mobile);
			$('#inputPin').val(address.zip);

		}

	}

	// console.log(cid);
});

$("#searchCust").click(function(e) {
	console.log($("#inputName").val());
	var n = $("#inputName").val();

	if (n == "" || n == null) {
		return;
	}

	$.ajax({
		url : "../ajaxServlet",
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
				label.setAttribute("class", "radio radio-inline m-r-20");

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
				var text = document.createTextNode(radioText);
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
			}
			else
				clearError("#categoryDiv");

			$.get("../ajaxServlet", {
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
	}
	else
		clearError("#productDiv");
	if (qty == "") {
		showErrorValidation("#quantityDiv","No Quantity entered !");
		success = false;
	}
	else 
		clearError("#quantityDiv");
	if (price == "" || price == null) {
		showErrorValidation("#priceDiv","No Price entered!");
		success = false;
	}
	else
		clearError("#priceDiv");
	if (avail == "") {
		showErrorValidation("#productDiv","No Product available !");
		success = false;
	}
	else
		clearError("#productDiv");
	

	if(!success)
		return;
	
	qty = parseInt(qty);
	price = parseInt(price);
	avail = parseFloat(avail);
	
	if(avail < qty)
	{
		showErrorValidation("#productDiv","Product quantity is greater than its availablity !");
		success = false;
	}
	else
		clearError("#productDiv");
	
	if(!success)
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
	productName.setAttribute("name", "prodName" + index);

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
	productQty.setAttribute("name", "prodQty" + index);

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
	productQty.setAttribute("name", "prodPrice" + index);

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
