<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
	AdminFuncController afc = (AdminFuncController)session.getAttribute("AdminController");
	ArrayList<Account> allAccounts = afc.getAllAccounts();
	
%>
<html>

<head>
<title>ManageUsers</title>
</head>

<body>
	<table style="text-align: left; width: 100%;" border="1" cellpadding="2" cellspacing="2">
		<tbody>
			<tr>
				<td style="vertical-align: top;" colspan="8">
				<div style="text-align: center;">
					<a href="AddAccount.jsp" target="_self">Add New User</a><br>
				</div>
				</td>
			</tr>
    		<tr>
      			<td style="vertical-align: top; width: 100px;"><br>
      			</td>
      			<td style="vertical-align: top;">First<br>
      			</td>
      			<td style="vertical-align: top;">Last<br>
      			</td>
      			<td style="vertical-align: top;">Username<br>
      			</td>
      			<td style="vertical-align: top;">Password<br>
      			</td>
      			<td style="vertical-align: top; width: 50px;">Type<br>
      			</td>
      			<td style="vertical-align: top; width: 50px;">Status<br>
      			</td>
      			<td style="vertical-align: top; width: 50px;"><br>
				</td>
			</tr>
			<%
			for(Account acc: allAccounts) {
				String firstName = acc.getFirstName();
				String lastName = acc.getLastName();
				String username = acc.getUsername();
				String password = acc.getPassword();
				char type = acc.getType();
				char status = acc.getActive();
			%>
    		<tr>
      			<td style="vertical-align: top;">
      			<form method="post" action="Deactivate_action.jsp" name="Deactivate">
      				<input name="Deactivate" value="Deactivate" type="submit">
      				<input value="<%=username%>" name="Username" type="hidden"><br>
      			</form>
      			</td>
      			<td style="vertical-align: top;"><%=firstName%><br>
      			</td>
      			<td style="vertical-align: top;"><%=lastName%><br>
      			</td>
      			<td style="vertical-align: top;"><%=username%><br>
      			</td>
      			<td style="vertical-align: top;"><%=password%><br>
      			</td>
      			<td style="vertical-align: top;"><%=type%><br>
      			</td>
      			<td style="vertical-align: top;"><%=status%><br>
      			</td>
      			<td style="vertical-align: top;">
      			<form method="post" action="EditAccount.jsp" name="EditAccount">
      				<input name="Edit" value="Edit" type="submit">
      				<input value="<%=username%>" name="Username" type="hidden"><br>
      			</form>
				</td>  
    		</tr>
    		<%} %>
  		</tbody>
	</table>
</body>
</html>