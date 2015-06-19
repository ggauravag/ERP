<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Product</title>
	
	<%@include file="../css/includecss.jsp" %>
	
</head>
<body>
	<%@ include file="../header.jsp" %>
			
	<section id="main">
	
		<%@ include file="../panel/leftpanel.jsp" %>
	
		<section id="content">
			<div class="container">		
				<div class="card">	
					<form action="" method="post" id="">
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
										<small id="error" class="help-block"></small>
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
									</div>
								</div>
							</div>
	
							<input type="hidden" id="numProd" name="numProd" value="0">
	
						</div>
						
						<br /><br />
	
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary btn-lg col-sm-3">Add
									Product</button>
								<button type="reset" class="btn btn-default btn-lg col-sm-2"
									style="margin-left: 10%">Reset</button>
							</div>
						</div>
							<br /><br />
					</div>
					</form>
				</div>
			</div>
		</section>
	</section>	
			
			<%@include file="../js/includejs.jsp" %>
</body>
</html>