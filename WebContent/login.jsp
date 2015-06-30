<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
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
	<!-- Login -->

	<div class="lc-block toggled" id="l-login">
		<form action="./login.do" id="loginForm" method="post">
			<div class="input-group m-b-20">
				<span class="input-group-addon"><i class="md md-person"></i></span>
				<div class="fg-line">

					<input type="text" value='${param["email"]}' class="form-control"
						name="email" placeholder="<bean:message key="username.label"/>">
				</div>

			</div>
			<div class="has-error">
				<h3>
					<small class="help-block"><html:errors
							property="emailError" /></small>
						
				</h3>
			</div>
			<div class="input-group m-b-20">
				<span class="input-group-addon"><i
					class="md md-accessibility"></i></span>
				<div class="fg-line">
					<input type="password" value='${param["password"]}'
						class="form-control" name="password"
						placeholder="<bean:message key="password.label"/>">

				</div>

			</div>
			<div class="has-error">
				<h3>
					<small class="help-block"><html:errors
							property="passwordError" /></small>
				</h3>
			</div>
			<div class="clearfix"></div>

			<div class="checkbox">
				<label> <input type="checkbox" value="Yes" name="rememberMe">
					<i class="input-helper"></i> <bean:message key="signin.label" />
				</label>
			</div>

			<a href="javascript:submitForm('loginForm');"
				class="btn btn-login btn-danger btn-float"><i
				class="md md-arrow-forward"></i></a>

			<ul class="login-navigation">
				<!--  <li data-block="#l-register" class="bgm-red"><bean:message key="register.label"/></li>-->
				<li data-block="#l-forget-password" class="bgm-orange"><bean:message
						key="forgotPassword.label" /></li>
			</ul>
		</form>
	</div>


	<!-- Register -->

	<div class="lc-block" id="l-register">
		<form action="" method="post">
			<div class="input-group m-b-20">

				<span class="input-group-addon"><i class="md md-person"></i></span>
				<div class="fg-line">
					<input type="text" class="form-control" placeholder="Username">
				</div>
			</div>

			<div class="input-group m-b-20">
				<span class="input-group-addon"><i class="md md-mail"></i></span>
				<div class="fg-line">
					<input type="text" class="form-control" placeholder="Email Address">
				</div>
			</div>

			<div class="input-group m-b-20">
				<span class="input-group-addon"><i
					class="md md-accessibility"></i></span>
				<div class="fg-line">
					<input type="password" class="form-control" placeholder="Password">
				</div>
			</div>

			<div class="clearfix"></div>

			<div class="checkbox">
				<label> <input type="checkbox" value=""> <i
					class="input-helper"></i> Accept the license agreement
				</label>
			</div>

			<a href="#" class="btn btn-login btn-danger btn-float"><i
				class="md md-arrow-forward"></i></a>

			<ul class="login-navigation">
				<li data-block="#l-login" class="bgm-green"><bean:message
						key="login.label" /></li>
				<li data-block="#l-forget-password" class="bgm-orange"><bean:message
						key="forgotPassword.label" /></li>
			</ul>
		</form>
	</div>

	<!-- Forgot Password -->
	<div class="lc-block" id="l-forget-password">
		<form action="" id="forgotPassword" method="post">
			<p class="text-left">
				<bean:message key="resetPassword.label" />
			</p>

			<div class="input-group m-b-20">
				<span class="input-group-addon"><i class="md md-email"></i></span>
				<div class="fg-line">
					<input type="email" name="email" class="form-control"
						placeholder="<bean:message key="email.label"/>">
				</div>
			</div>

			<a href="javascript:submitForm('forgotPassword');"
				class="btn btn-login btn-danger btn-float"><i
				class="md md-arrow-forward"></i></a>

			<ul class="login-navigation">
				<li data-block="#l-login" class="bgm-green"><bean:message
						key="login.label" /></li>
				<!--<li data-block="#l-register" class="bgm-red">Register</li>  -->
			</ul>
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
	<jspcore:if test="${error != null}">
		<script>
			swal("<bean:message key='${error}'/>");
		</script>
	</jspcore:if>



	<div class="modal fade" data-modal-color="green" id="preventClick"
		data-backdrop="static" data-keyboard="false" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<form action="./otpAuth.do?method=otpVerify" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">
							<bean:message key="otp.title" />
						</h4>
					</div>
					<div class="modal-body">
						<p>
							<bean:message key="otp.text.label" />
						</p>
						<input type="text" class="form-control" name="otp"
							placeholder=" Enter OTP here" />
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-link">
							<bean:message key="otp.button.verify.label" />
						</button>
						<button type="button" class="btn btn-link" data-dismiss="modal">
							<bean:message key="otp.button.close.label" />
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>



	<jspcore:if test="${sessionScope.otp != null}">
		<script>
			$('#preventClick').modal('show');
			//console.log('Modal triggered');
		</script>
	</jspcore:if>


	<script type="text/javascript">
		function submitForm(form) {
			//console.log(form);
			document.getElementById(form).submit();
		}
	</script>

</body>
</html>