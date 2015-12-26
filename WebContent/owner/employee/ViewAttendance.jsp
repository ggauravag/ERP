<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String basePath = request.getContextPath();
	String bPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ basePath + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=bPath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Attendance</title>

<%@include file="../../css/includecss.jsp"%>
</head>

<body>
	<%@ include file="../../header.jsp"%>

	<section id="main"> <%@include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="owner.employee.viewattendance.label" /></h1>
		</div>

		<div class="card">
			<form action="" class="form-horizontal" method="post"
				id="viewAttendanceForm">

				<jsp:useBean id="employee" class="com.dbt.dao.EmployeeDAO"></jsp:useBean>
				<jspcore:set var="emp" value="${employee.getEmployeeDetails()}"></jspcore:set>

				<div class="card-header">
					<h2><bean:message key="owner.employee.viewattendance.head1" /></h2>
				</div>
				<div class="card-body card-padding">

					<div class="row">
						<div class="form-input" id="sdate">
							<label for="" class="col-sm-2 control-label"><bean:message key="owner.employee.viewattendance.fromdate" />
							</label>
							<div class="col-sm-3 m-b-25">
								<div class="dtp-container dropdown fg-line open">
									<input type="text" name="startDate"
										class="form-control date-picker" data-toggle="dropdown"
										aria-expanded="true" id="startDate" placeholder="End Date">
								</div>
								<span class="md md-event form-control-feedback"></span>
							</div>
						</div>

						<div class="form-input" id="edate">
							<label for="" class="col-sm-2 control-label"><bean:message key="owner.employee.viewattendance.todate" /> </label>
							<div class="col-sm-3 m-b-25">

								<div class="dtp-container dropdown fg-line open">
									<input type="text" name="endDate"
										class="form-control date-picker" data-toggle="dropdown"
										aria-expanded="true" id="endDate" placeholder="End Date">
								</div>
								<span class="md md-event form-control-feedback"></span>

							</div>
						</div>
					</div>
					
					<div class="card-header">
						<h2><bean:message key="owner.employee.viewattendance.head2" /></h2>
					</div>
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th><bean:message key="owner.employee.viewattendance.table.name" /></th>
									<th><bean:message key="owner.employee.viewattendance.table.email" /></th>
									<th><bean:message key="owner.employee.viewattendance.table.mobile" /></th>
									<th><bean:message key="owner.employee.viewattendance.table.salary" /></th>
									<th><bean:message key="owner.employee.viewattendance.table.doj" /></th>
									<th><bean:message key="owner.employee.viewattendance.table.status" /></th>
								</tr>
							</thead>
							<tbody>
								<jspcore:forEach var="empDetail" items="${emp}">
									<tr>
										<td><label class="radio radio-inline m-r-20"> <input onchange="getEmpAttendance(this)"
												type="radio" name="empRadio" id="empRadio${empDetail.employee_id}" value="${empDetail.employee_id} ${empDetail.salary}">
												<i class="input-helper"></i></label></td>

										<td>${empDetail.user.firstName}
											${empDetail.user.lastName}</td>

										<jspcore:if test="${empDetail.user.email == null}">
											<td>-----</td>
										</jspcore:if>
										<jspcore:if test="${empDetail.user.email != null}">
											<td>${empDetail.user.email}</td>
										</jspcore:if>

										<td>${empDetail.user.mobile}</td>
										<td>${empDetail.salary}</td>
										<td>${empDetail.date_of_join}</td>
										<td>${empDetail.status}</td>
									</tr>
								</jspcore:forEach>
							</tbody>
						</table>
					</div>
					<br>
					<div class="card-header">
						<h2><bean:message key="owner.employee.viewattendance.head3" /></h2>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="control-label col-sm-4 "><b><u><bean:message key="owner.employee.viewattendance.totalattendance" /></u> :</b>
								<font color="red" size="5px"><span
									class="WebRupee">&nbsp; </span><label id="attendance"></label></font>
							</label> 
							<label class="control-label col-sm-4 col-sm-offset-1"><b><u><bean:message key="owner.employee.viewattendance.calculatedsalary" /></u> :</b> <font color="green" size="5px"><span
									class="WebRupee">&nbsp; </span><label id="salary"></label></font>
							</label>
						</div>
					</div>
					
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th><bean:message key="owner.employee.viewattendance.table.sno" /></th>
									<th><bean:message key="owner.employee.viewattendance.table.date" /></th>
									<th><bean:message key="owner.employee.viewattendance.table.status" /></th>
									<th><bean:message key="owner.employee.viewattendance.table.halfday" /></th>
								</tr>
							</thead>
							<tbody id="tableBody">
							</tbody>
						</table>
					</div>

				</div>
			</form>
		</div>
	</div>
	</section> </section>

	<%@include file="../../js/includejs.jsp"%>
	<script src="js/emp.js"></script>
	<script src="vendors/moment/moment.min.js"></script>
	<script
		src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

</body>
</html>