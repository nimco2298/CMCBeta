<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
	AdminFuncController afc = (AdminFuncController)session.getAttribute("AdminController");
	String username = request.getParameter("Username");
	boolean check = afc.deactivate(username);
	response.sendRedirect("AdminHomepage.jsp");
%>