<%@page import="com.dbt.forms.ExpenditureForm"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Expenditure</title>

<%@include file="/css/includecss.jsp"%>

</head>

<%
	Date date = new Date();
%>



<body>
	<%@ include file="../header.jsp"%>

	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1>Add Expenditure</h1>
		</div>

		<div class="card">
			<form action="<%=request.getContextPath()%>/AddExpenditure.do"
				method="post" id="expForm" name="expForm" class="form-horizontal"
				role="form">

				<div class="card-header">
					<h2>Add Expenditure Details</h2>
				</div>

				<div class="card-body card-padding">
					<div id="typeDiv" class="form-group">
						<label for="selectType" class="col-sm-2 control-label">Expenditure
							Type</label>
						<div class="col-sm-8">
							<select class="form-control selectpicker" id="selectType"
								name="selectType" onchange="generate()">
								<option value="noSelect">Select Type</option>
								<option value="salary">Salary Expenditure</option>
								<option value="daily">Daily Expenditure</option>
								<option value="loan">Loan Expenditure</option>
								<option value="interest">Interest Expenditure</option>
							</select> <small id="error" class="help-block"></small>
						</div>
					</div>

					<div id="dynamic">
						<!-- <div class="form-input" id="dynamic1">
							<label for="dynamic1" class="col-sm-2 control-label">Employee Name</label>
							<div class="col-sm-3 m-b-25">
								<div class="fg-line">
									<input type="text" id="dynamic1" name="dynamic1" class="form-control"
										placeholder="Enter Employee Name">
								</div>
								<span class="md md-person form-control-feedback"></span> <small
									id="error" class="help-block"></small>
							</div>
						</div>

						<div class="form-input" id="dynamic2">
							<label for="dynamic2" class="col-sm-2 control-label">Received By</label>
							<div class="col-sm-3 m-b-25">
								<div>
									<div class="fg-line">
										<input type="text" id="dynamic2" name="dynamic2" class="form-control"
											placeholder="Received By (person name)">
									</div>
									<span class=" md md-person form-control-feedback"></span> <small
										id="error" class="help-block"></small>
								</div>
							</div>
						</div> -->
						<!-- <div class="form-group" id="dynamic1">
							<label for="dynamic1" class="col-sm-2 control-label">Employee Details</label>
							<div class="col-sm-8 m-b-25">
								<div class="fg-line">
									<input type="text" id="dynamic1" name="dynamic1" class="form-control"
										placeholder="Enter Details">
								</div>
								<span class="md md-person form-control-feedback"></span> <small
									id="error" class="help-block"></small>
							</div>
						</div> -->

						<!-- <div class="form-group">
							<label for="dynamic1" class="col-sm-2 control-label">Loan
								Detail</label>
							<div class="col-sm-6">
								<select class="form-control selectpicker" id="dynamic1"
									name="dynamic1">
									<option>Select Type</option>
									<option value="salary">Salary Expenditure</option>
									<option value="daily">Daily Expenditure</option>
								</select> 
								
									<small id="error" class="help-block"></small>
								
							</div>
						</div> -->

					</div>
					
					<div class="modal fade" data-modal-color="cyan"
							id="modalEmployee" data-backdrop="static" data-keyboard="false"
							tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">Select Employee</h4>
									</div>
									<div class="modal-body">
										<p>Here's the list of all employees :</p>

										<div id="employeeList"></div>

									</div>
									<div class="modal-footer">
										<button type="button" id="selectEmployeeBtn"
											class="btn btn-link">Select Employee</button>
										<button type="button" class="btn btn-link"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
						
						<input type="hidden" id="empId" name="empId">

					<hr>
					<br>

					<div class="row">
						<div class="form-input" id="amountDiv">
							<label for="inputAmount" class="col-sm-2 control-label">Amount</label>
							<div class="col-sm-3 m-b-25">
								<div class="fg-line">
									<input type="text" id="amount" name="amount"
										class="form-control" placeholder="Enter Amount">
								</div>
								<span class="md-attach-money form-control-feedback"></span> <small
									id="error" class="help-block"></small>
							</div>
						</div>

						<div class="form-input" id="dateDiv">
							<label for="inputDate" class="col-sm-2 control-label">Date/Time</label>
							<div class="col-sm-3 m-b-25">
								<div>
									<div class="fg-line">
										<input type="text" id="date" name="date" value=<%=date%>
											class="form-control">
									</div>
									<span class=" md-access-time form-control-feedback"></span> <small
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
								<option value="noSelect">Select Mode</option>
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
							<button type="submit" class="btn btn-primary btn-lg col-sm-3" id="addExp">Add
								Expenditure</button>
							<button type="reset" class="btn btn-default btn-lg col-sm-2"
								style="margin-left: 10%">Reset</button>
						</div>
					</div>
				</div>
				<!-- card-body card-padding div -->
			</form>
		</div>
		<!-- card div -->

	</div>
	<!-- container div --> </section> </section>


	<!-- Javascript Libraries -->

	<%@include file="/js/includejs.jsp"%>
		<jspcore:set var="expStatus" value="${sessionScope.expStatus}"></jspcore:set>
			<jspcore:choose>
				<jspcore:when test="${expStatus == 'Success'}">
					<script>
						swal("Successful !", "Expenditure details added successfully", "success");
					</script>
				</jspcore:when>
				<jspcore:otherwise>
					<jspcore:if test="${expStatus == 'Failure'}">
						<script>
							swal("Error Occurred !", "Unable to add Expenditure details, try again.", "failure");
						</script>
					</jspcore:if>	
				</jspcore:otherwise>	
			</jspcore:choose>
			<%session.removeAttribute("expStatus"); %>

</body>
</html>