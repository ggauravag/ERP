<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
    String bPath = request.getScheme() + "://" + request.getServerName () + ":" + request.getServerPort () + basePath + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=bPath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><bean:message key="project.name" /></title>

<!-- Vendor CSS -->

<link rel="icon" href="img/favicon.ico">
<link href="vendors/animate-css/animate.min.css" rel="stylesheet">
<link href="vendors/sweet-alert/sweet-alert.min.css" rel="stylesheet">
<link href="vendors/material-icons/material-design-iconic-font.min.css"
	rel="stylesheet">
<link href="vendors/socicon/socicon.min.css" rel="stylesheet">

<!-- CSS -->
<link href="css/app.min.1.css" rel="stylesheet">
<link href="css/app.min.2.css" rel="stylesheet">
</head>
<body class="login-content">
	<div id="loaderImage" style="display: none">
		<div class="modalNew">
			<div class="centerNew">
				<img alt="" src="<%=basePath%>/img/loader.gif" />
			</div>
		</div>
	</div>

	<!-- Login -->
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<div class="lc-block toggled" id="l-login">
		<form action="./ForgotPassword.do" id="passwordForm" method="post">
			<input type="hidden" name="action" value="resetPassword" />
			<jspcore:if test="${param.authToken != null}">
				<input type="hidden" name="authToken" value="${param.authToken}">
			</jspcore:if>
			<jspcore:if test="${authToken != null}">
				<input type="hidden" name="authToken" value="${authToken}">
			</jspcore:if>

			<div id="passwordDiv" class="">
				<div class="form-group fg-float">
					<span class="input-group-addon"><i class="zmdi zmdi-shield-security"></i></span>

					<div class="fg-line">
						<input type="password" id="newPassword" name="newPassword"
							class="form-control fg-input">
					</div>
					<label class="fg-label">Enter New Password</label>
				</div>
				<div class="has-error">
					<h3>
						<small class="help-block" id="error"><html:errors
								property="newPasswordError" /></small>
					</h3>
				</div>
			</div>
			<div id="confirmPassDiv" class="">
				<div class="fg-float form-group">
					<span class="input-group-addon"><i class="zmdi zmdi-shield-security"></i></span>

					<div class="fg-line">
						<input type="password" id="confirmPassword" name="confirmPassword"
							class="form-control fg-input">
					</div>
					<label class="fg-label">Re-Enter Password</label>
				</div>
				<div class="has-error">
					<h3>
						<small class="help-block" id="error"><html:errors
								property="confirmPasswordError" /></small>
					</h3>
				</div>
			</div>

			<a href="javascript:submitForm('passwordForm');"
				class="btn btn-login btn-danger btn-float"><i
				class="md md-arrow-forward"></i></a>

		</form>
	</div>

	<!-- Javascript Libraries -->
	<script src="js/jquery-2.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script src="vendors/nicescroll/jquery.nicescroll.min.js"></script>
	<script src="vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
	<script src="vendors/sweet-alert/sweet-alert.min.js"></script>
	<script src="vendors/waves/waves.min.js"></script>
	<script src="vendors/sweet-alert/sweet-alert.min.js"></script>

	<script src="js/functions.js"></script>
	<script src="js/demo.js"></script>

	<jspcore:if test="${status == false}">
		<script>
			swal(
					"Invalid Token",
					"Unable to change password, there was one of the following issue(s) : 1. Link has expired, 2. Link has been already utilized, 3. Invalid Authentication Token ",
					"error");
		</script>
	</jspcore:if>

</body>
</html>