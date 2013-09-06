<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Zettl</title>
		<link rel="stylesheet" type="text/css" href="${context}/css/zettl.css">
		<script type="text/javascript" src="${context}/js/zettl.js"></script>
		<script type="text/javascript" src="${context}/js/jquery-1.3.1.min.js"></script>
	</head>
	
	<body>
		<%@include file="/WEB-INF/views/header.jsp" %>
		<div>
			${description}
		</div>
		
		<div>
			<a href="javascript:print('${id}', '${context}');">
				Abreißzettel drucken
			</a>
		</div>
		
		<c:if test="${editUrl != null}">
			<div>
				<a href="${editUrl}">Bearbeiten</a>
			</div>
		</c:if>
		
		<%@include file="/WEB-INF/views/footer.jsp" %>
	</body>
</html>