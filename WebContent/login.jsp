<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><bean:message key="project.name" /></title>

<!-- Vendor CSS -->

<link rel="icon" href="img/favicon.ico">

<!-- Vendor CSS -->
<link href="vendors/bower_components/animate.css/animate.min.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css"
	rel="stylesheet">

<!-- CSS -->
<link href="css/app.min.1.css" rel="stylesheet">
<link href="css/app.min.2.css" rel="stylesheet">




</head>

<body class="login-content">
	<!-- Login -->
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<div class="lc-block toggled" id="l-login">
		<form action="./login.do" id="loginForm" method="post">
			<input type="hidden" name="action" value="login" />
			<div id="emailDiv" class="">
				<div class="input-group m-b-20">
					<span class="input-group-addon"><i class="zmdi zmdi-account"></i></span>
					<div class="fg-line">

						<input type="text" value='${param["email"]}' class="form-control"
							name="email" id="email"
							placeholder="<bean:message key="username.label"/>">
					</div>

				</div>
				<div class="has-error">
					<h3>
						<small class="help-block" id="error"><html:errors
								property="emailError" /></small>
					</h3>
				</div>
			</div>
			<div id="passwordDiv" class="">
				<div class="input-group m-b-20">
					<span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
					<div class="fg-line">
						<input type="password" value='${param["password"]}'
							class="form-control" id="password" name="password"
							placeholder="<bean:message key="password.label"/>">

					</div>

				</div>
				<div class="has-error">
					<h3>
						<small class="help-block" id="error"><html:errors
								property="passwordError" /></small>
					</h3>
				</div>
			</div>
			<div class="clearfix"></div>

			<div class="checkbox">
				<label> <input type="checkbox" value="Yes" name="rememberMe">
					<i class="input-helper"></i> <bean:message key="signin.label" />
				</label>
			</div>

			<a href="javascript:submitForm('loginForm');"
				class="btn btn-login btn-danger btn-float"><i class="zmdi zmdi-arrow-forward"></i></a>

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

				<span class="input-group-addon"><i class="zmdi zmdi-email"></i></span>
				<div class="fg-line">
					<input type="text" class="form-control" placeholder="Username">
				</div>
			</div>

			<div class="input-group m-b-20">
				<span class="input-group-addon"><i class="zmdi zmdi-mail"></i></span>
				<div class="fg-line">
					<input type="text" class="form-control" placeholder="Email Address">
				</div>
			</div>

			<div class="input-group m-b-20">
				<span class="input-group-addon"><i
					class="zmdi zmdi-accessibility"></i></span>
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
				class="zmdi zmdi-arrow-forward"></i></a>

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
		<form action="./login.do" id="forgotPassword" method="post">
			<input type="hidden" name="action" value="forgotPassword" />
			<p class="text-left">
				<bean:message key="resetPassword.label" />
			</p>
			<div id="forgetEmailDiv" class="">
				<div class="input-group m-b-20">
					<span class="input-group-addon"><i class="zmdi zmdi-email"></i></i></span>
					<div class="fg-line">
						<input type="email" name="email" id="forgetEmail"
							class="form-control"
							placeholder="<bean:message key="email.label"/>">
					</div>
				</div>
				<div class="has-error">
					<h3>
						<small class="help-block" id="error"><html:errors
								property="forgotEmailError" /></small>
					</h3>
				</div>
			</div>
			<a href="javascript:submitForm('forgotPassword');"
				class="btn btn-login btn-danger btn-float"><i class="zmdi zmdi-arrow-forward"></i></a>

			<ul class="login-navigation">
				<li data-block="#l-login" class="bgm-green"><bean:message
						key="login.label" /></li>
				<!--<li data-block="#l-register" class="bgm-red">Register</li>  -->
			</ul>
		</form>
	</div>

	<!-- Javascript Libraries -->
	<script src="vendors/bower_components/jquery/dist/jquery.min.js"></script>
	<script
		src="vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<script src="vendors/bower_components/Waves/dist/waves.min.js"></script>
    
	<!-- Placeholder for IE9 -->
	<!--[if IE 9 ]>
      <script src="vendors/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
    <![endif]-->


	<script src="js/functions.js"></script>
	<script src="js/demo.js"></script>
	<jspcore:if test="${error != null}">
		<script>
			$("#l-forget-password").removeClass("toggled");
			$("#l-login").addClass("toggled");
			alert("<bean:message key='${error}'/>");
		</script>
	</jspcore:if>


	<jspcore:if test="${forgetPassword == true}">
		<script>
			$("#l-login").removeClass("toggled");
			$("#l-forget-password").addClass("toggled");
		</script>
	</jspcore:if>
	<jspcore:if test="${login == true}">
		<script>
			$("#l-login").addClass("toggled");
			$("#l-forget-password").removeClass("toggled");
		</script>
	</jspcore:if>

	<jspcore:if test="${sendStatus == true}">
		<script>
			$("#l-login").addClass("toggled");
			$("#l-forget-password").removeClass("toggled");
			alert("Password reset link has been sent ! ");
			swal(
					"Email Sent !",
					"Password reset link has been sent to the registered email, please click the link withhin 24 hours from now, else it will expire !",
					"success");
			
		</script>
	</jspcore:if>
	<jspcore:if test="${sendStatus == false}">
		<script>
			$("#l-login").removeClass("toggled");
			$("#l-forget-password").addClass("toggled");
			alert("Invalid Email Account !");
			swal("Invalid Email Account !",
					"This email is not registered with any valid account !",
					"error");
			
		</script>
	</jspcore:if>


	<div class="modal fade" data-modal-color="green" id="preventClick"
		data-backdrop="static" data-keyboard="false" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<form action="./login.do" id="otpVerify" method="post">
				<input type="hidden" name="action" value="otpVerify" />
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
						<div id="otpDiv" class="">
							<div class="input-group col-sm-12">
								<input type="text" class="form-control" id="otpField" name="otp"
									placeholder=" Enter OTP here" />
							</div>

							<div class="has-error">
								<h3>
									<small class="help-block" id="error">${errorOTP}<html:errors
											property="otpBlank"></html:errors></small>
								</h3>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" id="submitOTP" class="btn btn-link">
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
			$("#l-forget-password").removeClass("toggled");
			$("#l-login").addClass("toggled");
			$('#preventClick').modal('show');
			//console.log('Modal triggered');
		</script>
	</jspcore:if>
	<jspcore:if test="${sessionScope.forgotName != null}">
		<jspcore:remove var="forgotName" scope="session" />
		<script>
			alert("Password changed successfully !");
			swal(
					"Password Changed",
					"Your login password has been successfully changed ! Now, login using your new password !",
					"success");
			
		</script>
	</jspcore:if>

	<script>
		$("#submitOTP").click(function() {
			var otp = $("#otpField").val();
			//console.log("OTP Submitted : " + otp);
			var success = true;
			if (otp == "" || otp.length < 6) {
				showErrorValidation("#otpDiv", "OTP is invalid / blank !");
				success = false;
			} else
				clearError("#otpDiv");

			if (success)
				$("#otpVerify").submit();
		});
	</script>



</body>
</html>