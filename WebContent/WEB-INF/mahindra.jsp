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
     background-image: url(C://Users/Asus/workspace2/Spring_mvc-emailsending/WebContent/images/mahindra_logo1.jpg);
     background-repeat: no-repeat;
     background-size:cover;
 }
 </style>
</head>
<body>
<h1 style="color:white;">WELCOME TO MAHINDRA COMPANY </h1>
<%-- <img id="percentageSize" src="${pageContext.request.contextPath}/images/mahindra_logo.jpg" /> --%>
</body>

	<form action="/Spring_mvc-emailsending/select_car">
	<p style="color:blue;">select car name</p>
		<select name="car_type">
  			<option value="mahindraTUV">Mahindra TUV 300</option>
  			<option value="mahindraThar">Mahindra Thar</option>
  			<option value="mahindrascorpio">Mahindra Scorpio</option>
  			<option value="mahindraxylo">MahindraXylo</option>
		</select>
		<input type="submit" value="submit">
	</form>
</html>