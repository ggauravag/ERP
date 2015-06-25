<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Process Order</title>
<%@include file="../css/includecss.jsp"%>
</head>

<%@ include file="../header.jsp"%>

<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

<section id="content">
<div class="container">
	<div class="block-header">
		<h1>Process Order</h1>
	</div>

	<div class="card">
		<form class="form-horizontal" action="../ProcessOrder.do" method="get" role="form">

			<div class="card-header">
				<h2>Enter Order ID or Name or Mobile and verify the details</h2>
			</div>
			<div class="card-body card-padding">
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
							<input type="text" class="form-control" id="selectName" disabled>
						</div>
						<span class="md md-person form-control-feedback"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-8">
						<div class="fg-line">
							<input type="email" class="form-control" id="selectEmail"
								disabled>
						</div>
						<span class="md md-email form-control-feedback"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="inputMobile" class="col-sm-2 control-label">Mobile
						Number</label>
					<div class="col-sm-8">
						<div class="fg-line">
							<input type="text" class="form-control" id="selectMobile"
								disabled>
						</div>
						<span class="md-phone-android form-control-feedback"></span>
					</div>
				</div>

				<div>
					<h4>Product Details :</h4>
					
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-1">
							<label class="control-label"><strong>Is Shipped
									? (Checked)</strong></label>
						</div>
						<div class="col-sm-2 col-sm-offset-1">
							<label class="control-label"><strong>Product
									Name</strong></label>
						</div>
						<div class="col-sm-2 col-sm-offset-1">
							<label class="control-label"><strong>Quantity</strong></label>
						</div>
					</div>
					<div id="productDetails"></div>
				</div>
			</div>
			<!-- card-body card-padding div 2-->

			<div class="card-header">
				<h2>Shipping Details</h2>
			</div>
			<div class="card-body card-padding">
				<div class="row">
					<div class="form-input">
						<label for="inputMedium" class="col-sm-2 control-label">Shipping
							Medium</label>
						<div class="select col-sm-4 m-b-25">
							<select name="mediumType" class="form-control"
								data-live-search="true">
								<option>Select Transport Medium</option>
								<option>Tempo / Owner Vehicle</option>
								<option>Buyer's Own</option>
								<option>Transport Company</option>
							</select>
						</div>
					</div>

					<div class="form-input">
						<div class="col-sm-3">
							<div class="fg-line">
								<input type="text" name="vehicleNum" class="form-control"
									placeholder="Enter Vehicle Number">
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-input">
						<label for="inputHouse" class="col-sm-2 control-label">Driver
							Details</label>
						<div class="col-sm-4 m-b-25">
							<div class="fg-line open">
								<input type="text" name="mediumName" class="form-control"
									placeholder="Driver Name">
							</div>
						</div>
					</div>
					<div class="form-input">
						<div class="col-sm-3 m-b-25">
							<div class="fg-line">
								<input type="text" name="contactMedium" class="form-control"
									id="inputAddress1" placeholder="Contact Number">
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<label for="inputDate" class="col-sm-2 control-label">Shipping
						Date/Time</label>
					<div class="col-sm-4">
						<div class="input-group form-group">
							<span class="input-group-addon"><i class="md md-event"></i></span>
							<div class="dtp-container dropdown fg-line open">
								<input type="text" name="shipDate"
									class="form-control date-time-picker" data-toggle="dropdown"
									aria-expanded="true" id="inputDate"
									placeholder="Shipping Date & Time">
							</div>
						</div>
					</div>
				</div>


			</div>

			<br /> <br /> <br />
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-lg col-sm-3">Process
						Order</button>
					<button type="reset" class="btn btn-default btn-lg col-sm-3"
						style="margin-left: 10%">Modify Order</button>
				</div>
			</div>
		</form>
	</div>

</div>
<!-- card div -->

</div>
<!-- container div --> </section> </section>

<%@include file="../js/includejs.jsp"%>
<script
	src="<%=request.getContextPath()%>/vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../js/extra.js"></script>
</body>
</html>