<%@ page language="java" import="controller.*, entity.*, interfaces.*,java.util.*"%>
<%
	AdminFuncController afc = (AdminFuncController)session.getAttribute("AdminController");
	String username = request.getParameter("Username");
	Account acc = afc.getAccount(username);
%>

<html>
<head>
<title>EditAccount</title>
</head>

<body>
<form method="post" action="EditAccount_action.jsp" name="EditAccount">
  <table style="text-align: left; width: 100%;" border="1" cellpadding="2" cellspacing="2">
    <tbody>
      <tr>
        <td style="vertical-align: top;">First Name<br>
        </td>
        <td style="vertical-align: top;"><input value="<%=acc.getFirstName()%>" name="FirstName"><br>
        </td>
      </tr>
      <tr>
        <td style="vertical-align: top;">Last Name<br>
        </td>
        <td style="vertical-align: top;"><input value="<%=acc.getLastName()%>" name="LastName">
        </td>
      </tr>
      <tr>
        <td style="vertical-align: top;">Username<br>
        </td>
        <td style="vertical-align: top;"><input value="<%=acc.getUsername()%>" readonly="readonly" name="Username">
        </td>
      </tr>
      <tr>
        <td style="vertical-align: top;">Password<br>
        </td>
        <td style="vertical-align: top;"><input value="<%=acc.getPassword()%>" name="Password">
        </td>
      </tr>
      <tr>
        <td style="vertical-align: top;">Type<br>
        </td>
      	<td style="vertical-align: top;">current type <input readonly="readonly" name="TypeRd" value=<%=acc.getType()%>> new type
        <select name="Type">
        <option value="u">User</option>
        <option value="a">Admin</option>
        </select>
        <br>
        </td>
      </tr>
      <tr>
        <td style="vertical-align: top;">Status<br>
        </td>
        <td style="vertical-align: top;">current status <input readonly="readonly" name="StatusRd" value=<%=acc.getActive()%>> new status
        <select name="Status">
        <option value="N">Inactive</option>
        <option value="Y">Active</option>
        </select>
        <br>
        </td>
      </tr>

    </tbody>
  </table>
  <input name="Edit User" value="Edit User" type="submit"> 
  <input name="Reset" value="Reset" type="reset"><br>
</form>


<br>



  

</body></html>