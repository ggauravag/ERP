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
<title>Add Stock</title>

<%@include file="../css/includecss.jsp"%>

</head>

<body>
	
	<%@ include file="../header.jsp"%>
<jspcore:remove var="newStockDetails" scope="session" />
	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1>
				<bean:message key="operator.addstock.label" />
			</h1>
		</div>

		<div class="card">
			<form action="AddStock.do" method="post" id="stockForm"
				name="stockForm" class="form-horizontal" role="form">

				<div class="card-header">
					<h2>
						<bean:message key="operator.addstock.head1" />
					</h2>
				</div>

				<div class="card-body card-padding">

					<div id="catDiv" class="row form-group">
						<label for="selectCat" class="col-sm-2 control-label"><bean:message
								key="operator.addstock.category" /></label>
						<div class="col-sm-6">
							<jsp:useBean id="list" class="com.dbt.dao.StockDAO" scope="page"></jsp:useBean>
							<select class="selectpicker" id="selectCat"
								name="selectCat" data-live-search="true">
								<option value="">Select Category</option>

								<jspcore:forEach var="cat" items="${list.allCategory}">
									<option value="${cat.id}">${cat.name}</option>
								</jspcore:forEach>
							</select> <small id="error" class="help-block"></small>
						</div>

						<button class="btn bgm-gray"
							type="button" id="addCatButton">
							<i class="zmdi zmdi-add"></i> Add New Category
						</button>
					</div>


					<div class="modal fade" data-modal-color="bluegray" id="modalCat"
						data-backdrop="static" data-keyboard="false" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Add Category</h4>
								</div>
								<div class="modal-body">
									<p>Enter the new category name to be added :</p>
									<div class="form-input" id="addCatDiv">
										<input type="text" class="form-control" name="catName"
											id="catName" placeholder="Enter new Category name"> <small
											id="error" class="help-block"> </small>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" id="addCategory" class="btn btn-link">Add
										Category</button>
									<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>

					<hr>
					<br>

					<div id="prodForm">
						<div class="form-group row" id="prodOuterDiv">
							<label for="selectProd" class="col-sm-2 control-label"><bean:message
									key="operator.addstock.productname" /></label>
							<div class="col-sm-6" id="prodDiv">
								<select class="selectpicker" id="selectProd"
									name="selectProd" data-live-search="true">
									<option value="">Select Product</option>
								</select> <small id="error" class="help-block"></small>
							</div>

							<button
								class="btn bgm-gray"
								type="button" id="addProdButton">
								<i class="zmdi zmdi-add"></i> Add New Product
							</button>
						</div>


						<div class="modal fade" data-modal-color="bluegray" id="modalProd"
							data-backdrop="static" data-keyboard="false" tabindex="-1"
							role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">Add Product</h4>
									</div>
									<div class="modal-body">
										<p>Enter new product details to be added :</p>
										<div class="form-input" id="prodModelCat">
											<input type="text" class="form-control" id="prodCat" disabled>
											<small id="error" class="help-block"> </small>
										</div>
										<div class="form-input" id="addNameDiv">
											<input type="text" class="form-control" name="prodName"
												id="prodName" placeholder="Enter new product name">
											<small id="error" class="help-block"> </small>
										</div>
										<div class="form-input" id="addQtyDiv">
											<input type="text" class="form-control" name="prodQty"
												value="0" id="prodQty"
												placeholder="Enter Quantity of new product" readonly="true">
											<small id="error" class="help-block"> </small>
										</div>
										<div class="form-input" id="addSpDiv">
											<input type="text" class="form-control" name="prodSp"
												id="prodSp" placeholder="Enter Selling price"> <small
												id="error" class="help-block"> </small>
										</div>
										<div class="form-input" id="addCpDiv">
											<input type="text" class="form-control" name="prodCp"
												id="prodCp" placeholder="Enter Cost Price"> <small
												id="error" class="help-block"> </small>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" id="addProduct" class="btn btn-link">Add
											Product</button>
										<button type="button" class="btn btn-link"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>

						<div class="form-group" id="qtyDiv">
							<label for="inputQuantity" class="col-sm-2 control-label"><bean:message
									key="operator.addstock.qty" /></label>
							<div class="col-sm-6">
								<div class="fg-line">
									<input type="text" id="quantity" name="quantity"
										class="form-control" placeholder="Enter Quantity">
								</div>
								<span class="zmdi zmdi-shopping-basket form-control-feedback"></span> <small
									id="error" class="help-block"></small> <small
									class="help-block">Quantity available : <label
									id="avail"></label></small>
							</div>
						</div>

						<div class="row">
							<div id="cpDiv" class="form-input">
								<label for="cp" class="col-sm-2 control-label"><bean:message
										key="operator.addstock.cp" /></label>
								<div class="col-sm-3 m-b-25">
									<div class="fg-line">
										<input type="text" id="cp" name="cp" class="form-control"
											placeholder="Enter Cost Price">
									</div>
									<span class="zmdi zmdi-money form-control-feedback"></span> <small
										id="error" class="help-block"></small>
								</div>
							</div>

							<div id="spDiv" class="form-input">
								<label for="sp" class="col-sm-2 control-label"><bean:message
										key="operator.addstock.sp" /></label>
								<div class="col-sm-3 m-b-25">
									<div class="fg-line">
										<input type="text" id="sp" name="sp" class="form-control"
											placeholder="Enter Selling Price">
									</div>
									<span class="zmdi zmdi-money form-control-feedback"></span> <small
										id="error" class="help-block"></small>
								</div>
							</div>
						</div>

						<div id="product"></div>
						<input type="hidden" id="numProd" name="numProd" value="0">

					</div>

					<a onClick="addMoreStock()" class="btn bgm-blue btn-float"
						data-toggle="tooltip" data-placement="top" title="Add stock"
						id="ms-compose"> <i class="zmdi zmdi-plus"></i>
					</a> <br /> <br /> <br />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary btn-lg col-sm-4"
								id="contButton">Continue</button>
							<button type="reset"  class="btn btn-warning btn-lg col-sm-4"
								style="margin-left: 10%">Reset</button>
						</div>
					</div>
				</div>
				<!-- card-body card-padding div -->
			</form>
		</div>
		<!-- card div -->

	</div>
	<!-- container div --> </section> </section>

	<!-- Javascript Libraries -->

	<%@include file="../js/includejs.jsp"%>
	
	<jspcore:set var="stockStatus" value="${sessionScope.stockStatus}"></jspcore:set>
	
	<jspcore:if test="${stockStatus == 'Success'}">
		<script>
			setTimeout(function(){swal("Stock Added !",
					"New Stock details has been added successfully", "success")},0);
		</script>
		<jspcore:remove var="stockStatus" scope="session" />
	</jspcore:if>

	<jspcore:if test="${stockStatus == 'Failure'}">
		<script>
		setTimeout(function(){swal("Error Occurred !",
					"Unable to add new Stock details, try again.", "failure")},0);
		</script>
		<jspcore:remove var="stockStatus" scope="session" />
	</jspcore:if>


</body>
</html>