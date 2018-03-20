<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<h1>Welcome to user registration form</h1>
<head>
<style type="text/css">
body
 {
     background-image: url(https://static.pexels.com/photos/89784/bmw-suv-all-terrain-vehicle-fog-89784.jpeg);
     background-repeat: no-repeat;
     background-size:cover;
 }
 </style>
 </head>
<center>
<body>
<form action="registration" method="post">

	<table>
	<tr>
			<td>USER_ID  :</td><td><input type="text" name="user_id"/></td>
		</tr>
		<tr>
			<td>USERNAME  :</td><td><input type="text" name="username"/></td>
		</tr>
		<tr>
			<td>Password  :</td><td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td>ACTIVE :</td><td><input type="text" name="active"/></td>
		</tr>
		<tr>
			<td>AUTHORITY :</td><td><input type="text" name="authority"/></td>
		</tr>
		<tr>
			<td>EMAIL :</td><td><input type="text" name="email"/></td>
		</tr>
		
		<tr>
			<td><input type="submit" value="register"/></td>
		</tr>
		<tr>
			<td><input type="RESET" value="reset"/></td>
		</tr>
		
	</table>
	

</form>

</body>
</center>
</html>