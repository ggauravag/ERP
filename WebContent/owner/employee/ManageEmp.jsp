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
<title>Manage Employee</title>

<%@include file="../../css/includecss.jsp"%>

</head>
<body>

	<%@ include file="../../header.jsp"%>

	<section id="main"> <%@include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="owner.employee.manageemp.label" /></h1>
		</div>

		<div class="card">
			<form action="owner/employee/ManageEmployee.do" class="form-horizontal"
				method="post" enctype="multipart/form-data" id="ManempForm">
				<div class="card-header">
					<h2><bean:message key="owner.employee.manageemp.head1" /></h2>
				</div>
				<div class="card-body card-padding">
					<div id="nameDiv" class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.empname" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputName"
									name="Empname" placeholder="Enter Employee Name"
									autofocus="true" />
							</div>
							<span class="md md-person form-control-feedback"></span> <small
								id="error" class="help-block"><font color="red"></font>
								<html:errors property="nameError" /> </small>
						</div>
						<button type="button" class="btn btn-info" data-toggle="tooltip"
							data-placement="top" title data-original-title="Search Employees"
							id="searchEmployeeDetails">
							<i class="md md-search"></i>
						</button>

						<div class="modal fade" data-modal-color="amber" id="modalEmp"
							data-backdrop="static" data-keyboard="false" tabindex="-1"
							role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">Employee Search</h4>
									</div>
									<div class="modal-body">
										<p>Here's the list of Employees :</p>

										<div id="empList"></div>

									</div>
									<div class="modal-footer">
										<button type="button" id="selectEmpBtn" class="btn btn-link">Select
											Employee</button>
										<button type="button" class="btn btn-link"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>


					</div>

					<div id="empData" style="visibility:; display:;">

						<div class="form-group">
							<label for="inputEmpID" class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.empid" /></label>
							<div class="col-sm-8">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputEmpID"
										name="EmployeeID" readonly="true" />
								</div>
								<span class="md md-visibility form-control-feedback"></span> <small
									id="error" class="help-block"><font color="red"></font>
									<html:errors property="empidError" /> </small>
							</div>
						</div>

						<div class="form-group">
							<label for="inputEmpEmail" class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.empemail" /></label>
							<div class="col-sm-8">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputEmpEmail"
										name="EmployeeEmail" readonly="true" />
								</div>
								<span class="md md-email form-control-feedback"></span> <small
									id="error" class="help-block"><font color="red"></font>
									<html:errors property="emailError" /> </small>
							</div>
						</div>


						<div class="form-group">
							<label for="inputEmpRole" class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.role" /></label>
							<div class="col-sm-8">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputEmpRole"
										name="EmployeeRole" readonly="true" />
								</div>
								<span class="md md-visibility form-control-feedback"></span> <small
									id="error" class="help-block"><font color="red"></font>
									<html:errors property="roleError" /> </small>
							</div>
						</div>

						<div class="form-group">
							<label for="inputEmpDoj" class="col-sm-2 control-label"><bean:message key="owner.employee.addemp.doj" /></label>
							<div class="col-sm-8">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputEmpDoj"
										name="EmployeeDoj" readonly="true" />
								</div>
								<span class="md md-event form-control-feedback"></span> <small
									id="error" class="help-block"><font color="red"></font>
									<html:errors property="dojError" /> </small>
							</div>
						</div>

						<div class="form-group">
							<label for="inputEmpMobile" class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.mobile" /></label>
							<div class="col-sm-8">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputEmpMobile"
										name="EmployeeMobile" />
								</div>
								<span class="md-phone-android form-control-feedback"></span> <small
									id="error" class="help-block"> <font color="red"></font>
									<html:errors property="mobileError" />
								</small>
							</div>
						</div>



						<div class="form-group">
							<label for="inputEmpSal" class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.empsalary" /></label>
							<div class="col-sm-8">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputEmpSal"
										name="EmployeeSal" />
								</div>
								<span class="md md-visibility form-control-feedback"></span> <small
									id="error" class="help-block"><font color="red"></font>
									<html:errors property="salaryError" /> </small>
							</div>
						</div>

						<div class="form-group">
							<label for="inputEmpDueSal" class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.duesalary" /></label>
							<div class="col-sm-8">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputEmpDueSal"
										name="EmployeeDueSal" />
								</div>
								<span class="md md-visibility form-control-feedback"></span> <small
									id="error" class="help-block"><font color="red"></font>
									<html:errors property="salaryDueError" /> </small>
							</div>
						</div>



						<div class="row">
							<div class="form-input">
								<label for="inputEmpHouse" class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.empaddress" /></label>
								<div class="col-sm-2 m-b-25">
									<div class="fg-line">
										<input type="text" class="form-control" id="inputEmpHouse"
											name="EmployeeHouse" placeholder="House No.">

									</div>
									<small id="error" class="help-block"><font color="red"></font>
										<html:errors property="houseError" /></small>
								</div>
							</div>
							<div class="form-input">
								<div class="col-sm-6 m-b-25">
									<div class="fg-line">
										<input type="text" class="form-control" id="inputEmpAddress1"
											name="Employeeline1" placeholder="Address line 1">

									</div>
									<span class="md md-location-on form-control-feedback"></span> <small
										id="error" class="help-block"><font color="red"></font>
										<html:errors property="line1Error" /></small>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="inputEmpAddress2" class="col-sm-2 control-label"></label>
							<div class="col-sm-8">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputEmpAddress2"
										name="Employeeline2" placeholder="Address line 2">
								</div>
								<small id="error" class="help-block"><font color="red"></font>
									<html:errors property="line2Error" /></small>
							</div>
						</div>
						<div class="row">
							<div id="cityDiv" class="form-input">
								<label for="inputCity" class="col-sm-2 control-label"></label>
								<div class="col-sm-3 m-b-25 selectpicker">

									<select class="form-control" id="inputCity" name="EmployeeCity">
									<option>Select City</option>

								</select><small id="error" class="help-block"><font color="red"></font>
										<html:errors property="cityError" /></small>
								</div>

							</div>


							<div id="stateDiv" class="form-input">
								<div class="col-sm-3 m-b-25 selectpicker">
									<select class="form-control" id="inputState" name="EmployeeState">
									<jsp:useBean id="orderDAO" class="com.dbt.dao.OrderDAO"
										scope="page"></jsp:useBean>
									<jspcore:set var="states" value="${orderDAO.getStates()}"></jspcore:set>
									<option>Select State</option>
									<jspcore:forEach var="state" items="${states}">
										<option value="${state}">${state}</option>
									</jspcore:forEach>
								</select>  <small id="error" class="help-block"><font color="red"></font>
										<html:errors property="stateError" /></small>

								</div>

							</div>

							<div class="form-input">
								<div id="pinDiv" class="col-sm-2">
									<div class="fg-line">
										<input type="text" class="form-control" id="inputEmpZip"
											placeholder="Enter PinCode" name="EmployeeZip">
									</div>
									<span class="md md-my-location form-control-feedback"></span> <small
										id="error" class="help-block"><font color="red"></font>
										<html:errors property="pinError" /></small>
								</div>
							</div>

						</div>



						<div class="form-group" id="p1">
							<label for="inputDoj" class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.proof1" /></label>

							<div class="fileinput fileinput-new " data-provides="fileinput">
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput"></div>
								<div>
									<span class="btn btn-info btn-file"> <span
										class="fileinput-new">Select image</span> <span
										class="fileinput-exists">Change</span> <input type="file"
										name="proof1" />


									</span> <a href="#" class="btn btn-danger fileinput-exists"
										data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>

						<div class="form-group" id="p2" style="display: none;">
							<label class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.proof2" /></label>

							<div class="fileinput fileinput-new " data-provides="fileinput">
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput"></div>
								<div>
									<span class="btn btn-info btn-file"> <span
										class="fileinput-new">Select image</span> <span
										class="fileinput-exists">Change</span> <input type="file"
										name="proof2" />

									</span> <a href="#" class="btn btn-danger fileinput-exists"
										data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
						<div class="form-group" id="p3"
							style="visibility: hidden; display: none;">
							<label class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.proof3" /></label>

							<div class="fileinput fileinput-new " data-provides="fileinput">
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput"></div>
								<div>
									<span class="btn btn-info btn-file"> <span
										class="fileinput-new">Select image</span> <span
										class="fileinput-exists">Change</span> <input type="file"
										name="proof3" />

									</span> <a href="#" class="btn btn-danger fileinput-exists"
										data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
						<div class="form-group" id="p4"
							style="visibility: hidden; display: none;">
							<label class="col-sm-2 control-label"><bean:message key="owner.employee.manageemp.proof4" /></label>

							<div class="fileinput fileinput-new " data-provides="fileinput">
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput"></div>
								<div>
									<span class="btn btn-info btn-file"> <span
										class="fileinput-new">Select image</span> <span
										class="fileinput-exists">Change</span> <input type="file"
										name="proof4" />

									</span> <a href="#" class="btn btn-danger fileinput-exists"
										data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
						<input type="hidden" id="numIDProof" name="numIDProof" value="1" />
						<a onClick="addID()" class="btn bgm-blue btn-float"
							data-toggle="tooltip" data-placement="top"
							title="Add more ID proofs" id="ms-compose"> <i
							class="md md-add"></i>
						</a> <br /> <br /> <br />
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success btn-lg col-sm-2">Update
								</button>
								<button type="reset" class="btn btn-default btn-lg col-sm-2"
									style="margin-left: 10%">Reset</button>
							</div>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>
	</section> </section>

	<%@include file="../../js/includejs.jsp"%>

	<jspcore:if test="${status == 'success'}">
		<script>
			swal("Employee Updated Successfully",
					"The details has been modified.", "success");
		</script>
	</jspcore:if>
	<jspcore:if test="${status == 'failure'}">
		<script>
			swal("Employee Not Updated.", "Please try again !", "error");
		</script>
	</jspcore:if>


	<script src="vendors/moment/moment.min.js"></script>
	<script src="vendors/noUiSlider/jquery.nouislider.all.min.js"></script>
	<script src="vendors/input-mask/input-mask.min.js"></script>
	<script src="vendors/farbtastic/farbtastic.min.js"></script>
	<script src="vendors/summernote/summernote.min.js"></script>
	<script src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/fileinput/fileinput.min.js"></script>
	<script src="<%=basePath%>/js/emp.js"></script>
</body>
</html>