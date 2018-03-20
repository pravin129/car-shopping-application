<html>
<head>
<style type="text/css">
#percentageSize {
  width: 70%;
  height: 70%;
  background: red;
}

</style>
<style type="text/css">
body
 {
     background-image: url(C://Users/Asus/workspace2/Spring_mvc-emailsending/WebContent/images/marutisuzuki_logo1.jpg);
     background-repeat: no-repeat;
     background-size:cover;
 }
 </style>
</head>
<body>
<h1 style="color:white;">WELCOME TO MARUTI SUZUKI COMPANY </h1>
<%-- <img id="percentageSize" src="${pageContext.request.contextPath}/images/MarutiSuzuki_Logo.jfif" /> --%>
</body>

	<form action="/Spring_mvc-emailsending/select_car">
	<p style="color:white;">select car name</p>
		<select name="car_type">
  			<option value="MarutiSuzuki_swift">swift</option>
  			<option value="MarutiSuzuki_breeza">brezza</option>
  			<option value="MarutiSuzuki_dzire">dzire</option>
  			<option value="MarutiSuzuki_alto">alto</option>
		</select>
		<input type="submit" value="submit">
	</form>
</html>