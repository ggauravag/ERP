<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title> Dashboard </title>
	
	<!-- Vendor CSS -->
    <link href="vendors/fullcalendar/fullcalendar.css" rel="stylesheet">
    <link href="vendors/animate-css/animate.min.css" rel="stylesheet">
    <link href="vendors/sweet-alert/sweet-alert.min.css" rel="stylesheet">
    <link href="vendors/material-icons/material-design-iconic-font.min.css" rel="stylesheet">
    <link href="vendors/socicon/socicon.min.css" rel="stylesheet">
            
    <!-- CSS -->
    <link href="css/app.min.1.css" rel="stylesheet">
    <link href="css/app.min.2.css" rel="stylesheet">	
	
</head>
<body>
		<header id="header">
            <ul class="header-inner">
                <li id="menu-trigger" data-trigger="#sidebar">
                    <div class="line-wrap">
                        <div class="line top"></div>
                        <div class="line center"></div>
                        <div class="line bottom"></div>
                    </div>
                </li>
            
                <li class="logo hidden-xs">
                    <a href="index.jpg">Operator Dashboard</a>
                </li>
                
                <li class="pull-right">
                <ul class="top-menu">
                    <li id="toggle-width">
                        <div class="toggle-switch">
                            <input id="tw-switch" type="checkbox" hidden="hidden">
                            <label for="tw-switch" class="ts-helper"></label>
                        </div>
                    </li>
                    <li id="top-search">
                        <a class="tm-search" href="#"></a>
                    </li>
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="tm-message" href="#"><i class="tmn-counts">2</i></a>
                        <div class="dropdown-menu dropdown-menu-lg pull-right">
                            <div class="listview">
                                <div class="lv-header">
                                    Messages
                                </div>
                                <div class="lv-body c-overflow">
                                    <a class="lv-item" href="#">
                                        <div class="media">
                                            <div class="pull-left">
                                                <img class="lv-img-sm" src="img/profile-pics/mypic.jpg" alt="">
                                            </div>
                                            <div class="media-body">
                                                <div class="lv-title">David Belle</div>
                                                <small class="lv-small">Cum sociis natoque penatibus et magnis dis parturient montes</small>
                                            </div>
                                        </div>
                                    </a>
                                    <a class="lv-item" href="#">
                                        <div class="media">
                                            <div class="pull-left">
                                                <img class="lv-img-sm" src="img/profile-pics/mypic.jpg" alt="">
                                            </div>
                                            <div class="media-body">
                                                <div class="lv-title">Jonathan Morris</div>
                                                <small class="lv-small">Nunc quis diam diamurabitur at dolor elementum, dictum turpis vel</small>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <a class="lv-footer" href="#">View All</a>
                            </div>
                        </div>
                    </li>
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="tm-notification" href="#"><i class="tmn-counts">3</i></a>
                        <div class="dropdown-menu dropdown-menu-lg pull-right">
                            <div class="listview" id="notifications">
                                <div class="lv-header">
                                    Notification
                    
                                    <ul class="actions">
                                        <li class="dropdown">
                                            <a href="#" data-clear="notification">
                                                <i class="md md-done-all"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="lv-body c-overflow">
                                    <a class="lv-item" href="#">
                                        <div class="media">
                                            <div class="pull-left">
                                                <img class="lv-img-sm" src="img/profile-pics/mypic.jpg" alt="">
                                            </div>
                                            <div class="media-body">
                                                <div class="lv-title">David Belle</div>
                                                <small class="lv-small">Cum sociis natoque penatibus et magnis dis parturient montes</small>
                                            </div>
                                        </div>
                                    </a>
                                    <a class="lv-item" href="#">
                                        <div class="media">
                                            <div class="pull-left">
                                                <img class="lv-img-sm" src="img/profile-pics/mypic.jpg" alt="">
                                            </div>
                                            <div class="media-body">
                                                <div class="lv-title">Jonathan Morris</div>
                                                <small class="lv-small">Nunc quis diam diamurabitur at dolor elementum, dictum turpis vel</small>
                                            </div>
                                        </div>
                                    </a>
                                    <a class="lv-item" href="#">
                                        <div class="media">
                                            <div class="pull-left">
                                                <img class="lv-img-sm" src="img/profile-pics/mypic.jpg" alt="">
                                            </div>
                                            <div class="media-body">
                                                <div class="lv-title">Fredric Mitchell Jr.</div>
                                                <small class="lv-small">Phasellus a ante et est ornare accumsan at vel magnauis blandit turpis at augue ultricies</small>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <a class="lv-footer" href="#">View Previous</a>
                            </div>
                        </div>
                    </li>
                    <li class="dropdown hidden-xs">
                        <a data-toggle="dropdown" class="tm-task" href="#"><i class="tmn-counts">2</i></a>
                        <div class="dropdown-menu pull-right dropdown-menu-lg">
                            <div class="listview">
                                <div class="lv-header">
                                    Tasks
                                </div>
                                <div class="lv-body">
                                    <div class="lv-item">
                                        <div class="lv-title m-b-5">HTML5 Validation Report</div>
                    
                                        <div class="progress">
                                            <div class="progress-bar" role="progressbar" aria-valuenow="95" aria-valuemin="0" aria-valuemax="100" style="width: 95%">
                                                <span class="sr-only">95% Complete (success)</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="lv-item">
                                        <div class="lv-title m-b-5">Google Chrome Extension</div>
                    
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                                <span class="sr-only">80% Complete (success)</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="lv-item">
                                        <div class="lv-title m-b-5">Social Intranet Projects</div>
                    
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                                <span class="sr-only">20% Complete</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="lv-item">
                                        <div class="lv-title m-b-5">Bootstrap Admin Template</div>
                    
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                                <span class="sr-only">60% Complete (warning)</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="lv-item">
                                        <div class="lv-title m-b-5">Youtube Client App</div>
                    
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                                <span class="sr-only">80% Complete (danger)</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                    
                                <a class="lv-footer" href="#">View All</a>
                            </div>
                        </div>
                    </li>
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="tm-settings" href="#"></a>
                        <ul class="dropdown-menu dm-icon pull-right">
                            <li>
                                <a data-action="fullscreen" href="#"><i class="md md-fullscreen"></i> Toggle Fullscreen</a>
                            </li>
                            <li>
                                <a data-action="clear-localstorage" href="#"><i class="md md-delete"></i> Clear Local Storage</a>
                            </li>
                            <li>
                                <a href="#"><i class="md md-person"></i> Privacy Settings</a>
                            </li>
                            <li>
                                <a href="#"><i class="md md-settings"></i> Other Settings</a>
                            </li>
                        </ul>
                    </li>
                    
                    </ul>
                </li>
            </ul>
            
            <!-- Top Search Content -->
            <div id="top-search-wrap">
                <input type="text">
                <i id="top-search-close">&times;</i>
            </div>
        </header>
        
        <section id="main">
            <aside id="sidebar">
                <div class="sidebar-inner">
                    <div class="si-inner">
                        <div class="profile-menu">
                            <a href="#">
                                <div class="profile-pic">
                                    <img src="img/profile-pics/mypic.jpg" alt="">
                                </div>
                                
                                <div class="profile-info">
                                    OPERATOR
                                    
                                    <i class="md md-arrow-drop-down"></i>
                                </div>
                            </a>
                            
                            <ul class="main-menu">
                                <li>
                                    <a href="profile-about.html"><i class="md md-person"></i> View Profile</a>
                                </li>
                                <li>
                                    <a href="#"><i class="md md-settings"></i> Settings</a>
                                </li>
                                <li>
                                    <a href="#"><i class="md md-history"></i> Logout</a>
                                </li>
                            </ul>
                        </div>
                        
                        <ul class="main-menu">
                            <li class="active"><a href="index.jsp"><i class="md md-home"></i> Home</a></li>
                            <li class="sub-menu">
                                <a href="#"><i class="md-group"></i> Employee</a>
                                <ul>
                                    <li><a href="tables.html">Mark Attendance</a></li>
                                </ul>
                            </li>
                            <li class="sub-menu">
                                <a href="#"><i class="md-shopping-cart"></i> Orders</a>

                                <ul>
                                    <li><a href="widget-templates.html">Take Orders</a></li>
                                    <li><a href="widget-templates.html">Process Orders</a></li>
                                    <li><a class="active" href="widgets.html">View Previous Orders</a></li>
                                    <li><a href="widget-templates.html">Generate Bill</a></li>
                                    <li><a href="widget-templates.html">Generate Challan</a></li>
                                </ul>
                            </li>
                            <li class="sub-menu">
                                <a href="#"><i class="md-description"></i> Complaints</a>
                
                                <ul>
                                    <li><a href="tables.html">View Complaints</a></li>
                                    <li><a href="tables.html">Register Complaints</a></li>
                                    <li><a href="data-tables.html">Process Complaints</a></li>
                                </ul>
                            </li>
                            <li class="sub-menu">
                                <a href="#"><i class="md-add-shopping-cart"></i> Inventory</a>
                
                                <ul>
                                    <li><a href="form-elements.html">Add Stock</a></li>
                                </ul>
                            </li>
                            <li class="sub-menu">
                                <a href="#"><i class="md-my-library-add"></i>Expenditure</a>
                                <ul>
                                    <li><a href="colors.html">Add Expenditure</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </aside>
            
            <section id="content">
                <div class="container">
                    <div class="block-header">
                        <h2>Dashboard</h2>
                        
                        <ul class="actions">
                            <li>
                                <a href="#">
                                    <i class="md md-trending-up"></i>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="md md-done-all"></i>
                                </a>
                            </li>
                            <li class="dropdown">
                                <a href="#" data-toggle="dropdown">
                                    <i class="md md-more-vert"></i>
                                </a>
                                
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li>
                                        <a href="#">Refresh</a>
                                    </li>
                                    <li>
                                        <a href="#">Manage Widgets</a>
                                    </li>
                                    <li>
                                        <a href="#">Widgets Settings</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        
                    </div>
                    
                    <div class="card">
                        <div class="card-header">
                            <h2>Sales Statistics <small>Statistics are based on per month sale</small></h2>
                            
                            <ul class="actions">
                                <li>
                                    <a href="#">
                                        <i class="md md-cached"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="md md-file-download"></i>
                                    </a>
                                </li>
                                <li class="dropdown">
                                    <a href="#" data-toggle="dropdown">
                                        <i class="md md-more-vert"></i>
                                    </a>
                                    
                                    <ul class="dropdown-menu dropdown-menu-right">
                                        <li>
                                            <a href="#">Change Date Range</a>
                                        </li>
                                        <li>
                                            <a href="#">Change Graph Type</a>
                                        </li>
                                        <li>
                                            <a href="#">Other Settings</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        
                        <div class="card-body">
                            <div class="chart-edge">
                                <div id="curved-line-chart" class="flot-chart "></div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="mini-charts">
                        <div class="row">
                            <div class="col-sm-6 col-md-3">
                                <div class="mini-charts-item bgm-cyan">
                                    <div class="clearfix">
                                        <div class="chart stats-bar"></div>
                                        <div class="count">
                                            <small>Website Traffics</small>
                                            <h2>987,459</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-6 col-md-3">
                                <div class="mini-charts-item bgm-orange">
                                    <div class="clearfix">
                                        <div class="chart stats-line"></div>
                                        <div class="count">
                                            <small>Total Sales</small>
                                            <h2>$ 458,778</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <div class="dash-widgets">
                        <div class="row">
                            <div class="col-md-3 col-sm-6">
                                <div id="pie-charts" class="dash-widget-item">
                                    <div class="bgm-pink">
                                        <div class="dash-widget-header">
                                            <div class="dash-widget-title">Email Statistics</div>
                                        </div>
                                        
                                        <div class="clearfix"></div>
                                        
                                        <div class="text-center p-20 m-t-25">
                                            <div class="easy-pie main-pie" data-percent="75">
                                                <div class="percent">45</div>
                                                <div class="pie-title">Total Emails Sent</div>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="p-t-20 p-b-20 text-center">
                                        <div class="easy-pie sub-pie-1" data-percent="56">
                                            <div class="percent">56</div>
                                            <div class="pie-title">Bounce Rate</div>
                                        </div>
                                        <div class="easy-pie sub-pie-2" data-percent="84">
                                            <div class="percent">84</div>
                                            <div class="pie-title">Total Opened</div>
                                        </div>
                                    </div>
    
                                </div>
                            </div>
                            
                            
    
                            <div class="col-md-3 col-sm-6">
                                <div id="best-selling" class="dash-widget-item">
                                    <div class="dash-widget-header">
                                        <div class="dash-widget-title">Best Sellings</div>
                                        <img src="img/widgets/alpha.jpg" alt="">
                                        <div class="main-item">
                                            <small>Samsung Galaxy Alpha</small>
                                            <h2>$799.99</h2>
                                        </div>
                                    </div>
                                
                                    <div class="listview p-t-5">
                                        <a class="lv-item" href="#">
                                            <div class="media">
                                                <div class="pull-left">
                                                    <img class="lv-img-sm" src="img/widgets/note4.jpg" alt="">
                                                </div>
                                                <div class="media-body">
                                                    <div class="lv-title">Samsung Galaxy Note 4</div>
                                                    <small class="lv-small">$850.00 - $1199.99</small>
                                                </div>
                                            </div>
                                        </a>
                                        <a class="lv-item" href="#">
                                            <div class="media">
                                                <div class="pull-left">
                                                    <img class="lv-img-sm" src="img/widgets/mate7.jpg" alt="">
                                                </div>
                                                <div class="media-body">
                                                    <div class="lv-title">Huawei Ascend Mate</div>
                                                    <small class="lv-small">$649.59 - $749.99</small>
                                                </div>
                                            </div>
                                        </a>
                                        <a class="lv-item" href="#">
                                            <div class="media">
                                                <div class="pull-left">
                                                    <img class="lv-img-sm" src="img/widgets/535.jpg" alt="">
                                                </div>
                                                <div class="media-body">
                                                    <div class="lv-title">Nokia Lumia 535</div>
                                                    <small class="lv-small">$189.99 - $250.00</small>
                                                </div>
                                            </div>
                                        </a>
                                        
                                        <a class="lv-footer" href="#">
                                            View All
                                        </a>
                                    </div>
                                </div>
                            </div>
                            
                            <!-- Todo Lists -->
                            <div class="col-sm-6">
	                            <div id="todo-lists">
	                                <div class="tl-header">
	                                    <h2>Todo Lists</h2>
	                                    <small>Add, edit and manage your Todo Lists</small>
	                                    
	                                    <ul class="actions actions-alt">
	                                        <li class="dropdown">
	                                            <a href="#" data-toggle="dropdown">
	                                                <i class="md md-more-vert"></i>
	                                            </a>
	                                            
	                                            <ul class="dropdown-menu dropdown-menu-right">
	                                                <li>
	                                                    <a href="#">Refresh</a>
	                                                </li>
	                                                <li>
	                                                    <a href="#">Manage Widgets</a>
	                                                </li>
	                                                <li>
	                                                    <a href="#">Widgets Settings</a>
	                                                </li>
	                                            </ul>
	                                        </li>
	                                    </ul>
	                                </div>
	                                    
	                                <div class="clearfix"></div>
	                                    
	                                <div class="tl-body">
	                                    <div id="add-tl-item">
	                                        <i class="add-new-item md md-add"></i>
	                                        
	                                        <div class="add-tl-body">
	                                            <textarea placeholder="What you want to do..."></textarea>
	                                            
	                                            <div class="add-tl-actions">
	                                                <a href="#" data-tl-action="dismiss"><i class="md md-close"></i></a>
	                                                <a href="#" data-tl-action="save"><i class="md md-check"></i></a>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    
	                                    <div class="checkbox media">
	                                        <div class="pull-right">
	                                            <ul class="actions actions-alt">
	                                                <li class="dropdown">
	                                                    <a href="#" data-toggle="dropdown">
	                                                        <i class="md md-more-vert"></i>
	                                                    </a>
	                                                    
	                                                    <ul class="dropdown-menu dropdown-menu-right">
	                                                        <li><a href="#">Delete</a></li>
	                                                        <li><a href="#">Archive</a></li>
	                                                    </ul>
	                                                </li>
	                                            </ul>
	                                        </div>
	                                        <div class="media-body">
	                                            <label>
	                                                <input type="checkbox">
	                                                <i class="input-helper"></i>
	                                                <span>Task 1 - To be done tommorow</span>
	                                            </label>
	                                        </div>
	                                    </div>
	                                    
	                                    <div class="checkbox media">
	                                        <div class="pull-right">
	                                            <ul class="actions actions-alt">
	                                                <li class="dropdown">
	                                                    <a href="#" data-toggle="dropdown">
	                                                        <i class="md md-more-vert"></i>
	                                                    </a>
	                                                    
	                                                    <ul class="dropdown-menu dropdown-menu-right">
	                                                        <li><a href="#">Delete</a></li>
	                                                        <li><a href="#">Archive</a></li>
	                                                    </ul>
	                                                </li>
	                                            </ul>
	                                        </div>
	                                        <div class="media-body">
	                                            <label>
	                                                <input type="checkbox">
	                                                <i class="input-helper"></i>
	                                                <span>Task 2 - To be done tommorow</span>
	                                            </label>
	                                        </div>
	                                    </div>
	                                    
	                                    <div class="checkbox media">
	                                        <div class="pull-right">
	                                            <ul class="actions actions-alt">
	                                                <li class="dropdown">
	                                                    <a href="#" data-toggle="dropdown">
	                                                        <i class="md md-more-vert"></i>
	                                                    </a>
	                                                    
	                                                    <ul class="dropdown-menu dropdown-menu-right">
	                                                        <li><a href="#">Delete</a></li>
	                                                        <li><a href="#">Archive</a></li>
	                                                    </ul>
	                                                </li>
	                                            </ul>
	                                        </div>
	                                        <div class="media-body">
	                                            <label>
	                                                <input type="checkbox">
	                                                <i class="input-helper"></i>
	                                                <span>Task 3 - To be done tommorow</span>
	                                            </label>
	                                        </div>
	                                    </div>
	                                    
	                                    <div class="checkbox media">
	                                        <div class="pull-right">
	                                            <ul class="actions actions-alt">
	                                                <li class="dropdown">
	                                                    <a href="#" data-toggle="dropdown">
	                                                        <i class="md md-more-vert"></i>
	                                                    </a>
	                                                    
	                                                    <ul class="dropdown-menu dropdown-menu-right">
	                                                        <li><a href="#">Delete</a></li>
	                                                        <li><a href="#">Archive</a></li>
	                                                    </ul>
	                                                </li>
	                                            </ul>
	                                        </div>
	                                        <div class="media-body">
	                                            <label>
	                                                <input type="checkbox">
	                                                <i class="input-helper"></i>
	                                                <span>Task 4 - To be done tommorow</span>
	                                            </label>
	                                        </div>
	                                    </div>
	                                    
	                                    <div class="checkbox media">
	                                        <div class="pull-right">
	                                            <ul class="actions actions-alt">
	                                                <li class="dropdown">
	                                                    <a href="#" data-toggle="dropdown">
	                                                        <i class="md md-more-vert"></i>
	                                                    </a>
	                                                    
	                                                    <ul class="dropdown-menu dropdown-menu-right">
	                                                        <li><a href="#">Delete</a></li>
	                                                        <li><a href="#">Archive</a></li>
	                                                    </ul>
	                                                </li>
	                                            </ul>
	                                        </div>
	                                        <div class="media-body">
	                                            <label>
	                                                <input type="checkbox">
	                                                <i class="input-helper"></i>
	                                                <span>Task 5 - To be done tommorow</span>
	                                            </label>
	                                        </div>
	                                    </div>
	                                    
	                                    <div class="checkbox media">
	                                        <div class="pull-right">
	                                            <ul class="actions actions-alt">
	                                                <li class="dropdown">
	                                                    <a href="#" data-toggle="dropdown">
	                                                        <i class="md md-more-vert"></i>
	                                                    </a>
	                                                    
	                                                    <ul class="dropdown-menu dropdown-menu-right">
	                                                        <li><a href="#">Delete</a></li>
	                                                        <li><a href="#">Archive</a></li>
	                                                    </ul>
	                                                </li>
	                                            </ul>
	                                        </div>
	                                        <div class="media-body">
	                                            <label>
	                                                <input type="checkbox">
	                                                <i class="input-helper"></i>
	                                                <span>Task 6 - To be done tommorow</span>
	                                            </label>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div><!-- Todo Lists -->
                           </div> 
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-sm-6">
                            <!-- Calendar -->
                            <div id="calendar-widget"></div>
                        </div>
                        
                        <div class="col-sm-6">
                            <!-- Recent Items -->
                            <div class="card">
                                <div class="card-header">
                                    <h2>Recent Items <small>Phasellus condimentum ipsum id auctor imperdie</small></h2>
                                    <ul class="actions">
                                        <li class="dropdown">
                                            <a href="#" data-toggle="dropdown">
                                                <i class="md md-more-vert"></i>
                                            </a>
                                            
                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li>
                                                    <a href="#">Refresh</a>
                                                </li>
                                                <li>
                                                    <a href="#">Settings</a>
                                                </li>
                                                <li>
                                                    <a href="#">Other Settings</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                                
                                <div class="card-body m-t-0">
                                    <table class="table table-inner table-vmiddle">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th style="width: 60px">Price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td class="f-500 c-cyan">2569</td>
                                                <td>Samsung Galaxy Mega</td>
                                                <td class="f-500 c-cyan">$521</td>
                                            </tr>
                                            <tr>
                                                <td class="f-500 c-cyan">9658</td>
                                                <td>Huawei Ascend P6</td>
                                                <td class="f-500 c-cyan">$440</td>
                                            </tr>
                                            <tr>
                                                <td class="f-500 c-cyan">1101</td>
                                                <td>HTC One M8</td>
                                                <td class="f-500 c-cyan">$680</td>
                                            </tr>
                                            <tr>
                                                <td class="f-500 c-cyan">6598</td>
                                                <td>Samsung Galaxy Alpha</td>
                                                <td class="f-500 c-cyan">$870</td>
                                            </tr>
                                            <tr>
                                                <td class="f-500 c-cyan">4562</td>
                                                <td>LG G3</td>
                                                <td class="f-500 c-cyan">$690</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div id="recent-items-chart" class="flot-chart"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </section>
        
        
        <!-- Javascript Libraries -->
        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
        <script src="vendors/flot/jquery.flot.min.js"></script>
        <script src="vendors/flot/jquery.flot.resize.min.js"></script>
        <script src="vendors/flot/plugins/curvedLines.js"></script>
        <script src="vendors/sparklines/jquery.sparkline.min.js"></script>
        <script src="vendors/easypiechart/jquery.easypiechart.min.js"></script>
        
        <script src="vendors/fullcalendar/lib/moment.min.js"></script>
        <script src="vendors/fullcalendar/fullcalendar.min.js"></script>
        <script src="vendors/simpleWeather/jquery.simpleWeather.min.js"></script>
        <script src="vendors/auto-size/jquery.autosize.min.js"></script>
        <script src="vendors/nicescroll/jquery.nicescroll.min.js"></script>
        <script src="vendors/waves/waves.min.js"></script>
        <script src="vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
        <script src="vendors/sweet-alert/sweet-alert.min.js"></script>
        
        <script src="js/flot-charts/curved-line-chart.js"></script>
        <script src="js/flot-charts/line-chart.js"></script>
        <script src="js/charts.js"></script>
        
        <script src="js/charts.js"></script>
        <script src="js/functions.js"></script>
        <script src="js/demo.js"></script>
</body>
</html>