<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 467px
}

/* Set gray background color and 100% height */
.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}

.sidebar {
	padding-top: 30px;
	background-color: #f1f1f1;
	height: 100%;
}

.sidebar a {
	padding: 10px 8px 10px 16px;
	text-decoration: none;
	font-size: 20px;
	color: #818181;
	display: block;
}

.sidebar a:hover {
	color: #a94442;
	background-color: #555;
}
</style>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Logo</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Projects</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${isUserLogin == true}">
						<li><a href="<c:url value='/otslogin' />">
					 		<span class="glyphicon glyphicon-log-out"></span> Logout</a>
						</li>
					</c:if>
					<c:if test="${isUserLogin == false || empty isUserLogin }">
						<li><a href="<c:url value='/otslogin' />">
					 		<span class="glyphicon glyphicon-log-in"></span> Login</a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidebar">
				<a href="#home"><i class="fa fa-fw fa-home"></i> Home</a> 
					<a href="<c:url value='/task' />"><i class="fa fa-tasks"></i> Task</a> 
					<a href="<c:url value='/client' />"><i class="fa fa-fw fa-user"></i> Clients</a> 
					<a href="#clients"><i class="fa fa-clock-o"></i> TimeSheet</a> 
					<a href="#services"><i class="fa fa-bell"></i> Reminder</a> 
					<a href="#services"><i class="fa fa-fw fa-wrench"></i> Services</a>
			</div>
			<div class="col-sm-8 text-left">
				<div>
					<h2>Client</h2>
					<p>All Current Client Are </p>
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>#</th>
									<th>TASK NAME</th>
									<th>STATUS</th>
									<th>ASSIGNEE</th>
									<th>PROJECT</th>
									<th>COUNTRY</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>Anna</td>
									<td>Pitt</td>
									<td>35</td>
									<td>New York</td>
									<td>USA</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-2 sidenav">
				<div class="well">
					<p>ADS</p>
				</div>
				<div class="well">
					<p>ADS</p>
				</div>
			</div>
		</div>
	</div>

	<footer class="container-fluid text-center">
		<p>Footer Text</p>
	</footer>

</body>
</html>
