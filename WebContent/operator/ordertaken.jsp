


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Order Successful</title>

<!-- Vendor CSS -->

<%@include file="/css/includecss.jsp"%>


</head>

<body>
	<%@ include file="../header.jsp"%>

	<section id="main"> 
	
	<%@ include file="../panel/leftpanel.jsp"%>

	<section id="content">
	<div class="container">
		<div class="block-header">
			<h1>Order Confirmation</h1>
		</div>

		<div class="card">
			<form action="../ConfirmOrder.do" method="get" id="custForm"
				name="custForm" class="form-horizontal" role="form">

				<div class="card-header">
					<h2>Order Details</h2>
				</div>
				
				<div class="card-body card-padding">
				   <div class="form-group">
						<label for="inputName" class="col-sm-2 control-label">
							Order Number : </label>
						<div class="col-sm-8">
							<div class="fg-line disabled">
								<label class="control-label"><strong>215325</strong></label>
							</div>
							
						</div>
					</div>
				
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label">Customer
							Name :</label>
						<div class="col-sm-8">
							<div class="fg-line disabled">
								<label class="control-label"><strong>215325</strong></label>
							</div>
							
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label">Email :</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<label class="control-label"><strong>215325</strong></label>
							</div>
							
						</div>
					</div>

					<div class="row">
						<div class="form-input">
							<label for="inputHouse" class="col-sm-2 control-label">Address :</label>
							<div class="col-sm-8">
								<div class="fg-line">
									<label class="control-label"><strong>215325</strong></label>
								</div>
							</div>
						</div>
						
					</div>
				
				
				<jspcore:forEach items="order.products" var="product">
					<div class="row">
						<div class="form-input">
							<label for="inputHouse" class="col-sm-2 control-label">Product Name :</label>
							<div class="col-sm-8">
								<div class="fg-line">
									<label class="control-label"><strong>215325</strong></label>
								</div>
							</div>
						</div>
					</div>
				
				</jspcore:forEach>
				
				</div>
				<!-- card-body card-padding div 2-->
			</form>
		</div>
		<!-- card div -->

	</div>
	<!-- container div --> </section> </section>


	<!-- Javascript Libraries -->

	<%@include file="/js/includejs.jsp"%>

	<script>
		function load(id) {
			console.log("Selected : " + id);
		}
	</script>



</body>

</html>