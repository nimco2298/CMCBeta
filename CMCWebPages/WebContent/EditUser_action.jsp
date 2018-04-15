<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
	UserFuncController ufc = (UserFuncController)session.getAttribute("UserController"); 
	GeneralUser gu = ufc.getCurrentGeneralUser();
	ufc.editProfile(gu,request.getParameter("FirstName"),request.getParameter("LastName"),request.getParameter("Password"));
	response.sendRedirect("EditUser.jsp");
%>