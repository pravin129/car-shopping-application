<%@ taglib tagdir='/WEB-INF/tags' prefix='sc'%>
<html>
<h1>Prove that you are not a Robot</h1>
<head>
    <title>Captcha Entry Form</title>
    <style type="text/css">
	body
 	{
     	background-image: url(https://static.pexels.com/photos/89784/bmw-suv-all-terrain-vehicle-fog-89784.jpeg);
     	background-repeat: no-repeat;
     	background-size:cover;
 	}
 </style>
</head>
<center>
<body>
Are you human? <font color="red">${message}</font>
<br/>
<form action="recaptcha" method="post"> 
    <sc:captcha/>
    <input type="submit" value="submit"/> 
</form>
</body>
</center>
</html>