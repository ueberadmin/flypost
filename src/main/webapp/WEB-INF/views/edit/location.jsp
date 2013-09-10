<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Zettl</title>
		<link rel="stylesheet" type="text/css" href="${context}/css/zettl.css">
		<link rel="stylesheet" type="text/css" href="${context}/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${context}/css/location.css">
		<script type="text/javascript" src="${context}/js/zettl.js"></script>
		<script type="text/javascript" src="${context}/js/location.js"></script>
	</head>
	
	<body onload="//init()">
		<div class="zettl">
			<%@include file="/WEB-INF/views/header.jsp" %>
			<form:form method="post" class="locationForm">
				<div>
					<h2>
						Wer zettlt?
					</h2>
					<div>
						<form:input path="emailAddress" placeholder="Deine EMail" class="zettlInput longZettlInput" />
					</div>
				</div>
						
				<div>
					<h2>
						Wo wird gezettlt?
					</h2>
					<div>
						<div>
							<form:input path="street" placeholder="Deine Strasse, Nr." class="zettlInput longZettlInput"/>
						</div>
						
						<div class="shortLongRow">
							<div class="left">
								<form:input path="postCode" placeholder="PLZ" class="zettlInput short"/>
							</div>
							<div class="right">
								<form:input path="city" placeholder="Ort" class="zettlInput long"/>
							</div>
						</div>
						
						<div class="shortLongRow">
							<div class="left">
								<input class="radiusLabel" placeholder="Umkreis" readonly="readonly" style="background-color:ffffff;">
							</div>
							
							<div class="right">
								<form:input path="radius" placeholder="500 m" class="zettlInput long"/>
							</div>
						</div>
					</div>
				</div>
				<div id="buttons" class="buttons">
					<div class="left">
						<button onclick="browser.back();">Zur&uuml;ck</button>
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