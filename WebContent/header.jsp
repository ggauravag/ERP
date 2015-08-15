<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="jspcore" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="loaderImage" style="display: none">
	<div class="modalNew">
		<div class="centerNew">
			<img alt="" src="<%=basePath%>/img/loader.gif" />
		</div>
	</div>
</div>
<input type="hidden" id="basePath" value="<%=basePath%>">
<header id="header">
	<ul class="header-inner">
		<li id="menu-trigger" data-trigger="#sidebar">
			<div class="line-wrap">
				<div class="line top"></div>
				<div class="line center"></div>
				<div class="line bottom"></div>
			</div>
		</li>

		<li class="logo hidden-xs"><a href="#"><bean:message
					key="enterprise.label" /></a></li>

		<li class="pull-right">
			<ul class="top-menu">
				<li id="toggle-width">
					<div class="toggle-switch">
						<input id="tw-switch" type="checkbox" hidden="hidden"> <label
							for="tw-switch" class="ts-helper"></label>
					</div>
				</li>
				<li id="top-search"><a class="tm-search" href="#"></a></li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="tm-message" href="#"><i class="tmn-counts">1</i></a>
					<div class="dropdown-menu dropdown-menu-lg pull-right">
						<div class="listview">
							<div class="lv-header">Messages</div>
							<div class="lv-body c-overflow">
								<a class="lv-item" href="#">
									<div class="media">
										<div class="pull-left">
											<img class="lv-img-sm"
												src="img/profile-pics/mypic.jpg"
												alt="">
										</div>
										<div class="media-body">
											<div class="lv-title">David Belle</div>
											<small class="lv-small">This is a sample message.</small>
										</div>
									</div>
								
							</div>
							<a class="lv-footer" href="#">View All</a>
						</div>
					</div></li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="tm-notification" href="#"><i class="tmn-counts">1</i></a>
					<div class="dropdown-menu dropdown-menu-lg pull-right">
						<div class="listview" id="notifications">
							<div class="lv-header">
								Notification

								<ul class="actions">
									<li class="dropdown"><a href="#" data-clear="notification">
											<i class="md md-done-all"></i>
									</a></li>
								</ul>
							</div>
							<div class="lv-body c-overflow">
								 <a class="lv-item" href="#">
									<div class="media">
										<div class="pull-left">
											<img class="lv-img-sm"
												src="img/profile-pics/4.jpg"
												alt="">
										</div>
										<div class="media-body">
											<div class="lv-title">Sample Notification</div>
											<small class="lv-small">This is the place for sample notification.</small>
										</div>
									</div>
								</a> 
							</div>

							<a class="lv-footer" href="#">View Previous</a>
						</div>

					</div></li>
				<li class="dropdown hidden-xs"><a data-toggle="dropdown"
					class="tm-task" href="#"><i class="tmn-counts">1</i></a>
					<div class="dropdown-menu pull-right dropdown-menu-lg">
						<div class="listview">
							<div class="lv-header">Tasks</div>
							<div class="lv-body">
								<div class="lv-item">
									<div class="lv-title m-b-5">HTML5 Validation Report</div>

									<div class="progress">
										<div class="progress-bar" role="progressbar"
											aria-valuenow="95" aria-valuemin="0" aria-valuemax="100"
											style="width: 95%">
											<span class="sr-only">95% Complete (success)</span>
										</div>
									</div>
								</div>
							</div>

							<a class="lv-footer" href="#">View All</a>
						</div>
					</div></li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="tm-settings" href="#"></a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li><a data-action="fullscreen" href="#"><i
								class="md md-fullscreen"></i> Toggle Fullscreen</a></li>
						
						<li><a href="#"><i class="md md-settings"></i> Other
								Settings</a></li>
					</ul></li>

			</ul>
		</li>
	</ul>

	<!-- Top Search Content -->
	<div id="top-search-wrap">
		<input type="text"> <i id="top-search-close">&times;</i>
	</div>
</header>