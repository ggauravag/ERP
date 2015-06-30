<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Request Complaint</title>


<link href="<%=request.getContextPath() %>/vendors/fullcalendar/fullcalendar.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/vendors/animate-css/animate.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/vendors/sweet-alert/sweet-alert.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/vendors/material-icons/material-design-iconic-font.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath() %>/vendors/socicon/socicon.min.css" rel="stylesheet">

<link href="<%=request.getContextPath() %>/vendors/noUiSlider/jquery.nouislider.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath() %>/vendors/farbtastic/farbtastic.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/vendors/summernote/summernote.css" rel="stylesheet">

<link href="<%=request.getContextPath() %>/css/app.min.1.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/app.min.2.css" rel="stylesheet">

</head>

<body>
     <%@ include file="../../header.jsp"%>
     
     <section id="main"> 
   		<%@include file="../../panel/leftpanel.jsp" %>
<section id="content">
	<div class="container">
		<div class="block-header">
			<h1>Request Complaint</h1>
		</div>
     
          <div class="card">
			<form action="../../RequestComplaint.do" class="form-horizontal" role="form" method="post">

		
		      <div class="card-header">
					<h2>Order Details</h2>
			   </div>
				
			  <div class="card-body card-padding">
			      <div class="form-group">
						<label for="inputOrderId" class="col-sm-2 control-label">Order
							Id</label>
						<div class="col-sm-8">
							<div class="fg-line">
							<input type="text" class="form-control" id="inputOrderId"
									placeholder="Enter Order Id" />
							</div>
							<span class="md-visibility form-control-feedback"></span>
					    </div>
			       </div>
			       
			      <div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label">Mobile Number
							</label>
						<div class="col-sm-8">
							<div class="fg-line">
							<input type="text" class="form-control" id="inputMobile"
									placeholder="Enter Mobile Number" />
							</div>
							<span class="md-phone-android form-control-feedback"></span>
					    </div>
			       </div>
			       
			       
			       <div class="form-group">
						<label for="inputName" class="col-sm-2 control-label">Name
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
						  <button type="button" class="btn btn-primary btn-lg col-sm-2" id="SearchOrderbtn">Search
									</button>
						  
						</div>
				  </div>
			 
		  
		 
		  <div id="fillOrderDetails">
			  
			  
			 
		                 
                              
              </div>          
             
		 <div id="fillComment">
		 
		 </div>
		 </div>  <!-- 	End of Card Template-->
		    </form>
		  </div>
     
     </div>
     </section>
     <!-- Javascript Libraries -->
	 
	<%@include file="../../js/includejs.jsp" %>
	<script>
	<jspcore:if test="${status == 'success'}">
			swal("Complaint Registered","Your complaint has been registered with Complaint ID : ${complaintID}","success");
	</jspcore:if>
	<jspcore:if test="${status == 'failure'}">
	swal("Unable to Register Complaint","Something went wrong ! Please try again !","failure");
	</jspcore:if>
	</script>
</body>
</html>