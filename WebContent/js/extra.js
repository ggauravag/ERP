/**
 * New node file
 */



$("#searchOrderButton").click(
  function(){
	 var orderID = $("#inputOrderID").val();
  }		
);

$("#searchOrderButton").click(function(e)
		{
	var s = document.getElementById("fillOrderDetails");
	
	
	var ipnm=$("#inputName").val();
	var ipmob=$("#inputMobile").val();
	var ipoid = $("#inputOrderID").val();
	
	if((ipnm=="" || ipnm == null) && (ipmob=="" || ipmob == null) && (ipoid=="" || ipoid == null))
	{
		alert("No data Found. Please Fill At Least One Category");
	    return;
	}
	
	s.innerHTML="";

	$.ajax({
		
		    url : "../ajaxServlet.do",
		    
		    data : 
		    {
			name : ipnm,
			order : ipoid,
			mobile : ipmob,
			action : "getOrderByNameIDMobile"
	     	},
	     	error : function(data) {
				alert("Error : " + data);
			},
			success : function(data) {
				
				var heading = document.createElement('div');
				heading.setAttribute("class","card-header");
				
				var hd1 = document.createElement('h2');
				$(hd1).text("Order Details");
				var hd2 = document.createElement('small');
				$(hd2).text("Please select the order for complain");
				
				
				heading.appendChild(hd1);
				heading.appendChild(hd2);
				s.appendChild(heading);
		 
				
				var dtbl = document.createElement('div');
				dtbl.setAttribute("class","table-responsive");
			
			    var table = document.createElement('table');
			    table.setAttribute("class", "table table-condensed");
			
			    var tbhead = document.createElement('thead');
			    var tbhdrow = document.createElement('tr');
			    
			    
			    var tbhdtd1 = document.createElement('td');
			    $(tbhdtd1).text("Select");
			    tbhdrow.appendChild(tbhdtd1);
			    
			    var tbhdtd2 = document.createElement('td');
			    $(tbhdtd2).text("Order ID");
			    tbhdrow.appendChild(tbhdtd2);
			    
			    var tbhdtd3 = document.createElement('td');
			    $(tbhdtd3).text("Name");
			    tbhdrow.appendChild(tbhdtd3);
			    
			    var tbhdtd4 = document.createElement('td');
			    $(tbhdtd4).text("Email");
			    tbhdrow.appendChild(tbhdtd4);
			    
			    
			    var tbhdtd5 = document.createElement('td');
			    $(tbhdtd5).text("Mobile");
			    tbhdrow.appendChild(tbhdtd5);
			    
			    var tbhdtd6 = document.createElement('td');
			    $(tbhdtd6).text("Amount");
			    tbhdrow.appendChild(tbhdtd6);
			    
			    var tbhdtd7 = document.createElement('td');
			    $(tbhdtd7).text("Date");
			    tbhdrow.appendChild(tbhdtd7);
			    
			    var tbhdtd8 = document.createElement('td');
			    $(tbhdtd8).text("Time");
			    tbhdrow.appendChild(tbhdtd8);
			    
			    
			    var tbhdtd9 = document.createElement('td');
			    $(tbhdtd9).text("View Details");
			    tbhdrow.appendChild(tbhdtd9);
			    
			    tbhead.appendChild(tbhdrow);
			    table.appendChild(tbhead);  // Header inserted
			    
			    
			    
			    var orderdetails = data.orderdetails;
			    
			    var tbbody = document.createElement('tbody');
			    
			    for(var i=0;i<data.orderdetails.length;i++)
				{
			    
			    var tbbdrw = document.createElement('tr');
			    	    
			    
					    var input = document.createElement('input');
					    input.setAttribute("type", "radio");
					    input.setAttribute("name", "orderid");					   
					    input.setAttribute("onchange", "showComment(this.value)");
					    input.setAttribute("value", orderdetails[i].oid);			    
					    var tbbdtd = document.createElement('td');
					    tbbdtd.appendChild(input);
					    tbbdrw.appendChild(tbbdtd);
			    
			    	    var tbbdtd1 = document.createElement('td');
					    $(tbbdtd1).text(orderdetails[i].oid);
					    tbbdrw.appendChild(tbbdtd1);
					    
					    var tbbdtd2 = document.createElement('td');
					    $(tbbdtd2).text(orderdetails[i].Customer.name);
					    tbbdrw.appendChild(tbbdtd2);
					    
					    var tbbdtd3 = document.createElement('td');
					    $(tbbdtd3).text(orderdetails[i].Customer.email);
					    tbbdrw.appendChild(tbbdtd3);
					    
					    
					    var tbbdtd4 = document.createElement('td');
					    $(tbbdtd4).text(orderdetails[i].Customer.mobile);
					    tbbdrw.appendChild(tbbdtd4);
					    
					    var tbbdtd5 = document.createElement('td');
					    $(tbbdtd5).text(orderdetails[i].amount);
					    tbbdrw.appendChild(tbbdtd5);
					    
					    var tbbdtd6 = document.createElement('td');
					    $(tbbdtd6).text(orderdetails[i].date);
					    tbbdrw.appendChild(tbbdtd6);
					    
					    var tbbdtd7 = document.createElement('td');
					    $(tbbdtd7).text(orderdetails[i].time);
					    tbbdrw.appendChild(tbbdtd7);
					    
					    var tbbdtd8 = document.createElement('td');
					    var vdiv = document.createElement('div');
					    vdiv.setAttribute("class", "row dialog col-sm-2");
					    var vdetail = document.createElement('button');
					    vdetail.setAttribute("id", "sa-basic");
					    vdetail.setAttribute("class", "btn btn-info");
					    vdetail.setAttribute("type", "button");
					    vdetail.setAttribute("value", orderdetails[i].oid);
					    vdetail.setAttribute("onclick", "window.open('../../PrintOrder.do?print=view&oid="+orderdetails[i].oid+"',1,'width=300,height=300','resizable=yes','status=yes')");
					    $(vdetail).text("Click here");
					    vdiv.appendChild(vdetail);
					    tbbdtd8.appendChild(vdiv);
					    tbbdrw.appendChild(tbbdtd8);
					    tbbody.appendChild(tbbdrw);
				}
			     
			    table.appendChild(tbbody);
			    dtbl.appendChild(table);
			    s.appendChild(dtbl);
			}
	});
}

);