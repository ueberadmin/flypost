<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>${vm.headline}</title>
	</head>
	
	<body>
		<div class="flypost" style="width:100%; height:100%; text-align: center; border-style: none; border-width: 0px;">
			<div style="font-weight: bold; font-size: 25px; width:100%;">
				${vm.headline}
			</div>
			<c:if test="${imageUrl != null}">
				<div style="height: 40%;">
					<img src="${imageUrl}" style="height: 100%; border-style: none;">
				</div>
				<!-- textarea style="width:100%; height: 28%; border-style:none; resize: none;" readonly>${vm.description}</textarea -->
				<div style="width:100%; height: 25%; border-style:none; resize: none;">${vm.description}</div>
			</c:if>
			<c:if test="${imageUrl == null}">
				<!--  textarea style="width:100%; height: 68%; border-style:none; resize: none;" readonly>${vm.description}</textarea -->
				<div style="width:100%; height: 65%; border-style:none; resize: none;">${vm.description}</div>
			</c:if>
			<div style="height: 10%; margin: 1px; text-align: right;">
				<img src="${qrCodeUrl}" style="border-style: none; height: 100%;">
			</div>
			
			<c:if test="${addSheets}">
				<div style="width:100%; height: 19%; border-top-style: dashed; border-width: 1px;">
				
					<%for (int i = 0; i < 5; i++) {%>
				
						<div style="margin:0px; width:16%; border-right: grey 1px dashed; height:100%; float:left; box-sizing:border-box; -moz-box-sizing:border-box; -webkit-box-sizing:border-box;">
							 <div style="-moz-transform: rotate(270deg);  /* FF3.5+ */        
										-o-transform: rotate(270deg);  /* Opera 10.5 */   
										-webkit-transform: rotate(270deg);  /* Saf3.1+, Chrome */              
										filter:  progid:DXImageTransform.Microsoft.BasicImage(rotation=3);  /* IE6,IE7 */          
										-ms-filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=3); /* IE8 */
										position:relative;
										left:-50%;
										top:40%;
										font-size:0.7em;
										width:200%">
							 	<div>
							 		${vm.headline}
							 	</div>	
							 	<div style="float:left; width:75%;">
							 		${vm.contactData}
							 	</div>
							 </div>
						</div>
					<%} %>
					
					<div style="margin:0px; width:16%; border-right: grey 1px none; height:100%; float:left; box-sizing:border-box; -moz-box-sizing:border-box; -webkit-box-sizing:border-box;">
						 <div style="-moz-transform: rotate(270deg);  /* FF3.5+ */        
									-o-transform: rotate(270deg);  /* Opera 10.5 */   
									-webkit-transform: rotate(270deg);  /* Saf3.1+, Chrome */              
									filter:  progid:DXImageTransform.Microsoft.BasicImage(rotation=3);  /* IE6,IE7 */          
									-ms-filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=3); /* IE8 */
									position:relative;
									left:-50%;
									top:40%;
									font-size:0.7em;
									width:200%">
						 	<div>
						 		${vm.headline}
						 	</div>	
						 	<div style="float:left; width:75%;">
						 		${vm.contactData}
						 	</div>
						 </div>
					</div>
				</div>
			</c:if>
		</div>
	</body>
	
</html>