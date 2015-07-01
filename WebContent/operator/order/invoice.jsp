<doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Invoice Details</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css">
<style>
@import url(http://fonts.googleapis.com/css?family=Bree+Serif);

body,h1,h2,h3,h4,h5,h6 {
	font-family: 'Bree Serif', serif;
}
</style>

</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-3">
				<h1>
					<img height="100px"
						src="<%=request.getContextPath()%>/img/ramfurlogo.jpg">
				</h1>
			</div>
			<div class="col-xs-9 text-right">
				<h2>Invoice Details</h2>
				<h1>
					<small></small>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<strong>Ram Furniture</strong>
						</h4>
					</div>
					<div class="panel-body">
						<p>
							<strong> Shop No. 50, Santosh Nagar, Opp. Ganga-Jamuna
								Petrol Pump <br> Near New Aatish Market, Gopalpura Bypass,
								Jaipur - 302019
							</strong> <br> 9413333853, 9460008990 <br><br>
							ramfurnitures@gmail.com
						</p>
					</div>
				</div>
			</div>
			<div class="col-xs-6  text-right">
				<div class="panel panel-default">
					<div class="panel-heading">
						Details
					</div>
					
					<div class="panel-body">
						<table>
 					<tr>
 						<td> Invoice No. </td>
 						<td>: &nbsp; 211212</td>
 					</tr>
 					
 					<tr>
 						<td>Dated </td>
 						<td>: &nbsp; 10-06-2015</td>
 					</tr>
 					
 					<tr>
 						<td>GR/RR No. </td>
 						<td>: &nbsp; </td>
 					</tr>
 					
 					<tr>
 						<td>Transport </td>
 						<td>: &nbsp; </td>
 					</tr>
 					
 					<tr>
 						<td>Vehicle No. </td>
 						<td>: &nbsp; RJ14 GE 1897</td>
 					</tr>
 					
 					<tr>
 						<td>TIN No </td>
 						<td>: &nbsp; </td>
 					</tr>
 					
 					<tr>
 						<td>Way Bill No.</td>
 						<td>: &nbsp; </td>
 					</tr>
 					
 				</table>
					</div>
				</div>
			</div>
		</div>
		<!-- / end client details section -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th class="text-left">
						<h5>
							<strong>Particulars</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong>Quantity</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong>Rate</strong>
						</h5>
					</th>
					<th>
						<h5 class="text-right">
							<strong>Sub Total</strong>
						</h5>
					</th>
				</tr>
			</thead>
			<tbody>

				
					<tr>
						<td>Hello</td>
						<td class="text-right">Hello</td>
						<td class="text-right">Hello</td>
						<td class="text-right">Hello</td>
					</tr>
				

				
							<tr>
								<td>&nbsp;</td>
								<td class="text-right">&nbsp;</td>
								<td class="text-right">&nbsp;</td>
								<td class="text-right">&nbsp;-</td>
							</tr>
				
							<tr>
								<td>&nbsp;</td>
								<td class="text-right">&nbsp;</td>
								<td class="text-right">&nbsp;</td>
								<td class="text-right">&nbsp;-</td>
							</tr>
				
		</table>
		<div class="row text-right">
			<div class="col-xs-2 col-xs-offset-8">
				<p>
					<strong> Sub Total : <br> TAX : <br> Total : <br>
					</strong>
				</p>
			</div>
			<div class="col-xs-2">
				<strong> Hello
					<br> hello <br> hello
					<br>
				</strong>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-8">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h4>Terms & Conditions</h4>
					</div>
					<div class="panel-body">
						<p>1. This is an order confirmation not an invoice (VAT
							Exclusive).</p>
						<p>2. Fulfillment of order is subject to stock availablity.</p>
						<p>3. Order shall be processed only after 100% payment.</p>
					</div>
				</div>
			</div>
			<div class="col-xs-4">
				<div class="span7">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h4>For RAM FURNITURE</h4>
						</div>
						<div class="panel-body">
							<p>&nbsp;&nbsp;</p>
							<p>&nbsp;&nbsp;</p>
							<p>
							<strong><h5 class="text-right">Authorised Signatory</h5></strong>
							</p>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
