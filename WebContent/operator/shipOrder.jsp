<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String basePath = request.getContextPath();
    String bPath = request.getScheme() + "://" + request.getServerName () + ":" + request.getServerPort () + basePath + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=bPath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Shipping Details</title>
<%@include file="../css/includecss.jsp"%>
</head>

<body>
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<%@ include file="../header.jsp"%>

	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.shiporder.label" /></h1>
		</div>

		<div class="card">
			<form class="form-horizontal" action="operator/ProcessOrder.do"
				method="post" role="form">

				<jspcore:set var="shipment" value="${sessionScope.shipment}"></jspcore:set>
				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h2><bean:message key="operator.shiporder.head1" /></h2>
				</div>
				<div class="card-body card-padding">
					<div class="form-group row">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.shiporder.shippingmedium" /> </label>
						<div class="col-sm-8">
							<label class="control-label"><strong>${shipment.medium}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label"><bean:message key="operator.shiporder.mediumname" /> </label>
						<div class="col-sm-8">
							<div class="fg-line">
								<label class="control-label"><strong>${shipment.mediumName}</strong></label>
							</div>

						</div>
					</div>
					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.shiporder.mediumno" /> </label>
						<div class="col-sm-8">
							<div class="fg-line">
								<label class="control-label"><strong>${shipment.mediumNumber}</strong></label>
							</div>

						</div>
					</div>
					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.shiporder.contactno" /> </label>
						<div class="col-sm-8">
							<div class="fg-line">
								<label class="control-label"><strong>${shipment.contact}</strong></label>
							</div>

						</div>
					</div>
					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.shiporder.shippingtime" /> </label>
						<div class="col-sm-8">
							<div class="fg-line">
								<label class="control-label"><strong>${shipment.time}</strong></label>
							</div>

						</div>
					</div>

					<div>
						<h4>Product Details :</h4>

						<div class="form-group">
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><bean:message key="operator.shiporder.isshipped" /></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><bean:message key="operator.processorder.prodname" /></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><bean:message key="operator.processorder.qty" /></strong></label>
							</div>
						</div>


						<div id="productDetails">
							<jspcore:forEach var="prod" items="${shipment.items}">
								<div class="form-group">
									<div class="col-sm-2 col-sm-offset-1">
										<label class="control-label"><input type="checkbox"
											disabled checked> </label>
									</div>
									<div class="col-sm-2 col-sm-offset-1">
										<label class="control-label">${prod.name}</label>
									</div>
									<div class="col-sm-2 col-sm-offset-1">
										<label class="control-label">${prod.quantity}</label>
									</div>
								</div>
							</jspcore:forEach>
						</div>

					</div>
				</div>
				<!-- card-body card-padding div 2-->


				<div class="modal fade" data-modal-color="blue" id="modalFirm"
					data-backdrop="static" data-keyboard="false" tabindex="-1"
					role="dialog" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Select Billing Firm :</h4>
							</div>
							<div class="modal-body" id="firmDiv"></div>
							<div class="modal-footer">
								<button type="button" id="selectFirmID" class="btn btn-link">Select</button>
								<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>

				<div class="form-group row">

					<button type="button" id="processPayment"
						class="col-sm-offset-1 col-sm-2 btn btn-primary">
						<i class="zmdi zmdi-balance-wallet" style="padding-right: 10px"></i> Process Payment
					</button>
					<button type="button" id="generateChallan"
						class="col-sm-offset-1 col-sm-3 btn btn-primary">
						<i class="zmdi zmdi-local-shipping" style="padding-right: 10px"></i> Generate Challan
					</button>
					<button type="button" id="sendShipment"
						class="col-sm-offset-1 col-sm-3 btn btn-primary">
						<i class="zmdi zmdi-phone-msg" style="padding-right: 10px"></i> SEND SMS/EMAIL CONFIRMATION
					</button>

				</div>
				<br> <br> <br>
				<div class="modal fade" data-modal-color="blue" id="modalMobile"
					data-backdrop="static" data-keyboard="false" tabindex="-1"
					role="dialog" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Send SMS/Email Confirmation</h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="form-group">
										<label for="inputName" class="col-sm-6 control-label"><bean:message key="operator.shiporder.modal.mobile" /></label>
										<div class="col-sm-12">
											<div class="fg-line">
												<input class="form-control" type="text" id="sendMobiles"
													value="${sessionScope.mobile}" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label for="inputName" class="col-sm-6 control-label"><bean:message key="operator.shiporder.modal.email" /></label>
										<div class="col-sm-12">
											<div class="fg-line">
												<input type="text" class="form-control" id="sendEmails"
													value="${sessionScope.email}" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" id="sendShipmentDetails"
									class="btn btn-link">Send</button>
								<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
			</form>

		</div>

	</div>
	<!-- card div --> <!-- container div --> </section> </section>

	<%@include file="../js/includejs.jsp"%>
	<script type="text/javascript" src="js/extra.js"></script>
</body>
</html>