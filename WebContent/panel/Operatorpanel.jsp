
<%
	String path = request.getContextPath();

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
						Operator <i class="md md-arrow-drop-down"></i>
					</div>
				</a>

				<ul class="main-menu">
					<li><a href="profile-about.html"><i class="md md-person"></i>
							View Profile</a></li>
					<li><a href="#"><i class="md md-settings-input-antenna"></i>
							Privacy Settings</a></li>
					<li><a href="#"><i class="md md-settings"></i> Settings</a></li>
					<li><a href="./logout.do"><i class="md md-history"></i>
							Logout</a></li>
				</ul>
			</div>

			<ul class="main-menu">
				<li class="active"><a href="dashboard.jsp"><i
						class="md md-home"></i> Home</a></li>
						
						
				<li class="sub-menu"><a href="#"><i class="md-group"></i>Employee</a>
					<ul>
						<li><a href="operator/takeorder.jsp">Mark Attendance</a></li>
					</ul>
				</li>
				<li class="sub-menu"><a href="#"><i
						class="md-shopping-cart"></i> Orders</a>

					<ul>
						<li><a href="operator/takeorder.jsp">Take Orders</a></li>
						<li><a href="operator/takeorder.jsp">Process Orders</a></li>
						<li><a href="operator/takeorder.jsp">View Previous Orders</a></li>
						<li><a href="operator/takeorder.jsp">Generate Bill</a></li>
						<li><a href="operator/takeorder.jsp">Generate Challan</a></li>
					</ul></li>
				<li class="sub-menu"><a href="#"><i class="md-description"></i>
						Complaints</a>

					<ul>
						<li><a href="operator/takeorder.jsp">View Complaints</a></li>
						<li><a href="operator/takeorder.jsp">Register Complaints</a></li>
						<li><a href="operator/takeorder.jsp">Process Complaints</a></li>
					</ul></li>
				<li class="sub-menu"><a href="#"><i
						class="md-add-shopping-cart"></i> Inventory</a>

					<ul>
						<li><a href="operator/takeorder.jsp">Add Stock</a></li>
					</ul></li>
				<li class="sub-menu"><a href="#"><i
						class="md-my-library-add"></i>Expenditure</a>
					<ul>
						<li><a href="operator/takeorder.jsp">Add Expenditure</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</aside>