<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Order Details</title>
<script type="text/javascript">

function displayODetails()
{
	
var oid = getQueryStringValue("oid");
if(oid!=null && oid!="")
	{
	alert(oid);
	$.ajax({
		
	    url : "../../ajaxServlet",	    
	    data : 
	    {
	    	ord : oid,
	        action : "getAllOrderDetails"
	    },
     	error : function(data) {
			alert("Error : " + data);
		},
		success : function(data) {		
			alert(data);
		}
	});
	}
}
</script>
</head>
<body onload="displayODetails()">
<div id="fillOdetails">

</div>
<script src="<%=request.getContextPath() %>/js/myjs.js"></script>
</body>
</html>