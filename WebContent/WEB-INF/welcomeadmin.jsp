<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <html>
 <h1>welcome admin</h1>
<h1>Message : ${message}</h1> 
 <h1>Admin : ${Admin}</h1>
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
 <!-- <body style="background:url(C://Users/Asus/workspace2/Spring_mvc-emailsending/WebContent/images/car123.jpg) no-repeat center center cover;"> --> 
<body>
<form action="admin123/managedata" method="post">
	<a href='<c:url value="/j_spring_security_logout"/>' > Logout</a>
	<table>
 	<tr>
			<td><input type="submit" name="action" value="show_user_list"/></td>
			<td><input type="submit" name="action" value="car_productimage_management"/></td>
			<td><input type="submit" name="action" value="add_new_product"/></td>
			<td><input type="submit" name="action" value="update_product"/></td>
		</tr>
		
 </table>
</form>
</body>
</html>