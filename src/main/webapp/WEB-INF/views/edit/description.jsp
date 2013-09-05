<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Zettl</title>
		<link rel="stylesheet" type="text/css" href="${context}/css/zettl.css">
		<link rel="stylesheet" type="text/css" href="${context}/css/edit.css">
		<link rel="stylesheet" type="text/css" href="${context}/css/bootstrap.min.css">
		<script type="text/javascript" src="${context}/js/zettl.js"></script>
		<script type="text/javascript" src="${context}/js/description.js"></script>
	</head>
	
	<body onload="init()">
		<div id="zettl" class="aspectwrapper">
			<div id="content" class="content">
				<%@include file="/WEB-INF/views/header.jsp" %>
				
				<div id="descriptionContainer" class="specificContent">
					<div id="left" class="left">
						<form:form method="post" id="descriptionForm">
							<div>
								<form:textarea path="description" class="description" placeholder="Dein Zettltxt ..." tabindex="-1" />
							</div>
							<div id="left_bottom" class="left_bottom">
								<button type="submit">Zettln</button>
							</div>
						</form:form>
					</div>
					
					<div id="right" class="right">
						<div class="btn-group btn-group-vertical">
							<button class="btn" onclick="increaseFont()">A+</button>
 							<button class="btn" onclick="decreaseFont()">A-</button>
						</div>
					</div>
				</div>
			</div>
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
		
	</body>
</html>