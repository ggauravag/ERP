
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
<title>Return Payment</title>

<!-- Vendor CSS -->


<%@include file="/css/includecss.jsp"%>

</head>

<body>
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<%@ include file="../../header.jsp"%>
	<input type="hidden" id="page" value="return">
	<section id="main"> <%@ include
		file="../../panel/leftpanel.jsp"%> <section
		id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.return.returnpayment.label" /></h1>
		</div>

		<div class="card">
			<form action="operator/return/TakeReturn.do" method="get" id="custForm"
				name="custForm" class="form-horizontal" role="form">
				<input type="hidden" name="action" value="payment">

				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h4><bean:message key="operator.return.returnpayment.head1" /></h4>
				</div>
				<div class="card-body card-padding">
					

					<div class="row">
						<div id="mediumDiv" class="form-input">
							<label for="inputMedium" class="col-sm-3 control-label"><bean:message key="operator.return.returnpayment.paymode" /></label>
							<div class="select col-sm-4 m-b-25">
								<select name="modeType" class="form-control" id="selectMedium">
									<option value="Select Payment Mode">Select Payment Mode</option>
									<option value="Cash">Cash</option>
									<option value="Cheque">Cheque</option>
									<option value="PayUMoney Online Gateway">PayUMoney Online Gateway</option>
								</select> <small class="help-block" id="error"><font color="red"><html:errors
											property="mediumError" /></font></small>
							</div>

						</div>

					</div>
					<input type='hidden' name="orderID" value="${orderID}"/>
					<div class="row">
						<div id="amountDiv" class="form-input">
							<label for="inputHouse" class="col-sm-3 control-label"><bean:message key="operator.return.returnpayment.amount" /></label>
							<div class="col-sm-4 m-b-25">
								<div class="fg-line open">
									<input type="text" name="amount" id="inputAmount"
										class="form-control" value="${payAmount}" placeholder="Order Amount">
								</div>
								<small class="help-block" id="error"><font color="red"><html:errors
											property="amountError" /></font></small>
							</div>
						</div>
					</div>

					<div class="row">
						<div id="descriptionDiv" class="form-input">
							<label for="inputHouse" class="col-sm-3 control-label"><bean:message key="operator.return.returnpayment.desc" /></label>
							<div class="col-sm-4 m-b-25">
								<div class="fg-line open">
									<input type="text" name="description" id="inputDescription"
										class="form-control"
										placeholder="Ex : Chq. No. 45789 / 20-11-2015">
								</div>
								<small class="help-block" id="error"><font color="red"><html:errors
											property="descError" /></font></small>
							</div>
						</div>
					</div>


					<div class="row">
						<div id="paidByDiv" class="form-input">
							<label for="inputHouse" class="col-sm-3 control-label"><bean:message key="operator.return.returnpayment.paidby" /></label>
							<div class="col-sm-4 m-b-25">
								<div class="fg-line open">
									<input type="text" name="paidBy" id="inputPaidBy"
										class="form-control" placeholder="Paid By Mr. XYZ">
								</div>
								<small class="help-block" id="error"><font color="red"><html:errors
											property="paidByError" /></font></small>
							</div>
						</div>
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
							class="btn btn-primary btn-lg col-sm-4 col-sm-offset-4">Pay For Return</button>
						
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

	<%@include file="../../js/includejs.jsp"%>
	<script type="text/javascript" src="js/extra.js"></script>
</body>
</html>