<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${context}/css/print.css">
		<script type="text/javascript" src="${context}/js/jquery-1.3.1.min.js" > </script> 
	</head>
	
	<body>
		<form:form>
			<%@include file="flypost.jsp" %>
		</form:form>
	</body>
	
</html>