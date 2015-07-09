<%@page import="com.dbt.support.NumberToWord"%>
<%@page import="com.dbt.data.Payment"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<doctype html>


<html lang="en">
<head>
<meta charset="UTF-8">
<title>Payment Receipt</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css">
<style>
@import url(http://fonts.googleapis.com/css?family=Bree+Serif);

body,h1,h2,h3,h4,h5,h6 {
	font-family: 'Bree Serif', serif;
}

u {
	border-bottom: 3px dotted #000;
	text-decoration: none;
}

p.text {
	line-height: 125%;
}
</style>

</head>

<body>
	
	<div class="container">
		<div class="row">
			<div class="col-xs-2">
				<h1>
					<img height="100px"
						src="<%=request.getContextPath()%>${merchant.logo}">
				</h1>
			</div>
			<div class="col-xs-10 text-right">
				<h2>Payment Receipt</h2>
				<h1>
					<small>Receipt No. #${payment.id + 4000} | Date :
						${payment.printableTime}</small>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
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
			
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="panl panel-default">
					<div class="panel-body">
						<table class="table">
							<tr>
								<td><strong>Amount (In Figures) :</strong></td>
								<td><span class="WebRupee">&#x20B9; </span>${payment.printableAmount}</td>
							</tr>
							<tr>
								<td><strong>Amount (In Words) :</strong></td>
								<td>${payment.amountInWords}</td>
							</tr>
							<tr>
								<td><strong>Paid By :</strong></td>
								<td>${payment.paidBy}</strong></td>
							</tr>
							<tr>
								<td><strong>Order ID :</strong></td>
								<td>${payment.orderId}</td>
							</tr>
							<tr>
								<td><strong>Mode :</strong></td>
								<td>${payment.mode}</td>
							</tr>
							<tr>
								<td><strong>Description :</strong></td>
								<td>${payment.description}</td>
							</tr>

						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- / end client details section -->


		<div class="row">
			<div class="col-xs-7">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h4>Note :</h4>
					</div>
					<div class="panel-body">
						<p>1. This is computer generated receipt and only valid after
							signature of an authorised signatory.</p>
						<p>2. Receipt is for acknowledgement purpose only.</p>
					</div>
				</div>
			</div>

			<div class="col-xs-5">
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