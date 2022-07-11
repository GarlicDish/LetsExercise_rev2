<%@page import="dto.club.GuDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<GuDto> gu = (List) request.getAttribute("gu");
%>
<option style="text-align: center;">---선택---</option>
<% for (int i = 0; i < gu.size(); i++) { %>
<option value="<%=gu.get(i).getGunumber()%>"><%=gu.get(i).getGuname()%></option>
<% } %>
