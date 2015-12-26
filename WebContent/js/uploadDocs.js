/**
 * New node file
 */
var orders = new Array();
var docs = new Array();


$("#uploadButton").click(function(){
	
	var file = $("#inputFile").val();
	var data = $("input[name=selectedId]:checked").val();
	console.log("Data is : "+data);
	
	if(file == "")
	{
		swal("No File Uploaded ! Please upload a file.");
		return;
	}
	
	if(data == undefined)
	{
		swal("No Order or Purchase selected for uploading file !");
		return;
	}
	
	var parts = data.split(":");
	$("#docId").val(parts[0]);
	$("#type").val(parts[1]);
	var formData = new FormData();
	console.log("File : "+$("#inputFile")[0].files[0]);
	formData.append('file',$("#inputFile")[0].files[0]);
	formData.append('id',parts[0]);
	formData.append('type',parts[1]);
	console.log("Form data is : "+formData);
	$.ajax({
		
		xhr: function() {
		    var xhr = new window.XMLHttpRequest();

		    xhr.upload.addEventListener("progress", function(evt) {
		      if (evt.lengthComputable) {
		        var percentComplete = evt.loaded / evt.total;
		        percentComplete = parseInt(percentComplete * 100);
		        console.log(percentComplete);
		        $('.progress-bar').css('width', percentComplete+'%').attr('aria-valuenow', percentComplete); 
		        if (percentComplete === 100) {
		        	swal({   
	                    title: "File Uploaded !",   
	                    text: "But please wait while the file is being processed !",   
	                    timer: 5000,   
	                    showConfirmButton: true 
	                });
		        	$("#loaderImage").show();
		        }
		      }
		    }, false);

		    return xhr;
		  },
		  
		url : $("#basePath").val() + "/UploadServlet",
		data : formData,
		processData : false,
		contentType : false,
		type : 'POST',
		success : function(data){
			$("#loaderImage").hide();
			if(data.status == 1)
			{
				var doc = new Object();
				doc.name = data.name;
				doc.url = data.url;
				doc.id = data.id;
				docs[docs.length] = doc;
				loadImage(docs);
				$('.progress-bar').css('width', 0+'%').attr('aria-valuenow', 0); 
				swal("File Upload Completed","The file was successfully uploaded & processed","success");
			}
			else if(data.status == 0)
			{
				swal("File Not Uploaded","Something went unexpected, please try again !","failure");
			}
			else
			{
				alert("Some Error at Server's End");
			}
		},
		error : function(data){
			$("#loaderImage").hide();
			console.log(data);
		}
	});
	
});

$("#searchDocsButton").click(
		function()
		{
			var id = $("#inputOrderID").val();
			var mobile = $("#inputMobile").val();
			var from = $("#inputFromDate").val();
			var name = $("#inputName").val();
			var to = $("#inputToDate").val();
			
			var result = false;
			var msg = "";
			if(id != "" && isNaN(id))
			{
				result = true;
				msg += "Invalid Order/Purchase ID\n";
			}
			if(mobile != "" && isNaN(mobile))
			{
				result = true;
				msg += "Invalid Mobile\n";
			}
			
			if(id == "" && mobile == "" && name == "" && (from == "" || to == ""))
			{
				result = true;
				msg += "All Fields are invalid or blank\n";
			}
			
			
			if(result)
			{
				alert(msg);return;
			}
			
			$("#loaderImage").show();
			$.ajax({
				
				url : $("#basePath").val() + "/ajaxServlet.do",
				
				data : {
					action : "getOrderAndPurchase",
					inputId : id,
					inputName : name,
					inputFromDate : from,
					inputToDate : to,
					inputMobile : mobile
				},
				
				method : "post",
				
				success : function(data){
					$("#loaderImage").hide();
					if(data.status == 1)
					{
						var html = "";
						orders = data.orders;
						for(var i = 0; i < data.orders.length; i++)
						{
							var order = data.orders[i];
							html += "<tr>";
							html += "<td><input type='radio' onclick='displayImage(this.value)' name='selectedId' value='"+order.id+":"+order.type+":"+i+"'/></td>";
							html += "<td>"+order.id+"</td>";
							html += "<td>"+order.name+"</td>";
							html += "<td>"+order.mobile+"</td>";
							html += "<td>"+order.amount+"</td>";
							html += "<td>"+order.date+"</td>";
							if(order.type == "SELL")
							{
								if(order.discount == "null")
									html += "<td>Not Shipped</td>";
								else
									html += "<td>Shipped</td>";
							}
							else
								html += "<td> ----- </td>";
							html += "<td>"+order.type+"</td>";
							html += "</tr>";
						}
						$("#fillOrderDetails").html(html);
					}
					else
						alert("Some Error while fetching data !");
				},
				
				error : function(data){
					$("#loaderImage").hide();
					alert("Some Error while fetching data");
				}	
			});
			
		}
);

function remove(value)
{
	var input = value.split(":");
	$("#loaderImage").show();
	$.ajax({
		
		url : $("#basePath").val() + "/ajaxServlet.do",
		data : {
			action : "removeDocument",
			fileName : input[1],
			id : input[0]
		},
		success : function(data){
			$("#loaderImage").hide();
			if(data.status == 1)
			{
				for(var i = 0; i < docs.length; i++)
				{
					if(docs[i].id == input[0])
					{
						docs.splice(i, 1);
						break;
					}
				}
				swal("Document Removed","The document has been removed successfully !","success");
				loadImage(docs);
			}
			else if(data.status == 0)
			{
				swal("Cannot Remove Document","Cannot remove the document, it does not exists !","error");
			}
			else
			{
				swal("Server Failure","There has been an unexpected error, please try again !","error");
			}
		},
		error : function(data){
			$("#loaderImage").hide();
			alert("Some Error while fetching data");
		}
	});
}


function removeDocument(value)
{
	swal({   
        title: "Are you sure?",   
        text: "You will not be able to recover this document !",   
        type: "warning",   
        showCancelButton: true,   
        confirmButtonColor: "#DD6B55",   
        confirmButtonText: "Yes, delete it!",   
        cancelButtonText: "No, cancel please !",   
        closeOnConfirm: true,   
        closeOnCancel: false 
    }, function(isConfirm){   
        if (isConfirm) {     
            remove(value);
        } else {     
            swal("Cancelled", "Your file is safe :)", "error");
        } 
    });

	
	
}


function loadImage(documents)
{
	var htmls = "";
	$("#imageDiv").nextAll("div").remove();
	//alert("Num of docs : "+data.documents.length)
	var table = "";
	for(var i = 0; i < documents.length; i++)
	{
		var doc = documents[i];
		
		htmls += "<div data-src=\""+doc.url+"\" class=\"col-sm-2 col-xs-6\" data-sub-html=\"<em><h3>"+doc.name+"</h3><p></p></em>\">";
		htmls += "<div class='lightbox-item'>";
		htmls += "<img src='"+doc.url+"' height='100px' alt='' />";
		htmls += "</div>";
		htmls += "</div>";
		
		table += "<tr>";
		table += "<td>"+doc.name+"</td>";
		table += "<td><a class='btn btn-primary' href='"+doc.url+"' download='"+doc.url+"'>Download</a>";
		table += "<td><button class='btn btn-danger' value='"+doc.id+":"+doc.name+"' onclick='removeDocument(this.value)' type='button'>Remove</button></td>";
		table += "</tr>";
	}
	
	$("#imageTable").html(table);
	$("#imageDiv").after(htmls);
}

function displayImage(value)
{
	//alert("Function called : "+value);
	var data = value.split(":");
	var html = "";
	html += "<div data-src=\"media/gallery/6.jpg\" class=\"col-sm-2 col-xs-6\" data-sub-html=\"<em><h3>This is a caption heading</h3><p>Here goes the description...</p></em>\">";
	html += "<div class='lightbox-item'>";
	html += "<img src='media/gallery/thumbs/6.jpg' alt=''  />";
	html += "</div>";
	html += "</div>";
	
	var i = parseInt(data[2]);
	$("#selectName").val(orders[i].name);
	$("#selectMobile").val(orders[i].mobile);
	
	$("#loaderImage").show();
	$.ajax({
		
		url : $("#basePath").val() + "/ajaxServlet.do",
		
		data : {
			action : "getDocumentsById",
			id : data[0],
			type : data[1]
		},
		
		success : function(data){
			$("#loaderImage").hide();
			if(data.status == 1)
			{
				docs = data.documents;
				loadImage(data.documents);
			}
		},
		
		error : function(data){
			$("#loaderImage").hide();
			alert("Some Unexpected Error !");
		}
	});
	
}
