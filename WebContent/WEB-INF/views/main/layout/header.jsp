<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
  <!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">

    <meta charset="utf-8">
    <title></title>
  	
  	<style>

</style>
<style type="text/css">
.main div {
	border-collapse: 1px solid #ccc;
}

.main ul {
	margin: 0;
	padding: 0;
} 

.main li {
	list-style-type: none;
	display: inline-block;
} 

.main a {
 	display: block; 
	padding: 8px 20px; 
	text-transform: uppercase;
	text-decoration: none;
	font-weight: bold;
	color: #333;
	transition: all 0.3s ease-in-out;
} 

.main a:hover {
	color: #fff;
	background: #111;
}

.dropdown {
/*  	padding: 20px 0 20px 10px; */
 	font-size: 16px;
	text-align:center;
}
.dropdowm ul {
	hieght : 40px;
}

.dropdown li {
	display:inline;
	text-align:center;
	display:inline-block;
	width : 150px;
}

.dropdown a {
/* 	display:inline-block; */
}

.profilebox {
	width: 70px;
    height: 70px; 
    border-radius: 70%;
    overflow: hidden;
    vertical-align: baseline;
}

.profileviewbox {
	width: 100px;
    height: 100px; 
    border-radius: 70%;
    overflow: hidden;
}

.replyprofilebox {
	width: 40px;
    height: 40px; 
    border-radius: 70%;
    overflow: hidden;
}

.profile {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.repprofile {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.SNSWriter {
	width: 600px;
	height: 20px;
}

.SNScontent {
	width: 600px;
	height : 100px;
	overflow: auto;
	white-space:pre-line;
	word-wrap: break-word;
}

.SNSViewcontent {
	width: 600px;
	height : 100%;
	overflow: auto;
	white-space:pre-line;
	word-wrap: break-word;
}

.btnSNS {
	float:right;	
}

.SNSlist {
	border : 1px solid gray;
	border-radius : 15px;
	width : 800px;
	height : 100%;
	margin : auto 0;
	padding : 10px;
}

.reply {
	padding : 5px;
	width: 1100px;
	height : auto;
	margin : 5px;
}

.reply td{
	border-top: 1px solid gray;
	padding : 3px;
}

img {
	

}
/* ul li:nth-child(1) { */
/* 	width : 0.1px; */
/* 	height : 0.1px; */
/* 	magin : auto 0; */
/* } */

</style>
    
  </head>
  <body>
	<nav>
		<div class="main" style="margin-right: 20px; float:right;">
			<%
				if( session.getAttribute("login") == null) { // ???????????? ??????
			%>
			<button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath()%>/member/login'">?????????</button>
			<button class="btn btn-outline-dark" onclick="location.href='/member/join'">????????????</button> <br>
			<%
				} else { // ????????? ??????
			%>
			<button class="btn btn-outline-dark" onclick="window.open('/index.jsp', '_blank', 'width=400px, height= 680px')">?????????</button>
			<button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/member/logout'">????????????</button>
			<%		
				}
			%>
		</div>
	</nav>
	
	<br>
	<nav>
		<div class="main dropdown">
			<ul>
				<li><a href="<%= request.getContextPath() %>/main"><img src="/images/logo1.png" width = "100" height="50"></img></a></li>
				<li><a href="<%= request.getContextPath() %>/find/list">?????? ??????</a></li>
				<li><a href="<%= request.getContextPath() %>/club/list">????????? ??????</a></li>
				<li><a href="<%= request.getContextPath() %>/notice/list">????????????</a></li>
				<li><a href="<%= request.getContextPath() %>/qna/list">QnA</a></li>
				<li><a href="<%= request.getContextPath() %>/myClub/list">My ?????????</a></li>
				<li><a href="<%= request.getContextPath() %>/info/view">My ?????????</a></li>
			</ul>
		</div>
	</nav>
		<hr>
	<br>
	<br>