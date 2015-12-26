<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String basePath = request.getContextPath();
    String bPath = request.getScheme() + "://" + request.getServerName () + ":" + request.getServerPort () + basePath + "/";
%>
<html>
<head>
<base href="<%=bPath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Employee</title>


<%@include file="../../css/includecss.jsp"%>
</head>
<body>

	<%@ include file="../../header.jsp"%>

	<section id="main"> <%@include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="owner.employee.addemp.label" /></h1>
		</div>

		<div class="card">
			<html:form action="owner/employee/EmployeeAdd.do" styleId="empForm"
				styleClass="form-horizontal" method="post"
				enctype="multipart/form-data">
				<div class="card-header">
					<h2><bean:message key="owner.employee.addemp.head1" /></h2>
				</div>
				<div class="card-body card-padding">
					<div class="form-group" id="nameDiv">
						<label for="Fname" class="col-sm-2 control-label"><bean:message key="owner.employee.addemp.name" /></label>
						<div class="col-sm-4">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputFname"
									name="fname" placeholder="Enter First Name" autofocus="true" />
							</div>
						</div>
						<div class="col-sm-4">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputSname"
									name="lname" placeholder="Enter Last Name" />
							</div>
							<span class="md md-person form-control-feedback"></span> <small
								id="error" class="help-block"><font color="red"></font>
								<html:errors property="nameError" /> </small>
						</div>
					</div>

					<div class="form-group" id="emailDiv">
						<label for="inputEmail" class="col-sm-2 control-label"><bean:message key="owner.employee.addemp.email" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputEmail"
									name="email" placeholder="Enter Email ID" />
							</div>
							<span class="md md-email form-control-feedback"></span> <small
								id="error" class="help-block"><font color="red"></font>
								<html:errors property="emailError" /> </small>
						</div>
					</div>


					<div class="form-group" id="mobileDiv">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="owner.employee.addemp.mobile" /></label>
						<div class="col-sm-8">
							<div class="fg-line">

								<input type="text" class="form-control input-mask"
									id="inputMobile" name="mobile" placeholder="Enter Mobile"
									data-mask="000-000-0000" />

							</div>
							<span class="md-phone-android form-control-feedback"></span> <small
								id="error" class="help-block"> <font color="red"></font>
								<html:errors property="mobileError" />
							</small>
						</div>
					</div>

					<div class="row">
						<div id="houseDiv" id="houseDiv" class="form-input">
							<label for="inputHouse" class="col-sm-2 control-label"><bean:message key="owner.employee.addemp.address" /></label>
							<div class="col-sm-2 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputHouse"
										name="house" placeholder="House No.">

								</div>
								<small id="error" class="help-block"><font color="red"></font>
									<html:errors property="houseError" /></small>
							</div>
						</div>
						<div id="line1Div" class="form-input">
							<div class="col-sm-6 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputAddress1"
										name="line1" placeholder="Address line 1">

								</div>
								<span class="md md-location-on form-control-feedback"></span> <small
									id="error" class="help-block"><font color="red"></font>
									<html:errors property="line1Error" /></small>
							</div>
						</div>
					</div>

					<div id="line2Div" class="form-group">
						<label for="inputAddress2" class="col-sm-2 control-label"></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputAddress2"
									name="line2" placeholder="Address line 2">
							</div>
							<small id="error" class="help-block"><font color="red"></font>
								<html:errors property="line2Error" /></small>
						</div>
					</div>

					<div class="row">
						<div id="cityDiv" class="form-input">
							<label for="inputCity" class="col-sm-2 control-label"></label>
							<div class="col-sm-3 m-b-25 selectpicker">
								<select class="form-control" id="inputCity" name="city">
									<option>Select City</option>
								</select> <small id="error" class="help-block"><font color="red"></font>
									<html:errors property="cityError" /></small>
							</div>

						</div>


						<div id="stateDiv" class="form-input">
							<div class="col-sm-3 m-b-25 selectpicker">
								<select class="form-control" id="inputState" name="state">
									<jsp:useBean id="orderDAO" class="com.dbt.dao.OrderDAO"
										scope="page"></jsp:useBean>
									<jspcore:set var="states" value="${orderDAO.getStates()}"></jspcore:set>
									<option>Select State</option>
									<jspcore:forEach var="state" items="${states}">
										<option value="${state}">${state}</option>
									</jspcore:forEach>
								</select> <small id="error" class="help-block"><font color="red"></font>
									<html:errors property="stateError" /></small>

							</div>

						</div>

						<div class="form-input">
							<div id="pinDiv" class="col-sm-2">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputPin"
										placeholder="Enter PinCode" name="zip">
								</div>
								<span class="md md-my-location form-control-feedback"></span> <small
									id="error" class="help-block"><font color="red"></font>
									<html:errors property="pinError" /></small>
							</div>
						</div>

					</div>

					<div id="roleDiv" class="form-group">
						<label for="inputRole" class="col-sm-2 control-label"><bean:message key="owner.employee.addemp.role" /></label>
						<div class="col-sm-3">
							<div class="fg-line select">

								<select onchange="showDateTime(this.value)" class="form-control"
									id="inputRole" name="role">
									<option value="" selected="selected" disabled="disabled">Select
										an Option</option>
									<option value="OPERATOR">OPERATOR</option>
									<option value="DBA">DBA</option>
									<option value="OTHER">OTHER</option>
								</select>

							</div>
							<small id="error" class="help-block"> <font color="red"></font>
								<html:errors property="roleError" />
							</small>
						</div>
					</div>

					<div id="salaryDiv" class="form-group">
						<label for="inputSalary" class="col-sm-2 control-label"><bean:message key="owner.employee.addemp.salary" /></label>
						<div class="col-sm-3">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputSalary"
									name="salary" placeholder="Enter Salary" />
							</div>
							<span class="md-phone-android form-control-feedback"></span> <small
								id="error" class="help-block"><font color="red"></font>
								<html:errors property="salaryError" /> </small>
						</div>
					</div>


					<div id="showDT" style="display: none;">
						<div class="form-group">
							<label class="col-sm-2 control-label"><bean:message key="owner.employee.addemp.selecttime" /></label>
							<div class="col-sm-6">

								<div class="radio radio-inline m-r-20">
									<label> <input type="radio"
										onchange=getSpecificTime(this.value); id="ft" name="timeType"
										value="Full" checked="checked"> <i
										class="input-helper"></i> Full Time
									</label>
								</div>

								<div class="radio radio-inline m-r-20">
									<label> <input type="radio"
										onchange=getSpecificTime(this.value); id="st" name="timeType"
										value="SPECIFIC"> <i class="input-helper"></i>
										Specific Time
									</label>
								</div>


							</div>
						</div>

						<div id="SEtime" class="form-group" style="display: none;">
							<label class="col-sm-2 control-label"><bean:message key="owner.employee.addemp.time" /></label>
							<div class="col-sm-4">
								<div class="input-group">
									<div class="dtp-container dropdown fg-line">
										<input type='text' class="form-control time-picker"
											name="stime" autofocus="true" data-toggle="dropdown"
											placeholder="Start Time">
									</div>
								</div>
							</div>


							<div class="col-sm-4">
								<div class="input-group">
									<div class="dtp-container dropdown fg-line">
										<input type='text' class="form-control time-picker"
											name="etime" data-toggle="dropdown" placeholder="End Time">
									</div>
								</div>
							</div>
							<span class="md md-access-time form-control-feedback"></span> <small
								id="error" class="help-block"> <html:errors
									property="timeError" />
							</small>
						</div>


						<div class="form-group">
							<label for="inputDoj" class="col-sm-2 control-label"><bean:message key="owner.employee.addemp.profilepic" /></label>

							<div class="fileinput fileinput-new " data-provides="fileinput">
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput"></div>
								<div>
									<span class="btn btn-info btn-file"> <span
										class="fileinput-new">Select image</span> <span
										class="fileinput-exists">Change</span> <html:file
											property="profilePicture"></html:file>

									</span> <a href="#" class="btn btn-danger fileinput-exists"
										data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>


					</div>
					<div id="dojDiv" class="form-group">
						<label for="inputDoj" class="col-sm-2 control-label"><bean:message key="owner.employee.addemp.doj" /></label>
						<div class="col-sm-4">
							<div class="input-group">
								<div class="dtp-container dropdown fg-line">
									<input type="text" class="form-control date-picker"
										id="inputDoj" name="doj" placeholder="Enter Date"
										data-toggle="dropdown" />
								</div>
								<span class="md md-event form-control-feedback"></span> <small
									id="error" class="help-block"> <font color="red"></font>
									<html:errors property="dateError" />
								</small>
							</div>
						</div>
					</div>

					<div class="form-group"></div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary btn-lg col-sm-2"
								id="CreateUser">Create User</button>
						</div>
					</div>
				</div>
			</html:form>

		</div>

	</div>


	</section> </section>
	<%@include file="../../js/includejs.jsp"%>

	<jspcore:if test="${status == 'success'}">
		<script>
			swal("Employee Added Successfully",
					"Check Your Email Id for more details", "success");
		</script>
	</jspcore:if>
	<jspcore:if test="${status == 'failure'}">
		<script>
			swal(
					"Employee Not Added.",
					"Email Id Already Registered ! Please try again with another Email Id !",
					"error");
		</script>
	</jspcore:if>

	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/moment/moment.min.js"></script>
	<script src="vendors/noUiSlider/jquery.nouislider.all.min.js"></script>
	<script src="vendors/input-mask/input-mask.min.js"></script>
	<script src="vendors/farbtastic/farbtastic.min.js"></script>
	<script src="vendors/summernote/summernote.min.js"></script>
	<script src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/fileinput/fileinput.min.js"></script>
	<script src="<%=basePath%>/js/extra.js"></script>
</body>
</html>