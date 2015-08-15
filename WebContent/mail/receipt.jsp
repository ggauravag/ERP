<doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Invoice Details</title>

</head>

<body
	style="padding: 0px; margin: 0px; font-family: 'Bree Serif', serif;">
	<table height="100%" width="100%"
		style="border: 2px solid #eee; border-collapse: collapse;">

		<!-- First Row -->

		<tr>
			<td>TIN : 07623681967</td>

			<td style="font-style: italic;" align="right" colspan="2">
				Original</td>
		</tr>

		<!-- Second Row -->

		<tr>
			<td align="center" colspan="3"><img height="10%"
				src="http://erp.dreambit.co.in/img/logo/ramfurlogo.jpg" align="left"
				style="padding-top: 2%;" /> <b><u> PAYMENT RECEIPT </u></b> <br>
				<font size="5%"><b>KHUSHI INTERNATIONAL</b></font><br> 251,
				SAMAYPUR VILLAGE NEAR SHIV MANDIR <br> NEAR RAILWAY ROAD, DELHI
				- 110042 <br> <b>Phone : 9982100000</b><br> <b>Deals
					In : All Kinds of Furniture</b></td>
		</tr>
		<tr>
			<td align="center" colspan="3"><hr noshade="noshade"></td>
		</tr>
		<tr>
			<td colspan="3"><table width="100%">
					<tr>
						<td><strong style="color: black;">Amount (In
								Figures) :</strong></td>
						<td style="color: black;" align="left"><span class="WebRupee">&#x20B9;
						</span>${payment.printableAmount}</td>
					</tr>
					<tr>
						<td><strong style="color: black;">Amount (In Words):</strong></td>
						<td style="color: black;" align="left">${payment.amountInWords}</td>
					</tr>
					<tr>
						<td><strong style="color: black;">Paid By :</strong></td>
						<td style="color: black;" align="left">${payment.paidBy}</strong></td>
					</tr>
					<tr>
						<td><strong style="color: black;">Order ID :</strong></td>
						<td style="color: black;" align="left">${payment.orderId}</td>
					</tr>
					<tr>
						<td><strong style="color: black;">Mode :</strong></td>
						<td style="color: black;" align="left">${payment.mode}</td>
					</tr>
					<tr>
						<td><strong style="color: black;">Description :</strong></td>
						<td style="color: black;" align="left">${payment.description}</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td align="center" colspan="3"><hr noshade="noshade"></td>
		</tr>
		<tr>
			<td><b><u>Terms & Conditions</u></b> <br> E. & O.E. <br>
				1. This receipt is for acknowledgement purpoe only. <br> 2.This
				receipt may represent partial payment for the order.<br></td>
		</tr>
	</table>
	<style>
@import url(http://fonts.googleapis.com/css?family=Bree+Serif);
</style>
</body>
</html>