<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%><doctype
	html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Challan Details</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
<style>
@import url(http://fonts.googleapis.com/css?family=Bree+Serif);

body,h1,h2,h3,h4,h5,h6 {
	font-family: 'Bree Serif', serif;
}
</style>

</head>

<body>
	<jspcore:set var="merchant" value="${sessionScope.merchant}"></jspcore:set>
	<div class="container">
		<div class="row">
			<div class="col-xs-3">
				<h1>
					<img height="100px"
						src="<%=request.getContextPath()%>${merchant.logo}">
				</h1>
			</div>
			<div class="col-xs-9 text-right">
				<h2>Delivery Challan</h2>
				<h1>
					<small>Challan No. #${shipment.id + 2000} | Date :
						${shipment.getPrintableTime()}</small>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<strong>${merchant.name} (TIN No. ${merchant.tin})</strong>
						</h4>
					</div>
					<jspcore:set var="merAdd" value="${merchant.address}"></jspcore:set>
					<div class="panel-body">
						<table>
							<tr>
								<td></td>
								<td><strong> ${merAdd.houseNo}, ${merAdd.line1}<br>
										${merAdd.line2}, ${merAdd.city} - ${merAdd.zip}
								</strong></td>
							</tr>
							<tr>
								<td></td>
								<td>${merchant.mobile}</td>
							</tr>
							<tr>
								<td></td>
								<td>${merchant.email}</td>
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
						<strong>${add.houseNo}, ${add.line1},<br>${add.line2},
							${add.city} - ${add.zip},${add.state}
						</strong> <br>${order.customer.mobile}<br> ${order.customer.email }
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="panl panel-default">
					<div class="panel-body">
						<table>
						
							<jspcore:choose>
						
								<jspcore:when test='${shipment.medium == "Tempo / Owner Vehicle"}'>
									<tr>
										<td>Shipping Medium</td>
										<td>: Tempo / Commercial Vehicle</td>
									</tr>
									<tr>
										<td>Vehicle Number</td>
										<td>: ${shipment.mediumNumber}</td>
									</tr>
								</jspcore:when>
								<jspcore:when test='${shipment.medium == "Buyer\'s Own"}'>
									<tr>
										<td>Shipping Medium</td>
										<td>: Buyer's Own</td>
									</tr>
									<tr>
										<td>Vehicle Number</td>
										<td>: &nbsp;None</td>
									</tr>
								</jspcore:when>
								<jspcore:when test="${shipment.medium == 'Transport Company'}">
									<tr>
										<td>Shipping Medium</td>
										<td>: Third Party Transport</td>
									</tr>
									<tr>
										<td>GR/B Number</td>
										<td>: ${shipment.mediumNumber}</td>
									</tr>
									<tr>
										<td>Transport Name</td>
										<td>: ${shipment.mediumName}</td>
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
				
				<jspcore:forEach items="${order.orderitems}" var="product">
					<tr>
						<td>${product.product_name}</td>
						<td class="text-right">${product.quantity}</td>
						<td class="text-right"><span class="WebRupee">&#x20B9;
						</span>${product.amount}</td>
						<td class="text-right"><span class="WebRupee">&#x20B9;
						</span>${product.quantity * product.amount}</td>
					</tr>
				</jspcore:forEach>

				<jspcore:choose>
					<jspcore:when test="${order.orderitems.size() <= 5}">
						<jspcore:forEach begin="${order.orderitems.size() + 1}" end="${5}">
							<tr>
								<td>&nbsp;</td>
								<td class="text-right">&nbsp;</td>
								<td class="text-right">&nbsp;</td>
								<td class="text-right">&nbsp;-</td>
							</tr>
						</jspcore:forEach>
					</jspcore:when>
					<jspcore:otherwise>
						<jspcore:forEach begin="${order.orderitems.size() + 1}" end="${10}">
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
					<strong> Total : <br>
					</strong>
				</p>
			</div>
			<div class="col-xs-2">
				<strong>${order.amount}<br>
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
						<p>1. This is a delivery challan not an invoice.</p>
						<p>2. If applicable, VAT & Taxes would be exclusive of price.</p>
						<p>3. Order shall be processed only after 100% payment.</p>
					</div>
				</div>
			</div>
			<div class="col-xs-4">
				<div class="span7">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h4>For ${merchant.name.toUpperCase()}</h4>
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
</body>
</html>