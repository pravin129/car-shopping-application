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
     background-image: url(C://Users/Asus/workspace2/Spring_mvc-emailsending/WebContent/images/volvo_logo1.jpg);
     background-repeat: no-repeat;
     background-size:cover;
 }
 </style>
</head>
<body>
<h1 style="color:white;">WELCOME TO VOLVO COMPANY </h1>
<%-- <img id="percentageSize" src="${pageContext.request.contextPath}/images/volvo_logo.png" /> --%>
</body>

	<form action="/Spring_mvc-emailsending/select_car">
	<p style="color:white;">select car name</p>
		<select name="car_type">
  			<option value="volvo_xc90">volvo XC90</option>
  			<option value="volvo_xc60">volvo XC60</option>
  			<option value="volvo_v90">volvo V90</option>
  			<option value="volvo_s60">volvoS60</option>
		</select>
		<input type="submit" value="submit">
	</form>
</html>