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
<%@include file="../css/includecss.jsp"%>
</head>
<body>

	<input type="hidden" id="basePath" value="<%=basePath%>">
	<%@ include file="../header.jsp"%>

	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.processorder.label" /></h1>
		</div>

		<div class="card">
			<form class="form-horizontal" action="operator/ProcessOrder.do"
				method="post" role="form">

				<div class="card-header">
					<h2><bean:message key="operator.processorder.head1" /></h2>
				</div>
				<div class="card-body card-padding">
					<font color="red"><html:errors property="orderError" /></font>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label"><bean:message key="operator.processorder.orderid" /></label>
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
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.processorder.mobile" /></label>
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
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.processorder.cusname" /></label>
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
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.processorder.orderdate" /> </label>
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
					<h2><bean:message key="operator.processorder.head2" /></h2>
				</div>
				<div class="card-body card-padding">
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.processorder.cusname" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="selectName" readonly>
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label"><bean:message key="operator.processorder.email" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" name="email" class="form-control"
									id="selectEmail" readonly>
							</div>
							<span class="zmdi zmdi-email form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.processorder.mobile" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" name="mobile" class="form-control"
									id="selectMobile" readonly>
							</div>
							<span class="zmdi zmdi-smartphone-android form-control-feedback"></span>
						</div>
					</div>

					<div>
						<h4>Product Details :</h4>

						<div class="form-group">
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><bean:message key="operator.processorder.tobeshipped" /> </strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><bean:message key="operator.processorder.prodname" /></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><bean:message key="operator.processorder.qty" /></strong></label>
							</div>
						</div>
						<div id="productDetails"></div>
						<font color="red"><html:errors property="productError" /></font>
					</div>
					<div>
						<h4>Discount Details :</h4>

						<div class="form-group">
							<div class="col-sm-3">
								<label for="discount" class="control-label"><bean:message key="operator.processorder.discount" /></label>
							</div>
							<div class="col-sm-3" style="margin-left: -75px">
								<div class="fg-line">
									<input type="text" name="discount" id="discount" class="form-control"
										placeholder="Enter Any Discount (If Applicable)">
										
								</div>
								<span class="input-group-addon"><i class="zmdi zmdi-money"></i></span>
							</div>
						</div>
					</div>
				</div>
				<!-- card-body card-padding div 2-->

				<div class="card-header">
					<h2><bean:message key="operator.processorder.head3" /></h2>
				</div>
				<div class="card-body card-padding">
					<input type="hidden" name="itemJSON" id="itemJSON" />
					<div class="row">
						<div class="form-input">
							<label for="inputMedium" class="col-sm-2 control-label"><bean:message key="operator.processorder.shippingmedium" /></label>
							<div class="select col-sm-4 m-b-25">
								<select name="mediumType" class="form-control"
									data-live-search="true">
									<option>Select Transport Medium</option>
									<option>Tempo / Owner Vehicle</option>
									<option>Buyer's Own</option>
									<option>Transport/Courier Company</option>
								</select>
							</div>
							<small class="help-block"><font color="red"><html:errors
										property="mediumError" /></font></small>
						</div>

						<div class="form-input">
							<div class="col-sm-3">
								<div class="fg-line">
									<input type="text" name="vehicleNum" class="form-control"
										placeholder="Vehicle/Shipment Number">
								</div>
								<span class="input-group-addon">
								<i class="zmdi zmdi-local-shipping"></i></span>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-input">
							<label for="inputHouse" class="col-sm-2 control-label"><bean:message key="operator.processorder.driverdetails" /></label>
							<div class="col-sm-4 m-b-25">
								<div class="fg-line open">
									<input type="text" name="mediumName" class="form-control"
										placeholder="Driver/Courier Company Name">
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
						<label for="inputDate" class="col-sm-2 control-label"><bean:message key="operator.processorder.shippingdatetime" /></label>
						<div class="col-sm-3">
							<div class="input-group form-group">
								
								<div class="dtp-container dropdown fg-line open">
									<input type="text" name="shipDate"
										class="form-control date-time-picker" data-toggle="dropdown"
										aria-expanded="true" id="inputDate"
										placeholder="Shipping Date & Time">
								</div>
								<span class="input-group-addon"><i class="zmdi zmdi-calendar"></i></span>
								<small class="help-block"><font color="red"><html:errors
											property="dateError" /></font></small>
							</div>
						</div>
					</div>
					
					<div class="row">
						<label for="inputCharge" class="col-sm-2 control-label"><bean:message key="operator.processorder.shippingcharges" /></label>
						<div class="col-sm-2">
							<div class="input-group form-group">
								
								<div class="dtp-container dropdown fg-line open">
									<input type="text" name="shippingCharge"
										class="form-control" id="inputCharge"
										placeholder="Ex. 1000">
								</div>
								<span class="input-group-addon"><i class="zmdi zmdi-money"></i></span>
								<small class="help-block"><font color="red"><html:errors
											property="dateError" /></font></small>
							</div>
						</div>
					</div>


				</div>

				<br /> <br /> <br />
				<div class="form-group">
					<div class="row">
						<button type="submit"
							class="btn btn-primary btn-lg col-sm-6 col-sm-offset-3">Process
							Order</button>

					</div>
				</div>
				<br>

			</form>
		</div>
		<br>
	</div>
	<!-- card div --> <!-- container div --> </section> </section>

	<%@include file="../js/includejs.jsp"%>
		<script src="vendors/fileinput/fileinput.min.js"></script>
	<script type="text/javascript" src="js/extra.js"></script>
</body>
</html>