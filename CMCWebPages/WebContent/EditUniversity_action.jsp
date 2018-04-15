<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
	AdminFuncController afc = (AdminFuncController)session.getAttribute("AdminController");
	String schoolName = request.getParameter("SchoolName");
	University univ = afc.getUniversity(schoolName);
	ArrayList<String> emphases = univ.getEmphases();
	afc.editUniversity(schoolName, request.getParameter("State"), request.getParameter("Location"), 
			request.getParameter("Control"), Integer.parseInt(request.getParameter("Students")),
			Integer.parseInt(request.getParameter("FemPerc")), Integer.parseInt(request.getParameter("SatV")), 
			Integer.parseInt(request.getParameter("SatM")), Integer.parseInt(request.getParameter("Cost")), 
			Integer.parseInt(request.getParameter("FinAidPerc")), Integer.parseInt(request.getParameter("Applicants")), 
			Integer.parseInt(request.getParameter("Admitted")), Integer.parseInt(request.getParameter("Enrolled")),
			Integer.parseInt(request.getParameter("AcadScale")), Integer.parseInt(request.getParameter("SocScale")), 
			Integer.parseInt(request.getParameter("QualScale")), emphases);
	response.sendRedirect("AdminHomepage.jsp");
%>