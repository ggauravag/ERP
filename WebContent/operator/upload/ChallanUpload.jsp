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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Upload Documents</title>

<link href="vendors/bower_components/animate.css/animate.min.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/lightgallery/light-gallery/css/lightGallery.css"
	rel="stylesheet">
<link
	href="vendors/bower_components/mediaelement/build/mediaelementplayer.css"
	rel="stylesheet">
<link href="css/app.min.1.css" rel="stylesheet">
<link href="css/app.min.2.css" rel="stylesheet">
<link href="img/favicon.ico" rel="icon" />
<!-- CSS -->
<style>
.modalNew {
	position: fixed;
	z-index: 999;
	height: 100%;
	width: 100%;
	top: 0;
	filter: alpha(opacity = 60);
	opacity: 0.6;
	-moz-opacity: 0.8;
}

.centerNew {
	z-index: 1000;
	margin: 300px auto;
	padding: 10px;
	width: 130px;
	background-color: White;
	border-radius: 10px;
	filter: alpha(opacity = 100);
	opacity: 1;
	-moz-opacity: 1;
}

.centerNew img {
	height: 150px;
	width: 150px;
}
</style>
</head>
<body>

	<input type="hidden" id="basePath" value="<%=basePath%>">
	<%@ include file="../../header.jsp"%>

	<section id="main"> <%@ include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1>
				<bean:message key="operator.uploadChallan.title" />
			</h1>
		</div>

		<div class="card">
			<form class="form-horizontal" enctype="multipart/form-data"
				action="ajaxServlet.do" id="fileForm" method="post" role="form">

				<div class="card-header">
					<h2>
						<bean:message key="operator.uploadChallan.label" />
					</h2>
				</div>
				<div class="card-body card-padding">
					<font color="red"><html:errors property="orderError" /></font>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label"><bean:message
								key="operator.uploadChallan.idLabel" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputOrderID"
									placeholder="Enter Order/Purchase ID">
							</div>
							<span class="zmdi zmdi-shopping-basket form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="row">
						<div class="form-input">
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message
									key="operator.processorder.mobile" /></label>
							<div class="col-sm-6 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputMobile"
										placeholder="Enter Mobile">
								</div>
								<span class="zmdi zmdi-smartphone-android form-control-feedback"></span>
							</div>
						</div>

						<div class="form-input">
							<div class="col-sm-4 m-b-25">
								<div class="fg-line">
									<button type="button" id="searchDocsButton"
										class="btn btn-primary btn-lg col-sm-6">Search</button>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message
								key="operator.uploadChallan.userName" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputName"
									placeholder="Enter Name">
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="form-group">
						<div class="form-input">
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message
									key="operator.uploadChallan.eventDate" /> </label>
							<div class="col-sm-2 m-b-25">
								<div class="dtp-container dropdown fg-line open">
									<input type="text" name="fromDate"
										class="form-control date-picker" data-toggle="dropdown"
										aria-expanded="true" id="inputFromDate"
										placeholder="From Date">
								</div>
								<span class="zmdi zmdi-calendar form-control-feedback"></span>

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
								<span class="zmdi zmdi-calendar form-control-feedback"></span>
							</div>
						</div>

					</div>
					<div>
						<table class="table">
							<thead>
								<tr>
									<th>Select</th>
									<th>ID</th>
									<th>Customer/Merchant Name</th>
									<th>Mobile</th>
									<th>Amount</th>
									<th>Date</th>
									<th>Status</th>
									<th>Type</th>
								</tr>
							</thead>
							<tbody id="fillOrderDetails">

							</tbody>
						</table>
					</div>
				</div>
				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h2>
						<bean:message key="operator.processorder.head2" />
					</h2>
				</div>
				<div class="card-body card-padding">
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message
								key="operator.processorder.cusname" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="selectName" readonly>
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>
					<%-- <div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label"><bean:message key="operator.processorder.email" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" name="email" class="form-control"
									id="selectEmail" readonly>
							</div>
							<span class="zmdi zmdi-email form-control-feedback"></span>
						</div>
					</div> --%>
					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message
								key="operator.processorder.mobile" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" name="mobile" class="form-control"
									id="selectMobile" readonly>
							</div>
							<span class="zmdi zmdi-smartphone-android form-control-feedback"></span>
						</div>
					</div>

					<div>

						<div class="card">
							<div class="card-header">
								<h2>
									Uploaded Documents <small>All Uploaded Documents are
										displayed here.</small>
								</h2>
							</div>

							<div class="card-body card-padding">
								<div class="lightbox photos">
									<div
										data-src="https://dl.dropbox.com/s/pdvekfsktnc0ypf/Click.jpg?dl=0"
										class="col-sm-2 col-xs-6" id="imageDiv">
										<div class="lightbox-item">
											<img
												src="https://dl.dropbox.com/s/pdvekfsktnc0ypf/Click.jpg?dl=0"
												alt="" />
										</div>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>

							<!--  <div class="lightbox row" id="imageDiv0" >
                               	<div data-src="https://dl.dropbox.com/s/pdvekfsktnc0ypf/Click.jpg?dl=0" class="col-sm-2 col-xs-6">
                                    <div class="lightbox-item">
                                        <img src="https://dl.dropbox.com/s/pdvekfsktnc0ypf/Click.jpg?dl=0" alt="" />  
                                    </div>
                                </div>
                                
                                <div data-src="https://dl.dropbox.com/s/pdvekfsktnc0ypf/Click.jpg?dl=0" class="col-sm-2 col-xs-6">
                                    <div class="lightbox-item">
                                        <img src="https://dl.dropbox.com/s/pdvekfsktnc0ypf/Click.jpg?dl=0" alt="" />  
                                    </div>
                                </div>  
                            </div>
                            
                            <br/><br/> -->

						</div>
					</div>
					<div class="card">
						<div class="card-header">
							<h2>
								Download & Remove <small>Select Challan to remove and
									download.</small>
							</h2>
						</div>

						<div class="card-body card-padding">

							<table class="table" id="imageTable">

							</table>
						</div>
					</div>

					<!-- card-body card-padding div 2-->

					<div class="card">
						<div class="card-header">
							<h2>
								Document Upload <small>Select file to upload</small>
							</h2>
						</div>

						<div class="card-body card-padding">

							<br />

							<p class="f-500 c-black m-b-20">Document Preview</p>

							<input id="docId" name="id" type="hidden"> <input
								id="type" name="type" type="hidden">
							<table>
								<tr>
									<td><div class="fileinput fileinput-new"
											data-provides="fileinput">
											<div class="fileinput-preview thumbnail"
												data-trigger="fileinput"></div>
											<div>
												<span class="btn btn-info btn-file"> <span
													class="fileinput-new">Select image</span> <span
													class="fileinput-exists">Change</span> <input
													id="inputFile" type="file" name="file">
												</span> <a href="#" class="btn btn-danger fileinput-exists"
													data-dismiss="fileinput">Remove</a>
											</div>
										</div> <br /> <br />
										<button class="btn btn-success" id="uploadButton"
											type="button">Upload</button></td>
									<td></td>
								</tr>
							</table>

							<br /> <br />
							<div class="progress" id="progressbox" >
								<div id="progressbar"
									class="progress-bar progress-bar-striped progress-bar-success active"
									role="progressbar" aria-valuenow="0" aria-valuemin="0"
									aria-valuemax="100" style="width: 0%">
									<span class="sr-only" id="percent">0% Complete</span>
								</div>
								<p id="message"></p>
							</div>


						</div>
					</div>
				</div>
				<br>
			</form>
		</div>
		<br>
	</div>
	<!-- card div --> <!-- container div --> </section> </section>

	<%@include file="../../js/includejs.jsp"%>
	<script type="text/javascript" src="js/uploadDocs.js"></script>
	<script
		src="vendors/bower_components/lightgallery/light-gallery/js/lightGallery.min.js"></script>
	<script
		src="vendors/bower_components/mediaelement/build/mediaelement-and-player.min.js"></script>
	<script src="vendors/fileinput/fileinput.min.js"></script>
	<script src="http://malsup.github.com/jquery.form.js"></script>
</body>
</html>