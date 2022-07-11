<%@page import="dto.club.User"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>
<%@ page import="dto.club.ClubDto"%>
<%@ page import="dto.club.ExerciseDto"%>
<%@ page import="java.util.List"%>
<% List<Map<String, Object>> list = (List) request.getAttribute("list"); %>

<script type="text/javascript">
</script>


<div class="container">

	<h1>동호회 찾기</h1>
	<hr>

	<div>
		<table class="table table-striped table-hover table-condensed">
			<tr class="info">
				<th><p class="text-center">동호회장 명</p></th>
				<th><p class="text-center">동호회 이름</p></th>
				<th><p class="text-center">동호회 지역</p></th>
				<th><p class="text-center">운동 종목</p></th>
				<th><p class="text-center">소개글</p></th>
				<th><p class="text-center">생성일</p></th>
				<th><p class="text-center">회원 수</p></th>
				<th><p class="text-center">가입</p></th>
			</tr>
			<%	for (int i = 0; i < list.size(); i++) { %>
			<tr>
				<td><p class="text-center"><%=((User) list.get(i).get("user")).getUsername()%></p></td>
				<td><p class="text-center"><%=((ClubDto)list.get(i).get("club")).getClubName()%></p></td>
				<td><p class="text-center"><%=((String)list.get(i).get("area"))%></p></td>
				<td><p class="text-center"><%=((ExerciseDto)list.get(i).get("exercise")).getExercisename()%></p></td>
				<td><p class="text-center"><%=((ClubDto)list.get(i).get("club")).getIntroduction()%></p></td>
				<td><p class="text-center"><%=((ClubDto)list.get(i).get("club")).getCreationDate()%></p></td>
				<td><p class="text-center"><%=(int)list.get(i).get("memberCNT")%></p></td>
				<td><p class="text-center"><a href="/club/join?clubnumber=<%=((ClubDto)list.get(i).get("club")).getClubNumber()%>"><button class="btn btn-outline-dark">가입하기</button></a></p></td>
			</tr>
			<%	} %>
		</table>
	</div>
</div> <%-- container 끝 --%>
<%@ include file="../main/layout/pagingClub.jsp"%>
<%@ include file="../main/layout/footer.jsp"%>