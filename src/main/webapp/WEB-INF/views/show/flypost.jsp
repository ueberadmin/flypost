<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${context}/css/flypost.css">
	</head>
	
	<body>
		<div class="flypost">
			<div class="headline">
				${vm.headline}
			</div>
			
			<div class="description">
				${vm.description}
			</div>
			
			<div class="sheets">
				<%for (int i = 0; i < 5; i++) {%>
			
					<div style="margin:0px; width:16.5%; border-right: grey 2px dashed; height:100%; float:left; box-sizing:border-box; -moz-box-sizing:border-box; -webkit-box-sizing:border-box;">
						 <div class="sheet">
						 	<div>
						 		${vm.headline}
						 	</div>	
						 	<div style="float:left; width:75%;">
						 		${vm.contactData}
						 	</div>
						 </div>
					</div>
				<%} %>
			</div>
		</div>
	</body>
	
</html>