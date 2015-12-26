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
<title>Generate Letter Head</title>
<%@include file="../../css/includecss.jsp"%>
<link href="vendors/summernote/summernote.css" rel="stylesheet">
</head>

<body>
	<input type="hidden" id="letter">
	<%@ include file="../../header.jsp"%>
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<section id="main"> <%@ include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.order.generateletterhead.label" /></h1>
		</div>

		<div class="card">
			<form class="form-horizontal" target="_blank" action="PrintOrder.do"
				method="post" id="letterForm" role="form">
				<input type="hidden" name="print" value="letterHead" /> <input
					type="hidden" name="letterText" id="letterTextField" />

				<div class="card-header">
					<h2><bean:message key="operator.order.generateletterhead.head1" /></h2>
				</div>
				<div class="card-body card-padding">
					<font color="red"><html:errors property="orderError" /></font>

					<div class="form-group">
						<label class="col-sm-2 control-label"><bean:message key="operator.order.generateletterhead.date" /> </label>
						<div class="input-group form-group col-sm-2">
							<span class="input-group-addon"><i class="zmdi zmdi-event"></i></span>
							<div class="dtp-container dropdown fg-line">
								<input type='text' name="date" class="form-control date-picker"
									data-toggle="dropdown" placeholder="Letter Date">
							</div>
							<span class="zmdi zmdi-calendar form-control-feedback"></span>
						</div>
						 
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><bean:message key="operator.order.generateletterhead.refno" /> </label>
						<div class="input-group col-sm-2">
							<div class="fg-line">
								<input type="text" name="refNum" class="form-control"
									placeholder="Ref. Number (if any)">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><bean:message key="operator.order.generateletterhead.to" /> </label>
						<div class="input-group col-sm-2">
							<div class="fg-line">
								<input type="text" name="to" class="form-control"
									placeholder="Client Name">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><bean:message key="operator.order.generateletterhead.addressotherdetail" /> </label>
						<div class="input-group col-sm-8">
							<div class="fg-line">
								<input type="text" name="toAddress" class="form-control"
									placeholder="Customer Address or Other relevant details">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><bean:message key="operator.order.generateletterhead.contact" /> </label>
						<div class="input-group col-sm-8">
							<div class="fg-line">
								<input type="text" name="toContact" class="form-control"
									placeholder="Mobile or Other relevant details">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><bean:message key="operator.order.generateletterhead.subject" />
						</label>
						<div class="input-group col-sm-7">
							<div class="fg-line">
								<input type="text" name="subject" class="form-control"
									placeholder="Ex. Quotation">
							</div>
						</div>
					</div>

					<div class="html-editor" id="letterText"></div>

					<br /> <br />


				</div>

			</form>
			<div class="form-group">
				<div class="row">
					<button type="submit" id="generateLetterHead"
						class="btn btn-primary btn-lg col-sm-4 col-sm-offset-4">Generate
						Letter Head</button>
				</div>
			</div>
			<br>
		</div>
		<!-- card-body card-padding div 1-->

	</div>

	<br>
	<div class="modal fade" data-modal-color="blue" id="modalFirm"
		data-backdrop="static" data-keyboard="false" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Select Billing Firm :</h4>
				</div>
				<div class="modal-body" id="firmDiv"></div>
				<div class="modal-footer">
					<button type="button" id="selectFirmID" class="btn btn-link">Select</button>
					<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>



	<br>

	<!-- card div --> <!-- container div --> </section> </section>

	<%@include file="../../js/includejs.jsp"%>
	<script src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/moment/moment.min.js"></script>
	<script src="vendors/noUiSlider/jquery.nouislider.all.min.js"></script>
	<script src="vendors/input-mask/input-mask.min.js"></script>
	<script src="vendors/farbtastic/farbtastic.min.js"></script>
	<script src="vendors/summernote/summernote.min.js"></script>
	<script src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/fileinput/fileinput.min.js"></script>
	<script src="js/extra.js"></script>
	
	<script>
		$("#generateLetterHead").click(function() {
			loadFirms();
		});
	</script>
</body>
</html>