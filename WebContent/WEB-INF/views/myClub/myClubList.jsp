<%@page import="dto.club.User"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../main/layout/header.jsp"%>
<%@ page import="dto.club.ClubDto"%>
<%@ page import="dto.club.ExerciseDto"%>
<%@ page import="java.util.List"%>
<%	List<Map<String, Object>> mcList = (List) request.getAttribute("mcList"); %>

<script type="text/javascript">
$(document).ready(function() {
	//동호회 생성
	$("#btnCreate").click(function() {
		$(location).attr("href","<%=request.getContextPath()%>/myClub/create");
	})
	//동호회 메인으로
	$("#btnToMain").click(function() {
		$(location).attr("href","<%=request.getContextPath()%>/club/main");
	}) 
	});
</script>
<div class="container">
	<h1>동호회 찾기</h1>
	<hr>

	<table class="table table-striped table-hover table-condensed">
		<tr class="info">
			<th><p class="text-center" >동호회장 명</p></th>
			<th><p class="text-center" >동호회 이름</p></th>
			<th><p class="text-center" >동호회 지역</p></th>
			<th><p class="text-center" >운동 종목</p></th>
			<th><p class="text-center" >생성일</p></th>
			<th><p class="text-center" >회원 수</p></th>
			<th><p class="text-center" >가입일</p></th>
			<th></th>
		</tr>
		<%
			if (mcList != null && mcList.size() != 0) {
			for (int i = 0; i < mcList.size(); i++) {
		%>
		<tr>

			<td><p class="text-center" ><%=((User) mcList.get(i).get("user")).getUsername()%></p></td>
			<td><p class="text-center" ><a
				href="<%=request.getContextPath()%>/club/sns/list?clubnumber=<%=((ClubDto)mcList.get(i).get("club")).getClubNumber()%>">
					<%=((ClubDto)mcList.get(i).get("club")).getClubName()%>
				</a></p>
			</td>
			<td><p class="text-center" ><%=((String)mcList.get(i).get("area"))%></p></td>
			<td><p class="text-center" ><%=((ExerciseDto)mcList.get(i).get("exercise")).getExercisename()%></p></td>
			<td><p class="text-center" ><%=((ClubDto)mcList.get(i).get("club")).getCreationDate()%></p></td>
			<td><p class="text-center" ><%=(int)mcList.get(i).get("memberCNT")%></p></td>
			<td><p class="text-center" ><%=(mcList.get(i).get("memberdate"))%></p></td>
			<td>
			<% if((int)(((ClubDto)mcList.get(i).get("club")).getClubChiefNumber()) != (int)(request.getSession().getAttribute("userno")) )  { %>
			<a href="/myClub/withdraw?clubnumber=<%=((ClubDto)mcList.get(i).get("club")).getClubNumber()%>"><button class="btn btn-outline-dark">탈퇴</button></a>
			<% } %>
			
			<% if((int)(((ClubDto)mcList.get(i).get("club")).getClubChiefNumber()) == (int)(request.getSession().getAttribute("userno")) )  { %>
					<a href="/myClub/manage?clubnumber=<%=((ClubDto)mcList.get(i).get("club")).getClubNumber()%>">
						<button class="btn btn-outline-dark">동호회 관리</button>
					</a>
					<a href="/myClub/deactivate?clubnumber=<%=((ClubDto)mcList.get(i).get("club")).getClubNumber()%>">
						<button class="btn btn-outline-dark">동호회 삭제</button>
					</a>
			<% } %>
			</td>			
		</tr>
		<%
			}
		} else {
		%>
		<tr>
			<td><h1>리스트가 없습니다.</h1></td>
		</tr>
		<%
			}
		%>
	</table>
	
	<div class="text-center" >
		<button id="btnCreate" class="btn btn-default">새 동호회 생성</button>
	</div>
</div>
<%-- container 끝 --%>
<%@ include file="../main/layout/pagingClub.jsp"%>
<%@ include file="../main/layout/footer.jsp"%>