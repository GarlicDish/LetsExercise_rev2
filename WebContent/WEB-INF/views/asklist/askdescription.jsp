<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

ul {
	margin: 0;
	padding: 0;
}

li {
	list-style-type: none;
	display: inline-block;
}

a {
	display: block;
	padding: 8px 20px;
	text-transform: uppercase;
	text-decoration: none;
	font-weight: bold;
	color: #333;
	transition: all 0.3s ease-in-out;
}

a:hover {
	color: #fff;
	background: #111;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th {
	height: 100px;
	width: 250px;
}
</style>
</head>
<body>

<ul>
	<li><a href="/kh/information">개인정보관리</a></li>
	<li><a href="/kh/mywork">나의활동</a></li>
	<li><a href="#">운동캘린더</a></li>
	<li><a href="/kh/ask">문의내역</a></li>
	<li><a href="/kh/bedge">획득뱃지</a></li>
</ul>

<h3>1:1 문의</h3>
<hr>

<button>최근1개월</button>
<button>최근3개월</button>

<input type="date" name="d">

<input type="date" name="d">

<input type="date" name="d">

<input type="date" name="d">

<button type="button" id="btnSelect" class="btn btn-outline-dark">조회</button>
<br><br>

<button>조회</button>
<br>
<table>
<tr>
	<th>유형</th>
	<th>문의내용</th>
</tr>

<tr>
	<th></th>
	<th></th>
</tr>
</table>

</body>
</html>