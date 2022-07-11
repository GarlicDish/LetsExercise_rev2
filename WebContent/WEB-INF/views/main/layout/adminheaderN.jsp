<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- css부트스트랩 적용 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> 
<!-- 부트스트랩 아이콘 적용 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">

<title>관리자페이지 - 운동어때?</title>

<!-- AJax수행을 위한 제이쿼리  -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<!-- script부트스트랩 적용 -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<style type="text/css">

/* navbar */
.sidebar {
	height:100vh;
	background: linear-gradient(rgba(0,0,0,0.7), rgba(0,0,0,0.7)), url(/images/img1.jpg);
	background-position: center;
	background-repeat: no-repeat;	
	background-size: cover;
	box-shadow: 5px 7px 25px #999;
}

.bottom-border {
	border-bottom: 2px groove #eee;
}

.siderbar-link {
	transition: all .4s;
}

.sidebar-link:hover {
	background-color: #444;
	border-radius: 5px;
}

.current {
	background-color: #f44336;
	border-radius: 7px;
	box-shadow: 2px 5px 10px #222;
}

.current:hover {
	background-color: #f66436;
	border-radius: 7px;
	box-shadow: 2px 5px 20px #111;
/* 	transform: translateY(-1px); */
}

/* end of navbar */


</style>


</head>
<body>
	<nav class="navbal navbar-expand-md navbar-light">
		<button class="navbar-toggler ml-auto mb-2 bg-light" type="button" 
			data-toggle="collapse" data-target="#sidebar">
			<span class="navber-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="sidebar">
			<div class="container-fluid">
				<div class="row">
					<!-- 사이드바 - 화면 왼쪽 고정 -->
					<div class="col-xl-2 col-lg-3 sidebar fixed-top">
						<a href="#" class="navbar-brand text-white text-center d-block mx-auto py-3 mb-4 
						bottom-border">운동어때?</a>
						<div class="bottom-border pb-3">
							<a href="#" class="text-white">관리자 이름</a>
						</div>
						<ul class="navbar-nav flex-column mt-4">
							<!-- Dashboard -->
							<li class="nav-item">
								<a href="<%=request.getContextPath() %>/admin/login" class="nav-link text-white p-3 mb-2 sidebar-link">
									<i class="bi bi-house-door-fill text-white fa-lg mr-3"></i>Dashboard
								</a>
							</li>
							<!-- Dashboard -->
							<li class="nav-item">
								<a href="<%=request.getContextPath() %>/admin/notice/list" class="nav-link text-white p-3 mb-2 current">
									<i class="bi bi-person-fill text-white fa-lg mr-3"></i>notice
								</a>
							</li>
							<!-- Dashboard -->
							<li class="nav-item">
								<a href="<%=request.getContextPath() %>/Admin/qna/list" class="nav-link text-white p-3 mb-2 sidebar-link">
									<i class="bi bi-people-fill text-white fa-lg mr-3"></i>QnA
								</a>
							</li>
						</ul>
					</div>
					<!-- 사이드바 end -->
					<!-- 상단메뉴 -->
					<div class="col-xl-10 col-lg-9 ml-auto bg-dark fixed-top py-2">
						<div class="row">
							<div class="col-md-4">
								<h4 class="text-light text-uppercase mb-0">관리자페이지</h4>
							</div>
							<div class="col-md-4">
							
							</div>
							<div class="col-md-4">
								<ul class="navbar-nav">
									<li class="nav-item ml-auto"><a href="#" class="nav-link"><i class="bi bi-door-closed-fill text-danger text-strong fa-lg"></i></a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 상단메뉴 end -->
				</div>
				<!-- main -->
				<section>
					<div class="container-fluid">
						<div class="row">
							<div class=" col-lg-9 ml-auto">
								<div class="row pt-5 mt-3 mb-5">
								