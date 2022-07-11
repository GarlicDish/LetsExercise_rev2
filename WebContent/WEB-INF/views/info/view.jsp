<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp" %>

<style type="text/css">

div {
   border-collapse: 1px solid #ccc;
}

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
</style>

<div class="container">
<ul>
	<li><a href="/info/view">개인정보관리</a></li>
	<li><a href="/work/main">나의활동</a></li>
	<li><a href="/ask/list">문의내역</a></li>
</ul>

<form  action="/info/update" method="get">

<!-- <label for="profile">프로필사진</label>
<input type="image" id="profile" name="profile"><br> -->
<hr>
<table class="table table-striped table-hover table-condensed">
<tr>
	<td><label for="userid">아이디</label></td>
	<td><%=session.getAttribute("userID") %> </td>
<tr>


<tr>
	<td><label for="email">이메일</label></td>
	<td><%=session.getAttribute("email") %></td>
</tr>

<tr>
	<td><label for="usernick">닉네임</label></td>
	<td><%=session.getAttribute("nickname") %></td>
</tr>

</table>
<button onclick = "location.href='/info/update'" class="btn btn-outline-dark">수정</button>
<button onclick = "location.href='/info/delete'" class="btn btn-outline-dark">탈퇴</button>

</form>
</div>

<%@ include file="../main/layout/footer.jsp" %>