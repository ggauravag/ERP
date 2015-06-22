<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Iterator"%>
<%@page import="com.dbt.data.User"%>
<%@page import="com.dbt.dao.LoginDAO"%>
<%@page import="com.dbt.data.Privilege"%>

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
     <%
	   User user = (User)session.getAttribute("user");
	   String type = user.getType();
	   String path = request.getContextPath();
	   String uri = request.getRequestURI();
	   Iterator<Privilege> iter = LoginDAO.getPrivileges(type);
	  %>
     
     
     <aside id="sidebar">
		<div class="sidebar-inner">
			<div class="si-inner">
				<div class="profile-menu">
					<a href="#">
						<div class="profile-pic">
							<img src="<%=path%>/img/profile-pics/mypic.jpg" alt="">
						</div>

						<div class="profile-info">
							<%=type %> <i class="md md-arrow-drop-down"></i>
						</div>
					</a>

					<ul class="main-menu">
						<li><a href="profile-about.html"><i class="md md-person"></i>
								View Profile</a></li>
						<li><a href="#"><i class="md md-settings-input-antenna"></i>
								Privacy Settings</a></li>
						<li><a href="#"><i class="md md-settings"></i> Settings</a></li>
						<li><a href="./logout.do"><i class="md md-history"></i>
								Logout</a></li>
					</ul>
				</div>

				<ul class="main-menu">
					<li class="active"><a href="dashboard.jsp"><i
							class="md md-home"></i> Home</a></li>
							
							<%
							while(iter.hasNext())
							{
								Privilege priv = iter.next();
								Iterator<Privilege> iterin = priv.subprivs.iterator();
								String classN = priv.getIconClass();
								%>
								<li class="sub-menu"><a href="#"><i class="<%=classN%>"></i><%=priv.getName() %></a>
									<ul>
								<%
								    while(iterin.hasNext())
								    {
								    	Privilege inpriv = iterin.next();
								    	String className = "";
								    	String p = inpriv.getPath();
								    	String link = path +"/"+ p;
								    	if(p != null && uri.contains(p))
								    		className = "class='active'";
									    	
								    	%>
								    	<li><a href="<%=link%>" <%=className %> ><%=inpriv.getName() %></a></li>
								    	
								    	<%
								    }
								
								%>
								
									</ul>
								</li>
								<%
							}
							
							%>
	   </ul>
		</div>
	</div>
</aside> 
<section id="content">
	<div class="container">
		<div class="block-header">
			<h1>Request Complaint</h1>
		</div>
     
          <div class="card">
			<form class="form-horizontal" role="form" method="post">

		
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
	 
	<script src="<%=request.getContextPath() %>/js/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>

	<script src="<%=request.getContextPath() %>/vendors/flot/jquery.flot.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/flot/jquery.flot.resize.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/flot/plugins/curvedLines.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/sparklines/jquery.sparkline.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/easypiechart/jquery.easypiechart.min.js"></script>

	<script src="<%=request.getContextPath() %>/vendors/chosen/chosen.jquery.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/fullcalendar/lib/moment.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/fullcalendar/fullcalendar.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/simpleWeather/jquery.simpleWeather.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/auto-size/jquery.autosize.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/nicescroll/jquery.nicescroll.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/waves/waves.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/sweet-alert/sweet-alert.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/bootstrap-select/bootstrap-select.min.js"></script>

	<script src="<%=request.getContextPath() %>/js/flot-charts/curved-line-chart.js"></script>
	<script src="<%=request.getContextPath() %>/js/flot-charts/line-chart.js"></script>
	<script src="<%=request.getContextPath() %>/js/charts.js"></script>

	<script src="<%=request.getContextPath() %>/vendors/noUiSlider/jquery.nouislider.all.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/input-mask/input-mask.min.js"></script>
	<script src="<%=request.getContextPath() %>/vendors/farbtastic/farbtastic.min.js"></script>

	<script src="<%=request.getContextPath() %>/js/charts.js"></script>
	<script src="<%=request.getContextPath() %>/js/functions.js"></script>
	<script src="<%=request.getContextPath() %>/js/demo.js"></script>
	<script src="<%=request.getContextPath() %>/js/myjs.js"></script>
    
	
	
</body>
</html>