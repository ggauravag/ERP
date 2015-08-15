<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
	String bPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ basePath + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=bPath%>">
<meta charset="UTF-8">
<title>Letter Head</title>
<link href="img/favicon.ico" rel="icon" />
<link rel="stylesheet" href="css/bootstrap.css">
<style>
@import url(http://fonts.googleapis.com/css?family=Bree+Serif);

body,h1,h2,h3,h4,h5,h6 {
	font-family: 'Bree Serif', serif;
}
</style>


<script src="js/jquery-2.1.1.min.js"></script>

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
				<h1 style="color: black;">${merchant.name}</h1>
				<jspcore:set var="merAdd" value="${merchant.address}"></jspcore:set>
				<h3 style="margin-top: 0px">
					<small style="color: black;">${merAdd.houseNo},
						${merAdd.line1}<br> ${merAdd.line2}, ${merAdd.city} -
						${merAdd.zip} (${merAdd.state})
					</small>
				</h3>
			</div>
		</div>

		<div style="height: 780px">
			<div class="col-sm-12">
				<table class="table"
					style="margin-bottom: 0px; border-style: solid; border-width: 5px; border-color: #000 white white white;">
					<tr>
						<td class="text-left"><h5>
								<strong>Date : </strong>${param.date}</h5></td>
						<td class="text-right"><h5>
								<strong>Ref No. : </strong>#${param.refNum}
							</h5></td>
					</tr>

				</table>
				<div class="panl panel-default">
					<div class="panel-body">
						<div class="text-left">
							<jspcore:if test="${param.to != null or param.to.length() != 0}">
							To,<br>${param.to}<br>${param.toAddress}<br>${param.toContact}
							</jspcore:if>

						</div>
						<div>
							<h4 class="text-center">
								<br> <strong><u>${param.subject}</u></strong>
							</h4>
							<br>
						</div>
						<div>${param.letterText}</div>

					</div>
				</div>
			</div>
		</div>
		<!-- / end client details section -->


		<div class="row" style="margin-bottom: 0px">
			<div class="col-xs-12">
				<hr
					style="border-style: solid; border-color: #000; border-width: medium;">
				<div class="text-center">
					<strong>Contact :</strong> ${merchant.mobile} <strong> |
						Email :</strong> ${merchant.email} <strong>| Web : </strong><u>www.ramfurniture.in</u>
				</div>
			</div>

		</div>
	</div>
	<script>
		function printChallan(buttonID) {

			$("#buttonDiv").hide();
			window.print();

			$("#buttonDiv").show();

		}
	</script>
</body>
</html>