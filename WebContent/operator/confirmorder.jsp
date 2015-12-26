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
<title>Confirm Order</title>

<!-- Vendor CSS -->
<%@include file="../css/includecss.jsp"%>
</head>

<body>


	<%@ include file="../header.jsp"%>

	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1>
				<bean:message key="operator.confirmorder.label" />
			</h1>
		</div>

		<div class="card">
			<form class="form-horizontal" action="operator/ConfirmOrder.do"
				method="post" role="form">

				<div class="card-header">
					<h2>
						<bean:message key="operator.confirmorder.head1" />
					</h2>
				</div>
				<div class="card-body card-padding">
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message
								key="operator.confirmorder.cusname" /></label>
						<div class="col-sm-8">
							<div class="fg-line disabled">
								<input type="text" class="form-control" id="inputName"
									value="${param.custName}" disabled>
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label"><bean:message
								key="operator.confirmorder.email" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" class="form-control" id="inputEmail"
									value="${param.custEmail}" disabled>
							</div>
							<span class="zmdi zmdi-email form-control-feedback"></span>
						</div>
					</div>

					<div class="row">
						<div class="form-input">
							<label for="inputHouse" class="col-sm-2 control-label"><bean:message
									key="operator.confirmorder.address" /></label>
							<div class="col-sm-2 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputHouse"
										value="${param.custHouseNo}" disabled>
								</div>
							</div>
						</div>
						<div class="form-input">
							<div class="col-sm-6 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputAddress1"
										value="${param.custAdd1}" disabled>
								</div>
								<span class="zmdi zmdi-my-location form-control-feedback"></span>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="inputAddress2" class="col-sm-2 control-label"></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputAddress2"
									value="${param.custAdd2}" disabled>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-input">
							<label for="inputCity" class="col-sm-2 control-label"></label>
							<div class="col-sm-3 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="city"
										value="${param.custCity}" disabled>
								</div>
							</div>
						</div>

						<div class="form-input">
							<div class="col-sm-3 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="state"
										value="${param.custState}" disabled>
								</div>
							</div>
						</div>

						<div class="form-input">
							<div class="col-sm-2">
								<div class="fg-line">
									<input type="text" class="form-control"
										value="${param.custPinCode}" disabled>
								</div>
								<span class="zmdi zmdi-pin form-control-feedback"></span>
							</div>
						</div>

					</div>

					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message
								key="operator.confirmorder.mobile" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputMobile"
									value="${param.custMobile}" disabled>
							</div>
							<span class="zmdi zmdi-smartphone-android form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message
								key="operator.confirmorder.amount" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputMobile"
									value="${orderAmount}" disabled>
							</div>
							<span class="zmdi zmdi-money form-control-feedback"></span>
						</div>
					</div>
				</div>
				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h2>
						<bean:message key="operator.confirmorder.head2" />
					</h2>
				</div>
				<div class="card-body card-padding">
					<div id="productForm">

						<jspcore:forEach var="i" begin="0" end="${param.numProd - 1}">
							<div class="row">
								<label for="selectProduct" class="col-sm-2 control-label"><bean:message
										key="operator.confirmorder.product" /></label>
								<div class="col-sm-8">
									<div class="form-group">
										<div class="fg-line">
											<input type="text" class="form-control" id="selectProduct"
												value='${paramValues.prodName[i]}' disabled>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<label for="inputProduct" class="col-sm-2 control-label"><bean:message
										key="operator.confirmorder.qty" /></label>

								<div class="col-sm-3">
									<div class="form-group">
										<div class="fg-line">
											<input type="text" id="quantity" class="form-control"
												value='${paramValues.prodQty[i]}' disabled>
										</div>
										<span class="zmdi zmdi-shopping-basket form-control-feedback"></span>
										<small class="help-block"></small>
									</div>
								</div>

								<label for="inputProduct" class="col-sm-2 control-label"><bean:message
										key="operator.confirmorder.price" /></label>
								<div class="col-sm-3">
									<jspcore:set var="index">${"prodPrice"}${i}</jspcore:set>
									<div class="form-group">
										<div class="fg-line">
											<input type="text" id="price" class="form-control"
												value='${paramValues.prodPrice[i]}' disabled>
										</div>

										<span class="zmdi zmdi-money form-control-feedback"></span> <small
											class="help-block">Total Price : Rs <label>${paramValues.prodPrice[i]*paramValues.prodQty[i]}</label>
										</small>
									</div>
									<input type="hidden" id="buttonValue" name="confirm" value="">
								</div>
							</div>
						</jspcore:forEach>
					</div>
					<jspcore:if test="${needTin == true}">
						<div class="modal fade" id="modalTinNumber" tabindex="-1"
							role="dialog" aria-hidden="true">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">TIN Number for Merchant M/s
											${param.custName}</h4>
									</div>
									<div class="modal-body">
										<p>You have just added a new Customer(Merchant) with name
											M/s : ${param.custName}, You can also add the TIN Number for
											the Merchant so that Invoices and Challans can have the same.</p>
										<div class="form-group fg-float">
											<div class="fg-line">
												<input type="text" name="tinNumber" id="tinNumber"
													class="form-control fg-input" value="${param.tin}">
											</div>
											<label class="fg-label">Merchant's TIN Number</label>
										</div>
										<p>
											<label class="control-label" style="color: red"
												id="tinMessage"></label>
										</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-link"
											onclick="checkTin()">Save TIN</button>
										<button type="button" class="btn btn-link"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
					</jspcore:if>
					<br />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" id="submitBTN"
								class="btn btn-primary btn-lg col-sm-3">Confirm Order</button>
							<button type="submit" id="resetBTN" name="confirm" value="reset"
								class="btn btn-default btn-lg col-sm-3" style="margin-left: 10%">Modify
								Order</button>
						</div>
					</div>
				</div>
				<!-- card-body card-padding div 2-->

			</form>
		</div>
		<!-- card div -->

	</div>
	<!-- container div --> </section> </section>


	<!-- Javascript Libraries -->

	<%@include file="../js/includejs.jsp"%>
	<jspcore:if test="${needTin == true}">
		<script>
			$("#modalTinNumber").modal('show');
			function checkTin() {
				var tin = $("#tinNumber").val();
				if (tin == "" || tin == null) {
					$("#tinMessage").text("TIN Number can't be blank !");
				} else if (tin.length != 11) {
					$("#tinMessage").text(
							"Invalid TIN Number ! Length has to be 11 only");
				} else {
					$("#modalTinNumber").modal('hide');
				}

			}
		</script>
	</jspcore:if>
</body>

</html>