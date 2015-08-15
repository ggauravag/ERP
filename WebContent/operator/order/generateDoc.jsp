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
<title>Generate Documents</title>
<%@include file="../../css/includecss.jsp"%>

</head>

<body>

	<%@ include file="../../header.jsp"%>

	<input type="hidden" id="page" value="viewOrder">
	<section id="main"> <%@ include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1>Generate Documents</h1>
		</div>

		<div class="card">
			<form class="form-horizontal" action="PrintOrder.do"
				method="post" id="printDocForm" role="form">

				<div class="card-header">
					<h2>Enter Order ID or Name or Mobile and verify the details</h2>
				</div>
				<div class="card-body card-padding">
					<font color="red"><html:errors property="orderError" /></font>
					<div class="form-group">
						<label for="inputOrderID" class="col-sm-2 control-label">Order
							ID</label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputOrderID"
									placeholder="Enter Order ID">
							</div>
							<span class="md-shopping-basket form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="row">
						<div class="form-input">
							<label for="inputMobile" class="col-sm-2 control-label">Mobile
								Number</label>
							<div class="col-sm-6">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputMobile"
										placeholder="Enter Mobile">
								</div>
								<span class="md-phone-android form-control-feedback"></span>
							</div>
						</div>

						<div class="form-input">
							<div class="col-sm-4">
								<div class="fg-line">
									<button type="button" id="searchOrderBtn"
										class="btn btn-primary btn-lg col-sm-6">Search Order</button>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label">Customer
							Name</label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputName"
									placeholder="Enter Name">
							</div>
							<span class="md md-person form-control-feedback"></span>
						</div>
					</div>
					<div id="fillOrderDetails"></div>
				</div>
				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h2>Customer Details</h2>
				</div>

				<div class="card-body card-padding">
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label">Customer
							Name</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="selectName" readonly>
							</div>
							<span class="md md-person form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" name="email" class="form-control"
									id="selectEmail" readonly>
							</div>
							<span class="md md-email form-control-feedback"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label">Mobile
							Number</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" name="mobile" class="form-control"
									id="selectMobile" readonly>
							</div>
							<span class="md-phone-android form-control-feedback"></span>
						</div>
					</div>

					<div id="orderDiv" class="form-input">
						<h4>Product Details :</h4>

						<div class="form-group">
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5>Product
											Name</h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5>Rate</h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5>Quantity</h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5>Total</h5></strong></label>
							</div>
						</div>
						<div id="productDetails"></div>
						<small class="help-block" id="error"><font color="red"><html:errors
									property="orderError" /></font> </small>
					</div>
					<div id="orderDiv" class="form-input">
						<h4>Shipment Details :</h4>
						<div class="panel-group" role="tablist" id="panelDiv"
							aria-multiselectable="true"></div>
					</div>
				</div>
				<!-- card-body card-padding div 2-->
				<input type="hidden" name="print" id="printType">
				<div class="card-header">
					<h4>Payment Details</h4>
				</div>
				<div class="card-body card-padding">
					<input type="hidden" name="itemJSON" id="itemJSON" />

					<div class="row">
						<div class="form-group">
							<label class="control-label col-sm-3 ">Total Payment : <font
								color="black" size="5px"><span class="WebRupee">&#x20B9;
								</span><label id="total"></label></font>
							</label> <label class="control-label col-sm-3 col-sm-offset-1">Payment
								Made : <font color="green" size="5px"><span
									class="WebRupee">&#x20B9; </span><label id="paid"></label></font>
							</label> <label class="control-label col-sm-3 col-sm-offset-1">Total
								Due : <font color="red" size="5px"><span class="WebRupee">&#x20B9;
								</span><label id="due"></label></font>
							</label>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<th>Txn ID</th>
								<th>Date/Time</th>
								<th>Amount</th>
								<th>Mode</th>
								<th>Paid By</th>
								<th>Order ID</th>
								<th>Type</th>
								<th>Receipt</th>
							</thead>
							<tbody id="paymentDetails">

							</tbody>
						</table>
					</div>
					<br>
					<div class="card-header">
						<h4>Choose Firm Type</h4>
					</div>
					<div class="row">
						<div class="radio m-b-15 radio-inline col-sm-5">
							<label> <input type="radio" name="vatApplicable"
								value="false" checked> <i class="input-helper"></i>
								Composition
							</label>
						</div>
						<div class="radio m-b-15 radio-inline col-sm-5">
							<label> <input type="radio" name="vatApplicable"
								value="true"> <i class="input-helper"></i> VAT
								Applicable
							</label>
						</div>
					</div>
					<div class="row">
						<label for="inputVatPercent" class="col-sm-2 control-label">Billing
							Date</label>
						<div class="col-sm-6">
							<div class="input-group form-group">
								<span class="input-group-addon"><i class="md md-event"></i></span>
								<div class="dtp-container dropdown fg-line">
									<input type='text' name="billDate"
										class="form-control date-picker" data-toggle="dropdown"
										placeholder="Click here...">
								</div>
							</div>
							<span class="md-shopping-basket form-control-feedback"></span>
						</div>
					</div>
					<br>
					<div class="form-group" id="vatDiv" style="display: none">
						<div class="row">
							<label for="inputVatPercent" class="col-sm-2 control-label">VAT
								Percent</label>
							<div class="col-sm-6">
								<div class="fg-line">
									<input type="text" class="form-control" name="vatPercent"
										id="inputVatPercent" value="14.5" placeholder="Ex : 14.5">
								</div>
								<span class="md-shopping-basket form-control-feedback"></span>
							</div>
						</div>

					</div>
					
					<div class="row">
						<label for="sendInvoiceOnEmail" class="col-sm-2 control-label">Send Invoice Email : </label>
						<div class="col-sm-6">
							<div class="radio m-b-15 radio-inline col-sm-5">
							<label> <input type="radio" name="sendInvoiceOnEmail" id="sendInvoiceOnEmail"
								value="true"> <i class="input-helper"></i> Yes
							</label>
						</div>
							
						</div>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" id="generateInvoice"
							class="btn btn-primary btn-lg col-sm-4">Generate Invoice</button>
						<button type="button" id="generateOrderConfirm"
							class="btn btn-primary btn-lg col-sm-5 col-sm-offset-1">Generate
							Order Confirmation</button>
					</div>
				</div>
				<br>
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
			</form>
		</div>
		<br>
	</div>
	<!-- card div --> <!-- container div --> </section> </section>

	<%@include file="../../js/includejs.jsp"%>
	<script
		src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/moment/moment.min.js"></script>
	<script src="vendors/noUiSlider/jquery.nouislider.all.min.js"></script>
	<script src="vendors/input-mask/input-mask.min.js"></script>
	<script src="vendors/farbtastic/farbtastic.min.js"></script>
	<script src="vendors/summernote/summernote.min.js"></script>
	<script src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/fileinput/fileinput.min.js"></script>
	<script type="text/javascript" src="js/extra.js"></script>
</body>
</html>