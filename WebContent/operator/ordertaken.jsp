
<%
	String basePath = request.getContextPath();
%>
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
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<%@ include file="../header.jsp"%>

	<section id="main"> <%@ include file="../panel/leftpanel.jsp"%>

	<jspcore:set var="orderS" value="${sessionScope.order}"></jspcore:set>

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
								<label class="control-label"><strong>${orderS.id}</strong></label>
							</div>

						</div>
					</div>

					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label">Customer
							Name :</label>
						<div class="col-sm-8">
							<div class="fg-line disabled">
								<label class="control-label"><strong>${orderS.customer.name}</strong></label>
							</div>

						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label">Email
							:</label>
						<div class="col-sm-8">
							<div class="fg-line">
								<label class="control-label"><strong>${orderS.customer.email}</strong></label>
							</div>

						</div>
					</div>

					<jspcore:set value="${orderS.customer.address}" var="add" />

					<div class="row">
						<div class="form-group">
							<label for="inputHouse" class="col-sm-2 control-label">Address
								:</label>
							<div class="col-sm-8">
								<div class="fg-line">
									<label class="control-label"><strong>${add.houseNo},
											${add.line1}, ${add.line2}, ${add.city} - ${add.zip},
											${add.state}</strong></label>
								</div>
							</div>
						</div>
					</div>


					<jspcore:forEach items="${orderS.products}" var="product">
						<div class="row">
							<div class="form-group">
								<label for="inputHouse" class="col-sm-2 control-label">Product
									Name :</label>
								<div class="col-sm-2">
									<div class="fg-line">
										<label class="control-label"><strong>${product.name}</strong></label>
									</div>
								</div>
								<label for="inputHouse" class="col-sm-1 control-label">Quantity
									:</label>
								<div class="col-sm-2">
									<div class="fg-line">
										<label class="control-label"><strong>${product.quantity}</strong></label>
									</div>
								</div>
								<label for="inputHouse" class="col-sm-1 control-label">Price
									:</label>
								<div class="col-sm-2">
									<div class="fg-line">
										<label class="control-label"><strong>${product.sellPrice}</strong></label>
									</div>
								</div>
							</div>
						</div>
					</jspcore:forEach>

					<div class="row">
						<div class="form-group">
							<label for="inputHouse" class="col-sm-3 control-label">Total
								Order Amount (VAT Exclusive) :</label>
							<div class="col-sm-4">
								<div class="fg-line">
									<label class="control-label"><strong>${orderS.amount}</strong></label>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group">
							<div class="col-sm-6 col-sm-offset-3">
								<button type="button" id="printOrder"
									class="btn btn-primary waves-effect waves-button waves-float">
									<i class="md md-print"></i> PRINT DETAILS
								</button>
								<button type="button" id="sendOrder"
									class="col-sm-offset-2 btn btn-primary waves-effect waves-button waves-float">
									<i class="md md-message"></i> SEND SMS/EMAIL CONFIRMATION
								</button>
							</div>
						</div>
					</div>

				</div>
				<!-- card-body card-padding div 2-->
			</form>
			<div class="row">
				<div class="form-group"></div>
			</div>
		</div>
		<div class="modal fade" data-modal-color="blue" id="modalMobile"
			data-backdrop="static" data-keyboard="false" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Send SMS/Email Confirmation</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="form-group">
								<label for="inputName" class="col-sm-6 control-label">Customer
									Mobile (if Multiple,then CSV) :</label>
								<div class="col-sm-12">
									<div class="fg-line">
										<input class="form-control" type="text" id="sendMobiles"
											value="${order.customer.mobile}" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label for="inputName" class="col-sm-6 control-label">Customer
									Email (if Multiple,then CSV) :</label>
								<div class="col-sm-12">
									<div class="fg-line">
										<input type="text" class="form-control" id="sendEmails"
											value="${order.customer.email}" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="sendOrderDetails" class="btn btn-link">Send</button>
						<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" data-modal-color="blue" id="modalPrint"
			data-backdrop="static" data-keyboard="false" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Select Billing Firm :</h4>
					</div>
					<div class="modal-body" id="firmDiv"></div>
					<div class="modal-footer">
						<button type="button" id="selectFirmDetails" class="btn btn-link">Select</button>
						<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<!-- card div -->

	</div>
	<!-- container div --> </section> </section>


	<!-- Javascript Libraries -->

	<%@include file="/js/includejs.jsp"%>

	<script type="text/javascript">
		$("#printOrder").click(
			function()
			{
				console.log("Done");
				loadFirms();
				//window.open("../PrintOrder.do?print=order","","");
			}
		);
		
		$("#sendOrder").click(
				function()
				{
					$('#modalMobile').modal('show');
				}		
		);
		
	</script>

	<jspcore:if test="${order != null}">
		<script type="text/javascript">
		swal("Order Successful !", "Order has been successfully placed with Order ID : "+${order.id}+".", "success");
		</script>
	</jspcore:if>



</body>

</html>