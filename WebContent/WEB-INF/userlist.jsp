<h1>welcome to the list of existing users</h1>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
<body>
<form:form method="post" commandName="users1">
	<table>
		<thead>
		<tr>
			<th>User_id</th>
			<th>Username</th>
			<th>Password</th>
		</tr>
		<tbody>
			<c:forEach items="${users1}" var="user" varStatus="vs">
				<tr>
              	<td><c:out value="${user.user_id}" /></td>
        		<td><c:out value="${user.username}" /></td>
        		<td><c:out value="${user.password}" /></td>
   				</tr>
        </c:forEach>
		</tbody>
	</table>
</form:form>
</body>
</html>