<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
	AdminFuncController afc = (AdminFuncController)session.getAttribute("AdminController");
	String schoolName = request.getParameter("SchoolName");
	University univ = afc.getUniversity(schoolName);
	ArrayList<String> emphases = univ.getEmphases();
%>
<html>
<head>
<title>EditUniversity</title>
</head>

<body>
<form method="post" action="EditUniversity_action.jsp" name="EditUniversity">
<table style="text-align: left; width: 100%;" border="1" cellpadding="2" cellspacing="2">
  <tbody>
    <tr>
      <td style="vertical-align: top;">SCHOOL<br>
      </td>
      <td style="vertical-align: top;"><input readonly="readonly" name="SchoolName" value="<%=schoolName%>"><br>
      </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">STATE<br>
      </td>
      <td style="vertical-align: top;"><input name="State" value="<%=univ.getState()%>"><br>
      </td>
    </tr>
    <tr>
      <td style="vertical-align: top;">LOCATION<br>
      </td>
      <td style="vertical-align: top;">current location <input readonly="readonly" name="LocationRd" value="<%=univ.getLocation()%>"> new location 
        <select name="Location">
        <option value="SUBURBAN">SUBURBAN</option>
        <option value="URBAN">URBAN</option>
        <option value="SMALL-CITY">SMALL-CITY</option>
        <option value="-1">UNKNOWN</option>
        </select>
        <br>
</td>
    </tr>
    <tr>
      <td style="vertical-align: top;">CONTROL<br>
      </td>
      <td style="vertical-align: top;">current control <input readonly="readonly" name="ControlRd" value=<%=univ.getControl()%>> new control 
        <select name="Control">
        <option value="PRIVATE">PRIVATE</option>
        <option value="STATE">STATE</option>
        <option value="CITY">CITY</option>
        <option value="-1">UNKNOWN</option>
        </select>
        <br>
</td>
    </tr>
    <tr>
      <td style="vertical-align: top;">NUMBER OF STUDENTS<br>
      </td>
      <td style="vertical-align: top;"><input name="Students" value="<%=univ.getStudents()%>"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;">% FEMALE<br>
      </td>
      <td style="vertical-align: top;"><input name="FemPerc" value="<%=univ.getFemPerc()%>"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;">SAT VERBAL<br>
      </td>
      <td style="vertical-align: top;"><input name="SatV" value="<%=univ.getSatV()%>"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;">SAT MATH<br>
      </td>
      <td style="vertical-align: top;"><input name="SatM" value="<%=univ.getSatM()%>"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;">EXPENSES<br>
      </td>
      <td style="vertical-align: top;"><input name="Cost" value="<%=univ.getCost()%>"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;">% FINANCIAL AID<br>
      </td>
      <td style="vertical-align: top;"><input name="FinAidPerc" value="<%=univ.getFinAidPerc()%>"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;">NUMBER OF APPLICANTS<br>
      </td>
      <td style="vertical-align: top;"><input name="Applicants" value="<%=univ.getApplicants()%>"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;">% ADMITTED<br>
      </td>
      <td style="vertical-align: top;"><input name="Admitted" value="<%=univ.getAdmitted()%>"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;">% ENROLLED<br>
      </td>
      <td style="vertical-align: top;"><input name="Enrolled" value="<%=univ.getEnrolled()%>"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;">ACADEMICS SCALE (1-5)<br>
      </td>
      <td style="vertical-align: top;">current scale <input readonly="readonly" name="AcadScaleRd" value="<%=univ.getAcadScale()%>"> new scale 
        <select name="AcadScale">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        </select>
        <br>
</td>
    </tr>
    <tr>
      <td style="vertical-align: top;">SOCIAL SCALE (1-5)</td>
      <td style="vertical-align: top;">current scale <input readonly="readonly" name="SocScaleRd" value="<%=univ.getSocScale()%>"> new scale 
        <select name="SocScale">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        </select>
        <br>
</td>
    </tr>
    <tr>
      <td style="vertical-align: top;">QUALITY OF LIFE SCALE (1-5)</td>
      <td style="vertical-align: top;">current scale <input readonly="readonly" name="QualScaleRd" value="<%=univ.getQualScale()%>"> new scale 
        <select name="QualScale">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        </select>
        <br>
</td>
    </tr>
    <tr>
      <td style="vertical-align: top;">EMPHASES<br>
      </td>
      <td style="vertical-align: top;">
      <%if(emphases.size()==1){ %>
      <input name="Emphasis1" value="<%=emphases.get(0)%>"><br><%}%>
      <%if(emphases.size()==2){ %>
      <input name="Emphasis2" value="<%=emphases.get(1)%>"><br><%}%>
      <%if(emphases.size()==3){ %>
      <input name="Emphasis3" value="<%=emphases.get(2)%>"><br><%}%>
      <%if(emphases.size()==4){ %>
      <input name="Emphasis4" value="<%=emphases.get(3)%>"><br><%}%>
      <%if(emphases.size()==5){ %>
      <input name="Emphasis5" value="<%=emphases.get(4)%>"><br><%}%>
      </td>
    </tr>
  </tbody>
</table>
<input name="Apply Changes" value="Apply Changes" type="submit"> 
<input name="Cancel Changes" value="Cancel Changes" type="reset"><br>
</form>

</body></html>