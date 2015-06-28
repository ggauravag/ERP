<%@page import="com.dbt.dao.LoginDAO"%>
<%@page import="com.dbt.data.Privilege"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
	String basePath = request.getContextPath();
%>
<%@ page import="com.dbt.data.User"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->

<!-- Mirrored from byrushan.com/projects/ma/v1-3-1/ by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 21 May 2015 12:08:46 GMT -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><bean:message key="operator.dashboard.label" /></title>

<!-- Vendor CSS -->
<link href="vendors/fullcalendar/fullcalendar.css" rel="stylesheet">
<link href="vendors/animate-css/animate.min.css" rel="stylesheet">
<link href="vendors/sweet-alert/sweet-alert.min.css" rel="stylesheet">
<link href="vendors/material-icons/material-design-iconic-font.min.css"
	rel="stylesheet">
<link href="vendors/socicon/socicon.min.css" rel="stylesheet">

<!-- CSS -->
<link href="css/app.min.1.css" rel="stylesheet">
<link href="css/app.min.2.css" rel="stylesheet">

</head>
<body>
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<%@ include file="header.jsp"%>

	<section id="main">

		<%@include file="panel/leftpanel.jsp"%>


		<section id="content">
			<div class="container">
				<div class="block-header">
					<h2>Dashboard</h2>
					<ul class="actions">
						<li><a href="#"> <i class="md md-trending-up"></i>
						</a></li>
						<li><a href="#"> <i class="md md-done-all"></i>
						</a></li>
						<li class="dropdown"><a href="#" data-toggle="dropdown">
								<i class="md md-more-vert"></i>
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
							<li><a href="#"> <i class="md md-cached"></i>
							</a></li>
							<li><a href="#"> <i class="md md-file-download"></i>
							</a></li>
							<li class="dropdown"><a href="#" data-toggle="dropdown">
									<i class="md md-more-vert"></i>
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
												<i class="md md-more-vert"></i>
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
					<div class="col-sm-6">
						<!-- Calendar -->
						<div id="calendar-widget"></div>
					</div>

					<div class="col-sm-6">
						<!-- Recent Items -->
						<div class="card">
							<div class="card-header">
								<h2>
									Recent Items <small>Phasellus condimentum ipsum id
										auctor imperdie</small>
								</h2>
								<ul class="actions">
									<li class="dropdown"><a href="#" data-toggle="dropdown">
											<i class="md md-more-vert"></i>
									</a>

										<ul class="dropdown-menu dropdown-menu-right">
											<li><a href="#">Refresh</a></li>
											<li><a href="#">Settings</a></li>
											<li><a href="#">Other Settings</a></li>
										</ul></li>
								</ul>
							</div>

							<div class="card-body m-t-0">
								<table class="table table-inner table-vmiddle">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th style="width: 60px">Price</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="f-500 c-cyan">2569</td>
											<td>Samsung Galaxy Mega</td>
											<td class="f-500 c-cyan">$521</td>
										</tr>
										<tr>
											<td class="f-500 c-cyan">9658</td>
											<td>Huawei Ascend P6</td>
											<td class="f-500 c-cyan">$440</td>
										</tr>
										<tr>
											<td class="f-500 c-cyan">1101</td>
											<td>HTC One M8</td>
											<td class="f-500 c-cyan">$680</td>
										</tr>
										<tr>
											<td class="f-500 c-cyan">6598</td>
											<td>Samsung Galaxy Alpha</td>
											<td class="f-500 c-cyan">$870</td>
										</tr>
										<tr>
											<td class="f-500 c-cyan">4562</td>
											<td>LG G3</td>
											<td class="f-500 c-cyan">$690</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div id="recent-items-chart" class="flot-chart"></div>
						</div>

					</div>
				</div>
			</div>

		</section>
	</section>

	<!-- Older IE warning message -->
	<!--[if lt IE 9]>
            <div class="ie-warning">
                <h1 class="c-white">IE SUCKS!</h1>
                <p>You are using an outdated version of Internet Explorer, upgrade to any of the following web browser <br/>in order to access the maximum functionality of this website. </p>
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
                <p>Upgrade your browser for a Safer and Faster web experience. <br/>Thank you for your patience...</p>
            </div>   
        <![endif]-->

	<!-- Javascript Libraries -->
	<%@include file="js/includejs.jsp"%>
</body>
</html>