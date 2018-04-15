<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
	AdminFuncController afc = (AdminFuncController)session.getAttribute("AdminController");
	ArrayList<String> emphases = new ArrayList<String>();
	if(request.getParameter("Emphasis1").length()!=0){
		emphases.add(request.getParameter("Emphasis1"));
	}
	if(request.getParameter("Emphasis2").length()!=0){
		emphases.add(request.getParameter("Emphasis2"));
	}
	if(request.getParameter("Emphasis3").length()!=0){
		emphases.add(request.getParameter("Emphasis3"));
	}
	if(request.getParameter("Emphasis4").length()!=0){
		emphases.add(request.getParameter("Emphasis4"));
	}
	if(request.getParameter("Emphasis5").length()!=0){
		emphases.add(request.getParameter("Emphasis5"));
	}
	afc.addUniversity(request.getParameter("SchoolName"), request.getParameter("State"), request.getParameter("Location"), 
			request.getParameter("Control"), Integer.parseInt(request.getParameter("Students")),
			Integer.parseInt(request.getParameter("FemPerc")), Integer.parseInt(request.getParameter("SatV")), 
			Integer.parseInt(request.getParameter("SatM")), Integer.parseInt(request.getParameter("Cost")), 
			Integer.parseInt(request.getParameter("FinAidPerc")), Integer.parseInt(request.getParameter("Applicants")), 
			Integer.parseInt(request.getParameter("Admitted")), Integer.parseInt(request.getParameter("Enrolled")),
			Integer.parseInt(request.getParameter("AcadScale")), Integer.parseInt(request.getParameter("SocScale")), 
			Integer.parseInt(request.getParameter("QualScale")), emphases);
	response.sendRedirect("AdminHomepage.jsp");
%>