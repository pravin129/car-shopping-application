<h1>welcome to the list of retailer companies</h1>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<form:form method="post" commandName="s">
	<table>
		<thead>
		<tr>
			<th>retailer_company_name</th>
			
		</tr>
		<tbody>
			<c:forEach items="${retailer}" var="ret" varStatus="vs">
				<tr>
              	<td><c:out value="${ret.company_name}" /></td>
        		
   				</tr>
        </c:forEach>
		</tbody>
	</table>
</form:form>
</html>