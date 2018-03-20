<html>
<head>
<style type="text/css">
#percentageSize {
  width:50%;
  height: auto;
  background: red;
}

</style>
<style type="text/css">
body
 {
     background-image: url(C://Users/Asus/workspace2/Spring_mvc-emailsending/WebContent/images/tata1.jpg);
     background-repeat: no-repeat;
     background-size:cover;
 }
 </style>
<body>
<h1>WELCOME TO TATA Company </h1>
<%-- <img id="percentageSize" src="${pageContext.request.contextPath}/images/tata.jpg" /> --%>
</body>
</head>
	<form action="/Spring_mvc-emailsending/select_car">
	<p>select car name</p>
		<select name="car_type">
  			<option value="vista">INDICA_VISTA</option>
  			<option value="TataHexa">TATA_HEXA</option>
  			<option value="nano">NANO</option>
  			<option value="tiago">TIAGO</option>
		</select>
		<input type="submit" value="submit">
	</form>
</html>