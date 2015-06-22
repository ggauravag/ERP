<%@page import="com.dbt.data.Order"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Order Details</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css">
<style>
@import url(http://fonts.googleapis.com/css?family=Bree+Serif);

body,h1,h2,h3,h4,h5,h6 {
	font-family: 'Bree Serif', serif;
}
</style>

</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-3">
				<h1>
					<img height="100px"
						src="<%=request.getContextPath()%>/img/ramfurlogo.jpg">
				</h1>
			</div>
			<div class="col-xs-9 text-right">
				<h2>Order Confirmation</h2>
				<h1>
					<small>Order ID #${order.id} | Date :
						${order.datetime.toGMTString()}</small>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<strong>Ram Furniture</strong>
						</h4>
					</div>
					<div class="panel-body">
						<p>
							<strong> Shop No. 50, Santosh Nagar, Opp. Ganga-Jamuna
								Petrol Pump <br> Near New Aatish Market, Gopalpura Bypass,
								Jaipur - 302019
							</strong> <br> 9413333853, 9460008990 <br>
							ramfurnitures@gmail.com
						</p>
					</div>
				</div>
			</div>
			<div class="col-xs-6  text-right">
				<div class="panel panel-default">
					<div class="panel-heading">
						<jspcore:if test='${order.customer.type eq "MERCHANT"}'>
							<h4>
								M/s : <strong>${order.customer.name} (TIN No.
									${order.customer.tin} )</strong>
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
						<p>
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

				<jspcore:forEach items="${order.products}" var="product">
					<tr>
						<td>${product.name}</td>
						<td class="text-right">${product.quantity}</td>
						<td class="text-right"><span class="WebRupee">&#x20B9;
						</span>${product.sellPrice}</td>
						<td class="text-right"><span class="WebRupee">&#x20B9;
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
		</table>
		<div class="row text-right">
			<div class="col-xs-2 col-xs-offset-8">
				<p>
					<strong> Sub Total : <br> TAX : <br> Total : <br>
					</strong>
				</p>
			</div>
			<div class="col-xs-2">
				<strong> <span class="WebRupee">&#x20B9; </span>${order.amount}
					<br> N/A <br> <span class="WebRupee">&#x20B9; </span>${order.amount}
					<br>
				</strong>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-8">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h4>Terms & Conditions</h4>
					</div>
					<div class="panel-body">
						<p>1. This is an order confirmation not an invoice (VAT
							Exclusive).</p>
						<p>2. Fulfillment of order is subject to stock availablity.</p>
						<p>3. Order shall be processed only after 100% payment.</p>
					</div>
				</div>
			</div>
			<div class="col-xs-4">
				<div class="span7">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h4>For RAM FURNITURE</h4>
						</div>
						<div class="panel-body">
							<p>&nbsp;&nbsp;</p>
							<p>&nbsp;&nbsp;</p>
							<p>
							<strong><h5 class="text-right">Authorised Signatory</h5></strong>
							</p>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
