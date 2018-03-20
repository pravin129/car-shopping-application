<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
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
<h1>Message : ${message1}</h1> 
 <h1>User : ${User}</h1>
<h1>welcome user</h1>
<a href='<c:url value="/j_spring_security_logout" />' > Logout</a>
<body>
<form action="/Spring_mvc-emailsending/company" method="post">
	<p>select which company car you want</p>
		<select name="car_company">
  			<option value="tata">TATA</option>
  			<option value="mahindra">Mahindra</option>
  			<option value="maruti_suzuki">Maruti_suzuki</option>
  			<option value="volvo">volvo</option>
		</select>
		<input type="submit" value="submit">
</form>
</body>
</html>