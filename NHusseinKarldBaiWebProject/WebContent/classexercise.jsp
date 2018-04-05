<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>classexercise.html</title>
</head>
<body>

<%

if (session.getAttribute("error1") != null){
	out.println(session.getAttribute("error1"));  
}

else if(session.getAttribute("error2") != null) {
out.println(session.getAttribute("error2"));
}
 %>  
<form method="get" action="createtable.jsp" name="Submit">

<table style="text-align: left; width: 100%;" border="1" cellpadding="2"
cellspacing="2">
<tbody>
<tr>
<td style="vertical-align: top;">Enter your name<br>
</td>
<td style="vertical-align: top;"><input name="nameField"><br>
</td>
</tr>
<tr>
<td style="vertical-align: top;">Enter number of rows<br>
</td>
<td style="vertical-align: top;"><input name="rowField" value="1"><br>
</td>
</tr>
<tr>
<td style="vertical-align: top;">Enter number of columns<br>
</td>
<td style="vertical-align: top;"><input name="columnField"
value="1"><br>
</td>
</tr>

<tr>
<td  style="vertical-align: top;"> <input name="Submit"
type="submit"> <br>
<br>
</td>
<td style="vertical-align: top;"><input name="Reset" type="reset"></td>
</tr>
</tbody>
</table>
 
</form>
<br>
<br>
</body>
</html>