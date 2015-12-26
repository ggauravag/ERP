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
<title>Process Complaint</title>

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
			<h1><bean:message key="operator.complaint.processcomp.label" /></h1>
		</div>
     
          <div class="card">
			<form action="" class="form-horizontal" role="form" method="post">

		
		      <div class="card-header">
					<h2><bean:message key="operator.complaint.processcomp.head1" /></h2>
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
							<label for="inputCompID" class="col-sm-2 control-label"><bean:message key="operator.complaint.processcomp.complaintid" /></label>
							<div class="col-sm-8">
									<div class="fg-line">
									<input type="text" class="form-control" id="inputCompID"
											placeholder="Enter Complaint Id" />
									</div>
								<span class="md-visibility form-control-feedback"></span>
						    </div>
				     </div>
			       
			       
			         <div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.complaint.processcomp.mobile" />
							</label>
						<div class="col-sm-8">
							<div class="fg-line">
							<input type="text" class="form-control input-mask" id="inputMobile" name="mobile"
									placeholder="Enter Mobile" data-mask="000-000-0000"/>
							</div>
							<span class="md-phone-android form-control-feedback"></span>
					    </div>
			       </div>
			       
			       
			       <div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
						  <button type="button" class="btn btn-primary btn-lg col-sm-2" id="SearchCompbtn">Search
									</button>
						  
						</div>
				  </div>
				    <div id="fillOrderDetails">
			  
			  
			 
		                 
                              
              </div>  
				  
			  </div>
		   </form>			    
		</div>
			  
        
</div>

</section>
</section>
<%@include file="../../js/includejs.jsp" %>
</body>
</html>