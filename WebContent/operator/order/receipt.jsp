<%@page import="com.dbt.support.NumberToWord"%>
<%@page import="com.dbt.data.Payment"%>
<%@page import="java.text.DecimalFormat"%>
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
<title>Payment Receipt</title>
<link href="img/favicon.ico" rel="icon" />
<link rel="stylesheet"
	href="css/bootstrap.css">
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



<script src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>

</head>

<body>

	<div class="container">
		<div class="row" id="buttonDiv">
			<br>
			<button type="button" id="ORIGINAL" onclick="printChallan(this.id)"
				class="btn btn-primary col-xs-offset-4 col-xs-4">Print</button>
		</div>
		<div class="row">
			<div class="col-xs-2">
				<h1>
					<img height="100px"
						src="<%=request.getContextPath()%>${merchant.logo}">
				</h1>
			</div>
			<div class="col-xs-10 text-right">
				<h2 style="color : black;">Payment Receipt</h2>
				<h5 style="color : black;" id="type"></h5>
				<h1 style="margin-top: 0px">
					<small style="color : black;">Receipt No. #${payment.receiptId} | Date :
						${payment.printableTime}</small>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<strong style="color : black;">${merchant.name} </strong><font style="color : black;" size="2px">&nbsp;TIN No.
								${merchant.tin}</font>
						</h4>
					</div>
					<jspcore:set var="merAdd" value="${merchant.address}"></jspcore:set>
					<div class="panel-body">
						<table>
							<tr>
								<td></td>
								<td><strong style="color : black;"> ${merAdd.houseNo}, ${merAdd.line1}<br>
										${merAdd.line2}, ${merAdd.city} - ${merAdd.zip}
								</strong></td>
							</tr>
							<tr>
								<td></td>
								<td style="color : black;">${merchant.mobile}</td>
							</tr>
							<tr>
								<td></td>
								<td style="color : black;">${merchant.email}</td>
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
								<td><strong style="color : black;">Amount (In Figures) :</strong></td>
								<td style="color : black;"><span class="WebRupee">&#x20B9; </span>${payment.printableAmount}</td>
							</tr>
							<tr>
								<td><strong style="color : black;">Amount (In Words) :</strong></td>
								<td style="color : black;">${payment.amountInWords}</td>
							</tr>
							<tr>
								<td><strong style="color : black;">Paid By :</strong></td>
								<td style="color : black;">${payment.paidBy}</strong></td>
							</tr>
							<tr>
								<td><strong style="color : black;">Order ID :</strong></td>
								<td style="color : black;">${payment.orderId}</td>
							</tr>
							<tr>
								<td><strong style="color : black;">Mode :</strong></td>
								<td style="color : black;">${payment.mode}</td>
							</tr>
							<tr>
								<td><strong style="color : black;">Description :</strong></td>
								<td style="color : black;">${payment.description}</td>
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
						<h4 style="color : black;">Note :</h4>
					</div>
					<div class="panel-body">
						<p style="color : black;margin-top: -5px">1. This is a computer generated receipt and is only valid after
							signature of an authorised signatory.</p>
						<p style="color : black;margin-top: -5px">2. Receipt is for acknowledgement purpose only.</p>
						<p style="color : black;margin-top: -5px;margin-bottom: 0px">3. Receipt may represent partial payment for order.</p>
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
								<strong style="color : black;"><h5 class="text-right">Authorised
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
			document.getElementById("type").innerText = "ORIGINAL";
			$("#buttonDiv").hide();
			window.print();
			document.getElementById("type").innerText = "";
			$("#buttonDiv").show();

		}
	</script>
</body>
</html>