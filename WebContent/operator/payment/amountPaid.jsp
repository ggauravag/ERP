<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
			<h1>Payment Receipt</h1>
		</div>

		<div class="card">
			<form class="form-horizontal" method="post" id="payForm" role="form">

				<div class="card-header">
					<h2>Payment Details</h2>
				</div>
				<div class="card-body card-padding">

					<div class="form-group">
						<label class="col-sm-3 control-label">Transaction ID : </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.id}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Transaction Date &
							Time : </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.datetime}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Order ID : </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.orderId}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Amount (In Figures)
							: </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.amount}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Amount (In Words) :
						</label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.getAmountInWords()}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Payment Mode : </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.mode}</strong></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Description/Paid By
							: </label>
						<div class="col-sm-5">
							<label class="control-label"><strong>${payment.paidBy}</strong></label>
						</div>
					</div>


				</div>

				<div class="form-group">
					<div class="col-sm-9 col-sm-offset-1">
						<button type="button" id="generateReceipt"
							class="col-sm-offset-1 btn btn-primary waves-effect waves-button waves-float">
							<i class="md md-receipt"></i> Generate Receipt
						</button>
						<button type="button" id="sendReceipt"
							class="col-sm-offset-1 btn btn-primary waves-effect waves-button waves-float">
							<i class="md md-message"></i> SEND SMS/EMAIL RECEIPT
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
										<label for="inputName" class="col-sm-6 control-label">Customer
											Mobile (if Multiple,then CSV) :</label>
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
										<label for="inputName" class="col-sm-6 control-label">Customer
											Email (if Multiple,then CSV) :</label>
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