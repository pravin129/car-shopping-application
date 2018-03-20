<html>
<h1>welcome to product management page</h1>
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
<form action="insertimages" method="post">
	<table width="500px">
	<tr>
		<td>
			Image id:
		</td>
		<td><input type="text" name="image_id"></td>
	</tr>
	<tr>
		<td>
			Product Status:
		</td>
		<td><input type="text" name="product_status"></td>
	</tr>
	<tr>
		<td>
			Image name:
		</td>
		<td><input type="text" name="image_name"></td>
	</tr>
	<tr>
		<td>
			Select image:
		</td>
		<td><input type="file" name="select_file" value="browse"></td>
	</tr>
	<tr><td><input type="submit" value="submit"></td></tr>
	</table>
</form>
</center>
</body>
</html>