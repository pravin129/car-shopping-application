<%-- <%@ page errorPage="errorpage.jsp" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:useBean id="cart" scope="session" class="com.pravin.shoppingcart.ShoppingCart" />
<html>
  <head>
    <title>Car Shopping</title>
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
  <%
  	int cartitem=0;
    String id = request.getParameter("id");
  	String Action=request.getParameter("cart");
  	System.out.println("Action on addtocart ="+Action);
    if ( id != null ) {
      String desc = request.getParameter("desc");
      Float price = new Float(request.getParameter("price"));
      if(Action.equals("remove"))
      {
    	  
    	  cart.removeItem(id);
    	  //cartitem=cart.getNumOfItems()-1;
    	  
      }else{
      cart.addItem(id, desc, price.floatValue(), 1);
      cartitem=cart.getNumOfItems();
    }
   }
  %>
  <!-- <a href="ShoppingCart.jsp">Shopping Cart Quantity:</a> -->
  <a href="<c:url value="/cartdisp" />" style="color:white;">Shopping Cart Quantity:</a>
   <%-- <%=cart.getNumOfItems()%> --%>
   <%=cartitem%>
  <hr>
  <center><h3>CAR SHOPPING CART</h3></center>
  <table border="1" width="300" cellspacing="0"
  cellpadding="2" align="center">
    <tr><th>Description</th><th>Price(In INR)</th><th>Add To Cart</th></th><th>remove item</th></tr>
    <tr>
      <form action="cart" method="post">
        <td>${c_details.product_name}</td>
        <td>${c_details.product_price}</td>
        <td><input type="submit" name="cart" value="Add"></td>
        <input type="hidden" name="id" value="1">
        <input type="hidden" name="desc" value="car">
        <input type="hidden" name="price" value="${c_details.product_price}">
        <td><input type="submit" name="cart" value="remove"></td>
      </form>
    </tr>
     <!-- <tr>
      <form action="AddToShoppingCart.jsp" method="post">
        <td>car</td>
        <td>$19.95</td>
        <td><input type="submit" name="Submit" value="Add"></td>
        <input type="hidden" name="id" value="2">
        <input type="hidden" name="desc" value="car">
        <input type="hidden" name="price" value="19.95">
      </form>
    </tr>  -->
    </table>
  </body>
</html>
