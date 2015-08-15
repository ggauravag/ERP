<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
		<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Error</title>
        
        <!-- Vendor CSS -->
        <link href="../vendors/animate-css/animate.min.css" rel="stylesheet">
        <link href="../vendors/sweet-alert/sweet-alert.min.css" rel="stylesheet">
        <link href="../vendors/material-icons/material-design-iconic-font.min.css" rel="stylesheet">
        <link href="../vendors/socicon/socicon.min.css" rel="stylesheet">
            
        <!-- CSS -->
        <link href="../css/app.min.1.css" rel="stylesheet">
        <link href="../css/app.min.2.css" rel="stylesheet">
</head>
<body class="four-zero-content">
		<div class="four-zero">
            <h2>Error!</h2>
            <small>Invalid Operation ! <html:errors></html:errors> </small>
            
            <footer>
                <a href="../login.jsp"><i class="md md-home"></i></a>
            </footer>
        </div>
        <script>
        function goBack()
        {
        	window.history.back();
        	console.log('Gone Back');
        }
        </script>
</body>
</html>