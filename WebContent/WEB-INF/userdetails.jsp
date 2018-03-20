<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<h1>welcome to the list of existing users</h1>
<body background="C://Users/Asus/workspace2/Spring_mvc-emailsending/WebContent/images/registration_bg.jpg">
<form:form method="post" commandName="map">
	<table>
		<thead>
		<tr>
			<th>Username</th>
			<th>Password</th>
		</tr>
		<tbody>
			<c:forEach items="${userdetails}" var="item" varStatus="ud">
				<tr>  
        		<td><c:out value="${item.key}" /></td>
        		<td><c:out value="${item.value}" /></td>
   				</tr>
        </c:forEach>
		</tbody>
	</table>
</form:form>
</body>
</html>