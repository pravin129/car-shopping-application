<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> </title>
<style type="text/css">
#percentageSize {
  width: 50%;
  height: 50%;
  background: red;
}

</style>
</head>
<body>
<!-- <div style="height: 1px;width:1px; text-align: center;"> -->
<h1>VISTA BASIC MODEL </h1>
<h1>${car_product.image_name}</h1>
<%-- <img id="percentageSize" src="${pageContext.request.contextPath}/images/indica_vista-1.png" /> --%>
	<img src="data:image/jpg;base64, <c:out value='${car_product.encodedImage}'/>"
        			width="214" height="138" />
<!-- </div> -->
</body>

<form action="/Spring_mvc-emailsending/cart" method="get">

	
	<input type="submit" name="cart" value="Add to cart">
</form>
</html>