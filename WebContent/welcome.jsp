<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to the car world </title>
<style type="text/css">
#percentageSize {
  width: 70%;
  height: 70%;
  background: red;
}
</style>
</head>
<body>
<h1>WELCOME TO THE CAR WORLD </h1>
<img id="percentageSize" src="${pageContext.request.contextPath}/images/car.jpg" />  
 <!-- </div> -->
</body>
<form action="/Spring_mvc-emailsending/frontpage">
	<input type="submit" name="action" value="register"/>
    <input type="submit" name="action" value="login" />
</form>
</html>