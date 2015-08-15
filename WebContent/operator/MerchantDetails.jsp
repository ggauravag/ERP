<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="com.dbt.data.Stock"%>
<%
	String basePath = request.getContextPath();
    String bPath = request.getScheme() + "://" + request.getServerName () + ":" + request.getServerPort () + basePath + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=bPath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Stock : Merchant Details</title>

<%@include file="../css/includecss.jsp"%>

</head>

<body>
	<%@ include file="../header.jsp"%>

	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1>Merchant Details</h1>
		</div>

		<div class="card">
			<form action="MerchantDetails.do" method="post" id="merchantForm" name="merchantForm"
				class="form-horizontal" role="form">

				<div class="card-header">
					<h2>Add Merchant details of the new stock</h2>
				</div>

				<div class="card-body card-padding">

					<div id="merchantDiv" class="row form-group">
						<label for="selectMerchant" class="col-sm-2 control-label">Merchant name</label>
						<div class="col-sm-6">
							<jsp:useBean id="list" class="com.dbt.dao.StockDAO" scope="page"></jsp:useBean>
							<select class="form-control selectpicker" id="selectMerchant"
								name="selectMerchant" data-live-search="true">
								<option value="">Select Category</option>

								<jspcore:forEach var="merchant" items="${list.allMerchants}">
									<option value="${merchant.id}">${merchant.name}</option>
								</jspcore:forEach>
							</select> <small id="error" class="help-block"></small>
						</div>

						<button class="btn btn-gray waves-effect waves-button waves-float" type="button"
							id="addMerchantButton">
							<i class="md md-add"></i> Add New Merchant
						</button>
					</div>


					<div class="modal fade" data-modal-color="bluegray" id="modalMerchant"
						data-backdrop="static" data-keyboard="false" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Add Merchant</h4>
								</div>
								<div class="modal-body">
									<p>Enter new merchant details to be added :</p>
									<div class="form-input" id="nameDiv">
										<input type="text" class="form-control" name="merchantName"
											id="merchantName" placeholder="Enter full merchant name"> <small
											id="error" class="help-block"> </small>
									</div>
									<div class="form-input" id="mobileDiv">
										<input type="text" class="form-control" name="mobile"
											id="mobile" placeholder="Enter mobile no">
										<small id="error" class="help-block"> </small>
									</div>
									<div class="form-input" id="emailDiv">
										<input type="text" class="form-control" name="email"
											id="email" placeholder="Enter email id"> <small
											id="error" class="help-block"> </small>
									</div>
									<div class="form-input" id="tinDiv">
										<input type="text" class="form-control" name="tin"
											id="tin" placeholder="Enter Tin no"> <small
											id="error" class="help-block"> </small>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" id="addMerchant" class="btn btn-link">Add
										Merchant</button>
									<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- card-body card-padding div1 -->


				<div class="card-header">
					<h2>Add Payment details</h2>
				</div>

				<div class="card-body card-padding">
					<div class="row">
						<div class="form-input" id="amountDiv">
							<label for="inputAmount" class="col-sm-2 control-label">Amount</label>
							<div class="col-sm-3 m-b-25">
								<div class="fg-line">
									<input type="text" id="amount" name="amount" value="${newStockDetails.amount}"
										class="form-control" placeholder="Enter Amount">
								</div>
								<span class="md-attach-money form-control-feedback"></span> <small
									id="error" class="help-block"></small>
							</div>
						</div>

						<div class="form-input" id="currentDiv">
							<label for="currentPayment" class="col-sm-2 control-label">Current payment</label>
							<div class="col-sm-3 m-b-25">
								<div>
									<div class="fg-line">
										<input type="text" id="currentPayment" name="currentPayment"
											class="form-control" placeholder="Enter current payment">
									</div>
									<span class="md-attach-money form-control-feedback"></span> <small
										id="error" class="help-block"></small>
								</div>
							</div>
						</div>
					</div>

					<div id="modeDiv" class="form-group">
						<label for="selectMode" class="col-sm-2 control-label">Expenditure
							Mode</label>
						<div class="col-sm-8">
							<select class="form-control selectpicker" id="selectMode"
								name="selectMode">
								<option value="">Select Mode</option>
								<option value="Cheque">Cheque</option>
								<option value="Cash">Cash</option>
								<option value="Credit/Debit">Credit/Debit card</option>
							</select> <small id="error" class="help-block"></small>
						</div>
					</div>

					<div id="descDiv" class="form-group">
						<label for="inputDesc" class="col-sm-2 control-label">Description</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" name="inputDesc"
									id="inputDesc" placeholder="Description for mode of payement">
							</div>
							<span class="md-description form-control-feedback"></span> <small
								id="error" class="help-block"> </small>
						</div>
					</div>

					<div id="paidDiv" class="form-group">
						<label for="inputPaid" class="col-sm-2 control-label">Paid
							by/to</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" name="inputPaid"
									id="inputPaid" placeholder="Name of the person">
							</div>
							<span class="md md-person form-control-feedback"></span> <small
								id="error" class="help-block"> </small>
						</div>
					</div>

					<br /> <br /> <br />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary btn-lg col-sm-3">Add Stock</button>
							<button type="reset" class="btn btn-default btn-lg col-sm-2"
								style="margin-left: 10%">Reset</button>
						</div>
					</div>
				</div>
				<!-- card-body card-padding div2 -->
			</form>
		</div>
		<!-- card div -->

	</div>
	<!-- container div --> </section> </section>
	
	<!-- Javascript Libraries -->

	<%@include file="../js/includejs.jsp"%>
	
</body>
</html>