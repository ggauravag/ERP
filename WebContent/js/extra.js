/**
 * New node file
 */

var orderdetails;

$("#searchOrderButton").click(function() {
	var orderID = $("#inputOrderID").val();
});


function showProducts(value)
{
   //alert("product : "+value);
   var selectedOrder = null;
   for(var i = 0; i < orderdetails.length; i++)
   {
	   if(orderdetails[i].oid == value)
	   {
		   selectedOrder = orderdetails[i];
		   $("#orderID").val(orderdetails[i].oid);
		   break;
	   }
   }
   
   $("#selectName").val(selectedOrder.Customer.name);
   
   $("#selectEmail").val(selectedOrder.Customer.email);
   $("#selectMobile").val(selectedOrder.Customer.mobile);
   $("#productDetails").html("");
   for(var j = 0; j < selectedOrder.items.length; j++)
   {
	   var item = selectedOrder.items[j];
	   //alert("Product id is : "+item.product_id);
	   var app = "<div class=\"form-group\">";
	   app += "<div class=\"col-sm-2 col-sm-offset-1\">";
	   if(item.ship_id == 0)
	   {
		   app += "<input type='checkbox' name='prodID' value='"+item.product_id+"' />";
	   }
	   else
	   {
		   app += "<input type='checkbox' name='prodID' value='"+item.product_id+"' disabled checked/>";
	   }
	   app += "</div>";
	   app += "<div class=\"col-sm-2 col-sm-offset-1\">";
	   app += "<label class=\"control-label\"><strong>"+item.product_name+"";
	   app += "</strong></label>";
	   app += "</div>";
	   app += "<div class=\"col-sm-2 col-sm-offset-1\">";
	   app += "<label class=\"control-label\"><strong>"+item.product_qty+"</strong></label>";
	   app += "</div></div>";
	   $("#productDetails").append(app);
   } 
}



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

					$
							.ajax({

								url : "../ajaxServlet.do",

								data : {
									name : ipnm,
									order : ipoid,
									mobile : ipmob,
									action : "getOrder"
								},
								error : function(data) {
									alert("Error : " + data);
								},
								success : function(data) {

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
														"window.open('../PrintOrder.do?print=view&oid="
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