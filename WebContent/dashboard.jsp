<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->
<%
	String basePath = request.getContextPath();
    String bPath = request.getScheme() + "://" + request.getServerName () + ":" + request.getServerPort () + basePath + "/";
%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Mirrored from 192.185.228.226/projects/ma/1-5-1/jquery/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 30 Oct 2015 19:30:30 GMT -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><bean:message key="operator.dashboard.label" /></title>

<!-- Vendor CSS -->
<link
	href="vendors/bower_components/fullcalendar/dist/fullcalendar.min.css"
	rel="stylesheet">
<link href="vendors/bower_components/animate.css/animate.min.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css"
	rel="stylesheet">
<link href="vendors/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

<link href="img/favicon.ico" rel="icon" />

<!-- CSS -->
<link href="css/app.min.1.css" rel="stylesheet">
<link href="css/app.min.2.css" rel="stylesheet">

</head>
<body>

	<input type="hidden" id="basePath" value="<%=basePath%>">
	<%@ include file="header.jsp"%>

	<section id="main">
		<%@include file="panel/leftpanel.jsp"%>

		<aside id="chat">
			<ul class="tab-nav tn-justified" role="tablist">
				<li role="presentation" class="active"><a href="#friends"
					aria-controls="friends" role="tab" data-toggle="tab">Friends</a></li>
				<li role="presentation"><a href="#online"
					aria-controls="online" role="tab" data-toggle="tab">Online Now</a></li>
			</ul>

			<div class="chat-search">
				<div class="fg-line">
					<input type="text" class="form-control" placeholder="Search People">
				</div>
			</div>

			<div class="tab-content">
				<div role="tabpanel" class="tab-pane fade in active" id="friends">
					<div class="listview">
						<a class="lv-item" href="#">
							<div class="media">
								<div class="pull-left p-relative">
									<img class="lv-img-sm" src="img/profile-pics/2.jpg" alt="">
									<i class="chat-status-busy"></i>
								</div>
								<div class="media-body">
									<div class="lv-title">Jonathan Morris</div>
									<small class="lv-small">Available</small>
								</div>
							</div>
						</a> <a class="lv-item" href="#">
							<div class="media">
								<div class="pull-left">
									<img class="lv-img-sm" src="img/profile-pics/1.jpg" alt="">
								</div>
								<div class="media-body">
									<div class="lv-title">David Belle</div>
									<small class="lv-small">Last seen 3 hours ago</small>
								</div>
							</div>
						</a> <a class="lv-item" href="#">
							<div class="media">
								<div class="pull-left p-relative">
									<img class="lv-img-sm" src="img/profile-pics/3.jpg" alt="">
									<i class="chat-status-online"></i>
								</div>
								<div class="media-body">
									<div class="lv-title">Fredric Mitchell Jr.</div>
									<small class="lv-small">Availble</small>
								</div>
							</div>
						</a> <a class="lv-item" href="#">
							<div class="media">
								<div class="pull-left p-relative">
									<img class="lv-img-sm" src="img/profile-pics/4.jpg" alt="">
									<i class="chat-status-online"></i>
								</div>
								<div class="media-body">
									<div class="lv-title">Glenn Jecobs</div>
									<small class="lv-small">Availble</small>
								</div>
							</div>
						</a> <a class="lv-item" href="#">
							<div class="media">
								<div class="pull-left">
									<img class="lv-img-sm" src="img/profile-pics/5.jpg" alt="">
								</div>
								<div class="media-body">
									<div class="lv-title">Bill Phillips</div>
									<small class="lv-small">Last seen 3 days ago</small>
								</div>
							</div>
						</a> <a class="lv-item" href="#">
							<div class="media">
								<div class="pull-left">
									<img class="lv-img-sm" src="img/profile-pics/6.jpg" alt="">
								</div>
								<div class="media-body">
									<div class="lv-title">Wendy Mitchell</div>
									<small class="lv-small">Last seen 2 minutes ago</small>
								</div>
							</div>
						</a> <a class="lv-item" href="#">
							<div class="media">
								<div class="pull-left p-relative">
									<img class="lv-img-sm" src="img/profile-pics/7.jpg" alt="">
									<i class="chat-status-busy"></i>
								</div>
								<div class="media-body">
									<div class="lv-title">Teena Bell Ann</div>
									<small class="lv-small">Busy</small>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane fade" id="online">
					<div class="listview">
						<a class="lv-item" href="#">
							<div class="media">
								<div class="pull-left p-relative">
									<img class="lv-img-sm" src="img/profile-pics/2.jpg" alt="">
									<i class="chat-status-busy"></i>
								</div>
								<div class="media-body">
									<div class="lv-title">Jonathan Morris</div>
									<small class="lv-small">Available</small>
								</div>
							</div>
						</a> <a class="lv-item" href="#">
							<div class="media">
								<div class="pull-left p-relative">
									<img class="lv-img-sm" src="img/profile-pics/3.jpg" alt="">
									<i class="chat-status-online"></i>
								</div>
								<div class="media-body">
									<div class="lv-title">Fredric Mitchell Jr.</div>
									<small class="lv-small">Availble</small>
								</div>
							</div>
						</a> <a class="lv-item" href="#">
							<div class="media">
								<div class="pull-left p-relative">
									<img class="lv-img-sm" src="img/profile-pics/4.jpg" alt="">
									<i class="chat-status-online"></i>
								</div>
								<div class="media-body">
									<div class="lv-title">Glenn Jecobs</div>
									<small class="lv-small">Availble</small>
								</div>
							</div>
						</a> <a class="lv-item" href="#">
							<div class="media">
								<div class="pull-left p-relative">
									<img class="lv-img-sm" src="img/profile-pics/7.jpg" alt="">
									<i class="chat-status-busy"></i>
								</div>
								<div class="media-body">
									<div class="lv-title">Teena Bell Ann</div>
									<small class="lv-small">Busy</small>
								</div>
							</div>
						</a>
					</div>
				</div>
			</div>
		</aside>


		<section id="content">
			<div class="container">
				<div class="block-header">
					<h2>Dashboard</h2>

					<ul class="actions">
						<li><a href="#"> <i class="zmdi zmdi-trending-up"></i>
						</a></li>
						<li><a href="#"> <i class="zmdi zmdi-check-all"></i>
						</a></li>
						<li class="dropdown"><a href="#" data-toggle="dropdown">
								<i class="zmdi zmdi-more-vert"></i>
						</a>

							<ul class="dropdown-menu dropdown-menu-right">
								<li><a href="#">Refresh</a></li>
								<li><a href="#">Manage Widgets</a></li>
								<li><a href="#">Widgets Settings</a></li>
							</ul></li>
					</ul>

				</div>

				<div class="card">
					<div class="card-header">
						<h2>
							Sales Statistics <small>Vestibulum purus quam
								scelerisque, mollis nonummy metus</small>
						</h2>

						<ul class="actions">
							<li><a href="#"> <i class="zmdi zmdi-refresh-alt"></i>
							</a></li>
							<li><a href="#"> <i class="zmdi zmdi-download"></i>
							</a></li>
							<li class="dropdown"><a href="#" data-toggle="dropdown">
									<i class="zmdi zmdi-more-vert"></i>
							</a>

								<ul class="dropdown-menu dropdown-menu-right">
									<li><a href="#">Change Date Range</a></li>
									<li><a href="#">Change Graph Type</a></li>
									<li><a href="#">Other Settings</a></li>
								</ul></li>
						</ul>
					</div>

					<div class="card-body">
						<div class="chart-edge">
							<div id="curved-line-chart" class="flot-chart "></div>
						</div>
					</div>
				</div>

				<div class="mini-charts">
					<div class="row">
						<div class="col-sm-6 col-md-3">
							<div class="mini-charts-item bgm-cyan">
								<div class="clearfix">
									<div class="chart stats-bar"></div>
									<div class="count">
										<small>Website Traffics</small>
										<h2>987,459</h2>
									</div>
								</div>
							</div>
						</div>

						<div class="col-sm-6 col-md-3">
							<div class="mini-charts-item bgm-lightgreen">
								<div class="clearfix">
									<div class="chart stats-bar-2"></div>
									<div class="count">
										<small>Website Impressions</small>
										<h2>356,785K</h2>
									</div>
								</div>
							</div>
						</div>

						<div class="col-sm-6 col-md-3">
							<div class="mini-charts-item bgm-orange">
								<div class="clearfix">
									<div class="chart stats-line"></div>
									<div class="count">
										<small>Total Sales</small>
										<h2>$ 458,778</h2>
									</div>
								</div>
							</div>
						</div>

						<div class="col-sm-6 col-md-3">
							<div class="mini-charts-item bgm-bluegray">
								<div class="clearfix">
									<div class="chart stats-line-2"></div>
									<div class="count">
										<small>Support Tickets</small>
										<h2>23,856</h2>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="dash-widgets">
					<div class="row">
						<div class="col-md-3 col-sm-6">
							<div id="site-visits" class="dash-widget-item bgm-teal">
								<div class="dash-widget-header">
									<div class="p-20">
										<div class="dash-widget-visits"></div>
									</div>

									<div class="dash-widget-title">For the past 30 days</div>

									<ul class="actions actions-alt">
										<li class="dropdown"><a href="#" data-toggle="dropdown">
												<i class="zmdi zmdi-more-vert"></i>
										</a>

											<ul class="dropdown-menu dropdown-menu-right">
												<li><a href="#">Refresh</a></li>
												<li><a href="#">Manage Widgets</a></li>
												<li><a href="#">Widgets Settings</a></li>
											</ul></li>
									</ul>
								</div>

								<div class="p-20">

									<small>Page Views</small>
									<h3 class="m-0 f-400">47,896,536</h3>

									<br /> <small>Site Visitors</small>
									<h3 class="m-0 f-400">24,456,799</h3>

									<br /> <small>Total Clicks</small>
									<h3 class="m-0 f-400">13,965</h3>
								</div>
							</div>
						</div>

						<div class="col-md-3 col-sm-6">
							<div id="pie-charts" class="dash-widget-item">
								<div class="bgm-pink">
									<div class="dash-widget-header">
										<div class="dash-widget-title">Email Statistics</div>
									</div>

									<div class="clearfix"></div>

									<div class="text-center p-20 m-t-25">
										<div class="easy-pie main-pie" data-percent="75">
											<div class="percent">45</div>
											<div class="pie-title">Total Emails Sent</div>
										</div>
									</div>
								</div>

								<div class="p-t-20 p-b-20 text-center">
									<div class="easy-pie sub-pie-1" data-percent="56">
										<div class="percent">56</div>
										<div class="pie-title">Bounce Rate</div>
									</div>
									<div class="easy-pie sub-pie-2" data-percent="84">
										<div class="percent">84</div>
										<div class="pie-title">Total Opened</div>
									</div>
								</div>

							</div>
						</div>

						<div class="col-md-3 col-sm-6">
							<div class="dash-widget-item bgm-lime">
								<div id="weather-widget"></div>
							</div>
						</div>

						<div class="col-md-3 col-sm-6">
							<div id="best-selling" class="dash-widget-item">
								<div class="dash-widget-header">
									<div class="dash-widget-title">Best Sellings</div>
									<img src="img/widgets/alpha.jpg" alt="">
									<div class="main-item">
										<small>Samsung Galaxy Alpha</small>
										<h2>$799.99</h2>
									</div>
								</div>

								<div class="listview p-t-5">
									<a class="lv-item" href="#">
										<div class="media">
											<div class="pull-left">
												<img class="lv-img-sm" src="img/widgets/note4.jpg" alt="">
											</div>
											<div class="media-body">
												<div class="lv-title">Samsung Galaxy Note 4</div>
												<small class="lv-small">$850.00 - $1199.99</small>
											</div>
										</div>
									</a> <a class="lv-item" href="#">
										<div class="media">
											<div class="pull-left">
												<img class="lv-img-sm" src="img/widgets/mate7.jpg" alt="">
											</div>
											<div class="media-body">
												<div class="lv-title">Huawei Ascend Mate</div>
												<small class="lv-small">$649.59 - $749.99</small>
											</div>
										</div>
									</a> <a class="lv-item" href="#">
										<div class="media">
											<div class="pull-left">
												<img class="lv-img-sm" src="img/widgets/535.jpg" alt="">
											</div>
											<div class="media-body">
												<div class="lv-title">Nokia Lumia 535</div>
												<small class="lv-small">$189.99 - $250.00</small>
											</div>
										</div>
									</a> <a class="lv-footer" href="#"> View All </a>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="card col-sm-6">
						<div class="card-header">
								<h2>
									Manage Reminders <small>Set, Remove or View Reminders
										here</small>
								</h2>
								<ul class="actions">
									<li class="dropdown">
									<a href="#" data-toggle="dropdown">
											<i class="zmdi zmdi-more-vert"></i>
									</a>

										<ul class="dropdown-menu dropdown-menu-right">
											<li><a href="" onclick="javascript:showReminders();return false;">View
													Reminder</a></li>

										</ul>
									</li>
								</ul>
						</div>
							
						<div class="card-body">
								<small style="color: red" id="errorMessage"></small>
								 <div class="form-group fg-float">
									<div class="fg-line">
										<input type="text" id="reminderTitle" class="form-control">
									</div>
									<label class="fg-label"><b>Title for Reminder</b></label>
								</div>
						
								<div class="form-group fg-float">
									<div class="fg-line">
										<input type="text" id="inputMobile" class="form-control">
									</div>
									<label class="fg-label"><b>Mobile Number to receive
											SMS</b></label>
								</div>
	
								<label class="control-label"><b>Message Template : </b></label>
								<div class="radio m-b-20">
									<label class="radio radio-inline m-r-20"> <input
										type="radio" name="messTemplate" value="cheque"> <i
										class="input-helper"></i> Cheque/Payment
									</label> <label class="radio radio-inline m-r-20"> <input
										type="radio" name="messTemplate" value="order"> <i
										class="input-helper"></i> Order Delivery
									</label>

								</div>

								<label class="control-label"><b>Message : </b></label>
								<div class="form-group">
									<div class="fg-line">
										<textarea class="form-control"
											placeholder="Enter your message here for reminder..."
											name="message" id="message"
											></textarea>
									</div>
								</div>

								<label class="control-label"><b>Frequency : </b></label>
								<div class="radio m-b-20">
									<label class="radio radio-inline m-r-20"> <input
										type="radio" name="frequency" value="DAILY" checked> <i
										class="input-helper"></i> Daily
									</label> <label class="radio radio-inline m-r-20"> <input
										type="radio" name="frequency" value="WEEKLY"> <i
										class="input-helper"></i> Weekly
									</label> <label class="radio radio-inline m-r-20"> <input
										type="radio" name="frequency" value="MONTHLY"> <i
										class="input-helper"></i> Monthly
									</label> <label class="radio radio-inline m-r-20"> <input
										type="radio" name="frequency" value="YEARLY"> <i
										class="input-helper"></i> Yearly
									</label>
								</div> 

								<div class="row form-group">
									<div class="input-group">
										
										<div class="dtp-container dropdown fg-line col-sm-2">
											<input type="text" id="startDate"
												class="form-control date-time-picker" data-toggle="dropdown"
												placeholder="Select Start Date..." aria-expanded="false">
											
										</div>
										<span class="input-group-addon"><i class="zmdi zmdi-calendar"></i></span>
									</div>
									<div class="input-group">
										
										<div class="dtp-container dropdown fg-line col-sm-2">
											<input type="text" id="endDate"
												class="form-control date-time-picker" data-toggle="dropdown"
												placeholder="Select End Date..." aria-expanded="false">

										</div>
										<span class="input-group-addon"><i class="zmdi zmdi-calendar"></i></span>
									</div>
								</div> 

								<button
									class="btn btn-info"
									id="setReminder">Set Reminder</button>
								<button type="reset"
									class="btn btn-link">Reset</button>		
						</div>
						<br>
						<div class="modal fade" id="modalReminder" tabindex="-1" role="dialog"
				aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Reminders</h4>
						</div>
						<div class="modal-body">
							<table class="table table-hover">
								<thead>
								<tr>
									<th>Title</th>
									<th>Message</th>
									<th>Start Date</th>
									<th>End Date</th>
									<th>Frequency</th>
								</tr>
								</thead>
								<tbody id="reminders">
								
								</tbody>
							
							</table>
						</div>
						<div class="modal-footer">
							
							<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
					</div>
					
					<div class="card">
						<div class="col-sm-6">
							<!-- Recent Items -->
							<div class="card">
								<div id="calendar-widget"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</section>

	<footer id="footer">
		Copyright &copy; 2015 Material Admin

		<ul class="f-menu">
			<li><a href="#">Home</a></li>
			<li><a href="#">Dashboard</a></li>
			<li><a href="#">Reports</a></li>
			<li><a href="#">Support</a></li>
			<li><a href="#">Contact</a></li>
		</ul>
	</footer>

	<!-- Older IE warning message -->
	<!--[if lt IE 9]>
            <div class="ie-warning">
                <h1 class="c-white">Warning!!</h1>
                <p>You are using an outdated version of Internet Explorer, please upgrade <br/>to any of the following web browsers to access this website.</p>
                <div class="iew-container">
                    <ul class="iew-download">
                        <li>
                            <a href="http://www.google.com/chrome/">
                                <img src="img/browsers/chrome.png" alt="">
                                <div>Chrome</div>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.mozilla.org/en-US/firefox/new/">
                                <img src="img/browsers/firefox.png" alt="">
                                <div>Firefox</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://www.opera.com">
                                <img src="img/browsers/opera.png" alt="">
                                <div>Opera</div>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.apple.com/safari/">
                                <img src="img/browsers/safari.png" alt="">
                                <div>Safari</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                                <img src="img/browsers/ie.png" alt="">
                                <div>IE (New)</div>
                            </a>
                        </li>
                    </ul>
                </div>
                <p>Sorry for the inconvenience!</p>
            </div>   
        <![endif]-->

	<!-- Javascript Libraries -->
	<script src="vendors/bower_components/jquery/dist/jquery.min.js"></script>
	<script
		src="vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<script src="vendors/bower_components/flot/jquery.flot.js"></script>
	<script src="vendors/bower_components/flot/jquery.flot.resize.js"></script>
	<script src="vendors/bower_components/flot.curvedlines/curvedLines.js"></script>
	<script src="vendors/sparklines/jquery.sparkline.min.js"></script>
	<script
		src="vendors/bower_components/jquery.easy-pie-chart/dist/jquery.easypiechart.min.js"></script>

	<script src="vendors/bower_components/moment/min/moment.min.js"></script>
	<script
		src="vendors/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
	<script
		src="vendors/bower_components/simpleWeather/jquery.simpleWeather.min.js"></script>
	<script
		src="vendors/bower_components/jquery.nicescroll/jquery.nicescroll.min.js"></script>
	<script src="vendors/bower_components/Waves/dist/waves.min.js"></script>
	<script src="vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
	<script
		src="vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.min.js"></script>
	<script src="vendors/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
	
	<!-- Placeholder for IE9 -->
	<!--[if IE 9 ]>
            <script src="vendors/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
        <![endif]-->

	<script src="js/flot-charts/curved-line-chart.js"></script>
	<script src="js/flot-charts/line-chart.js"></script>
	<script src="js/charts.js"></script>

	<script src="js/charts.js"></script>
	<script src="js/functions.js"></script>
	<script src="js/demo.js"></script>

	<script src="js/dashboard.js"></script>
</body>

</html>