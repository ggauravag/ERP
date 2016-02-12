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
<title>Pay for Order</title>
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
			<h1>
				<bean:message key="operator.payment.payorder.label" />
			</h1>
		</div>

		<div class="card">
			<form class="form-horizontal" action="operator/payment/PayOrder.do"
				method="post" id="payForm" role="form">

				<div class="card-header">
					<h2>
						<bean:message key="operator.payment.payorder.head1" />
					</h2>
				</div>
				<div class="card-body card-padding">
					<font color="red"><html:errors property="orderError" /></font>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label"><bean:message
								key="operator.payment.amountpaid.orderid" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputOrderID"
									placeholder="Enter Order ID">
							</div>
							<span class="zmdi zmdi-shopping-basket form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="row">
						<div class="form-input">
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message
									key="operator.payment.payorder.mobile" /></label>
							<div class="col-sm-6 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputMobile"
										placeholder="Enter Mobile">
								</div>
								<span class="zmdi zmdi-smartphone-android form-control-feedback"></span>
							</div>
						</div>

						<div class="form-input">
							<div class="col-sm-4 m-b-25">
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
						<label for="inputName" class="col-sm-2 control-label"><bean:message
								key="operator.payment.payorder.cusname" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputName"
									placeholder="Enter Name">
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="form-group">
						<div class="form-input">
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message
									key="operator.payment.payorder.orderdate" /> </label>
							<div class="col-sm-2 m-b-25">
								<div class="dtp-container dropdown fg-line open">
									<input type="text" name="fromDate"
										class="form-control date-picker" data-toggle="dropdown"
										aria-expanded="true" id="inputFromDate"
										placeholder="From Date">
								</div>
								<span class="zmdi zmdi-calendar form-control-feedback"></span>

							</div>
							<div class="col-sm-2 m-b-25">
								<label class="control-label">to</label>
							</div>
							<div class="col-sm-2 m-b-25">

								<div class="dtp-container dropdown fg-line open">
									<input type="text" name="toDate"
										class="form-control date-picker" data-toggle="dropdown"
										aria-expanded="true" id="inputToDate" placeholder="To Date">
								</div>
								<span class="zmdi zmdi-calendar form-control-feedback"></span>
							</div>
						</div>

					</div>
					<div id="fillOrderDetails"></div>
				</div>
				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h2>
						<bean:message key="operator.payment.payorder.head2" />
					</h2>
				</div>
				<div class="card-body card-padding">
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message
								key="operator.payment.payorder.cusname" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="selectName" readonly>
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label"><bean:message
								key="operator.payment.payorder.email" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" name="email" class="form-control"
									id="selectEmail" readonly>
							</div>
							<span class="zmdi zmdi-email form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message
								key="operator.payment.payorder.mobile" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" name="mobile" class="form-control"
									id="selectMobile" readonly>
							</div>
							<span class="zmdi zmdi-smartphone-android form-control-feedback"></span>
						</div>
					</div>

					<div id="orderDiv" class="form-input">
						<h4>Product Details :</h4>

						<div class="form-group">

							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5>
											<bean:message key="operator.payment.payorder.prodname" />
										</h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5>
											<bean:message key="operator.payment.payorder.rate" />
										</h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5>
											<bean:message key="operator.payment.payorder.qty" />
										</h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5>
											<bean:message key="operator.payment.payorder.total" />
										</h5></strong></label>
							</div>
						</div>
						<div id="productDetails"></div>
						<small class="help-block" id="error"><font color="red"><html:errors
									property="orderError" /></font> </small>
					</div>
				</div>
				<!-- card-body card-padding div 2-->

				<div class="card-header">
					<h4>
						<bean:message key="operator.payment.payorder.head3" />
					</h4>
				</div>
				<div class="card-body card-padding">
					<input type="hidden" name="itemJSON" id="itemJSON" />




					<div class="row">
						<div class="form-group">
							<label class="control-label col-sm-3 "><bean:message
									key="operator.vieworder.totalpayment" /> <font color="black"
								size="5px"><span class="WebRupee">&#x20B9; </span><label
									id="total"></label></font> </label> <label
								class="control-label col-sm-3 col-sm-offset-1"><bean:message
									key="operator.vieworder.paymentmade" /> <font color="green"
								size="5px"><span class="WebRupee">&#x20B9; </span><label
									id="paid"></label></font> </label> <label
								class="control-label col-sm-3 col-sm-offset-1"><bean:message
									key="operator.vieworder.due" /> <font color="red" size="5px"><span
									class="WebRupee">&#x20B9; </span><label id="due"></label></font> </label>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<th><bean:message key="operator.vieworder.table.txid" /></th>
								<th><bean:message key="operator.vieworder.table.datetime" /></th>
								<th><bean:message key="operator.vieworder.table.amount" /></th>
								<th><bean:message key="operator.vieworder.table.mode" /></th>
								<th><bean:message key="operator.vieworder.table.paidby" /></th>
								<th><bean:message key="operator.vieworder.table.orderid" /></th>
								<th><bean:message key="operator.vieworder.table.type" /></th>
								<th><bean:message key="operator.vieworder.table.receipt" /></th>
							</thead>
							<tbody id="paymentDetails">

							</tbody>
						</table>
						<br>
						<br>
						<br>
						<br>
					</div>

					<div class="row">
						<div id="mediumDiv" class="form-input">
							<label for="inputMedium" class="col-sm-3 control-label"><bean:message
									key="operator.payment.payorder.paymedium" /></label>
							<div class="select col-sm-4 m-b-25">
								<select name="modeType" class="form-control" id="selectMedium">
									<option>Select Payment Mode</option>
									<option>Cash</option>
									<option>Cheque</option>
									<option>Online/Bank Transfer</option>
									<option>PayUMoney Online Gateway</option>
								</select> <small class="help-block" id="error"><font color="red"><html:errors
											property="mediumError" /></font></small>
							</div>

						</div>

					</div>

					<div class="form-group">
						<label for="inputDate" class="col-sm-3 control-label"><bean:message
								key="operator.payment.payorder.paydatetime" /></label>
						<div class="col-sm-3">
							<div class="fg-line open">
								<input type="text" name="payTime"
									class="form-control date-time-picker" data-toggle="dropdown"
									aria-expanded="true" id="inputDate"
									placeholder="Payment Date & Time">
							</div>
							<span class="zmdi zmdi-calendar form-control-feedback"></span> <small
								class="help-block"><font color="red"><html:errors
										property="dateError" /></font></small>
						</div>
					</div>

					<div class="row">
						<div id="amountDiv" class="form-input">
							<label for="inputHouse" class="col-sm-3 control-label"><bean:message
									key="operator.payment.payorder.amount" /></label>
							<div class="col-sm-2 m-b-25">
								<div class="fg-line open">
									<input type="text" name="amount" id="inputAmount"
										class="form-control" placeholder="Order Amount">
								</div>
								<span class="zmdi zmdi-money form-control-feedback"></span>
								<small class="help-block" id="error"><font color="red"><html:errors
											property="amountError" /></font></small>
							</div>
						</div>
						
						<div id="discountDiv" class="form-input">
							<label for="inputHouse" class="col-sm-2 control-label"><bean:message
									key="operator.payment.discount" /></label>
							<div class="col-sm-3 m-b-25">
								<div class="fg-line open">
									<input type="text" value="0" name="discountAmount" id="inputAmount"
										class="form-control" placeholder="Discount Amount">
								</div>
								<span class="zmdi zmdi-money form-control-feedback"></span>
								<small class="help-block" id="error"><font color="red"><html:errors
											property="amountError" /></font></small>
							</div>
						</div>
						
					</div>

					<div class="row">
						<div id="descriptionDiv" class="form-input">
							<label for="inputHouse" class="col-sm-3 control-label"><bean:message
									key="operator.payment.payorder.desc" /></label>
							<div class="col-sm-4 m-b-25">
								<div class="fg-line open">
									<input type="text" name="description" id="inputDescription"
										class="form-control"
										placeholder="Ex : Chq. No. 45789 / 20-11-2015">
								</div>
								<span class="zmdi zmdi-file-text form-control-feedback"></span>
								<small class="help-block" id="error"><font color="red"><html:errors
											property="descError" /></font></small>
							</div>
						</div>
					</div>


					<div class="row">
						<div id="paidByDiv" class="form-input">
							<label for="inputHouse" class="col-sm-3 control-label"><bean:message
									key="operator.payment.payorder.payby" /></label>
							<div class="col-sm-4 m-b-25">
								<div class="fg-line open">
									<input type="text" name="paidBy" id="inputPaidBy"
										class="form-control" placeholder="Paid By Mr. XYZ">
								</div>
								<span class="zmdi zmdi-account form-control-feedback"></span>
								<small class="help-block" id="error"><font color="red"><html:errors
											property="paidByError" /></font></small>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<button type="submit"
							class="btn btn-primary btn-lg col-sm-4 col-sm-offset-4">Process
							Payment</button>
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
	<script src="vendors/moment/moment.min.js"></script>
	<script src="vendors/noUiSlider/jquery.nouislider.all.min.js"></script>
	<script src="vendors/input-mask/input-mask.min.js"></script>
	<script src="vendors/farbtastic/farbtastic.min.js"></script>
	<script src="vendors/summernote/summernote.min.js"></script>
	<script
		src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/fileinput/fileinput.min.js"></script>
	<script type="text/javascript" src="js/extra.js"></script>
</body>
</html>