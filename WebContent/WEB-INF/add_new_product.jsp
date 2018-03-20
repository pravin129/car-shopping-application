<html>
<h1>welcome to Add new Product Page</h1>
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
<center>
<form action="addproduct" method="post">
	<table width="500px">
	<tr>
		<td>
			Product Name:
		</td>
		<td><input type="text" name="product_name"></td>
	</tr>
	<tr>
		<td>
			Product Status:
		</td>
		<td><input type="text" name="product_status"></td>
	</tr>
	<tr>
		<td>
			product price:
		</td>
		<td><input type="text" name="product_price"></td>
	</tr>
	
	<tr>
		<td>
			product specifications:
		</td>
		<td><input type="text" name="product_specifications"></td>
	</tr>
	<tr><td><input type="submit" value="submit"></td></tr>
	</table>
</form>
</center>
</body>
</html>