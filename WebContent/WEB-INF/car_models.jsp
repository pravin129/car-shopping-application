<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<html>
<head>

</head>
<h1>welcome to ${carname} car models page</h1>
<!-- <body> -->
<!-- <h1>welcome to indica_vista page</h1> -->
<%-- <img id="percentageSize" src="${pageContext.request.contextPath}/images/indica_vista-1.png" /> --%>
<!-- </body> -->
<form>
	<table>
  <tr>
  <td>
  <p>BASIC MODEL</p>
  <div style="width:830px; background-color:white; height:220px; overflow:auto;">
		<div style="width: 2000px; height: 90px;">
			<c:forEach items="${car_product}" var="v">
 				<%-- <c:if test="${fn:startsWith(${v.image_name}, 'volvo')}">  --%>
 				<c:if test="${fn:startsWith(v.image_name, carname)}">
    				<a href="<c:url value="/cardetails/${v.image_id}/${v.id}" />"><img src="data:image/jpg;base64, <c:out value='${v.encodedImage}'/>"
        			width="214" height="138" /></a>
        		 </c:if> 
        		 
			</c:forEach>
		</div>
		</div>
		</td>
		</tr>
		
		<tr>
  <td>
  <p>TOP MODEL</p>
  <div style="width:830px; background-color:white; height:220px; overflow:auto;">
		<div style="width: 2000px; height: 90px;">
			<c:forEach items="${car_product}" var="v1">
    				<c:if test="${fn:startsWith(v1.image_name, carname)}">
    				<a href="<c:url value="/cardetails/${v1.image_id}/${v1.id}" />"><img src="data:image/jpg;base64, <c:out value='${v1.encodedImage}'/>"
        			width="214" height="138" /></a>
        		 </c:if> 
			</c:forEach>
		</div>
		</div>
		</td>
		</tr>
	</table>
</form>
</html>