<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>createtable.html</title>
</head>

<body style="background-color:white;">

<%  String name = request.getParameter("nameField");  
out.println("Hello " + name + "." +" Here is your table!");
 %> <br> <%
String row = request.getParameter("rowField");
String column = request.getParameter("columnField");

int r=Integer.parseInt(row);
int c=Integer.parseInt(column);

//int rowIssue = "";
//int columnIssue= "";
String val = request.getParameter("nameField");

 if(r <=0){
	 //rowIssue = "rowIssue";
     //session.setAttribute("error1",rowIssue);
	 session.setAttribute("error1", "Hello" + name + "PLEASE PROVIDE A ROW VALUE >=1");
	 // session.setAttribute("karld", 1 );
	 response.sendRedirect("classexcercise.jsp");
	 return;
}
 else if ( c <=0) {
	 //columnIssue = "columnIssue";
	 //session.setAttribute("error2",columnIssue);
	 session.setAttribute("error2", "Hello" + name + "PLEASE PROVIDE A COL VALUE >=1");
	 //session.setAttribute("karld", null);
      response.sendRedirect("classexcercise.jsp");
     return;
 }

%>
<br>
<table style="text-align: left; width: 30%;" border="1" >

<% for(int i = 0; i <= r; i++){ %>
	 <tr >
	 
	<% for(int j = 0; j <= c; j++){    %>
	
	<td> <% out.println("( " + i + "," + j + ")"); %> </td>
			
	<% 	
	}
	 %>
	 </tr>	 
<%} 
%>
</table> 
</body>
</html>