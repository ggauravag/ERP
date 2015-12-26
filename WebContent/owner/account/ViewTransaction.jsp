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
<title>View Transactions</title>
<%@include file="../../css/includecss.jsp"%>
</head>

<body>
	<%@ include file="../../header.jsp"%>

	<input type="hidden" id="page" value="viewOrder">
	<section id="main"> 
	<%@ include file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1>
				<bean:message key="owner.viewTransaction.label" />
			</h1>
		</div>

		<div class="card">
			<form class="form-horizontal" method="post" id="payForm" role="form">

				<div class="card-header">
					<h2>
						<bean:message key="owner.viewTransaction.head1" />
					</h2>
				</div>
				<div class="card-body card-padding">
					<font color="red"><html:errors property="transactionError" /></font>
					<div id="idDiv" class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label"><bean:message
								key="owner.viewTransaction.id.label" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputTransactionID"
									placeholder="Enter Transaction ID">
							</div>
							<span class="zmdi zmdi-shopping-basket form-control-feedback"></span>
						</div>
						<small class="help-block" style="color: red"></small>
					</div>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="row">
						<div id="mobileDiv" class="form-input">
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message
									key="operator.processorder.mobile" /></label>
							<div class="col-sm-6 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputMobile"
										placeholder="Enter Mobile">
								</div>
								<span class="zmdi zmdi-smartphone-android form-control-feedback"></span>
							</div>
							<small class="help-block" style="color: red"></small>
							
						</div>

						<div class="form-input">
							<div class="col-sm-4 m-b-25">
								<div class="fg-line">
									<button type="button" id="searchTransaction"
										class="btn btn-primary btn-lg col-sm-6">Search</button>
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
								key="operator.processorder.cusname" /></label>
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
					
					<div id="dateDiv" class="form-group">
						<div class="form-input">
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message
									key="owner.viewTransaction.date.label" /> </label>
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
						<small class="help-block"></small>
						
					</div>
					
					<div id="fillTransactionDetails">
					
					</div>
				</div>
				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h2>
						<bean:message key="owner.viewTransaction.transactionLabel" />
					</h2>
				</div>

				<div class="card-body card-padding">
					<table class="table table-hover">
						<tr id="orderIdDiv" style="display:none;">
							<td><label class="control-label" >Order ID : </label></td>
							<td><strong></strong></td>	
						</tr>
						<tr id="nameDiv" style="display:none;">
							<td><label class="control-label" >Customer Name : </label></td>
							<td><strong></strong></td>
						</tr>
						<tr id="orderAmountDiv" style="display:none;">
							<td><label class="control-label" >Order Amount : </label></td>
							<td><strong></strong></td>
						</tr>
						<tr id="timeDiv" style="display:none;">
							<td><label class="control-label" >Order Time : </label></td>
							<td><strong></strong></td>
						</tr>
						<tr id="expIdDiv" style="display:none;">
							<td><label class="control-label" >Expenditure ID : </label></td>
							<td><strong></strong></td>
						</tr>
						<tr id="typeDiv" style="display:none;">
							<td><label class="control-label" >Expenditure Type : </label></td>
							<td><strong></strong></td>
						</tr>
						<tr id="detailDiv" style="display:none;">
							<td><label class="control-label" >Expenditure Details : </label></td>
							<td><strong></strong></td>
						</tr>
					</table>
				</div>
				
				<div class="card-header">
					<h4>
						<bean:message key="owner.viewTransaction.subLabel" />
					</h4>
				</div>
				
				<div class="card-body card-padding">
					<input type="hidden" name="itemJSON" id="itemJSON" />

					<div class="row">
						<div class="form-group">
							<label class="control-label col-sm-3 "><bean:message
									key="owner.viewTransaction.netPayment" /> <font color="black"
								size="5px"><span class="WebRupee">&#x20B9; </span><label
									id="total"></label></font> </label> <label
								class="control-label col-sm-3 col-sm-offset-1"><bean:message
									key="owner.viewTransaction.creditPayment" /> <font
								color="green" size="5px"><span class="WebRupee">&#x20B9;
								</span><label id="paid"></label></font> </label> <label
								class="control-label col-sm-3 col-sm-offset-1"><bean:message
									key="owner.viewTransaction.debitPayment" /> <font color="red"
								size="5px"><span class="WebRupee">&#x20B9; </span><label
									id="due"></label></font> </label>
						</div>
					</div>
					<!--  
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
					</div>
					
					<br>
				</div>
				<br /> <br />
			</form>
		</div>
		<br>
	</div>
	<!-- card div --> <!-- container div --> </section> </section>

	<%@include file="../../js/includejs.jsp"%>
	<script src="js/owner.js"></script>
	<script src="vendors/moment/moment.min.js"></script>
	<script src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
</body>
</html>