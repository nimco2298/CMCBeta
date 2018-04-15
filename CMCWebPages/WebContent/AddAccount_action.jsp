<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
	AdminFuncController afc = (AdminFuncController)session.getAttribute("AdminController");
	String username = request.getParameter("Username");	
	Account acc = afc.getAccount(username);
	afc.addAccount(request.getParameter("Username"), request.getParameter("Password"), 
			request.getParameter("FirstName"), request.getParameter("LastName"), request.getParameter("Type"));
	response.sendRedirect("AdminHomepage.jsp");
%>