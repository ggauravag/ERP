<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Iterator"%>
<%@page import="com.dbt.data.User"%>
<%@page import="com.dbt.dao.LoginDAO"%>
<%@page import="com.dbt.data.Privilege"%>

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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Request Complaint</title>

<%@include file="../../css/includecss.jsp"%>

</head>

<body>
	
	<%@ include file="../../header.jsp"%>

	<section id="main"> <%@include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.complaint.requestcomplaint.label" /></h1>
		</div>

		<div class="card">
			<form class="form-horizontal" role="form" method="post">
				<div class="card-header">
					<h2><bean:message key="operator.complaint.requestcomplaint.head1" /></h2>
				</div>

				<div class="card-body card-padding">
					<div class="form-group">
						<label for="inputOrderId" class="col-sm-2 control-label"><bean:message key="operator.complaint.processcomp.orderid" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputOrderId"
									placeholder="Enter Order Id" />
							</div>
							<span class="md-visibility form-control-feedback"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.complaint.processcomp.mobile" /> </label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputMobile"
									placeholder="Enter Mobile Number" />
							</div>
							<span class="md-phone-android form-control-feedback"></span>
						</div>
					</div>


					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.complaint.requestcomplaint.name" />
						</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputName"
									placeholder="Enter Name" />
							</div>
							<span class="md md-person form-control-feedback"></span>
						</div>
					</div>


					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-primary btn-lg col-sm-2"
								id="SearchOrderbtn">Search</button>

						</div>
					</div>



					<div id="fillOrderDetails"></div>

					<div id="fillComment"></div>
				</div>
				<!-- 	End of Card Template-->
			</form>
		</div>

	</div>
	</section> <!-- Javascript Libraries --> <%@include file="../../js/includejs.jsp"%>
</body>
</html>