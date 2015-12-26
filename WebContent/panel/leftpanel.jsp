
<%@page import="com.dbt.dao.LoginDAO"%>
<%@page import="com.dbt.data.Privilege"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.dbt.data.User"%>
<%
	User user = (User)session.getAttribute("user");
    String type = user.getType();
    String path = request.getContextPath();
    String uri = request.getRequestURI();
    Iterator<Privilege> iter = LoginDAO.getPrivileges(type).iterator();
            	  /*  <jspcore:set var="privileges"
       					value="${LoginDAO.getPrivileges(type)}"></jspcore:set>
       				<jspcore:forEach var="priv" items="${LoginDAO.getPrivileges(type)}">
       					<jspcore:set var="subpriv" value="${priv.subprivs}"></jspcore:set>
       					<li class="sub-menu"><a href="#"><i class="${subpriv.iconClass}"></i>${subpriv.name}></a>
       						<ul>
       				</jspcore:forEach> */
%>

<jspcore:set var="user" value="${sessionScope.user}"></jspcore:set>

<aside id="sidebar">
	<div class="sidebar-inner c-overflow">
			<div class="profile-menu">
				<a href="#">
					<div class="profile-pic">
						<img src="<%=path%>/${user.photo}" alt="Profile Photo">
					</div>

					<div class="profile-info">
						${user.type} <i class="zmdi zmdi-arrow-drop-down"></i>
					</div>
				</a>

				<ul class="main-menu">
					<li><a href="Profile.jsp"><i class="zmdi zmdi-account"></i>
							View Profile</a></li>
					
					<li><a href="#"><i class="zmdi zmdi-settings"></i> Settings</a></li>
					<li><a href="<%=path%>/logout.do"><i class="zmdi zmdi-power"></i>
							Logout</a></li>
				</ul>
			</div>

			<ul class="main-menu">
				<li><a href="<%=path%>/dashboard.jsp"><i
						class="zmdi zmdi-home"></i> Home</a></li>
				<%
					while(iter.hasNext())
					{
						Privilege priv = iter.next();
						Iterator<Privilege> iterin = priv.subprivs.iterator();
						String classN = priv.getIconClass();
						String active = "";
						if(LoginDAO.isSubMenu(request.getRequestURI(), priv.getName()))
						{
							active = "active";
						}
				%>
				<li class="sub-menu <%=active%>"><a href="#"><i class="<%=classN%>"></i><%=priv.getName()%></a>
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
</aside>