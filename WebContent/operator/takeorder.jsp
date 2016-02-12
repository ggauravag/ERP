
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
<title>Take Order</title>

<!-- Vendor CSS -->


<%@include file="/css/includecss.jsp"%>

</head>

<body>
	
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<%@ include file="../header.jsp"%>
	<jspcore:remove var="order" scope="session" />
	<jspcore:remove var="order" scope="request" />
	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1>
				<bean:message key="operator.takeorder.label" />
			</h1>
		</div>

		<div class="card">
			<form action="operator/TakeOrder.do" method="post" id="custForm"
				name="custForm" class="form-horizontal" role="form">

				<div class="card-header">
					<h2>
						<bean:message key="operator.takeorder.head1" />
					</h2>
				</div>
				<div class="card-body card-padding">
					<div id="nameDiv" class="form-group">
						<label for="inputName" class="col-sm-2 control-label"><bean:message
								key="operator.ordertaken.custname" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" name="custName"
									id="inputName" value="${order.customer.name}"
									placeholder="Enter Full Name">
							</div>

							<span class="zmdi zmdi-account form-control-feedback"></span> <small
								id="error" class="help-block"> <html:errors
									property="nameError" />
							</small>
						</div>
						<button type="button" class="btn btn-info" data-toggle="tooltip"
							data-placement="top" title data-original-title="Search Customers"
							id="searchCust">
							<i class="zmdi zmdi-search"></i>
						</button>
						<div class="modal fade" data-modal-color="amber"
							id="modalCustomer" data-backdrop="static" data-keyboard="false"
							tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog modal-lg">
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
						<label for="inputEmail" class="col-sm-2 control-label"><bean:message
								key="operator.ordertaken.email" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="email" class="form-control" name="custEmail"
									id="inputEmail" value="${order.customer.email}"
									placeholder="Email">

							</div>
							<span class="zmdi zmdi-email form-control-feedback"></span> <small
								id="error" class="help-block"><html:errors
									property="emailError" /></small>
						</div>
					</div>
					<input type="hidden" name="tin" id="tin" value="" /> <input
						type="hidden" name="type" id="type" value="" />
					<jspcore:set value="${order.customer.address}" var="add" />
					<div class="row">
						<div id="houseDiv" class="form-input">
							<label for="inputHouse" class="col-sm-2 control-label"><bean:message
									key="operator.ordertaken.address" /></label>
							<div class="col-sm-2 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputHouse"
										name="custHouseNo" value="${add.houseNo}"
										placeholder="House No.">

								</div>
								<small id="error" class="help-block"><html:errors
										property="houseError" /></small>
							</div>
						</div>
						<div id="addDiv" class="form-input">
							<div class="col-sm-6 m-b-25">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputAddress1"
										name="custAdd1" value="${add.line1}"
										placeholder="Address line 1">

								</div>
								<span class="zmdi zmdi-my-location form-control-feedback"></span>
								<small id="error" class="help-block"><html:errors
										property="line1Error" /></small>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="inputAddress2" class="col-sm-2 control-label"></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputAddress2"
									name="custAdd2" value="${add.line2}"
									placeholder="Address line 2">
							</div>
						</div>
					</div>

					<div class="row">
						<div id="cityDiv" class="form-input">
							<label for="inputCity" class="col-sm-2 control-label"><button type="button" class="btn bgm-cyan" id="addCityButton"><i class="zmdi zmdi-plus"></i> Add City</button></label>
							<div class="col-sm-3 m-b-25 selectpicker">
								<select class="form-control" id="inputCity" value="${add.city}"
									name="custCity">

									<option>Select</option>

								</select> <small id="error" class="help-block"><html:errors
										property="cityError" /></small>
							</div>

						</div>

						<div id="stateDiv" class="form-input">
							<div class="col-sm-3 m-b-25 selectpicker">
								<select class="form-control" id="inputState"
									value="${add.state}" name="custState">
									<jsp:useBean id="orderDAO" class="com.dbt.dao.OrderDAO"
										scope="page"></jsp:useBean>
									<jspcore:set var="states" value="${orderDAO.getStates()}"></jspcore:set>
									<option>Select State</option>
									<jspcore:forEach var="state" items="${states}">
										<option value="${state}">${state}</option>
									</jspcore:forEach>
								</select> <small id="error" class="help-block"><html:errors
										property="stateError" /></small>

							</div>

						</div>

						<div class="form-input">
							<div id="pinDiv" class="col-sm-2">
								<div class="fg-line">
									<input type="text" class="form-control" id="inputPin"
										placeholder="Enter PinCode" value="${add.zip}"
										name="custPinCode">
								</div>
								<span class="zmdi zmdi-pin form-control-feedback"></span> <small
									id="error" class="help-block"><html:errors
										property="pinError" /></small>
							</div>
						</div>

					</div>

					<div id="mobileDiv" class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label"><bean:message
								key="operator.takeorder.mobile" /></label>
						<div class="col-sm-8">
							<div class="fg-line">
								<input type="text" class="form-control" id="inputMobile"
									name="custMobile" value="${order.customer.mobile}"
									placeholder="Enter Mobile Number">

							</div>
							<span class="zmdi zmdi-smartphone-android form-control-feedback"></span>
							<small id="error" class="help-block"><html:errors
									property="mobileError" /></small>
						</div>
					</div>
					<div id="mobileDiv" class="form-group">
						<label for="inputMobile" class="col-sm-2 control-label" style="margin-top: -10px"><bean:message
								key="operator.takeorder.customerType" /></label>
						<div class="col-sm-8">

							<label class="radio radio-inline m-r-20"> <input
								type="radio" name="custType" id="typeCustomer" value="CUSTOMER"> <i
								class="input-helper"></i>
							</label> <label style="margin-left: -20px;margin-right: 25px">Customer</label>
							
							 <label class="radio radio-inline m-r-20"> <input
								type="radio" name="custType" id="typeMerchant" value="MERCHANT"> <i
								class="input-helper"></i>
							</label> <label style="margin-left: -20px">Merchant </label>
							
							<span class="zmdi-phone-android form-control-feedback"></span> <small
								id="error" class="help-block"><html:errors
									property="typeError" /></small>
						</div>
					</div>
					<div id="timeDiv" class="form-group">
						<label for="inputDate" class="col-sm-2 control-label"><bean:message
								key="operator.takeorder.datetime" /></label>
						<div class="col-sm-3">
							<div class="fg-line">
								<input type="text" name="orderTime"
									class="form-control date-time-picker" data-toggle="dropdown"
									aria-expanded="true" id="inputDate"
									placeholder="Order Date & Time">

							</div>
							<span class="zmdi zmdi-calendar form-control-feedback"></span> <small
								class="help-block"><font color="red"><html:errors
										property="dateError" /></font></small>
						</div>
					</div>
					
				</div>
				<!-- card-body card-padding div 1-->

				<div class="card-header">
					<h2>
						<bean:message key="operator.takeorder.head2" />
					</h2>
				</div>
				<div class="card-body card-padding">
					<div id="productForm">
						<div id="categoryDiv" class="row">
							<label for="selectProduct" class="col-sm-2 control-label"><bean:message
									key="operator.takeorder.prodcat" /></label>
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
							<label for="selectProduct" class="col-sm-2 control-label"><bean:message
									key="operator.confirmorder.product" /></label>
							<div class="col-sm-8">
								<div id="productDiv" class="form-group fg-line select">
									<select class="selectpicker" id="selectProduct"
										data-live-search="true">
										<option value="">Select Product</option>

									</select> <small id="error" class="help-block"></small>
								</div>

							</div>

						</div>

						<div class="row">
							<label for="inputProduct" class="col-sm-2 control-label"><bean:message
									key="operator.confirmorder.qty" /></label>
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

							<label for="inputProduct" class="col-sm-2 control-label"><bean:message
									key="operator.confirmorder.price" /></label>
							<div class="col-sm-3">
								<div id="priceDiv" class="form-group">
									<div class="fg-line">
										<input type="text" id="price" class="form-control"
											placeholder="Enter Price">

									</div>
									<span class="zmdi zmdi-money-box form-control-feedback"></span> <small
										id="error" class="help-block"></small> <small
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
						title="Add more product" id="ms-compose"> <i
						class="zmdi zmdi-plus"></i>
					</a> <br /> <br /> <br />
					<div class="form-group">
						<div class="row">
							<button type="submit"
								class="btn btn-primary btn-lg col-sm-3 col-sm-offset-2">Take
								Order</button>
							<button type="reset"
								class="btn btn-warning btn-lg col-sm-3 col-sm-offset-3"
								style="margin-left: 10%">Reset</button>
						</div>
					</div>
				</div>
				<!-- card-body card-padding div 2-->
			</form>
		</div>
		<!-- card div -->
		
		<div class="modal fade" id="modalAddCity" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                          <h4 class="modal-title">Add City In State</h4>
                    </div>
                    <div class="modal-body">
                         <label>Select State : </label>		
                         <select class="form-control" id="stateToModify"
									value="${add.state}">
									<jspcore:set var="states" value="${orderDAO.getStates()}"></jspcore:set>
									<option>Select State</option>
									<jspcore:forEach var="state" items="${states}">
										<option value="${state}">${state}</option>
									</jspcore:forEach>
						</select>
						<br/>
						<label>New City Name : </label><input type="text" id="newCityName" class="form-control"/>
                   		<br/>
                   		<label style="color:red;font-size: large;" id="errorInCityAdd"></label>
                   	</div>
                    <div class="modal-footer">
                         <button type="button" id="addNewCity" class="btn btn-link" disabled>Add City</button>
                         <button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
                    </div>
                 </div>
            </div>
       </div>
		

	</div>
	<!-- container div --> </section> </section>


	<!-- Javascript Libraries -->

	<%@include file="/js/includejs.jsp"%>
	<jspcore:if test="${status == false}">
		<script>
			swal(
					"Try Again",
					"Either Email entered is duplicate or Some exception occurred ! Please try again !",
					"error");
		</script>
		<jspcore:remove var="status" scope="session"/>
		<jspcore:remove var="status" scope="request"/>
	</jspcore:if>
	<script>
		function load(id) {
			console.log("Selected : " + id);
		}
	</script>
	
	<script src="vendors/fileinput/fileinput.min.js"></script>
	<script type="text/javascript" src="js/extra.js"></script>

</body>

</html>