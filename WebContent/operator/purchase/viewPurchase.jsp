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
<title>View Purchases</title>
<%@include file="../../css/includecss.jsp"%>

</head>

<body>

	<%@ include file="../../header.jsp"%>

	<input type="hidden" id="page" value="viewOrder">
	<section id="main"> <%@ include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.purchase.viewpurchase.label" /></h1>
		</div>

		<div class="card">
			<form class="form-horizontal"
				action="operator/purchase/DeletePurchase.do" method="post"
				id="payForm" role="form">

				<div class="card-header">
					<h2><bean:message key="operator.purchase.viewpurchase.head1" /></h2>
				</div>
				<div class="card-body card-padding">
					<font color="red"><html:errors property="orderError" /></font>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label"><bean:message key="operator.purchase.viewpurchase.purchaseid" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputPurchaseID"
									placeholder="Enter Purchase ID">
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
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.purchase.viewpurchase.purchasedate" /> </label>
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

						<div class="form-input">
							<div class="col-sm-4 m-b-25">
								<div class="fg-line">
									<button type="button" id="searchPurchaseButton"
										class="btn btn-primary btn-lg">Search Purchase</button>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.purchase.viewpurchase.mername" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputMerchantName"
									placeholder="Enter Name">
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>
					<div id="fillPurchaseDetails"></div>
				</div>
				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h2><bean:message key="operator.purchase.viewpurchase.head2" /></h2>
				</div>

				<div class="card-body card-padding">
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.purchase.viewpurchase.mername" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="displayName"
									readonly>
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.purchase.viewpurchase.mertin" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="displayTin" readonly>
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label"><bean:message key="operator.processorder.email" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" id="displayEmail" class="form-control"
									id="selectEmail" readonly>
							</div>
							<span class="zmdi zmdi-email form-control-feedback"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.processorder.mobile" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" id="displayMobile" class="form-control"
									id="selectMobile" readonly>
							</div>
							<span class="zmdi zmdi-smartphone-android form-control-feedback"></span>
						</div>
					</div>

					<div id="orderDiv" class="form-input">
						<h4>Product Details :</h4>

						<div class="form-group">
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5><bean:message key="operator.processorder.prodname" /></h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5><bean:message key="operator.vieworder.rate" /></h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5><bean:message key="operator.processorder.qty" /></h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5><bean:message key="operator.vieworder.total" /></h5></strong></label>
							</div>
						</div>
						<div id="productDetails"></div>
						<small class="help-block" id="error"><font color="red"><html:errors
									property="orderError" /></font> </small>
					</div>

				</div>
				<!-- card-body card-padding div 2-->

				<div class="card-header">
					<h4><bean:message key="operator.purchase.viewpurchase.head3" /></h4>
				</div>
				<div class="card-body card-padding">
					<input type="hidden" name="itemJSON" id="itemJSON" />

					<div class="row">
						<div class="form-group">
							<label class="control-label col-sm-3 "><bean:message key="operator.vieworder.totalpayment" /> <font
								color="black" size="5px"><span class="WebRupee">&#x20B9;
								</span><label id="total"></label></font>
							</label> <label class="control-label col-sm-3 col-sm-offset-1"><bean:message key="operator.vieworder.paymentmade" /> <font color="green" size="5px"><span
									class="WebRupee">&#x20B9; </span><label id="paid"></label></font>
							</label> <label class="control-label col-sm-3 col-sm-offset-1"><bean:message key="operator.vieworder.due" /> <font color="red" size="5px"><span class="WebRupee">&#x20B9;
								</span><label id="due"></label></font>
							</label>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<th><bean:message key="operator.vieworder.table.txid" /></th>
								<th><bean:message key="operator.vieworder.table.datetime" /></th>
								<th><bean:message key="operator.vieworder.table.amount" /></th>
								<th><bean:message key="operator.vieworder.table.mode" /></th>
								<th><bean:message key="operator.vieworder.table.paidby" /></th>
								<th><bean:message key="operator.purchase.viewpurchase.table.desc" /></th>
							</thead>
							<tbody id="paymentDetails">

							</tbody>
						</table>
					</div>
					<br>
				</div>
				<br /> <br /> <br />
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button"
							onclick="javascript:window.open('operator/AddExpenditure.jsp', '_self', '', '');"
							class="btn btn-primary btn-lg col-sm-4">Pay for Purchase</button>
						<button type="submit"
							class="btn btn-warning btn-lg col-sm-4 col-sm-offset-1">Delete
							Purchase</button>
					</div>
				</div>
				<br>

			</form>
		</div>
		<br>
	</div>
	<!-- card div --> <!-- container div --> </section> </section>

	<%@include file="../../js/includejs.jsp"%>
	<script
		src="<%=basePath%>/vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script
		src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/moment/moment.min.js"></script>
	<script src="vendors/noUiSlider/jquery.nouislider.all.min.js"></script>
	<script src="vendors/input-mask/input-mask.min.js"></script>
	<script src="vendors/farbtastic/farbtastic.min.js"></script>
	<script src="vendors/summernote/summernote.min.js"></script>
	<script
		src="vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="vendors/fileinput/fileinput.min.js"></script>
	<script src="js/purchase.js"></script>

	<jspcore:if test="${status == true}">
		<script>
			swal("Purchase Deleted",
					"The purchase has been successfully deleted !", "success");
		</script>
		<jspcore:remove var="status" scope="request"/>
	</jspcore:if>
	<jspcore:if test="${status == false}">
		<script>
			swal(
					"Unable to Delete Purchase",
					"There was some error while deleting the purchase, please try again later !",
					"error");
		</script>
		<jspcore:remove var="status" scope="request"/>
	</jspcore:if>

</body>
</html>