<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
	String schoolName = request.getParameter("SchoolName");
	UserFuncController ufc = (UserFuncController)session.getAttribute("UserController");
	ufc.saveToSavedSchoolList(ufc.getUniversity(schoolName));
	response.sendRedirect("ManageSavedSchools.jsp");
%>