
<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Mirrored from byrushan.com/projects/ma/v1-3-1/form-examples.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 21 May 2015 12:12:19 GMT -->
<head>
<%
	String basePath = request.getContextPath();
	String bPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ basePath + "/";
%>
<base href="<%=bPath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Feedback</title>

<!-- Vendor CSS -->
<link href="vendors/animate-css/animate.min.css" rel="stylesheet">
<link href="vendors/sweet-alert/sweet-alert.min.css" rel="stylesheet">
<link href="vendors/material-icons/material-design-iconic-font.min.css"
	rel="stylesheet">
<link href="vendors/socicon/socicon.min.css" rel="stylesheet">
<link href="img/favicon.ico" rel="icon" />

<!-- CSS -->
<link href="css/app.min.1.css" rel="stylesheet">
<link href="css/app.min.2.css" rel="stylesheet">
</head>

<body>
	<section id="main">
		<div>
			<div class="card">
				<div class="card-header">
					<h2>
						Customer Feedback <small>Dear Customer, You had made a
							purchase with Ram Furnitures, Kindly spare a minute's time
							to give us relevant feedback about the product.</small> 5 - Completely
						Agree<br>4 - Somewhat Agree<br>3 - May be True<br>2
						- Somewhat Disagree<br>1 - Completely Disagree
					</h2>
				</div>

				<div class="card-body card-padding">
					<form role="form"
						action="feedback/Submitfeedback.do?action=submit"
						method="post">
						<jsp:useBean id="feed" class="com.dbt.dao.FeedbackDAO"></jsp:useBean>
						<jspcore:set var="ques"
							value="${feed.getFeedbackQues(param.token)}"></jspcore:set>

						<jspcore:if test="${ques.size() != 0}">
							<jspcore:set var="i" value="1"></jspcore:set>
							<jspcore:forEach var="question" items="${ques}">
								<input type="hidden" name="feedbackID"
									value="${question.feedbackID}">
								<jspcore:if test="${question.type == 'STAR'}">
									<div class="input-group">
										<label class="control-label"><b>Q.${i}
												${question.text }</b></label>
										<div class="col-sm-12">
											<label class="radio radio-inline"> <input
												type="radio" name="ans${i}" value="1:${question.id}"
												required> <i class="input-helper"></i> 1
											</label> <label class="radio radio-inline"> <input
												type="radio" name="ans${i}" value="2:${question.id}">
												<i class="input-helper"></i> 2
											</label> <label class="radio radio-inline"> <input
												type="radio" name="ans${i}" value="3:${question.id}">
												<i class="input-helper"></i> 3
											</label> <label class="radio radio-inline"> <input
												type="radio" name="ans${i}" value="4:${question.id}">
												<i class="input-helper"></i> 4
											</label> <label class="radio radio-inline"> <input
												type="radio" name="ans${i}" value="5:${question.id}">
												<i class="input-helper"></i> 5
											</label>
										</div>
									</div>
									<br>
								</jspcore:if>
								<jspcore:if test="${question.type == 'TEXT'}">
									<div class="input-group">
										<label class="control-label"><b>Q.${i}
												${question.text }</b></label>
										<div class="fg-line">
											<input type="text" class="form-control" name="ans${i}"
												required> <br> <input type="hidden"
												name="qno${i}" value="${question.id}">
										</div>

									</div>
								</jspcore:if>
								<jspcore:set var="i" value="${i+1}"></jspcore:set>
							</jspcore:forEach>

							<input type="hidden" name="quesNo" value="${i-1}">
							<br>

							<div class="input-group">
								<button class="btn btn-primary" type="submit">Submit
									Feedback</button>
							</div>

						</jspcore:if>
						<jspcore:if test="${status == null and ques.size() == 0}">
							<div class="input-group">
								<h2>You have already submitted the feedback or the token is
									invalid.</h2>
							</div>
						</jspcore:if>
						<jspcore:if test="${status == true}">
							<h2 style="color: green">Your feedback has been successfully
								submitted.</h2>
						</jspcore:if>
						<jspcore:if test="${status == false }">
							<h2 style="color: red">There was some error while submitting
								feedback. Please try again !.</h2>
						</jspcore:if>
					</form>
				</div>
			</div>




		</div>

	</section>



	<!-- Javascript Libraries -->
	<script src="js/jquery-2.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script src="vendors/nicescroll/jquery.nicescroll.min.js"></script>
	<script src="vendors/auto-size/jquery.autosize.min.js"></script>
	<script src="vendors/waves/waves.min.js"></script>
	<script src="vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
	<script src="vendors/sweet-alert/sweet-alert.min.js"></script>

	<script src="js/functions.js"></script>
	<script src="js/demo.js"></script>


</body>

<!-- Mirrored from byrushan.com/projects/ma/v1-3-1/form-examples.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 21 May 2015 12:12:19 GMT -->
</html>