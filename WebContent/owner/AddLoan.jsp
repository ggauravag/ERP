<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
    String bPath = request.getScheme() + "://" + request.getServerName () + ":" + request.getServerPort () + basePath + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=bPath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Loan</title>

	<%@include file="../css/includecss.jsp" %>
	
</head>

<body>
	<%@ include file="../header.jsp" %>
        
        <section id="main">
            
            <%@ include file="../panel/leftpanel.jsp" %>
            
            <section id="content">
                <div class="container">
                    <div class="block-header">
                        <h1><bean:message key="owner.employee.addloan.label" /></h1>
                    </div>
                    
                    <div class="card">
                        <form action="owner/AddLoan.do" method="post" 
                        		name="loanForm" id="loanForm" class="form-horizontal" role="form">
                            
                            <div class="card-header">
                                <h2><bean:message key="owner.employee.addloan.head1" /></h2>
                            </div>
                            <div class="card-body card-padding">
                                <div id="amountDiv" class="form-group">
                                    <label for="inputAmount" class="col-sm-2 control-label"><bean:message key="owner.addloan.amount" /></label>
                                    <div class="col-sm-8">
                                        <div class="fg-line">
                                            <input type="text" class="form-control" name="inputAmount" id="inputAmount" placeholder="Amount (Rs)">
                                        </div>
                                    	<span class="md md-person form-control-feedback"></span>
                                    	<small id="error" class="help-block">
										</small>
                                    </div>
                                </div>
                                <div id="tenureDiv" class="form-group">
                                    <label for="inputTenure" class="col-sm-2 control-label"><bean:message key="owner.addloan.tenure" /></label>
                                    <div class="col-sm-8">
                                        <div class="fg-line">
                                            <input type="text" class="form-control" name="inputTenure" id="inputTenure" placeholder="Tenure (years)">
                                        </div>
                                        <span class="md md-email form-control-feedback"></span>
                                        <small id="error" class="help-block">
										</small>
                                    </div>
                                </div>
                                
                                <div id="monthlyDiv" class="form-group">
                                    <label for="inputInstallment" class="col-sm-2 control-label"><bean:message key="owner.addloan.monthly" /></label>
                                    <div class="col-sm-8">
                                        <div class="fg-line">
                                            <input type="text" class="form-control" name="inputInstallment" id="inputInstallment" placeholder="Installment (monthly)">
                                        </div>
                                        <span class="md md-email form-control-feedback"></span>
                                        <small id="error" class="help-block">
										</small>
                                    </div>
                                </div>
                                
                                <div id="interestDiv" class="form-group">
                                    <label for="inputInterest" class="col-sm-2 control-label"><bean:message key="owner.addloan.rate" /></label>
                                    <div class="col-sm-8">
                                        <div class="fg-line">
                                            <input type="text" class="form-control" name="inputInterest" id="inputInterest" placeholder="Interest Rate (%)">
                                        </div>
                                        <span class="md md-email form-control-feedback"></span>
                                        <small id="error" class="help-block">
										</small>
                                    </div>
                                </div>
                            	                                    
                                <br />
                                <br />
                                <br />
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary btn-lg col-sm-2">Add Loan</button>
                                        <button type="reset" id="check" class="btn btn-default btn-lg col-sm-2" style="margin-left: 10%">Reset</button>
                                    </div>
                                </div>
                                
                                
                                
                            </div><!-- card-body card-padding div 2-->

                        </form>
                    </div> <!-- card div -->
                    
                </div> <!-- container div -->    
            </section>
        </section>
       
        
        <!-- Javascript Libraries -->
        <%@include file="../js/includejs.jsp" %>

			<jspcore:choose>
				<jspcore:when test="${loanStatus == 'Success'}">
					<script>
					swal("Success","Loan has been successfully added !","success");
					</script>
				</jspcore:when>
				<jspcore:otherwise>
					<jspcore:if test="${loanStatus == 'Failure'}">
						<script>
						swal("Failure","Unable to add Loan details, try again !","error");
						</script>
					</jspcore:if>
				</jspcore:otherwise>	
			</jspcore:choose>	
        
</body>
</html>