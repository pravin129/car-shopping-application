<%@ page errorPage="errorpage.jsp" %>
<%@ page import="java.util.*" %>

<jsp:useBean id="cart" scope="session" class="com.pravin.shoppingcart.ShoppingCart" />
<html>
<form action="cart" method="post">
  <head>
    <title>Shopping Cart Contents</title>
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
    <center>
    <table width="300" border="1" cellspacing="0"
      cellpadding="2" border="0">
      <caption><b>Shopping Cart Contents</b></caption>
      <tr>
        <th>Description</th>
        <th>Price(In INR)</th>
        <th>Quantity</th>
        <th>Total Cost</th>
      </tr>
      <%!float Total=0;%>
    <%
      Enumeration e = cart.getEnumeration();
      Total=cart.getCost();
      String[] tmpItem;
      // Iterate over the cart
      while (e.hasMoreElements()) {
        tmpItem = (String[])e.nextElement();
        %>
        <tr>
          <%-- <td><%=tmpItem[1] %></td>
          <td align="center"><%=tmpItem[2] %></td>
          <td align="center"><%=tmpItem[3] %></td>
          <td align="center"><%=Total %></td>  --%>
          
          <td><%=tmpItem[1] %></td>
          <td align="center">${c_details.product_price}</td>
          <td align="center"><%=tmpItem[3] %></td>
          <td align="center"><%=Total %></td> 
        </tr>
        <%
      }
    %> 
    </table>
    </center>
    <a href="welcome.jsp" style="color:white;">Back to welcome page</a>
  </body>
  </form>
</html>
