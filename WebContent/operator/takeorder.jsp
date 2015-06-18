<%@page import="com.dbt.data.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.dbt.dao.OrderDAO"%>
<%@page import="com.dbt.data.Privilege"%>
<%@page import="com.dbt.dao.LoginDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.dbt.data.User"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Take Order</title>

<!-- Vendor CSS -->

   <%@include file="/css/includecss.jsp" %>

<script>
	var products = new Array();
</script>
</head>

<body>
	<%@ include file="../header.jsp"%>

	<section id="main"> 
	 
	 <%@ include file="../panel/leftpanel.jsp" %>
	
	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1>Take Order</h1>
		</div>

		<div class="card">
			<form action="../ConfirmOrder.do" method="get" id="custForm"
				name="custForm" class="form-horizontal" role="form">

				<div class="card-header">
					<h2>Customer Details</h2>
				</div>
				<div class="card-body card-padding">
					<div id="nameDiv" class="form-group">
						<label for="inputName" class="col-sm-2 control-label">Customer
							Name</label>
						<div  class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" name="custName"
									id="inputName" placeholder="Enter Full Name">
					
							</div>

							<span class="md md-person form-control-feedback"></span> <small
								id="error" class="help-block">
								<html:errors property="nameError"/>
								</small>
						</div>
						<button type="button" class="btn btn-info" data-toggle="tooltip"
							data-placement="top" title data-original-title="Search Customers"
							id="searchCust">
							<i class="md md-search"></i>
						</button>
						<div class="modal fade" data-modal-color="amber"
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
										<button type="button" id="selectCustomerBtn"
											class="btn btn-link">Select Customer</button>
										<button type="button" class="btn btn-link"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="emailDiv" class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" class="form-control" name="custEmail"
									id="inputEmail" placeholder="Email">

							</div>
							<span class="md md-email form-control-feedback"></span> <small
								id="error" class="help-block"><html:errors property="emailError"/></small>
						</div>
					</div>

					<div class="row">
						<div id="houseDiv" class="form-input">
							<label for="inputHouse" class="col-sm-2 control-label">Address</label>
							<div class="col-sm-2 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputHouse"
										name="custHouseNo" placeholder="House No.">

								</div>
								<small id="error" class="help-block"><html:errors property="houseError"/></small>
							</div>
						</div>
						<div id="addDiv" class="form-input">
							<div class="col-sm-6 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputAddress1"
										name="custAdd1" placeholder="Address line 1">

								</div>
								<span class="md md-location-on form-control-feedback"></span> <small
									id="error" class="help-block"><html:errors property="line1Error"/></small>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="inputAddress2" class="col-sm-2 control-label"></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputAddress2"
									name="custAdd2" placeholder="Address line 2">
							</div>
						</div>
					</div>

					<div class="row">
						<div id="cityDiv" class="form-input">
							<label for="inputCity" class="col-sm-2 control-label"></label>
							<div class="col-sm-3 m-b-25 selectpicker">
								<select class="form-control" id="inputCity" name="custCity">
									<option >Select City</option>
									<option value="Jaipur">Jaipur</option>
									<option value="Tonk">Tonk</option>
									<option value="Jodhpur">Jodhpur</option>
									<option value="Jaisalmer">Jaisalmer</option>
									<option value='Kolkata'>Kolkata</option>
									<option value="Mumbai">Mumbai</option>
									<option value="Ahmedabad">Ahmedabad</option>
									<option value="Chennai">Chennai</option>
									<option value='Delhi'>Delhi</option>
								</select>
								<small id="error" class="help-block"><html:errors property="cityError"/></small>
							</div>
							
						</div>

						<div id="stateDiv" class="form-input">
							<div  class="col-sm-3 m-b-25 selectpicker">
								<select class="form-control" id="inputState" name="custState">
									<option>Select State</option>
									<option value="Rajasthan">Rajasthan</option>
									<option value="Maharashtra">Maharashtra</option>
									<option value="Madhya Pradesh">Madhya Pradesh</option>
									<option value="Uttar Pradesh">Uttar Pradesh</option>
									<option value="Gujarat">Gujarat</option>
									<option value="Karnataka">Karnataka</option>
								</select>
								
								  <small id="error" class="help-block"><html:errors property="stateError"/></small>
							
							</div>
							
						</div>

						<div class="form-input">
							<div id="pinDiv" class="col-sm-2">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputPin"
										placeholder="Enter PinCode" name="custPinCode"> 
								</div>
								<span class="md md-my-location form-control-feedback"></span> <small
									id="error" class="help-block"><html:errors property="pinError"/></small>
							</div>
						</div>

					</div>

					<div id="mobileDiv" class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label">Mobile
							Number</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputMobile"
									name="custMobile" placeholder="Enter Mobile Number">

							</div>
							<span class="md-phone-android form-control-feedback"></span> <small
								id="error" class="help-block"><html:errors property="mobileError"/></small>
						</div>
					</div>
				</div>
				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h2>Product Details</h2>
				</div>
				<div class="card-body card-padding">
					<div id="productForm">
						<div id="categoryDiv" class="row">
							<label for="selectProduct" class="col-sm-2 control-label">Product
								Category</label>
							<div class="col-sm-8">
								<div class="form-group">
									<jsp:useBean id="list" class="com.dbt.dao.OrderDAO"
										scope="page"></jsp:useBean>
									<select class="selectpicker form-control" id="selectCategory"
										data-live-search="true">
										<option value="">Select Category</option>

										<jspcore:forEach var="cat" items="${list.allCategory}">
											<option value="${cat.id}">${cat.name}</option>
										</jspcore:forEach>
									</select>
									<small id="error" class="help-block"><html:errors property="productError"/></small>
								</div>
								
							</div>
						</div>
						<div class="row">
							<label for="selectProduct" class="col-sm-2 control-label">Product
								Name</label>
							<div class="col-sm-8">
								<div id="productDiv" class="form-group">
									<select class="selectpicker" id="selectProduct"
										data-live-search="true">
										<option value="">Select Product</option>

									</select>
									<small id="error" class="help-block"></small>
								</div>
								
							</div>

						</div>

						<div class="row">
							<label for="inputProduct" class="col-sm-2 control-label">Quantity</label>
							<div id="quantityDiv" class="col-sm-3">
								<div class="form-group">
									<div class="fg-line">
										<input type="text" id="quantity" class="form-control"
											placeholder="Enter Quantity">

									</div>
									<span class="md-shopping-basket form-control-feedback"></span>
									<small id="error" class="help-block"></small>
									<small class="help-block">Quantity available : <label
										id="avail"></label></small>
								</div>
							</div>

							<label for="inputProduct" class="col-sm-2 control-label">Price</label>
							<div class="col-sm-3">
								<div id="priceDiv" class="form-group">
									<div class="fg-line">
										<input type="text" id="price" class="form-control"
											placeholder="Enter Price">

									</div>
									<span class="md-attach-money form-control-feedback"></span>
									  <small id="error" class="help-block"></small>
									 <small
										class="help-block">Selling Price : Rs <label
										id="sellPrice"></label></small>
								</div>
							</div>
						</div>

						<div id="products"></div>


						<input type="hidden" id="numProd" name="numProd" value="0">

					</div>

					<a onClick="addProduct()" class="btn bgm-blue btn-float"
						data-toggle="tooltip" data-placement="top"
						title="Add more product" id="ms-compose"> <i class="md md-add"></i>
					</a> <br /> <br /> <br />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary btn-lg col-sm-2">Take
								Order</button>
							<button type="reset" class="btn btn-default btn-lg col-sm-2"
								style="margin-left: 10%">Reset</button>
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

	<%@include file="/js/includejs.jsp" %>
	
	<script>
		function load(id) {
			console.log("Selected : " + id);
		}
	</script>



</body>

</html>