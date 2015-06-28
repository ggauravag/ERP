<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<% String basePath = request.getContextPath(); %>
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
		<h1>Process Order</h1>
	</div>

	<div class="card">
		<form class="form-horizontal" action="../ProcessOrder.do"
			method="post" role="form">

			<div class="card-header">
				<h2>Enter Order ID or Name or Mobile and verify the details</h2>
			</div>
			<div class="card-body card-padding">
				<font color="red"><html:errors property="orderError" /></font>
				<div class="form-group">
					<label for="inputOrder" class="col-sm-2 control-label">Order
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
						<div class="col-sm-6 m-b-25">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputMobile"
									placeholder="Enter Mobile">
							</div>
							<span class="md-phone-android form-control-feedback"></span>
						</div>
					</div>

					<div class="form-input">
						<div class="col-sm-4 m-b-25">
							<div class="fg-line">
								<button type="button" id="searchOrderButton"
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

				<div>
					<h4>Product Details :</h4>

					<div class="form-group">
						
						<div class="col-sm-2 col-sm-offset-1">
							<label class="control-label"><strong>Product
									Name</strong></label>
						</div>
						<div class="col-sm-2 col-sm-offset-1">
							<label class="control-label"><strong>Rate</strong></label>
						</div>
						<div class="col-sm-2 col-sm-offset-1">
							<label class="control-label"><strong>Quantity</strong></label>
						</div>
						<div class="col-sm-2 col-sm-offset-1">
							<label class="control-label"><strong>Amount</strong></label>
						</div>
					</div>
					<div id="productDetails"></div>
					<font color="red"><html:errors property="productError" /></font>
				</div>
			</div>
			<!-- card-body card-padding div 2-->

			<div class="card-header">
				<h4>Payment Details</h4>
			</div>
			<div class="card-body card-padding">
				<input type="hidden" name="itemJSON" id="itemJSON" />
				<div class="row">
					<div class="form-input">
						<label for="inputMedium" class="col-sm-3 control-label">Payment
							Medium :</label>
						<div class="select col-sm-4 m-b-25">
							<select name="modeType" class="form-control"
								data-live-search="true">
								<option>Select Payment Mode</option>
								<option>Cash</option>
								<option>Cheque</option>
								<option>PayUMoney Online Gateway</option>
							</select>
						</div>
						<small class="help-block"><font color="red"><html:errors
									property="mediumError" /></font></small>
					</div>
				</div>

				<div class="row">
					<div class="form-input">
						<label for="inputHouse" class="col-sm-3 control-label">Amount :</label>
						<div class="col-sm-4 m-b-25">
							<div class="fg-line open">
								<input type="text" name="amount" class="form-control"
									placeholder="Order Amount">
							</div>
							<small class="help-block"><font color="red"><html:errors
										property="dateError" /></font></small>
						</div>
					</div>
					
				</div>
				

				<div class="row">
					<div class="form-input">
						<label for="inputHouse" class="col-sm-3 control-label">Paid By / Received Through :</label>
						<div class="col-sm-4 m-b-25">
							<div class="fg-line open">
								<input type="text" name="amount" class="form-control"
									placeholder="Order Amount">
							</div>
							<small class="help-block"><font color="red"><html:errors
										property="dateError" /></font></small>
						</div>
					</div>
					
				</div>


			</div>

			<br /> <br /> <br />
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-lg col-sm-3">Pay For Order</button>

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