<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
	AdminFuncController afc = (AdminFuncController)session.getAttribute("AdminController");
	String username = request.getParameter("Username");
	Account acc = afc.getAccount(username);
	afc.editUser(acc, request.getParameter("FirstName"), request.getParameter("LastName"), 
			request.getParameter("Password"), request.getParameter("Status").charAt(0), request.getParameter("Type").charAt(0));
	response.sendRedirect("AdminHomepage.jsp");
%>