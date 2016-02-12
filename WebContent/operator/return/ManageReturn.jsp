
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
<title>Manage Returns</title>

<!-- Vendor CSS -->


<%@include file="/css/includecss.jsp"%>

</head>

<body>
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<%@ include file="../../header.jsp"%>
	<input type="hidden" id="page" value="return">
	<input type="hidden" name="action" value="return">
	<section id="main"> <%@ include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.return.managereturn.label" /></h1>
		</div>

		<div class="card">
			<form action="operator/return/TakeReturn.do" method="post" id="custForm"
				name="custForm" class="form-horizontal" role="form">
				<input type="hidden" name="action" value="return">
				<div class="card-header">
					<h2><bean:message key="operator.return.managereturn.head1" /></h2>
				</div>
				<div class="card-body card-padding">
					<font color="red"><html:errors property="orderError" /></font>
					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label"><bean:message key="operator.complaint.processcomp.orderid" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputOrderID"
									placeholder="Enter Order ID">
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
							<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.complaint.processcomp.mobile" /></label>
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
									<button type="button" id="searchOrderBtn"
										class="btn btn-primary btn-lg col-sm-6">Search Order</button>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="inputOrder" class="col-sm-2 control-label">OR</label>
						<div class="col-sm-6"></div>
					</div>
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.feedback.viewfeedback.custname" /></label>
						<div class="col-sm-6">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputName"
									placeholder="Enter Name">
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>
					<div id="fillOrderDetails"></div>
				</div>
				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h2><bean:message key="operator.return.managereturn.head2" /></h2>
				</div>

				<div class="card-body card-padding">
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.feedback.viewfeedback.custname" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="selectName" readonly>
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label"><bean:message key="operator.editorder.email" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" name="email" class="form-control"
									id="selectEmail" readonly>
							</div>
							<span class="zmdi zmdi-email form-control-feedback"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.editorder.mobile" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" name="mobile" class="form-control"
									id="selectMobile" readonly>
							</div>
							<span class="zmdi zmdi-smartphone-android form-control-feedback"></span>
						</div>
					</div>

					<div id="orderDiv" class="form-input">
						<h4>Product Details :</h4>
						<input type="hidden" name="returnAmount" id="returnAmount" value="0"/>
						<div class="form-group">
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5><bean:message key="operator.editorder.prodname" /></h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5><bean:message key="operator.vieworder.rate" /></h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5><bean:message key="operator.editorder.qty" /></h5></strong></label>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<label class="control-label"><strong><h5><bean:message key="operator.vieworder.total" /></h5></strong></label>
							</div>
						</div>
						<div id="productDetails"></div>
						<small class="help-block" id="error"><font color="red"><html:errors
									property="orderError" /></font> </small>
					</div>
					<div id="returnDiv" class="form-input">
						<h4>Return Details :</h4>
						<div class="form-group">
							<div class="col-sm-2">
								<label class="control-label"><strong><h5><bean:message key="operator.editorder.prodname" /></h5></strong></label>
							</div>
							<div class="col-sm-2">
								<label class="control-label"><strong><h5><bean:message key="operator.vieworder.rate" /></h5></strong></label>
							</div>
							<div class="col-sm-2">
								<label class="control-label"><strong><h5><h5><bean:message key="operator.editorder.qty" /></h5></strong></label>
							</div>
							
							<div class="col-sm-2">
								<label class="control-label"><strong><h5><bean:message key="operator.return.managereturn.returntime" /></h5></strong></label>
							</div>
							<div class="col-sm-2">
								<label class="control-label"><strong><h5><bean:message key="operator.return.managereturn.returnqty" /></h5></strong></label>
							</div>
							<div class="col-sm-2">
								<label class="control-label"><strong><h5><bean:message key="operator.return.managereturn.return" /></h5></strong></label>
							</div>
						</div>
						<div id="returnDetails"></div>
						<small class="help-block" id="error"><font color="red"><html:errors
									property="returnError" /></font> </small>
					</div>
				</div>
				<!-- card-body card-padding div 1-->

				<!-- card-body card-padding div 1-->



				<div id="products"></div>
				<input type="hidden" id="numProd" name="numProd" value="0">
				<br />
				<div class="form-group">
					<div class="row">
						<button type="submit"
							class="btn btn-primary btn-lg col-sm-3 col-sm-offset-2">Process
							Return</button>
						<button type="reset"
							class="btn btn-warning btn-lg col-sm-3 col-sm-offset-3"
							style="margin-left: 10%">Reset</button>
					</div>
				</div>
				<br /> <br />
		</div>
		<!-- card-body card-padding div 2-->
		</form>
	</div>
	<!-- card div -->
	</div>
	<!-- container div --> </section> </section>
	<!-- Javascript Libraries -->

	<%@include file="/js/includejs.jsp"%>
	<script type="text/javascript" src="js/extra.js"></script>
	<jspcore:if test="${status == 'success'}">
		<script>
		setTimeout(function(){swal("Return Processed","The returned items has been suceesfully processed and the payment for the return has been credited !","success")},0);
		</script>
		<jspcore:remove var="status" scope="session"/>
	</jspcore:if>
	<jspcore:if test="${status == 'failure'}">
		<script>
		setTimeout(function(){swal("Unable to Process Return","The return request cannot be processed now, please try again later !","error")},0);
		</script>
		<jspcore:remove var="status" scope="session"/>
	</jspcore:if>
</body>
</html>