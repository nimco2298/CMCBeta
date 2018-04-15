<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>

<html>
<head>
  <title>ViewRecSchools</title>
</head>
<%
	UserFuncController recUFC = (UserFuncController)session.getAttribute("UserController");
	SearchController sc = new SearchController();
	String recSchoolName = request.getParameter("SchoolName");
	ArrayList<University> recSchools = sc.recSearch(recUFC.getUniversity(recSchoolName));
%>

<body>

<form method="post" action="SaveSchool_action.jsp" name="SaveSchool">
	<input name="Save" value="Save" type="submit"> 
	<input name="SchoolName" value="<%=recSchoolName%>" type="hidden"> <br>
	<%@include file="ViewSchoolDetails.jsp"%>
</form>

MAY WE ALSO RECOMMEND<br>

<%for(int i=0; i<recSchools.size(); i++){ 
	String univName = recSchools.get(i).getName();
	String stateRec = ufc.getUniversity(univName).getState();
	String locationRec = ufc.getUniversity(univName).getLocation();
	String controlRec = ufc.getUniversity(univName).getControl();
	int studentsRec = ufc.getUniversity(univName).getStudents();
%>
<form method="post" action="SaveSchool_action.jsp" name="SaveSchool"> 
	<input name="Save" value="Save" type="submit"> 
	<input name="SchoolName" value="<%=univName%>" type="hidden"> <br>
	
	<table style="text-align: left; width: 100%;" border="1" cellpadding="2" cellspacing="2">

  		<tbody>
    		<tr>
      			<td style="vertical-align: top;">SCHOOL<br>
      			</td>
      			<td style="vertical-align: top;"><input value="<%=univName%>" readonly="readonly" name="School"><br>
      			</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">STATE<br>
      			</td>
      			<td style="vertical-align: top;"><input value="<%=stateRec%>" readonly="readonly" name="State"> </td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">LOCATION<br>
      			</td>
     			 <td style="vertical-align: top;"><input value="<%=locationRec%>" readonly="readonly" name="Location"> </td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">CONTROL<br>
      			</td>
      			<td style="vertical-align: top;"><input value="<%=controlRec%>" readonly="readonly" name="Control"> </td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">NUMBER OF STUDENTS<br>
      			</td>
      			<td style="vertical-align: top;"><input value="<%=studentsRec%>" name="Students" readonly="readonly"> </td>
    		</tr>
    	</tbody>
	</table>
</form>

<%} %>
<br>

</body></html>