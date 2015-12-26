<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><bean:message key="project.name" /></title>
<%@include file="../../css/includecss.jsp"%>

</head>
<body>

	<%@ include file="../../header.jsp"%>

	<section id="main"> <%@include file="../../panel/leftpanel.jsp"%> 
	<section id="content">
	      
                <div class="container">
                    
                    <div class="block-header">
                        <h2> <small></small></h2>
                        
                        <ul class="actions m-t-20 hidden-xs">
                            <li class="dropdown">
                                <a href="#" data-toggle="dropdown">
                                    <i class="zmdi zmdi-more-vert"></i>
                                </a>
                    
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li>
                                        <a href="#">Privacy Settings</a>
                                    </li>
                                    <li>
                                        <a href="#">Account Settings</a>
                                    </li>
                                    <li>
                                        <a href="#">Other Settings</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    
                    
                    
                    <jspcore:set var="user" value="${sessionScope.user}"></jspcore:set>
                    <div class="card" id="profile-main">
                        <div class="pm-overview c-overflow">
                            <div class="pmo-pic">
                                <div class="p-relative">
                                
                                    <a href="#">
                                        <img class="img-responsive" src=${user.photo} alt=""> 
                                    </a>
                                    
                                    <a href="#" class="pmop-edit">
                                        <i class="zmdi zmdi-camera-alt"></i> <span class="hidden-xs">Update Profile Picture</span>
                                    </a>
                                </div>
                                
                                
                                
                            </div>
                            
                            <div class="pmo-block pmo-contact hidden-xs">
                                <h2>Contact</h2>
                                
                                <ul>
                                    <li><i class="zmdi zmdi-phone"></i> ${user.mobile }</li>
                                    <li><i class="zmdi zmdi-email"></i> ${user.email }</li>
                                    <!-- <li>
                                        <i class="zmdi zmdi-location-on"></i>
                                        <address class="m-b-0">
                                            10098 ABC Towers, <br/>
                                            Dubai Silicon Oasis, Dubai, <br/>
                                            United Arab Emirates
                                        </address>
                                    </li> -->
                                </ul>
                            </div>
                            
                            
                            
                        </div>
                        
                        <div class="pm-body clearfix">
                            <ul class="tab-nav tn-justified">
                                <li class="active waves-effect"><a href="#">About</a></li>
                           </ul>
                            
                         
                            
                            <div class="pmb-block">
                                <div class="pmbb-header">
                                    <h2><i class="zmdi zmdi-person m-r-5"></i> Basic Information</h2>
                                    
                                    <ul class="actions">
                                        <li class="dropdown">
                                            <a href="#" data-toggle="dropdown">
                                                <i class="zmdi zmdi-more-vert"></i>
                                            </a>
                                            
                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li>
                                                    <a data-pmb-action="edit" href="#">Edit</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                                <div class="pmbb-body p-l-30">
                                    <div class="pmbb-view">
                                    <dl class="dl-horizontal">
                                            <dt>User ID</dt>
                                            <dd>${user.id}</dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt>Full Name</dt>
                                            <dd>${user.firstName}&nbsp;&nbsp;${user.lastName}</dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt>Start Time</dt>
                                            <dd>${user.st_time}</dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt>End Time</dt>
                                            <dd>${user.end_time}</dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt>User Type</dt>
                                            <dd>${user.type}</dd>
                                        </dl>
                                    </div>
                                    <form action="./Profile.do" Id="proNameForm" method="post">
                                    <div class="pmbb-edit">
                                        <dl class="dl-horizontal" id="fnameDiv">
                                            <dt class="p-t-10">First Name</dt>
                                            <dd>
                                                <div class="fg-line">
                                                    <input type="text" id="inputFname" name="Fname" class="form-control" placeholder="${user.firstName }">
                                                </div>
                                                <span class="zmdi zmdi-person form-control-feedback"></span> <small
								id="error" class="help-block"><font color="red"></font>
								<html:errors property="fnameError" /> </small>
                                            </dd>
                                        </dl>
                                       <dl class="dl-horizontal" id="lnameDiv">
                                            <dt class="p-t-10">Last Name</dt>
                                            <dd>
                                                <div class="fg-line">
                                                    <input type="text" id="inputLname" name="Lname" class="form-control" placeholder="${user.lastName }">
                                                </div>
                                                <span class="zmdi zmdi-person form-control-feedback"></span> <small
								id="error" class="help-block"><font color="red"></font>
								<html:errors property="lnameError" /> </small>
                                            </dd>
                                        </dl>
                                       
                                        <input type="hidden" name="action" value="Name" />
                                        <input type="hidden" name="userid" value="${user.id}" />
                                        <div class="m-t-30">
                                            <button type="submit" class="btn btn-primary btn-sm">Save</button>
                                            <button data-pmb-action="reset" class="btn btn-link btn-sm-2">Cancel</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                       
                        
                            <div class="pmb-block">
                                <div class="pmbb-header">
                                    <h2><i class="zmdi zmdi-phone m-r-5"></i> Contact Information</h2>
                                    
                                    <ul class="actions">
                                        <li class="dropdown">
                                            <a href="#" data-toggle="dropdown">
                                                <i class="zmdi zmdi-more-vert"></i>
                                            </a>
                                            
                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li>
                                                    <a data-pmb-action="edit" href="#">Edit</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                                <div class="pmbb-body p-l-30">
                                <form action="./Profile.do" id="ContactForm" method="post">
                                    <div class="pmbb-view">
                                        <dl class="dl-horizontal">
                                            <dt>Mobile Phone</dt>
                                            <dd>${user.mobile}</dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt>Email Address</dt>
                                            <dd>${user.email}</dd>
                                        </dl>
                                        
                                    </div>
                                        <input type="hidden" name="action" value="Contact" />
                                        <input type="hidden" name="userid" value="${user.id}" />
                                    <div class="pmbb-edit">
                                        <dl class="dl-horizontal" id="mobileDiv">
                                            <dt class="p-t-10">Mobile Phone</dt>
                                            <dd>
                                                <div class="fg-line">
                                                    <input type="text" class="form-control input-mask"
									id="inputMobile" name="mobile" placeholder="Enter Mobile"
									data-mask="000-000-0000" />
                                                </div>
                                                <span class="zmdi zmdi-phone-android form-control-feedback"></span> <small
								id="error" class="help-block"> <font color="red"></font>
								<html:errors property="mobileError" />
							</small>
                                            </dd>
                                        </dl>
                                        
                                        <div class="m-t-30">
                                            <button type="submit" class="btn btn-primary btn-sm">Save</button>
                                            <button data-pmb-action="reset" class="btn btn-link btn-sm">Cancel</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                            
                            
                            
                             <div class="pmb-block">
                                <div class="pmbb-header">
                                    <h2><i class="zmdi zmdi-equalizer m-r-5"></i> Change Password</h2>
                                    
                                    <ul class="actions">
                                        <li class="dropdown">
                                            <a href="#" data-toggle="dropdown">
                                                <i class="zmdi zmdi-more-vert"></i>
                                            </a>
                                            
                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li>
                                                    <a data-pmb-action="edit" href="#">Edit</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                                <div class="pmbb-body p-l-30">
                                    <form action="./Profile.do" id="PassForm" method="post">
                                     <input type="hidden" name="action" value="Pass" />
                                        <input type="hidden" name="userid" value="${user.id}" />
                                    <div class="pmbb-edit">
                                        <dl class="dl-horizontal" id="passDiv">
                                            <dt class="p-t-10">Password</dt>
                                            <dd>
                                                <div class="fg-line">
                                                    <input type="password" class="form-control"
									id="inputPass" name="password" placeholder="Enter Password"/>
                                                </div>
                                <small id="error" class="help-block"> <font color="red"></font>
								<html:errors property="passError" />
							</small>
                                            </dd>
                                        </dl>
                                        
                                        <dl class="dl-horizontal" id="cpassDiv">
                                            <dt class="p-t-10">Confirm Password</dt>
                                            <dd>
                                                <div class="fg-line">
                                                    <input type="password" class="form-control"
									id="inputConfPass" name="confPass" placeholder="Enter Password"/>
                                                </div>
                                <small id="error" class="help-block"> <font color="red"></font>
								<html:errors property="confpassError" />
							</small>
                                            </dd>
                                        </dl>
                                        
                                        <div class="m-t-10">
                                            <button type="submit" class="btn btn-primary btn-sm">Save</button>
                                            <button data-pmb-action="reset" class="btn btn-link btn-sm">Cancel</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                            
                            
                             <div class="pmb-block">
                                <div class="pmbb-header">
                                    <h2><i class="zmdi zmdi-equalizer m-r-5"></i> Change Profile Picture</h2>
                                    
                                    <ul class="actions">
                                        <li class="dropdown">
                                            <a href="#" data-toggle="dropdown">
                                                <i class="zmdi zmdi-more-vert"></i>
                                            </a>
                                            
                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li>
                                                    <a data-pmb-action="edit" href="#">Edit</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                                <div class="pmbb-body p-l-30">
                                    <form action="./Profile.do" id="ProfilePicForm" enctype="multipart/form-data" method="post">
                                     <input type="hidden" name="action" value="Pic" />
                                        <input type="hidden" name="userid" value="${user.id}" />
                                    <div class="pmbb-edit">
                                        <dl class="dl-horizontal" id="picDiv">
                                            <dt class="p-t-10">Profile Picture</dt>
                                            <dd>
                                               <div class="fileinput fileinput-new " data-provides="fileinput">
                                               <div class="fileinput-preview thumbnail" data-trigger="fileinput"></div>
                                               <div>
									<span class="btn btn-info btn-file">
									<span class="fileinput-new">Select image</span>
									<span class="fileinput-exists">Change</span>
									
									           </span><a href="#" class="btn btn-danger fileinput-exists"
										data-dismiss="fileinput">Remove</a>
									           </div>
                                               </div>
                                            </dd>
                                        </dl>
                                        
                                        
                                        
                                        <div class="m-t-10">
                                            <button type="submit" class="btn btn-primary btn-sm">Save</button>
                                            <button data-pmb-action="reset" class="btn btn-link btn-sm">Cancel</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </section>
        </section>

	<%@include file="../../js/includejs.jsp"%>

	<jspcore:if test="${status == 'done'}">
		<script>
			swal("Profile Updated Successfully",
					"The details has been modified.", "success");
		</script>
	</jspcore:if>
	<jspcore:if test="${status == 'failure'}">
		<script>
			swal("Profile Not Updated.", "Please try again !", "error");
		</script>
	</jspcore:if>


	<script src="<%=basePath%>/vendors/fileinput/fileinput.min.js"></script>
	<script src="<%=basePath%>/js/emp.js"></script>
</body>
</html>