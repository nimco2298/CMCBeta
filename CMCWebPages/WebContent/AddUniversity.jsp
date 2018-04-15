<html>

<head>
<title>AddUniversity</title>
</head>

<body>
<form method="post" action="AddUniversity_action.jsp" name="AddUniversity">
	<table style="text-align: left; width: 100%;" border="1" cellpadding="2" cellspacing="2">
		<tbody>
	    	<tr>
	      		<td style="vertical-align: top;">SCHOOL<br>
	      		</td>
	      		<td style="vertical-align: top;"><input name="SchoolName"><br>
	      		</td>
	    	</tr>
    		<tr>
      			<td style="vertical-align: top;">STATE<br>
      			</td>
      			<td style="vertical-align: top;"><input name="State"><br>
      			</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">LOCATION</td>
        		<td style="vertical-align: top;">
        			<select name="Location">
        				<option value="SUBURBAN">SUBURBAN</option>
        				<option value="URBAN">URBAN</option>
        				<option value="SMALL-CITY">SMALL-CITY</option>
        				<option value="-1">UNKNOWN</option>
        			</select><br>
        		</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">CONTROL</td>
        		<td style="vertical-align: top;">
        			<select name="Control">
        				<option value="PRIVATE">PRIVATE</option>
        				<option value="STATE">STATE</option>
        				<option value="CITY">CITY</option>
        				<option value="-1">UNKNOWN</option>
        			</select><br>
        		</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">NUMBER OF STUDENTS<br>
      			</td>
      			<td style="vertical-align: top;"><input name="Students">
      			</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">% FEMALE<br>
      			</td>
      			<td style="vertical-align: top;"><input name="FemPerc">
      			</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">SAT VERBAL<br>
      			</td>
      			<td style="vertical-align: top;"><input name="SatV">
      			</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">SAT MATH<br>
      			</td>
      			<td style="vertical-align: top;"><input name="SatM">
      			</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">EXPENSES<br>
      			</td>
      			<td style="vertical-align: top;"><input name="Cost">
      			</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">% FINANCIAL AID<br>
      			</td>
      			<td style="vertical-align: top;"><input name="FinAidPerc">
      			</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">NUMBER OF APPLICANTS<br>
      			</td>
      			<td style="vertical-align: top;"><input name="Applicants">
      			</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">% ADMITTED<br>
      			</td>
      			<td style="vertical-align: top;"><input name="Admitted">
      			</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">% ENROLLED<br>
      			</td>
      			<td style="vertical-align: top;"><input name="Enrolled">
      			</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">ACADEMICS SCALE (1-5)</td>
        		<td style="vertical-align: top;">
        			<select name="AcadScale">
        				<option value="1">1</option>
        				<option value="2">2</option>
        				<option value="3">3</option>
        				<option value="4">4</option>
        				<option value="5">5</option>
        			</select><br>
        		</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">SOCIAL SCALE (1-5)</td>
      			<td style="vertical-align: top;">
        			<select name="SocScale">
        				<option value="1">1</option>
        				<option value="2">2</option>
        				<option value="3">3</option>
        				<option value="4">4</option>
        				<option value="5">5</option>
        			</select><br>
        		</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">QUALITY OF LIFE SCALE (1-5)</td>
      			<td style="vertical-align: top;">
        			<select name="QualScale">
        				<option value="1">1</option>
        				<option value="2">2</option>
        				<option value="3">3</option>
        				<option value="4">4</option>
        				<option value="5">5</option>
        			</select><br>
        		</td>
    		</tr>
    		<tr>
      			<td style="vertical-align: top;">EMPHASES<br>
      			</td>
      			<td style="vertical-align: top;">
      				<input name="Emphasis1"><br>
      				<input name="Emphasis2"><br>
      				<input name="Emphasis3"><br>
      				<input name="Emphasis4"><br>
      				<input name="Emphasis5"><br>
      			</td>
    		</tr>
  		</tbody>
	</table>
	<input name="Add School" value="Add School" type="submit"> 
	<input name="Cancel Changes" value="Cancel Changes" type="reset"><br>
</form>
</body>
</html>