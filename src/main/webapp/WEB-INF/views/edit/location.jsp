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
	
	<body onload="init()">
		<div id="zettl" class="aspectwrapper">
			<div id="content" class="content">
				<%@include file="/WEB-INF/views/header.jsp" %>
				<div class="specificContent"> 
					<form:form method="post">
						<div>
							<h2>
								Wer zettlt?
							</h2>
							<div>
								<form:input path="emailAddress" placeholder="Deine EMail" class="zettlInput" />
							</div>
						</div>
						
						<div>
							<h2>
								Wo wird gezettlt?
							</h2>
							<div>
								<div>
									<form:input path="street" placeholder="Deine Strasse, Nr." class="zettlInput"/>
								</div>
								
								<div class="postcodeAndCity">
									<div class="left">
										<form:input path="postCode" placeholder="PLZ" class="zettlInput"/>
									</div>
									<div>
										<form:input path="city" placeholder="Ort" class="zettlInput"/>
									</div>
								</div>
								
								<div>
									<div id="radiusLabel" class="radiusLabel left">
										Umkreis
									</div>
									
									<div>
										<form:input path="radius" placeholder="500" class="zettlInput"/> m
									</div>
								</div>
							</div>
						</div>
						<div id="buttons" class="buttons">
							<div class="left">
								<button onclick="browser.back();">Zur&uuml;ck</button>
							</div>
							<div>
								<button type="submit">Zettln</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</body>
	
</html>