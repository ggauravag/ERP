<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dbt.forms.ExpenditureForm"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
    String bPath = request.getScheme() + "://" + request.getServerName () + ":" + request.getServerPort () + basePath + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=bPath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Expenditure</title>

<%@include file="/css/includecss.jsp"%>

</head>

<%
	Date date = new Date();
	SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
%>



<body>
	<%@ include file="../header.jsp"%>

	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.addexpenditure.label" /></h1>
		</div>

		<div class="card">
			<form action="AddExpenditure.do"
				method="post" id="expForm" name="expForm" class="form-horizontal"
				role="form">

				<div class="card-header">
					<h2><bean:message key="operator.addexpenditure.head1" /></h2>
				</div>

				<div class="card-body card-padding">
					<div id="typeDiv" class="form-group">
						<label for="selectType" class="col-sm-2 control-label"><bean:message key="operator.addexpenditure.type" /></label>
						<div class="col-sm-8">
							<select class="selectpicker" id="selectType"
								name="selectType" onchange="generate()" data-live-search="true">
								<option value="noSelect">Select Type</option>
								<option value="salary">Salary Expenditure</option>
								<option value="daily">Daily Expenditure</option>
								<option value="loan">Loan Expenditure</option>
								<option value="interest">Interest Expenditure</option>
								<option value="purchase">Purchase Expenditure</option>
							</select> <small id="error" class="help-block"></small>
						</div>
					</div>
					
					

					<div id="dynamic">
					</div>
					
					<div class="modal fade" data-modal-color="cyan"
							id="modalEmployee" data-backdrop="static" data-keyboard="false"
							tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title"><bean:message key="operator.addexpenditure.modal.selectemp" /></h4>
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
							<label for="inputAmount" class="col-sm-2 control-label"><bean:message key="operator.addexpenditure.amount" /></label>
							<div class="col-sm-3 m-b-25">
								<div class="fg-line">
									<input type="text" id="amount" name="amount"
										class="form-control" placeholder="Enter Amount">
								</div>
								<span class="zmdi zmdi-money form-control-feedback"></span> <small
									id="error" class="help-block"></small>
							</div>
						</div>

						<div class="form-input" id="dateDiv">
							<label for="inputDate" class="col-sm-2 control-label"><bean:message key="operator.addexpenditure.date" /></label>
							<div class="col-sm-3 m-b-25">
								<div>
									<div class="fg-line">
										<input type="text" id="date" name="date" value=<%=sd.format(date)%>
											class="form-control" disabled>
									</div>
									<span class="zmdi zmdi-calendar form-control-feedback"></span> <small
										id="error" class="help-block"></small>
								</div>
							</div>
						</div>
					</div>

					<div id="modeDiv" class="form-group">
						<label for="selectMode" class="col-sm-2 control-label"><bean:message key="operator.addexpenditure.expmode" /></label>
						<div class="col-sm-8">
							<select class="selectpicker" id="selectMode" data-live-search="true"
								name="selectMode">
								<option value="noSelect">Select Mode</option>
								<option value="Cheque">Cheque</option>
								<option value="Cash">Cash</option>
								<option value="Cash">Online Bank Transfer</option>
								<option value="Credit/Debit">Credit/Debit card</option>
							</select> <small id="error" class="help-block"></small>
						</div>
					</div>

					<div id="descDiv" class="form-group">
						<label for="inputDesc" class="col-sm-2 control-label"><bean:message key="operator.addexpenditure.desc" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" name="inputDesc"
									id="inputDesc" placeholder="Description for mode of payement">
							</div>
							<span class="zmdi zmdi-file-text form-control-feedback"></span> <small
								id="error" class="help-block"> </small>
						</div>
					</div>

					<div id="paidDiv" class="form-group">
						<label for="inputPaid" class="col-sm-2 control-label"><bean:message key="operator.addexpenditure.paid" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" name="inputPaid"
									id="inputPaid" placeholder="Name of the person">
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span> <small
								id="error" class="help-block"> </small>
						</div>
					</div>

					<br /> <br /> <br />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary btn-lg col-sm-4" id="addExp">Add
								Expenditure</button>
							<button type="reset" class="btn btn-warning btn-lg col-sm-4"
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
							swal("Error Occurred !", "Unable to add Expenditure details, try again.", "error");
						</script>
					</jspcore:if>	
				</jspcore:otherwise>	
			</jspcore:choose>
			<%session.removeAttribute("expStatus"); %>

</body>
</html>