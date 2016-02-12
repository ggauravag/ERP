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
<title>Process Order</title>
<%@include file="../../css/includecss.jsp"%>
</head>

<body>
	<%@ include file="../../header.jsp"%>
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<section id="main"> <%@ include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.payment.amountpaid.label" /></h1>
		</div>

		<div class="card">
			<form class="form-horizontal" method="post" id="payForm" role="form">

				<div class="card-header">
					<h2><bean:message key="operator.payment.amountpaid.head1" /></h2>
				</div>
				<div class="card-body card-padding">

					<div class="form-group">
						<label class="col-sm-3 control-label"><bean:message key="operator.payment.amountpaid.txid" /> </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.id}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><bean:message key="operator.payment.amountpaid.txdatetime" /> </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.datetime}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><bean:message key="operator.payment.amountpaid.orderid" /> </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.orderId}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><bean:message key="operator.payment.amountpaid.amountfig" /> </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.amount}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><bean:message key="operator.payment.amountpaid.amountword" />
						</label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.getAmountInWords()}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><bean:message key="operator.payment.amountpaid.discount " /> </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${discount}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><bean:message key="operator.payment.amountpaid.paymode" /> </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.mode}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><bean:message key="operator.payment.amountpaid.desc" /> </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.paidBy}</strong></label>
						</div>
					</div>


				</div>

				<div class="form-group">
					<div class="row">
						<button type="button" id="generateReceipt"
							class="col-sm-offset-2 col-sm-3 btn btn-primary">
							<i class="zmdi zmdi-receipt" style="padding-left: 10px"></i>Generate Receipt
						</button>
						<button type="button" id="sendReceipt"
							class="col-sm-offset-2 col-sm-3 btn btn-primary">
							<i class="zmdi zmdi-phone-msg" style="padding-left: 10px"></i>SEND SMS/EMAIL RECEIPT
						</button>
					</div>
				</div>
				<!-- card-body card-padding div 1-->

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
										<label for="inputName" class="col-sm-6 control-label"><bean:message key="operator.payment.amountpaid.mobile" /></label>
										<div class="col-sm-12">
											<div class="fg-line">
												<input class="form-control" type="text" id="sendMobiles"
													value="${order.customer.mobile}" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label for="inputName" class="col-sm-6 control-label"><bean:message key="operator.payment.amountpaid.email" /></label>
										<div class="col-sm-12">
											<div class="fg-line">
												<input type="text" class="form-control" id="sendEmails"
													value="${order.customer.email}" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" id="sendReceiptDetails"
									class="btn btn-link">Send</button>
								<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>





					<br>
			</form>
		</div>
		<br>
	</div>
	<!-- card div --> <!-- container div --> </section> </section>

	<%@include file="../../js/includejs.jsp"%>
	<script
		src="<%=basePath%>/vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="../../js/extra.js"></script>
</body>
</html>