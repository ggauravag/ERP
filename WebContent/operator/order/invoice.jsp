<%@page import="com.dbt.support.NumberToWord"%>
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
<title>Order Invoice</title>
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
<script src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<jspcore:set var="merchant" value="${sessionScope.merchant}"></jspcore:set>
	<jspcore:set var="order" value="${invoice.order}"></jspcore:set>
	<jsp:useBean id="convert" class="com.dbt.support.NumberToWord" scope="page"></jsp:useBean>
		
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
		<div class="row">
			<div class="col-xs-3">
				<h1>
					<img height="100px"
						src="<%=request.getContextPath()%>${merchant.logo}">
				</h1>
			</div>
			<div class="col-xs-9 text-right">
				<h2 style="color : black;">Order Invoice</h2>
				<h5 style="color : black;" id="type"></h5>
				<h1 style="margin-top: 0px">
					<small style="color : black;">Invoice No. #${invoice.id} | Date :
						${invoice.getPrintableDate()}</small>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<strong style="color : black;">${merchant.name}</strong><br> <font size="2px" style="color : black;">TIN No.
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
			<div class="col-xs-6  text-right">
				<div class="panel panel-default">
					<div class="panel-heading">
						<jspcore:if test='${order.customer.type eq "MERCHANT"}'>
							<h4>
								M/s : <strong style="color : black;">${order.customer.name} </strong><br><font size="2px" color="black">TIN
									No. ${order.customer.tin}</font>
							</h4>
						</jspcore:if>
						<jspcore:if test='${order.customer.type eq "CUSTOMER"}'>
							<h4>
								Mr./Mrs. : <strong style="color : black;">${order.customer.name}</strong>
							</h4>
						</jspcore:if>
					</div>
					<jspcore:set value="${order.customer.address}" var="add" />
					<div class="panel-body">
						<table align="right">
							<tr>
								<td></td>
								<td class="text-right" style="color : black;"><strong> ${add.houseNo}, ${add.line1}<br>
										${add.line2}, ${add.city} - ${add.zip}
								</strong></td>
							</tr>
							<tr>
								<td></td>
								<td class="text-right" style="color : black;">${order.customer.mobile}</td>
							</tr>
							<tr>
								<td></td>
								<td class="text-right" style="color : black;">${order.customer.email}</td>
							</tr>
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
							<strong style="color : black;">Particulars</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong style="color : black;">Quantity</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong style="color : black;">Rate</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong style="color : black;">Sub Total</strong>
						</h5>
					</th>
				</tr>
			</thead>
			<tbody>

				<jspcore:forEach items="${order.orderitems}" var="product">
					<tr>
						<td style="color : black;">${product.product_name}</td>
						<td class="text-right" style="color : black;">${product.quantity}</td>
						<td class="text-right" style="color : black;"><span class="WebRupee">&#x20B9;
						</span>${convert.customFormat(product.amount)}</td>
						<td class="text-right" style="color : black;"><span class="WebRupee">&#x20B9;
						</span>${convert.customFormat(product.quantity * product.amount)}</td>
					</tr>
				</jspcore:forEach>

				<jspcore:choose>
					<jspcore:when test="${order.orderitems.size() <= 6}">
						<jspcore:forEach begin="${order.orderitems.size() + 1}" end="${6}">
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
		</table>
		<div class="row text-right">
			<div class="col-xs-7 text-left">
				<jspcore:if test="${invoice.hasVAT() == false}">
					<p style="font-size: 18px;color: black;">
						<u>Under Composition Scheme</u>
					</p>
				</jspcore:if>
				<p style="font-size: 18px;color:black;">
					Amount(In Words) : <strong style="color : black;">${invoice.getTotalInWords()}</strong>
				</p>
			</div>
			<div class="col-xs-3">
				<p style="font-size: 18px">
					<strong style="color : black;"> Total : <br> <jspcore:if
							test="${invoice.hasVAT()}">
					VAT @ ${invoice.vatPercent} % : <br>
						</jspcore:if> Grand Total : <br>
					</strong>
				</p>
			</div>
			<div class="col-xs-2">
				<p style="font-size: 18px">
					<strong style="color : black;">${invoice.total}<br> <jspcore:if
							test="${invoice.hasVAT()}">
					${invoice.tax}<br>
						</jspcore:if> ${invoice.grandTotal}<br>
					</strong>
				</p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h4 style="color : black;">Terms & Conditions</h4>
					</div>
					<div class="panel-body">
						<p style="color : black;">1. This is an Order Invoice.</p>
						<jspcore:if test="${merchant.name != 'Ram Furniture' }">
							<p style="color : black;">2. Grand Total is VAT / Taxes inclusive.</p>
							<p style="color : black;margin-bottom: 5px">3. All matters are under Jaipur jurisdiction.</p>
						</jspcore:if>
						<jspcore:if test="${merchant.name == 'Ram Furniture' }">
							<p style="color : black;">2. Goods once sold will not be taken back.</p>
							<p style="color : black;margin-bottom: 5px">3. All matters are under Jaipur jurisdiction.</p>
						</jspcore:if>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
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
			document.getElementById("type").innerText = buttonID;
			$("#buttonDiv").hide();
			window.print();
			document.getElementById("type").innerText = "";
			$("#buttonDiv").show();
		}
	</script>
</body>
</html>