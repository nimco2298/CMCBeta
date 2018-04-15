<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
	AdminFuncController afc = (AdminFuncController)session.getAttribute("AdminController");
	ArrayList<University> allUnivs = afc.getAllUniversities();
%>
<html>
<head>
  <title>ManageUniversities</title>  
</head>

<body>
<table style="text-align: left; width: 100%;" border="1" cellpadding="2" cellspacing="2">

  <tbody>
    <tr>
      <td style="vertical-align: top;" colspan="17">
      	<div style="text-align: center;">
      		<a href="AddUniversity.jsp" target="_self">Add a New University</a>
      	</div>
      </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">School<br>
      </td>
      <td style="vertical-align: top;">State<br>
      </td>
      <td style="vertical-align: top;">Location<br>
      </td>
      <td style="vertical-align: top;">Control<br>
      </td>
      <td style="vertical-align: top;"># of Students<br>
      </td>
      <td style="vertical-align: top;">% Females<br>
      </td>
      <td style="vertical-align: top;">SAT Verbal<br>
      </td>
      <td style="vertical-align: top;">SAT Math
      </td>
      <td style="vertical-align: top;">Expenses<br>
      </td>
      <td style="vertical-align: top;">% with Financial Aid<br>
      </td>
      <td style="vertical-align: top;"># of Applicants<br>
      </td>
      <td style="vertical-align: top;">% Admitted<br>
      </td>
      <td style="vertical-align: top;">% Enrolled<br>
      </td>
      <td style="vertical-align: top;">Academics Scale (1-5)<br>
      </td>
      <td style="vertical-align: top;">Social&nbsp; Scale (1-5)<br>
      </td>
      <td style="vertical-align: top;">Quality of Life Scale (1-5)<br>
      </td>
      <td style="vertical-align: top;"><br>
      </td>
    </tr>
    <%
    for(University univ: allUnivs) {
    	String schoolName = univ.getName();
    	String state = univ.getState();
    	String location = univ.getLocation();
    	String control = univ.getControl();
    	int students = univ.getStudents();
    	int femPerc = univ.getFemPerc();
    	int satV = univ.getSatV();
    	int satM = univ.getSatM();
    	int cost = univ.getCost();
    	int finAidPerc = univ.getFinAidPerc();
    	int applicants = univ.getApplicants();
    	int admitted = univ.getAdmitted();
    	int enrolled = univ.getEnrolled();
    	int acadScale = univ.getAcadScale();
    	int socScale = univ.getSocScale();
    	int qualScale = univ.getQualScale();
    %>
    	<tr>
      		<td style="vertical-align: top;"><%=schoolName%><br>
      		</td>
      		<td style="vertical-align: top;"><%=state%><br>
      		</td>
      		<td style="vertical-align: top;"><%=location%><br>
      		</td>
      		<td style="vertical-align: top;"><%=control%><br>
      		</td>
      		<td style="vertical-align: top;"><%=students%><br>
      		</td>
      		<td style="vertical-align: top;"><%=femPerc%><br>
      		</td>
      		<td style="vertical-align: top;"><%=satV%><br>
      		</td>
      		<td style="vertical-align: top;"><%=satM%><br>
      		</td>
      		<td style="vertical-align: top;"><%=cost%><br>
      		</td>
      		<td style="vertical-align: top;"><%=finAidPerc%><br>
      		</td>
      		<td style="vertical-align: top;"><%=applicants%><br>
      		</td>
      		<td style="vertical-align: top;"><%=admitted%><br>
      		</td>
      		<td style="vertical-align: top;"><%=enrolled%><br>
      		</td>
      		<td style="vertical-align: top;"><%=acadScale%><br>
      		</td>
      		<td style="vertical-align: top;"><%=socScale%><br>
      		</td>
      		<td style="vertical-align: top;"><%=qualScale%><br>
      		</td>
      		<td style="vertical-align: top;">
      		<form method="post" action="EditUniversity.jsp" name="EditUniversity">
      			<input value="Edit" name="Edit" type="submit"><br>
      			<input value="<%=schoolName%>" name="SchoolName" type="hidden"><br>
      		</form>
	  		</td>
    	</tr>
    <%} %>
  </tbody>
</table>

<br>

<br>

</body></html>