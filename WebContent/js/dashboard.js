/**
 * New node file
 */

$("input[name=messTemplate]").click(function(){
	//swal("Selected value is : "+this.value);
	
	if(this.value == "cheque")
	{
		$("#message").val("Cheque of amount Rs. XXXX will be debited from your XXX Bank Account. Please deposit Balance.");
		
	}
	else
	{
		$("#message").val("An order with order ID XXX need to be delivered by XXXX date. Please deliver it.");
	}
});


function showReminders()
{

	$("#loaderImage").show();
	$.ajax({
		
		url : $("#basePath").val() + "/ajaxServlet.do",
		
		data : {
			action : "getReminder"
		},
		
		success : function(data){
			$("#loaderImage").hide();
			if(data.status)
			{
				var html = "";
				for(var i = 0; i < data.reminders.length; i++)
				{
					html += "<tr>";
					html += "<td>"+data.reminders[i].title+"</td>";
					html += "<td>"+data.reminders[i].message+"</td>";
					html += "<td>"+data.reminders[i].startDate+"</td>";
					html += "<td>"+data.reminders[i].endDate+"</td>";
					html += "<td>"+data.reminders[i].frequency+"</td>";
					html += "</tr>";
				}
				$("#reminders").html(html);
				$("#modalReminder").modal('show');
			}
			else
			{
				swal("Cannot Fetch Data ! Some Error !");
			}
			
		},
		
		error : function(data){
			$("#loaderImage").hide();
		},
		
		method : "post"
		
	});

}


$("#setReminder").click(function(){
	var title1 = $("#reminderTitle").val();
	var mobiles = $("#inputMobile").val();
	var message1 = $("#message").val();
	var frequency1 = $("input[name=frequency]:checked").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	
	var mobile = mobiles.split(",");
	var msg = "";
	var result = true;
	if(mobile.length == 0)
	{
		result = false;msg += "No Mobile selected.";
	}
	else
	{
		for(var i = 0; i < mobile.length; i++)
		{
			if(mobile[i].length != 10)
			{
				result = false;msg += "Invalid Mobile "+mobile[i]+".";
			}
		}
	}
	
	if(endDate == null || endDate == "" || startDate == "")
	{
		result = false;msg += "Invalid start or end Date.";
	}
	if(message == "")
	{
		result = false;msg += "Message cannot be blank.";
	}
	
	$("#errorMessage").html(msg);
	
	if(!result)
	{
		return;
	}
	$("#loaderImage").show();
	$.ajax({
		
		url : $("#basePath").val() + "/ajaxServlet.do",
		
		data : {
			action : "setReminder",
			message : message1,
			frequency : frequency1,
			title : title1,
			from : startDate,
			to : endDate,
			mobile : mobiles
		},
		
		success : function(data) {
			$("#loaderImage").hide();
			if(data.status)
			{
				swal("Reminder Set","The reminder has been successfully added.","success");
				$("#reminderTitle").val("");
				$("#inputMobile").val("");
				$("#message").val("");
				$("#startDate").val("");
				$("input[name=frequency]").val("DAILY");
				$("#endDate").val("");
			}
			else
			{
				swal(data.message);
			}
			
		},
		
		error : function(data){
			$("#loaderImage").hide();
			alert("Error : "+data);
		},
		
		method : "post"
	});
		
	
	
	
});

















