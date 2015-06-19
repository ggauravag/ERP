
<%@page import="com.dbt.dao.LoginDAO"%>
<%@page import="com.dbt.data.Privilege"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.dbt.data.User"%>
<%
	User user = (User)session.getAttribute("user");
            	   String type = user.getType();
            	   String path = request.getContextPath();
            	   String uri = request.getRequestURI();
            	   Iterator<Privilege> iter = LoginDAO.getPrivileges(type);
%>
<aside id="sidebar">
	<div class="sidebar-inner">
		<div class="si-inner">
			<div class="profile-menu">
				<a href="#">
					<div class="profile-pic">
						<img src="<%=path%>/img/profile-pics/mypic.jpg" alt="">
					</div>

					<div class="profile-info">
						<%=type%>
						<i class="md md-arrow-drop-down"></i>
					</div>
				</a>

				<ul class="main-menu">
					<li><a href="profile-about.html"><i class="md md-person"></i>
							View Profile</a></li>
					<li><a href="#"><i class="md md-settings-input-antenna"></i>
							Privacy Settings</a></li>
					<li><a href="#"><i class="md md-settings"></i> Settings</a></li>
					<li><a href="<%=request.getContextPath()%>/logout.do"><i class="md md-history"></i>
							Logout</a></li>
				</ul>
			</div>

			<ul class="main-menu">
				<li class="active"><a href="dashboard.jsp"><i
						class="md md-home"></i> Home</a></li>

				<%
					while(iter.hasNext())
																																	{
																																		Privilege priv = iter.next();
																																		Iterator<Privilege> iterin = priv.subprivs.iterator();
																																		String classN = priv.getIconClass();
				%>
				<li class="sub-menu"><a href="#"><i class="<%=classN%>"></i><%=priv.getName()%></a>
					<ul>
						<%
							while(iterin.hasNext())
							 {
						    	Privilege inpriv = iterin.next();
						    	String className = "";
						    	String p = inpriv.getPath();
						    	String link = path +"/"+ p;
						    	if(p != null && uri.contains(p))
						    		className = "class='active'";
																																																												   
						%>
						<li><a href="<%=link%>" <%=className%>><%=inpriv.getName()%></a></li>
						<%
							}
						%>

					</ul></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
</aside>