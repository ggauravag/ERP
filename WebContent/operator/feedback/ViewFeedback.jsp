<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String basePath = request.getContextPath();
    String bPath = request.getScheme() + "://" + request.getServerName () + ":" + request.getServerPort () + basePath + "/";
%>
<head>
<base href="<%=bPath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>View Feedback</title>
<%@include file="../../css/includecss.jsp"%>

</head>
<body>
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<%@ include file="../../header.jsp"%>

	<section id="main"> <%@ include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.feedback.viewfeedback.label" /></h1>
		</div>

		<form class="form-horizontal" action="" method="post"
			id="viewFeedbackForm" name="viewFeedbackForm" role="form">

			<div class="card">
				<div class="card-header">
					<h2><bean:message key="operator.feedback.viewfeedback.head1" /></h2>
				</div>
				<div class="card-body card-padding">
					<div class="form-group">
						<label for="inputOrderID" class="col-sm-2 control-label"><bean:message key="operator.complaint.processcomp.orderid" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputOrderID"
									placeholder="Enter Order ID">
							</div>
							<span class="md-shopping-basket form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="row">
						<div class="form-input">
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.complaint.processcomp.mobile" /></label>
							<div class="col-sm-6 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputMobile"
										placeholder="Enter Customer Mobile">
								</div>
								<span class="md-phone-android form-control-feedback"></span>
							</div>
						</div>

						<div class="form-input">
							<div class="col-sm-4 m-b-25">
								<div class="fg-line">
									<button type="button" id="searchFeedback"
										class="btn btn-primary btn-lg col-sm-7">Search
										Feedback</button>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.feedback.viewfeedback.custname" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputName"
									placeholder="Enter Name">
							</div>
							<span class="md md-person form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="form-group">
						<div class="form-input">
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.feedback.viewfeedback.feeddate" /> </label>
							<div class="col-sm-2 m-b-25">
								<div class="dtp-container dropdown fg-line open">
									<input type="text" name="fromDate"
										class="form-control date-picker" data-toggle="dropdown"
										aria-expanded="true" id="inputFromDate"
										placeholder="From Date">
								</div>
								<span class="md md-event form-control-feedback"></span>

							</div>
							<div class="col-sm-2 m-b-25">
								<label class="control-label">to</label>
							</div>
							<div class="col-sm-2 m-b-25">

								<div class="dtp-container dropdown fg-line open">
									<input type="text" name="toDate"
										class="form-control date-picker" data-toggle="dropdown"
										aria-expanded="true" id="inputToDate" placeholder="To Date">
								</div>
								<span class="md md-event form-control-feedback"></span> <span
									class="md-phone-android form-control-feedback"></span>
							</div>
						</div>

					</div>
					<div>
						<table class="table table-hover">
							<thead>
								<tr>
									<th><bean:message key="operator.feedback.viewfeedback.table.select" /></th>
									<th><bean:message key="operator.feedback.viewfeedback.table.feedid" /></th>
									<th><bean:message key="operator.feedback.viewfeedback.table.orderid" /></th>
									<th><bean:message key="operator.feedback.viewfeedback.table.generatedate" /></th>
									<th><bean:message key="operator.feedback.viewfeedback.table.submitdate" /></th>
									<th><bean:message key="operator.feedback.viewfeedback.table.status" /></th>
								</tr>
							</thead>
							<tbody id="fillFeedbackDetails">



							</tbody>
						</table>
						<br> <br>

						
					</div>
					<div >
							<div class="form-group col-sm-2-offset" id="feedbackQues">
							
							</div>
					</div>
				</div>
			</div>
	</div>
	</form>
	</div>
	</section> </section>

	<%@include file="../../js/includejs.jsp"%>
	<script type="text/javascript" src="js/extra.js"></script>
	<script type="text/javascript" src="js/feedback.js"></script>
	<script src="vendors/moment/moment.min.js"></script>
	<script
		src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

</body>
</html>