<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Zettl</title>
		<link rel="stylesheet" type="text/css" href="${context}/css/zettl.css">
		<link rel="stylesheet" type="text/css" href="${context}/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${context}/css/geodatas.css">
	</head>
	
	<body">
		<div class="zettl">
			<%@include file="/WEB-INF/views/header.jsp" %>
			<form:form method="post" class="geodatasForm" action="${postUrl}">
				<label for="osm_id">Mehrere mögliche Adressen gefunden</label>
				<select name="osm_id" id="osm_id" class="osm_id">
					<c:forEach var="geodata" items="${geodatas}">
						<option value="${geodata.osmId}">${geodata.displayName}</option>
					</c:forEach>
				</select>
				
				<div id="buttons" class="buttons">
					<div class="left">
						<button onclick="window.browser.back();">Zur&uuml;ck</button>
					</div>
					<div class="right">
						<button type="submit">Zettln</button>
					</div>
				</div>
				
			</form:form>
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</body>
</html>