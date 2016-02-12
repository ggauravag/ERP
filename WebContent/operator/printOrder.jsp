<%@page import="com.dbt.data.Order"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String basePath = request.getContextPath();
    String bPath = request.getScheme() + "://" + request.getServerName () + ":" + request.getServerPort () + basePath + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=bPath%>">
<meta charset="UTF-8">
<title>Order Details</title>
<link href="img/favicon.ico" rel="icon" />
<link rel="stylesheet"
	href="css/bootstrap.css">
<style>
@import url(http://fonts.googleapis.com/css?family=Bree+Serif);

body,h1,h2,h3,h4,h5,h6 {
	font-family: 'Bree Serif', serif;
}
.table { border: 1px solid black; }
.table thead > tr > th { border : 1px solid black;  border-bottom: 3px solid black; }
.table tbody > tr > th, .table tfoot > tr > th, .table thead > tr > td, .table tbody > tr > td, .table tfoot > tr > td { border: 1px solid black; }

</style>
<script src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<jspcore:set var="merchant" value="${sessionScope.merchant}"></jspcore:set>
	<div class="container">
		<div class="row" id="buttonDiv">
			<br>
			<button type="button" id="ORIGINAL" onclick="printChallan(this.id)"
				class="btn btn-primary col-xs-offset-4 col-xs-4">Print
			</button>
		</div>

		<div class="row">
			<div class="col-xs-3">
				<h1>
					<img height="100px"
						src="${merchant.logo}">
				</h1>
			</div>
			<div class="col-xs-9 text-right">
				<h2><bean:message key="operator.printorder.head1" /></h2>
				<h5 id="type"></h5>
				<h1 style="margin-top: -10px">
					<small style="color : black;">Order ID #${order.id} | Date :
						${order.getPrintableTime()}</small>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<strong>${merchant.name}</strong><br><font size="2px"> TIN No.
								${merchant.tin}</font>
						</h4>
					</div>
					<jspcore:set var="merAdd" value="${merchant.address}"></jspcore:set>
					<div class="panel-body">
						<p style="color : black;">
							<strong> ${merAdd.houseNo}, ${merAdd.line1}<br>
								${merAdd.line2}, ${merAdd.city} - ${merAdd.zip}
							</strong> <br> ${merchant.mobile} <br> ${merchant.email}
						</p>
					</div>
				</div>
			</div>
			<div class="col-xs-6  text-right">
				<div class="panel panel-default">
					<div class="panel-heading">
						<jspcore:if test='${order.customer.type eq "MERCHANT"}'>
							<h4>
								M/s : <strong>${order.customer.name} </strong><br><font size="2px">TIN
									No. ${order.customer.tin }</font>
							</h4>
						</jspcore:if>
						<jspcore:if test='${order.customer.type eq "CUSTOMER"}'>
							<h4>
								Mr./Mrs. : <strong>${order.customer.name}</strong>
							</h4>
						</jspcore:if>
					</div>
					<jspcore:set value="${order.customer.address}" var="add" />
					<div class="panel-body">
						<p style="color : black;">
							<strong>${add.houseNo}, ${add.line1},<br>${add.line2},
								${add.city} - ${add.zip},${add.state}
							</strong><br>${order.customer.mobile}<br> ${order.customer.email }
						</p>
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
							<strong>Particulars</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong>Quantity</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong>Rate</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong>Sub Total</strong>
						</h5>
					</th>
				</tr>
			</thead>
			<tbody>
				<jspcore:if test="${order.products != null}">
					<jspcore:forEach items="${order.products}" var="product">
						<tr>
							<td style="color : black;">${product.name}</td>
							<td class="text-right" style="color : black;">${product.quantity}</td>
							<td class="text-right" style="color : black;"><span class="WebRupee">&#x20B9;
							</span>${product.sellPrice}</td>
							<td class="text-right" style="color : black;"><span class="WebRupee">&#x20B9;
							</span>${product.quantity * product.sellPrice}</td>
						</tr>
					</jspcore:forEach>

					<jspcore:choose>
						<jspcore:when test="${order.products.size() <= 7}">
							<jspcore:forEach begin="${order.products.size() + 1}" end="${7}">
								<tr>
									<td>&nbsp;</td>
									<td class="text-right">&nbsp;</td>
									<td class="text-right">&nbsp;</td>
									<td class="text-right">&nbsp;-</td>
								</tr>
							</jspcore:forEach>
						</jspcore:when>
						<jspcore:otherwise>
							<jspcore:forEach begin="${order.products.size() + 1}" end="${12}">
								<tr>
									<td>&nbsp;</td>
									<td class="text-right">&nbsp;</td>
									<td class="text-right">&nbsp;</td>
									<td class="text-right">&nbsp;-</td>
								</tr>
							</jspcore:forEach>
						</jspcore:otherwise>
					</jspcore:choose>
				</jspcore:if>
				<jspcore:if test="${order.orderitems != null}">
					<jspcore:forEach items="${order.orderitems}" var="product">
						<tr>
							<td style="color : black;">${product.product_name}</td>
							<td class="text-right" style="color : black;">${product.quantity}</td>
							<td class="text-right" style="color : black;"><span class="WebRupee">&#x20B9;
							</span>${product.amount}</td>
							<td class="text-right" style="color : black;"><span class="WebRupee">&#x20B9;
							</span>${product.quantity * product.amount}</td>
						</tr>
					</jspcore:forEach>

					<jspcore:choose>
						<jspcore:when test="${order.orderitems.size() <= 7}">
							<jspcore:forEach begin="${order.orderitems.size() + 1}"
								end="${7}">
								<tr>
									<td>&nbsp;</td>
									<td class="text-right">&nbsp;</td>
									<td class="text-right">&nbsp;</td>
									<td class="text-right">&nbsp;-</td>
								</tr>
							</jspcore:forEach>
						</jspcore:when>
						<jspcore:otherwise>
							<jspcore:forEach begin="${order.orderitems.size() + 1}"
								end="${12}">
								<tr>
									<td>&nbsp;</td>
									<td class="text-right">&nbsp;</td>
									<td class="text-right">&nbsp;</td>
									<td class="text-right">&nbsp;-</td>
								</tr>
							</jspcore:forEach>
						</jspcore:otherwise>
					</jspcore:choose>
				</jspcore:if>
			</tbody>
		</table>
		<div class="row text-right">
			<div class="col-xs-8 text-left">
				<jspcore:if test="${merchant.name == 'Ram Furniture' }">
					<p style="font-size: 18px;color: black;">
							<u>Under Composition Scheme</u>
					</p>
				</jspcore:if>
				
			</div>
		
		
			<div class="col-xs-2">
				<p>
					<strong> Total : <br>
					</strong>
				</p>
			</div>
			<div class="col-xs-2">
				<strong> <span class="WebRupee">&#x20B9; </span>${order.amount}
					<br>
				</strong>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-7">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h4 style="color : black;">Terms & Conditions</h4>
					</div>
					<div class="panel-body">
						<jspcore:if test="${merchant.name != 'Ram Furniture' }">
							<p style="color : black;">
							1. This is an order confirmation not an invoice (VAT
							Exclusive).
							</p>
						</jspcore:if>
						<jspcore:if test="${merchant.name == 'Ram Furniture' }">
							<p style="color : black;">
							1. This is an order confirmation not an invoice.
							</p>
						</jspcore:if>
						
						<p style="color : black;">2. Fulfillment of order is subject to stock availablity.</p>
						<p style="color : black;">3. Order shall be processed only after 100% payment.</p>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="span7">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h4 style="color : black;">For ${merchant.name.toUpperCase()}</h4>
						</div>
						<div class="panel-body">
							<p>&nbsp;&nbsp;</p>
							<p>&nbsp;&nbsp;</p>
							<p>
								<strong><h5 class="text-right">Authorised
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
