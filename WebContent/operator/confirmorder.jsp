<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Confirm Order</title>

<!-- Vendor CSS -->
<%@include file="/css/includecss.jsp"%>
</head>

<body>
	<%@ include file="../header.jsp"%>

	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1>Confirm Order</h1>
		</div>

		<div class="card">
			<form class="form-horizontal" action="./ConfirmOrder.do" method="post" role="form">

				<div class="card-header">
					<h2>Customer Details</h2>
				</div>
				<div class="card-body card-padding">
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label">Customer
							Name</label>
						<div class="col-sm-8">
							<div class="fg-line disabled">
								<input type="text" class="form-control" id="inputName"
									value="${param.custName}" disabled>
							</div>
							<span class="md md-person form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" class="form-control" id="inputEmail"
									value="${param.custEmail}" disabled>
							</div>
							<span class="md md-email form-control-feedback"></span>
						</div>
					</div>

					<div class="row">
						<div class="form-input">
							<label for="inputHouse" class="col-sm-2 control-label">Address</label>
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
								<span class="md md-location-on form-control-feedback"></span>
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
								<span class="md md-my-location form-control-feedback"></span>
							</div>
						</div>

					</div>

					<div class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label">Mobile
							Number</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputMobile"
									value="${param.custMobile}" disabled>
							</div>
							<span class="md-phone-android form-control-feedback"></span>
						</div>
					</div>
				</div>
				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h2>Product Details</h2>
				</div>
				<div class="card-body card-padding">
					<div id="productForm">

						<jspcore:forEach var="i" begin="0" end="${param.numProd - 1}">
								
								
							<div class="row">
								<label for="selectProduct" class="col-sm-2 control-label">Product
									Name</label>
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
								<label for="inputProduct" class="col-sm-2 control-label">Quantity</label>
								
								<div class="col-sm-3">
									<div class="form-group">
										<div class="fg-line">
											<input type="text" id="quantity" class="form-control"
												value='${paramValues.prodQty[i]}' disabled>
										</div>
										<span class="md-shopping-basket form-control-feedback"></span>
										<small class="help-block"></small>
									</div>
								</div>

								<label for="inputProduct" class="col-sm-2 control-label">Price</label>
								<div class="col-sm-3">
								<jspcore:set var="index">${"prodPrice"}${i}</jspcore:set>
									<div class="form-group">
										<div class="fg-line">
											<input type="text" id="price" class="form-control" value='${paramValues.prodPrice[i]}'
												disabled>
										</div>
								
										<span class="md-attach-money form-control-feedback"></span> <small
											class="help-block">Total Price : Rs <label>${param[index]}</label> </small>
									</div>
									<input type="hidden" id="buttonValue" name="confirm" value="">
								</div>
							</div>
						</jspcore:forEach>
					</div>

					<br /> <br /> <br />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" id="submitBTN" class="btn btn-primary btn-lg col-sm-3">Confirm
								Order</button>
							<button type="reset" id="resetBTN" class="btn btn-default btn-lg col-sm-3"
								style="margin-left: 10%">Modify Order</button>
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

</body>

</html>