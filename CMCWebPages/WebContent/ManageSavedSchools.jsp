<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
UserFuncController ufc = (UserFuncController)session.getAttribute("UserController"); 
GeneralUser gu = ufc.getCurrentGeneralUser();
ArrayList<String> allSavedSchoolNames = gu.getSavedSchools();

for(String s: allSavedSchoolNames){
	System.out.println(s);
}
%>

<html>
<head>
</head>

<body>

<table style="text-align: left; width: 100%;" border="1" cellpadding="2" cellspacing="2">

  <tbody>
    <tr align="center">
      <td colspan="3" rowspan="1" style="vertical-align: top; text-align: center;">Saved Schools<br>
      </td>
    </tr>
  
  	<tr>
  		<td style="vertical-align: top;">Remove
		</td>
		<td style="vertical-align: top; text-align: center;">University Name
		</td>
		<td style="vertical-align: top;">View
		</td>
  	</tr>
  
  	<% 
  	for(int i=0; i<allSavedSchoolNames.size();i++){
  		String schoolName = allSavedSchoolNames.get(i);
  	%>
    <tr>
      <td style="vertical-align: top; width: 150px;">
      <form method="post" action="RemoveSavedSchool_action.jsp" name="RemoveSavedSchool">
      		<input name="Remove" value="Remove" type="submit">
      		<input name="SchoolName" value="<%=schoolName%>" type="hidden"><br>
      </form>
      </td>
      
      <td style="vertical-align: top;"><%=schoolName%>
      </td>
      
      <td style="vertical-align: top; width: 150px;">
      <form method="post" action="ViewSchoolDetails.jsp" name="ViewSchoolDetails">
      		<input name="View" value="View" type="submit">
      		<input name="SchoolName" value="<%=schoolName%>" type="hidden"><br>
      </form>
      </td>
    </tr>
    <%}%>
  </tbody>
</table>

<form method="post" action="GeneralUserHomepage.jsp" name="GeneralUserHomepage">
      		<input name="Back" value="Back" type="submit">
</form>

</body>
</html>