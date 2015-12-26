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
<title>Manage Customer</title>

<%@include file="../css/includecss.jsp"%>
</head>

<body>

	<%@ include file="../header.jsp"%>

	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.managecustomer.label" /></h1>
		</div>

		<form class="form-horizontal" action="ManageCustomer.do" method="post"
			id="manageCusForm" name="manageCusForm" role="form">

			<div class="card">
				<div class="card-header">
					<h2><bean:message key="operator.managecustomer.head1" /></h2>
				</div>
				<div class="card-body card-padding">
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.managecustomer.custname" /></label>
						<div class="col-sm-5">
							<div class="fg-line">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="Enter Name">
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="or" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>

					<div class="row">
						<div class="form-input">
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.managecustomer.mobile" /></label>
							<div class="col-sm-5 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="mobile"
										name="mobile" placeholder="Enter Mobile">
								</div>
								<span class="zmdi zmdi-smartphone-android form-control-feedback"></span>
							</div>
						</div>

						<div class="form-input">
							<div class="col-sm-5 m-b-25">
								<div class="fg-line">
									<button type="button" id="searchCustomerButton"
										class="btn btn-primary btn-lg col-sm-6">Search
										Customer</button>
								</div>
							</div>
						</div>
					</div>

					<div id="fillCustomerDetails"></div>
				</div>
				<!-- card-body card-padding2 -->

				<div class="card-header">
					<h2><bean:message key="operator.managecustomer.head2" /></h2>
				</div>
				<div class="card-body card-padding">
					<div class="form-group" id="nameDiv">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.managecustomer.custname" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputName"
									name="inputName" value="" readonly>
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span> <small
								class="help-block" id="error"></small>
						</div>
					</div>
					<div class="form-group" id="emailDiv">
						<label for="inputEmail" class="col-sm-2 control-label"><bean:message key="operator.managecustomer.email" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" name="inputEmail" class="form-control"
									id="inputEmail" readonly>
							</div>
							<span class="zmdi zmdi-email form-control-feedback"></span> <small
								class="help-block" id="error"></small>
						</div>
					</div>

					<div class="row">
						<div class="form-input" id="mobileDiv">
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.managecustomer.mobile" /></label>
							<div class="col-sm-4 m-b-25">
								<div class="fg-line">
									<input type="text" name="inputMobile" class="form-control"
										id="inputMobile" readonly>
								</div>
								<span class="zmdi zmdi-smartphone-android form-control-feedback"></span> <small
									class="help-block" id="error"></small>
							</div>
						</div>

						<div class="form-input" style="display: none;" id="forTin">
							<label for="inputTin" class="col-sm-1 control-label"><bean:message key="operator.managecustomer.tin" /></label>
							<div class="col-sm-3 m-b-25">
								<div class="fg-line">
									<input type="text" name="tin" class="form-control"
										id="inputTin" readonly>
								</div>
								<span class="zmdi zmdi-smartphone-android form-control-feedback"></span> <small
									class="help-block" id="error"></small>
							</div>
						</div>
					</div>

					<div class="row">
						<div id="houseDiv" class="form-input">
							<label for="inputHouse" class="col-sm-2 control-label"><bean:message key="operator.managecustomer.address" /></label>
							<div class="col-sm-2 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputHouse"
										name="custHouseNo" readonly> <small class="help-block"
										id="error"></small>
								</div>
							</div>
						</div>
						<div id="addDiv1" class="form-input">
							<div class="col-sm-6 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputAddress1"
										name="custAdd1" readonly>
								</div>
								<span class="zmdi zmdi-location-on form-control-feedback"></span> <small
									class="help-block" id="error"></small>
							</div>
						</div>
					</div>

					<div class="form-group" id="addDiv2">
						<label for="inputAddress2" class="col-sm-2 control-label"></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputAddress2"
									name="custAdd2" readonly>
							</div>
						</div>
					</div>

					<div class="row">
						<div id="cityDiv" class="form-input">
							<label for="inputCity" class="col-sm-2 control-label"></label>
							<div class="col-sm-3 m-b-25">
								<select class="form-control" id="inputCity" name="custCity"
									disabled>

									<option value="">Select City</option>

								</select> <small class="help-block" id="error"></small>
							</div>
						</div>

						<div id="stateDiv" class="form-input">
							<div class="col-sm-3 m-b-25">
								<select class="form-control" id="inputState" name="custState"
									disabled>
									<jsp:useBean id="orderDAO" class="com.dbt.dao.OrderDAO"
										scope="page"></jsp:useBean>
									<jspcore:set var="states" value="${orderDAO.getStates()}"></jspcore:set>
									<option value="">Select State</option>
									<jspcore:forEach var="state" items="${states}">
										<option value="${state}">${state}</option>
									</jspcore:forEach>
								</select> <small class="help-block" id="error"></small>
							</div>
						</div>

						<div class="form-input">
							<div id="pinDiv" class="col-sm-2">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputPin"
										name="custPinCode" readonly>
								</div>
								<span class="zmdi zmdi-my-location form-control-feedback"></span> <small
									class="help-block" id="error"></small>
							</div>
						</div>
					</div>
					<input type="hidden" name="customerId" id="customerId" value="0" />
					<input type="hidden" name="customerType" id="customerType" value="" />
					<input type="hidden" name="buttonType" id="buttonType" value="" />

					<br /> <br />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-primary btn-lg col-sm-4"
								id="editCustDetails">Edit Details</button>
							<button type="button" class="btn btn-danger btn-lg col-sm-4"
								id="deleteCustDetails" style="margin-left: 10%">Delete
								Customer</button>
						</div>
					</div>
				</div>
				<!-- card-body card-padding2 -->
			</div>
		</form>
	</div>

	</section> </section>

	<%@include file="../js/includejs.jsp"%>

	<jspcore:set var="custStatus" value="${sessionScope.custStatus}"></jspcore:set>

	<jspcore:if test="${custStatus == 'Updated'}">
		<script>
			swal("Successful !", "Customer Details updated successfully",
					"success");
		</script>
	</jspcore:if>
	<jspcore:if test="${custStatus == 'notUpdated'}">
		<script>
			swal("Error Occurred !",
					"Unable to update customer details now, try again.",
					"error");
		</script>
	</jspcore:if>
	<jspcore:if test="${custStatus == 'Deleted'}">
		<script>
			swal("Successful !", "Customer deleted successfully.", "success");
		</script>
	</jspcore:if>
	<jspcore:if test="${custStatus == 'notDeleted'}">
		<script>
			swal("Error Occurred !",
					"Unable to delete customer now, try again.", "error");
		</script>
	</jspcore:if>

	<%
		request.getSession().removeAttribute("custStatus");
	%>

</body>
</html>