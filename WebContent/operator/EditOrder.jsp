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
	<title>Edit your order</title>
	<%@include file="../css/includecss.jsp"%>
</head>

<body>
	<input type="hidden" id="basePath" value="<%=basePath%>">	
	<%@ include file="../header.jsp"%>

	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1><bean:message key="operator.editorder.label" /></h1>
		</div>

		<form class="form-horizontal" action="<%=request.getContextPath()%>/EditOrder.do" 
			method="post" id="editOrderForm" name="editOrderForm" role="form">

			<div class="card">
				<div class="card-header">
					<h2><bean:message key="operator.editorder.head1" /></h2>
				</div>
				<div class="card-body card-padding">
					<div class="row">
						<div class="form-input" id="orderDiv">
							<label for="inputOrderId" class="col-sm-2 control-label"><bean:message key="operator.editorder.orderid" /></label>
							<div class="col-sm-5 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="orderId"
										name="orderId" placeholder="Enter Order ID">
								</div>
								<span class="zmdi zmdi-shopping-basket form-control-feedback"></span>
							</div>
						</div>

						<div class="form-input">
							<div class="col-sm-5 m-b-25">
								<div class="fg-line">
									<button type="button" id="searchOrderButton1"
										class="btn btn-primary btn-lg col-sm-6">Search Order</button>
								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="card-header">
					<h2><bean:message key="operator.editorder.head2" /></h2>
				</div>
				<div class="card-body card-padding">

					<div class="form-group" id="nameDiv">
						<label for="inputName" class="col-sm-2 control-label"><bean:message key="operator.editorder.custname" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputName"
									name="inputName">
							</div>
							<span class="zmdi zmdi-account form-control-feedback"></span> <small
								class="help-block" id="error"></small>
						</div>

						<button type="button" class="btn btn-info" data-toggle="tooltip"
							data-placement="top" title data-original-title="Search Customers"
							id="searchCust">
							<i class="zmdi zmdi-search"></i>
						</button>
						<div class="modal fade" data-modal-color="cyan"
							id="modalCustomer" data-backdrop="static" data-keyboard="false"
							tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">Customer Search</h4>
									</div>
									<div class="modal-body">
										<p>Here's the list of all customers :</p>

										<div id="custList"></div>

									</div>
									<div class="modal-footer">
										<button type="button" id="selectCustomerModalBtn"
											class="btn btn-link">Select Customer</button>
										<button type="button" class="btn btn-link"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="form-group" id="emailDiv">
						<label for="inputEmail" class="col-sm-2 control-label"><bean:message key="operator.editorder.email" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" name="inputEmail" class="form-control"
									id="inputEmail" readonly>
							</div>
							<span class="zmdi zmdi-email form-control-feedback"></span>
						</div>
					</div>


					<div class="form-group" id="mobileDiv">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message key="operator.editorder.mobile" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" name="inputMobile" class="form-control"
									id="inputMobile" readonly>
							</div>
							<span class="zmdi zmdi-smartphone-android form-control-feedback"></span> <small
								class="help-block" id="error"></small>
						</div>
					</div>



					<div class="row">
						<div id="houseDiv" class="form-input">
							<label for="inputHouse" class="col-sm-2 control-label"><bean:message key="operator.editorder.address" /></label>
							<div class="col-sm-2 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputHouse"
										name="custHouseNo" readonly>
								</div>
							</div>
						</div>
						<div id="addDiv1" class="form-input">
							<div class="col-sm-6 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputAddress1"
										name="custAdd1" readonly>
								</div>
								<span class="zmdi zmdi-my-location form-control-feedback"></span>
							</div>
						</div>
					</div>

					<div class="form-group" id="addDiv2">
						<label for="inputAddress2" class="col-sm-2 control-label"></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputAddress2"
									name="custAdd2" readonly>
							</div>
						</div>
					</div>

					<div class="row">
						<div id="cityDiv" class="form-input">
							<label for="inputCity" class="col-sm-2 control-label"></label>
							<div class="col-sm-3 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="city" name="city"
										readonly>
								</div>
							</div>
						</div>

						<div id="stateDiv" class="form-input">
							<div class="col-sm-3 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="state" name="state"
										readonly>
								</div>
							</div>
						</div>

						<div class="form-input" id="pinDiv">
							<div class="col-sm-2 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputPin"
										name="custPinCode" readonly>
								</div>
								<span class="zmdi zmdi-pin form-control-feedback"></span>
							</div>
						</div>
					</div>
					<div id="fillProducts"></div>
				</div>
				
				<br /> <br />
				<div class="card-header">
					<h2><bean:message key="operator.editorder.head3" /></h2>
				</div>
				<div class="card-body card-padding">

					<div id="productForm">
						<div id="categoryDiv" class="row">
							<label for="selectProduct" class="col-sm-2 control-label"><bean:message key="operator.editorder.prodcategory" /></label>
							<div class="col-sm-8">
								<div class="form-group">
									<jsp:useBean id="list" class="com.dbt.dao.OrderDAO"
										scope="page"></jsp:useBean>
									<select class="selectpicker" id="selectCategory"
										data-live-search="true">
										<option value="">Select Category</option>

										<jspcore:forEach var="cat" items="${list.allCategory}">
											<option value="${cat.id}">${cat.name}</option>
										</jspcore:forEach>
									</select> <small id="error" class="help-block"><html:errors
											property="productError" /></small>
								</div>

							</div>
						</div>
						<div class="row">
							<label for="selectProduct" class="col-sm-2 control-label"><bean:message key="operator.editorder.prodname" /></label>
							<div class="col-sm-8">
								<div id="productDiv" class="form-group">
									<select class="form-control" id="selectProduct"
										data-live-search="true">
										<option value="">Select Product</option>

									</select> <small id="error" class="help-block"></small>
								</div>

							</div>

						</div>

						<div class="row">
							<label for="inputProduct" class="col-sm-2 control-label"><bean:message key="operator.editorder.qty" /></label>
							<div id="quantityDiv" class="col-sm-3">
								<div class="form-group">
									<div class="fg-line">
										<input type="text" id="quantity" class="form-control"
											placeholder="Enter Quantity">

									</div>
									<span class="zmdi zmdi-shopping-basket form-control-feedback"></span>
									<small id="error" class="help-block"></small> <small
										class="help-block">Quantity available : <label
										id="avail"></label></small>
								</div>
							</div>

							<label for="inputProduct" class="col-sm-2 control-label"><bean:message key="operator.editorder.price" /></label>
							<div class="col-sm-3">
								<div id="priceDiv" class="form-group">
									<div class="fg-line">
										<input type="text" id="price" class="form-control"
											placeholder="Enter Price">

									</div>
									<span class="zmdi zmdi-money form-control-feedback"></span> <small
										id="error" class="help-block"></small> <small
										class="help-block">Selling Price : Rs <label
										id="sellPrice"></label></small>
								</div>
							</div>
						</div>

						<div id="products"></div>
						<input type="hidden" id="numProd" name="numProd" value="0">
						<input type="hidden" id="customerId" name="customerId" value="0" />
						<input type="hidden" id="deleteOrder" name="deleteOrder" value="notDeleted" />
						<input type="hidden" id="shipId" name="shipId" value="0" />
					</div>

					<a onClick="addProduct()" class="btn bgm-blue btn-float"
						data-toggle="tooltip" data-placement="top"
						title="Add product" id="ms-compose"> <i class="zmdi zmdi-plus"></i>
					</a> <br /> <br /> <br />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-primary btn-lg col-sm-4"
								id="editOrder">Edit Order</button>
							<button type="button" class="btn btn-warning btn-lg col-sm-4"
								id="delOrder" style="margin-left: 10%">Delete Order</button>
						</div>
					</div>

				</div>

			</div>
		</form>
	</div>
	</section> </section>

	<%@include file="../js/includejs.jsp"%>
	
	<jspcore:set var="editOrder" value="${sessionScope.editOrder}"></jspcore:set>
			
			
	<jspcore:if test="${editOrder == 'Edited'}">
		<script>
			swal("Order Updated successfully !", "Order has been updated successfully", "success");
		</script>
	</jspcore:if>
	<jspcore:if test="${editOrder == 'notEdited'}">
		<script>
			swal("Error Occurred !", "Unable to update order details, try again.", "error");
		</script>
	</jspcore:if>
	<jspcore:if test="${editOrder == 'Deleted'}">
		<script>
			swal("Order Deleted successfully !", "Order has been deleted successfully", "success");
		</script>
	</jspcore:if>
	<jspcore:if test="${editOrder == 'notDeleted'}">
		<script>
			swal("Error Occurred !","Unable to delete order now, try again.", "error");
		</script>
	</jspcore:if>	
	<%session.removeAttribute("editOrder");%>
				
</body>
</html>