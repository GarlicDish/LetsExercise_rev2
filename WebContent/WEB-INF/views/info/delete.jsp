<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp" %>

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
	display: block;/main
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
</style>
</head>
<body>

<ul>
	<li><a href="/info/view">개인정보관리</a></li>
	<li><a href="/work/main">나의활동</a></li>
	<li><a href="/ask/list">문의내역</a></li>
</ul>
 

<h3>회원탈퇴 신청</h3>
<hr>


<form action="/info/delete" method="post">
<div>탈퇴사유
<label><input type="radio" name="reason" value="r1">사이트 이용 불편</label>
<label><input type="radio" name="reason" value="r2">개인정보유출우려</label>
<label><input type="radio" name="reason" value="r3">이용빈도낮음</label>
<label><input type="radio" name="reason" value="r4">기타사유</label>
</div>
<hr>

<label for="content">고객님의 의견</label>
<textarea rows="10" cols="80" id="content" name="content" placeholder="운동친구에 대한 불편사항이나 의견이 있으시면 기재 부탁드립니다."></textarea>
<hr>

<a href="<%=request.getContextPath() %>/info/view">취소</a>
<button class="btn btn-outline-dark">탈퇴</button>
</form>


<%@ include file="../main/layout/footer.jsp" %>