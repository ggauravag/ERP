/**
 * New node file
 */

function showErrorValidation(div, message) {
	$(div).addClass("has-error");
	$(div+" > small").html(message);
}

function removeErrorvalidation(div){
	$(div).removeClass("has-error");
	$(div+" > small").html("");
}

$("#searchTransaction").click(
		function(){
			
			var inputId = $("#inputTransactionID").val();
			var inputMobile = $("#inputMobile").val();
			var inputName = $("#inputName").val();
			var start = $("#inputFromDate").val();
			var to = $("#inputToDate").val();
			var numberRegExp = /^[0-9]\d*$/;
			var mobileRegExp = /^[7-9]{1}[0-9]{9}$/;
			
			//console.log(inputId+":"+mobile+":"+name+":"+start+":"+to);
			
			var success = true;
			
			if((start == "" && to != "") || (start != "" && to == ""))
			{
				success = false;
				showErrorValidation("#dateDiv", "Enter both start and end date !")
			}
			else
				removeErrorvalidation("#dateDiv");
			
			if(inputId != "" && !numberRegExp.test(inputId))
			{
				success = false;
				showErrorValidation("#idDiv", "Please enter a valid numeric transaction ID");
			}
			else
				removeErrorvalidation("#idDiv");
			
			if(inputMobile != "" && !mobileRegExp.test(inputMobile))
			{
				success = false;
				showErrorValidation("#mobileDiv", "Please enter a valid mobile number");
			}
			else
				removeErrorvalidation("#mobileDiv");
			
			if(success == true && start == "" && to == "" && inputMobile == "" && inputId == "" && inputName == "")
			{
				success = false;
				alert("Please fill at least one field !");
			}
			
			//alert("Success is : "+success);
			if(!success)
				return;
			
			$("#loaderImage").show();
			$.ajax({
				
				url : $("#basePath").val() + "/ajaxServlet.do",
				
				data : {
					action : "getTransaction",
					name : inputName,
					mobile : inputMobile,
					id : inputId,
					from : start,
					end : to
				},
				
				method : "post",
				
				success : function(data){
					$("#loaderImage").hide();
					if(data.status)
					{
						var transactions = data.transactions;
						//alert("Number of transactions : "+transactions.length);
						var credit = 0;
						var debit = 0;
						var html = "<div class='table-responsive'><table class='table table-condensed'>";
						html += "<thead><tr><th>Select</th><th>ID</th><th>Amount</th><th>Mode</th><th>Paid By</th><th>Description</th><th>Type</th><th>Order/Exp. ID</th><th>Date-Time</th></tr></thead>";
						html += "<tbody>";
						for(var i = 0; i < transactions.length; i++)
						{			
							if(transactions[i].type == "DEBIT" || transactions[i].type == "EXPENDITURE")
							{	html += "<tr style='color:red'>"; debit -= transactions[i].amount; }
							else
							{	html += "<tr style='color:green'>"; credit += transactions[i].amount; }
							
							html += "<td><input name='txnID' type='radio' value='"+transactions[i].id+":"+transactions[i].type+":"+transactions[i].relatedId+"' onclick='showTransaction(this.value)' /></td>";
							
							html += "<td>"+transactions[i].id+"</td>";
							html += "<td>"+transactions[i].amount+"</td>";
							html += "<td>"+transactions[i].mode+"</td>";
							html += "<td>"+transactions[i].paidBy+"</td>";
							html += "<td>"+transactions[i].description+"</td>";
							
							html += "<td>"+transactions[i].type+"</td>";
							html += "<td>"+transactions[i].relatedId+"</td>";
							html += "<td>"+transactions[i].time+"</td>";
							html += "</tr>";
						}
						html += "</tbody>";
						$("#fillTransactionDetails").html(html);
						$("#total").text(credit+debit);
						$("#due").text(debit);
						$("#paid").text(credit);
					}
					else
					{
						alert("Some error while fetching data !");
					}
				},
				
				error : function(data){
					$("#loaderImage").hide();
					alert("Some error");
				}
			});
		}
);

function showTransaction(value)
{
	var data = value.split(":");
	var inputAction = "";
	if(data[1] == "EXPENDITURE")
	{
		inputAction = "getExpenditureById";
	}
	else
		inputAction = "getPaymentById";
	
	$("#loaderImage").show();
	
	$.ajax({
		url : $("#basePath").val()+"/ajaxServlet.do",
		data : {
			action : inputAction,
			id : data[0],
			relatedId : data[2]
		},
		success : function(response){
			$("#loaderImage").hide();
			
			if(data[1] == "EXPENDITURE")
			{
				$("#expIdDiv").show();
				$("#expIdDiv > td > strong").text(response.id);
				$("#typeDiv").show();
				$("#typeDiv > td > strong").text(response.type);
				$("#detailDiv").show();
				response.detail = response.detail.split("|").join(" ---- ");
				$("#detailDiv > td > strong").text(response.detail);
				$("#nameDiv").hide();
				$("#orderAmountDiv").hide();
				$("#timeDiv").hide();
				$("#orderIdDiv").hide();
			}
			else
			{
				$("#expIdDiv").hide();
				$("#typeDiv").hide();
				$("#detailDiv").hide();
				$("#orderIdDiv").show();
				$("#orderIdDiv > td > strong").text(response.order.id);
				$("#orderAmountDiv").show();
				$("#orderAmountDiv > td > strong").text(response.order.amount);
				$("#nameDiv").show();
				$("#nameDiv > td > strong").text(response.order.custName);
				$("#timeDiv").show();
				$("#timeDiv > td > strong").text(response.order.time);
			}
			
		},
		error : function(data){
			$("#loaderImage").hide();
		}
	});
	
}