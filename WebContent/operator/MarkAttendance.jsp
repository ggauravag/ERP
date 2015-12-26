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
<title>Mark Attendance</title>

<%@include file="../css/includecss.jsp"%>
</head>
<body>
	<%@ include file="../../header.jsp"%>

	<section id="main"> <%@include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.markattendance.label" /></h1>
		</div>

		<div class="card">
			<form action="operator/AttendEmp.do" class="form-horizontal" role="form"
				method="post">


				<div class="card-header">
					<h2><bean:message key="operator.markattendance.head1" /></h2>
				</div>

				<div class="card-body card-padding">
					<div id="nameDiv" class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.markattendance.empname" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputName"
									name="Empname" placeholder="Enter Employee Name"
									autofocus="true" />
							</div>
							<span class="zmdi zmdi-person form-control-feedback"></span> <small
								id="error" class="help-block"><font color="red"></font>
								<html:errors property="nameError" /> </small>
						</div>
						<button type="button" class="btn btn-info" data-toggle="tooltip"
							data-placement="top" title data-original-title="Search Employees"
							id="searchEmpDetails">
							<i class="zmdi zmdi-search"></i>
						</button>
					</div>


					<div id="viewEmpData"></div>

					<div id="todayDate" class="form-group"></div>

					<div id="markAtten" class="form-group"></div>
					
					<div id="HalfDayAtten" class="form-group">
					<div class="checkbox">
				<label> <input type="checkbox" value="Yes" name="halfDay">
					<i class="input-helper"></i> <bean:message key="operator.markattendance.halfday" /> 
				</label>
			</div>
					</div>
				</div>
			</form>
		</div>


	</div>

	</section> </section>
	<%@include file="../../js/includejs.jsp"%>
	<script>
		<jspcore:if test="${status == 'success'}">
		swal("Attendance Marked Successfully",
				"Select Another Employee to Mark Attendance", "success");

		</jspcore:if>
		<jspcore:if test="${status == 'failure'}">
		swal("Attendance Not Marked.", "Please try again !", "error");
		</jspcore:if>
	</script>

</body>
</html>