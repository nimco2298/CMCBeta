<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>

<html>
<head>
  <title>SearchResults</title>
</head>
<%
	//set up the controllers
	UserFuncController ufc = (UserFuncController)session.getAttribute("UserController"); 
	SearchController sc = new SearchController();
	
	//check for errors
	String schoolName = request.getParameter("SchoolName");
	String state = request.getParameter("State");
	String location = request.getParameter("Location");
	String control = request.getParameter("Control");
	if(request.getParameter("Students1").length()==0 || request.getParameter("Students2").length()==0 ||
	   request.getParameter("FemPerc1").length()==0 || request.getParameter("FemPerc2").length()==0 ||
	   request.getParameter("SatV1").length()==0 || request.getParameter("SatV2").length()==0 ||
	   request.getParameter("SatM1").length()==0 || request.getParameter("SatM2").length()==0 ||
	   request.getParameter("Cost1").length()==0 || request.getParameter("Cost2").length()==0 ||
	   request.getParameter("FinAidPerc1").length()==0 || request.getParameter("FinAidPerc2").length()==0 ||
	   request.getParameter("Applicants1").length()==0 || request.getParameter("Applicants2").length()==0 ||
	   request.getParameter("Admitted1").length()==0 || request.getParameter("Admitted2").length()==0 ||
	   request.getParameter("Enrolled1").length()==0 || request.getParameter("Enrolled2").length()==0 ||
	   request.getParameter("AcadScale1").length()==0 || request.getParameter("AcadScale2").length()==0 ||
	   request.getParameter("SocScale1").length()==0 || request.getParameter("SocScale2").length()==0 ||
	   request.getParameter("QualScale1").length()==0 || request.getParameter("QualScale2").length()==0){
			response.sendRedirect("SearchMenu.jsp?Error=-1");
			return;
	}
	//set the inputs from the user
	int students1 = Integer.parseInt(request.getParameter("Students1"));
	int students2 = Integer.parseInt(request.getParameter("Students2"));
	int femPerc1 = Integer.parseInt(request.getParameter("FemPerc1"));
	int femPerc2 = Integer.parseInt(request.getParameter("FemPerc2"));
	int satV1 = Integer.parseInt(request.getParameter("SatV1"));
	int satV2 = Integer.parseInt(request.getParameter("SatV2"));
	int satM1 = Integer.parseInt(request.getParameter("SatM1"));
	int satM2 = Integer.parseInt(request.getParameter("SatM2"));
	int cost1 = Integer.parseInt(request.getParameter("Cost1"));
	int cost2 = Integer.parseInt(request.getParameter("Cost2"));
	int finAidPerc1 = Integer.parseInt(request.getParameter("FinAidPerc1"));
	int finAidPerc2 = Integer.parseInt(request.getParameter("FinAidPerc2"));
	int applicants1 = Integer.parseInt(request.getParameter("Applicants1"));
	int applicants2 = Integer.parseInt(request.getParameter("Applicants2"));
	int admitted1 = Integer.parseInt(request.getParameter("Admitted1"));
	int admitted2 = Integer.parseInt(request.getParameter("Admitted2"));
	int enrolled1 = Integer.parseInt(request.getParameter("Enrolled1"));
	int enrolled2 = Integer.parseInt(request.getParameter("Enrolled2"));
	int acadScale1 = Integer.parseInt(request.getParameter("AcadScale1"));
	int acadScale2 = Integer.parseInt(request.getParameter("AcadScale2"));
	int socScale1 = Integer.parseInt(request.getParameter("SocScale1"));
	int socScale2 = Integer.parseInt(request.getParameter("SocScale2"));
	int qualScale1 = Integer.parseInt(request.getParameter("QualScale1"));
	int qualScale2 = Integer.parseInt(request.getParameter("QualScale2"));
	
	//get the emphases set
	ArrayList<String> emphases = new ArrayList<String>();
	if(request.getParameter("Emphases1").length()!=0){
		emphases.add(request.getParameter("Emphases1"));
	}
	if(request.getParameter("Emphases2").length()!=0){
		emphases.add(request.getParameter("Emphases2"));
	}
	if(request.getParameter("Emphases3").length()!=0){
		emphases.add(request.getParameter("Emphases3"));
	}
	if(request.getParameter("Emphases4").length()!=0){
		emphases.add(request.getParameter("Emphases4"));
	}
	if(request.getParameter("Emphases5").length()!=0){
		emphases.add(request.getParameter("Emphases5"));
	}
	
	//search based on inputs
	ArrayList<University> searchedUniversities = sc.search(schoolName, state, location, control, 
			students1, students2, 
			femPerc1, femPerc2, satV1, satV2, satM1, satM2, cost1, cost2, finAidPerc1, finAidPerc2, 
			applicants1, applicants2, admitted1, admitted2, enrolled1, enrolled2, acadScale1, acadScale2, 
			socScale1, socScale2, qualScale1, qualScale2, emphases);
%>
<body>
<table style="text-align: left; width: 100%;" border="1" cellpadding="2" cellspacing="2">

  <tbody>
    <tr>
      <td style="vertical-align: top; text-align: center;">School<br>
      </td>
    </tr>
  </tbody>
</table>

<table style="text-align: left; width: 100%;" border="1" cellpadding="2" cellspacing="2">

  <tbody>
  
  <%
  	for(int i=0; i<searchedUniversities.size(); i++){ 
  		String univName = searchedUniversities.get(i).getName();
  %>
    <tr>
      <td style="vertical-align: top; width: 50px;">
      <form method="post" action="SaveSchool_action.jsp" name="SaveSchool">
      		<input name="Save" value="Save" type="submit">
      		<input name="SchoolName" value="<%=univName%>" type="hidden"><br>
      </form>
      </td>
      
      <td style="vertical-align: top; width: 200px;"><%=univName%><br>
      </td>
      
      <td style="vertical-align: top; width: 50px;">
      <form method="post" action="ViewRecSchools.jsp" name="ViewRecSchools">
      		<input name="View" value="View" type="submit">
      		<input name="SchoolName" value="<%=univName%>" type="hidden"><br>
      </form>
	</td>
    </tr>
  <%} %>
  
  </tbody>
  
</table>

<br>

<br>
</body></html>