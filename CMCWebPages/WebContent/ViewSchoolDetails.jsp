<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<html>
<head>
</head>
<%
	UserFuncController ufc = (UserFuncController)session.getAttribute("UserController"); 
	String schoolName = request.getParameter("SchoolName");
	String state = ufc.getUniversity(schoolName).getState();
	String location = ufc.getUniversity(schoolName).getLocation();
	String control = ufc.getUniversity(schoolName).getControl();
	int students = ufc.getUniversity(schoolName).getStudents();
	int femPerc = ufc.getUniversity(schoolName).getFemPerc();
	int satV = ufc.getUniversity(schoolName).getSatV();
	int satM = ufc.getUniversity(schoolName).getSatM();
	int cost = ufc.getUniversity(schoolName).getCost();
	int finAidPerc = ufc.getUniversity(schoolName).getFinAidPerc();
	int applicants = ufc.getUniversity(schoolName).getApplicants();
	int admitted = ufc.getUniversity(schoolName).getAdmitted();
	int enrolled = ufc.getUniversity(schoolName).getEnrolled();
	int acadScale = ufc.getUniversity(schoolName).getAcadScale();
	int socScale = ufc.getUniversity(schoolName).getSocScale();
	int qualScale = ufc.getUniversity(schoolName).getQualScale();
%>

<body>
<table style="text-align: left; width: 100%;" border="1" cellpadding="2" cellspacing="2">

  <tbody>
    <tr>
      <td style="vertical-align: top;">SCHOOL<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=schoolName%>" readonly="readonly" name="School"><br>
      </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">STATE<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=state%>" readonly="readonly" name="State"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">LOCATION<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=location%>" readonly="readonly" name="Location"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">CONTROL<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=control%>" readonly="readonly" name="Control"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">NUMBER OF STUDENTS<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=students%>" name="Students" readonly="readonly"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">% FEMALE<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=femPerc%>" readonly="readonly" name="FemPerc"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">SAT VERBAL<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=satV%>" readonly="readonly" name="SatV"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">SAT MATH<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=satM%>" readonly="readonly" name="SatM"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">EXPENSES<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=cost%>" readonly="readonly" name="Cost"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">% FINANCIAL AID<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=finAidPerc%>" readonly="readonly" name="FinAidPerc"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">NUMBER OF APPLICANTS<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=applicants%>" readonly="readonly" name="Applicants"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">% ADMITTED<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=admitted%>" readonly="readonly" name="Admitted"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">% ENROLLED<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=enrolled%>" readonly="readonly" name="Enrolled"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;">ACADEMIC SCALE (1-5)<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=acadScale%>" readonly="readonly" name="AcadScale"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;">SOCIAL SCALE (1-5)<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=socScale%>" readonly="readonly" name="SocScale"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">QUALITY OF LIFE SCALE (1-5)<br>
      </td>
      <td style="vertical-align: top;"><input value="<%=qualScale%>" readonly="readonly" name="QualScale"> </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">EMPHASES<br>
      </td>
      <%ArrayList<String> emphases = ufc.getUniversity(schoolName).getEmphases();%>
      
      <% if(emphases.isEmpty()){ %>
      		<td style="vertical-align: top;">
      			<input value="none" readonly="readonly" name="Emphases"> <br>
      		</td>
      <%} 
      	else{%>
      		<td style="vertical-align: top;">
      <% 
      		for(int i=0; i<emphases.size();i++){ 
      			String emphasis = emphases.get(i);
      %>
      			
      				<input size="30" value="<%=emphasis%>" readonly="readonly" name="Emphases"> <br>
      			
      <%	} %>
      		</td>
      <%}%>
      	
      	
    </tr>
  </tbody>
</table>

<br>

<br>
</body></html>