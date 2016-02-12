<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%><doctype
	html> <%
 	String basePath = request.getContextPath();
 	String bPath = request.getScheme() + "://"
 			+ request.getServerName() + ":" + request.getServerPort()
 			+ basePath + "/";
 %> <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=bPath%>">
<meta charset="UTF-8">
<title>Challan Details</title>
<link href="img/favicon.ico" rel="icon" />
<link rel="stylesheet" href="css/bootstrap.css">
<style>
@import url(http://fonts.googleapis.com/css?family=Bree+Serif);

body,h1,h2,h3,h4,h5,h6 {
	font-family: 'Bree Serif', serif;
}

.table {
	border: 1px solid black;
}

.table thead>tr>th {
	border: 1px solid black;
	border-bottom: 3px solid black;
}

.table tbody>tr>th,.table tfoot>tr>th,.table thead>tr>td,.table tbody>tr>td,.table tfoot>tr>td
	{
	border: 1px solid black;
}
</style>
<script src="js/jquery-2.1.1.min.js"></script>

</head>

<body>
	<jspcore:set var="merchant" value="${sessionScope.merchant}"></jspcore:set>
	<div class="container">
		<div class="row" id="buttonDiv">
			<br>
			<button type="button" id="ORIGINAL" onclick="printChallan(this.id)"
				class="btn btn-primary col-xs-offset-1 col-xs-3">Print
				Original</button>
			<button type="button" id="DUPLICATE" onclick="printChallan(this.id)"
				class="btn btn-warning col-xs-offset-1 col-xs-3">Print
				Duplicate</button>
			<button type="button" id="TRIPLICATE" onclick="printChallan(this.id)"
				class="btn btn-info col-xs-offset-1 col-xs-3">Print
				Triplicate</button>
		</div>
		<jsp:useBean id="convert" class="com.dbt.support.NumberToWord"
			scope="page"></jsp:useBean>
		<div class="row" style="margin-top: -21px">
			<div class="col-xs-3">
				<h1>
					<img height="100px"
						src="${merchant.logo}">
				</h1>
			</div>
			<div class="col-xs-9 text-right">
				<h2 style="color: black;">Delivery Challan</h2>
				<h5 style="color: black;" id="type"></h5>
				<h1 style="margin-top: 0px">
					<small style="color: black;">Challan No.
						#${shipment.challanId} | Date : ${shipment.getPrintableTime()}</small>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="panel panel-default">
					<div class="panel-heading" style="margin-bottom: 0px;">
						<h4>
							<strong style="color: black;">${merchant.name}</strong><br>
							<font size="2px" color="black"> TIN No. ${merchant.tin}</font>
						</h4>
					</div>
					<jspcore:set var="merAdd" value="${merchant.address}"></jspcore:set>
					<div class="panel-body">
						<table style="margin-bottom: -9px;margin-top:-9px">
							<tr>
								<td></td>
								<td><strong style="color: black;">
										${merAdd.houseNo}, ${merAdd.line1}<br> ${merAdd.line2},
										${merAdd.city} - ${merAdd.zip}
								</strong></td>
							</tr>
							<tr>
								<td></td>
								<td style="color: black;">${merchant.mobile}</td>
							</tr>
							<tr>
								<td></td>
								<td style="color: black;">${merchant.email}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="col-xs-6  text-right">
				<div class="panel panel-default">
					<div class="panel-heading">
						<jspcore:if test='${order.customer.type eq "MERCHANT"}'>
							<h4>
								M/s : <strong style="color: black;">${order.customer.name}
								</strong><br>
								<font size="2px">TIN No. ${order.customer.tin}</font>
							</h4>
						</jspcore:if>
						<jspcore:if test='${order.customer.type eq "CUSTOMER"}'>
							<h4>
								Mr./Mrs. : <strong style="color: black;">${order.customer.name}</strong>
							</h4>
						</jspcore:if>
					</div>
					<jspcore:set value="${order.customer.address}" var="add" />
					<div class="panel-body">
						<table align="right">
							<tr>
								<td></td>
								<td class="text-right"><strong style="color: black;">
										${add.houseNo}, ${add.line1}<br> ${add.line2},
										${add.city} - ${add.zip}
								</strong></td>
							</tr>
							<tr>
								<td></td>
								<td class="text-right" style="color: black;">${order.customer.mobile}</td>
							</tr>
							<tr>
								<td></td>
								<td class="text-right" style="color: black;">${order.customer.email}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-default"
					style="margin-top: -10px; margin-bottom: 8px">
					<div class="panel-body">
						<table style="margin-top: -13px;margin-bottom : -12px">
							<jspcore:choose>
								<jspcore:when
									test='${shipment.medium == "Tempo / Owner Vehicle"}'>
									<tr>
										<td style="padding-right: 30px;"><strong
											style="color: black;">Shipping Medium</strong></td>
										<td class="text-left" style="color: black;">:&nbsp;&nbsp;
											Tempo / Commercial Vehicle</td>
									</tr>
									<tr>
										<td style="padding-right: 30px;"><strong
											style="color: black;">Vehicle Number</strong></td>
										<td class="text-left" style="color: black;">:&nbsp;&nbsp;
											${shipment.mediumNumber}</td>
									</tr>
								</jspcore:when>
								<jspcore:when test='${shipment.medium == "Buyer\'s Own"}'>
									<tr>
										<td style="padding-right: 30px;"><strong
											style="color: black;">Shipping Medium</strong></td>
										<td class="text-left" style="color: black;">:&nbsp;&nbsp;
											Buyer's Own</td>
									</tr>
									<tr>
										<td style="padding-right: 30px;"><strong
											style="color: black;">Vehicle Number</strong></td>
										<td class="text-left" style="color: black;">:&nbsp;&nbsp;
											None</td>
									</tr>
								</jspcore:when>
								<jspcore:when test="${shipment.medium == 'Transport/Courier Company'}">
									<tr>
										<td style="padding-right: 30px;"><strong
											style="color: black;">Shipping Medium</strong></td>
										<td class="text-left" style="color: black;">:&nbsp;&nbsp;
											Third Party Transport</td>
									</tr>
									<tr>
										<td style="padding-right: 30px;"><strong
											style="color: black;">GR/B/Vehicle Number</strong></td>
										<td class="text-left" style="color: black;">:&nbsp;&nbsp;
											${shipment.mediumNumber}</td>
									</tr>
									<tr>
										<td style="padding-right: 30px;"><strong
											style="color: black;">Transport Name</strong></td>
										<td class="text-left" style="color: black;">:&nbsp;&nbsp;
											${shipment.mediumName}</td>
									</tr>
								</jspcore:when>
							</jspcore:choose>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- / end client details section -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th class="text-left">
						<h5>
							<strong style="color: black;">Particulars</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong style="color: black;">Quantity</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong style="color: black;">Rate</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong style="color: black;">Sub Total</strong>
						</h5>
					</th>
				</tr>
			</thead>
			<tbody>
				<jspcore:set var="amount" value="${0}"></jspcore:set>
				<jspcore:forEach items="${shipment.items}" var="product">
					<tr>
						<td style="color: black;">${product.name}</td>
						<td class="text-right" style="color: black;">${convert.customFormat(product.quantity)}</td>
						<td class="text-right" style="color: black;"><span
							class="WebRupee">&#x20B9; </span>${convert.customFormat(product.sellPrice)}</td>
						<td class="text-right" style="color: black;"><span
							class="WebRupee">&#x20B9; </span>${convert.customFormat(product.quantity * product.sellPrice)}</td>
						<jspcore:set var="amount"
							value="${amount + product.quantity * product.sellPrice}"></jspcore:set>
					</tr>
				</jspcore:forEach>
				<jspcore:choose>
					<jspcore:when test="${shipment.medium == 'Transport Company'}">
						<jspcore:set var="limit" value="5"></jspcore:set>
					</jspcore:when>
					<jspcore:otherwise>
						<jspcore:set var="limit" value="6"></jspcore:set>
					</jspcore:otherwise>
				</jspcore:choose>

				<jspcore:choose>
					<jspcore:when test="${shipment.items.size() <= limit}">
						<jspcore:forEach begin="${shipment.items.size() + 1}"
							end="${limit}">
							<tr>
								<td>&nbsp;</td>
								<td class="text-right">&nbsp;</td>
								<td class="text-right">&nbsp;</td>
								<td class="text-right">&nbsp;-</td>
							</tr>
						</jspcore:forEach>
					</jspcore:when>
					<jspcore:otherwise>
						<jspcore:forEach begin="${shipment.items.size() + 1}"
							end="${limit*2}">
							<tr>
								<td>&nbsp;</td>
								<td class="text-right">&nbsp;</td>
								<td class="text-right">&nbsp;</td>
								<td class="text-right">&nbsp;-</td>
							</tr>
						</jspcore:forEach>
					</jspcore:otherwise>
				</jspcore:choose>
		</table>
		<div class="row text-right" style="margin-top: -14px; ">
			<div class="col-xs-6 text-left">
			<jspcore:if test="${merchant.name == 'Ram Furniture' }">
				<p style="font-size: 18px;color: black;">
						<u>Under Composition Scheme</u>
				</p>
			</jspcore:if>
				
			</div>
			<div class="col-xs-4">
				<p>
					<strong style="font-size: 16px; color: black;"> Challan
						Amount : <br> Discount/Less (-) : <br> Shipping Freight (+) :<br>Total Amount :
					</strong>
				</p>
			</div>

			<div class="col-xs-2">
				<strong style="font-size: 16px; color: black;">${convert.customFormat(amount)}<br>
				${convert.customFormat(order.discount)}<br>${convert.customFormat(shipment.charge)}<br>${convert.customFormat(amount - order.discount + shipment.charge)}
				</strong>
			</div>
		</div>
		<div class="row" >
			<div class="col-xs-7" >
				<div class="panel panel-info">

					<h5 style="color: black; padding-left: 10px">
						<b>Terms & Conditions :</b>
					</h5>

					<ul>
						<p style="color: black; margin-top: -7px; padding-left: 5px">
						<jspcore:if test="${merchant.name != 'Ram Furniture' }">
							<li>
								If applicable, VAT & Taxes would be exclusive of challan
								amount.
							</li>
						</jspcore:if>
						<jspcore:if test="${merchant.name == 'Ram Furniture' }">
							<li>
								Goods once sold will not be taken back.
							</li>
						</jspcore:if>
						</p>
					</ul>
					<hr style="border-top: 1px solid black; margin-top: -5px">
					<h5
						style="color: black; margin-top: -10px; padding-left: 10px; padding-bottom: 65px">
						<b>Receiver's Signature :</b>
					</h5>

				</div>
			</div>
			<div class="col-xs-5" >
				<div class="span7">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h4 style="color: black;">For ${merchant.name.toUpperCase()}</h4>
						</div>
						<div class="panel-body">
							<p>&nbsp;</p>
							<p>&nbsp;&nbsp;</p>
							<p>
								<strong><h5 class="text-right" style="color: black;">Authorised
										Signatory</h5></strong>
							</p>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script>
		function printChallan(buttonID) {
			document.getElementById("type").innerText = buttonID;
			$("#buttonDiv").hide();
			window.print();
			document.getElementById("type").innerText = "";
			$("#buttonDiv").show();
		}
	</script>
</body>
</html>