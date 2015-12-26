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
<title>Create Feedback</title>

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
			<h1><bean:message key="operator.feedback.createfeedback.label" /></h1>
		</div>

		<form class="form-horizontal" action="operator/feedback/CreateFeedback.do" method="post"
			id="createFeedbackForm" name="createFeedbackForm" role="form">

			<div class="card">
				<div class="card-header">
					<h2><bean:message key="operator.feedback.createfeedback.head1" /></h2>
				</div>
				<div class="card-body card-padding">
					<div class="row">
						<div class="form-input" id="orderDiv">
							<label for="inputOrderId" class="col-sm-2 control-label"><bean:message key="operator.complaint.processcomp.orderid" /></label>
							<div class="col-sm-5 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="orderId"
										name="orderId" placeholder="Enter Order ID">
								</div>
								<span class="md-shopping-cart form-control-feedback"></span>
								<html:errors property="orderError"/>
							</div>
						</div>

						<div class="form-input">
							<div class="col-sm-5 m-b-25">
								<div class="fg-line">
									<button type="submit"
										class="btn btn-primary btn-lg col-sm-6">Create
										Feedback</button>
								</div>
							</div>
						</div>
					</div>

					<jsp:useBean id="fbdao" class="com.dbt.dao.FeedbackDAO"
						scope="page"></jsp:useBean>

					<div>
						<div class="table-responsive">
						<html:errors property="questionError"/>
							<table class="table table-hover">
								<thead>
									<tr>
										<th><bean:message key="operator.feedback.createfeedback.table.qid" /></th>
										<th><bean:message key="operator.feedback.createfeedback.table.qtext" /></th>
										<th><bean:message key="operator.feedback.createfeedback.table.type" /></th>
										<th><bean:message key="operator.feedback.createfeedback.table.select" /></th>
									</tr>
								</thead>
								<tbody>
									<jspcore:forEach var="ques" items="${fbdao.getQuestions()}">
										<tr>
											<td>${ques.id}</td>
											<td>${ques.text}</td>
											<td>${ques.type}</td>
											<td><input type="checkbox" name="questions"
												value="${ques.id}"></td>
										</tr>
									</jspcore:forEach>


								</tbody>
							</table>
						</div>
					</div>

				</div>
			</div>

		</form>
	</div>
	</section> </section>

	<%@include file="../../js/includejs.jsp"%>
	<jspcore:if test="${status == true}">
		<script type="text/javascript">
			swal("Success","Feedback has been successfully created and a SMS has been sent to the customer's mobile.","success")
		</script>
	</jspcore:if>
</body>
</html>