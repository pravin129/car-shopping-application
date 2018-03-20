<%@page import="java.net.URL"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
<style type="text/css">
body
 {
     background-image: url(https://static.pexels.com/photos/89784/bmw-suv-all-terrain-vehicle-fog-89784.jpeg);
     background-repeat: no-repeat;
     background-size:cover;
 }
 </style>

</head>
<body>
<!-- <div style="height: 1px;width:1px; text-align: center;"> -->
<h1 style="color:white;">Details of car </h1>
<%-- <img id="percentageSize" src="${pageContext.request.contextPath}/images/indica_vista-1.png" /> --%>
	<c:set var="counter" value="1" scope="session"/>
	<%-- <c:forEach items="${car_product}" var="v">
		<c:if test="${fn:startsWith(v.image_name, carname)}">
			<c:if test="${fn:contains(v.image_name,'1')}">
			<img src="data:image/jpg;base64, <c:out value='${v.encodedImage}'/>"
        			width="214" height="138" />
        </c:if>
         </c:if>
      </c:forEach> --%>
      
      <img src="data:image/jpg;base64, <c:out value='${c_prod.encodedImage}'/>"
        			width="414" height="338" />
        <table>
        	<tr>
        		<td>PRODUCT NAME:</td><td>${c_details.product_name}</td>
        	</tr>
        	<tr>
        		<td>PRODUCT STATUS:</td><td>${c_details.product_status}</td>
        	</tr>
        	<tr>
        		<td>PRODUCT PRICE:</td><td>RS.${c_details.product_price}</td>
        	</tr>
        </table>
<!-- </div> -->
</body>

<form action="/Spring_mvc-emailsending/cart" method="get">

	
	<input type="submit" name="cart" value="Add to cart">
	<input type="submit" name="cart" value="share details">
</form>
</html>