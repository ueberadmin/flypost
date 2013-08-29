<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="flypost" class="aspectwrapper">
	<div id="content" class="content">
	
		<div style="margin-top:2%; width:100%; border-style:none;">
			<form:input id="headline" path="headline" type="text" placeholder="Überschrift hier eingeben" style="border:LightGray 1px ${borderStyle};margin-left:5%;margin-right:5%;width:90%;" />
		</div>
		
		<div style="margin-top:2%; width:100%; border-style:none;">
			<div style="border:LightGray 1px ${borderStyle};margin-left:5%;margin-right:5%;width:90%; height:30%">
				<c:if test="${forEdit}"><input type="file" id="files" name="image" style="height:20%" /></c:if>
				<div>
					<center id="imageArea">
						<c:if test="${imageUrl != null}">
							<img class="thumb" src="${imageUrl}" style="height:80%" />
						</c:if>
					</center>
				</div>
			</div>
		</div>
		<div style="margin-top:1%; width:100%; border-style:none;">
			<c:if test="${showSheets}">
				<form:textarea id="description" path="description" placeholder="Hier eine ausführliche Beschreibung eingeben." style="border:LightGray 1px ${borderStyle}; margin-left:5%;margin-right:5%;width:90%;height:20%"></form:textarea>
			</c:if>
			<c:if test="${!showSheets}">
				<form:textarea id="description" path="description" placeholder="Hier eine ausführliche Beschreibung eingeben." style="border:LightGray 1px ${borderStyle}; margin-left:5%;margin-right:5%;width:90%;height:40%"></form:textarea>
			</c:if>
		</div>
		<div style="margin-top:1%; width:100%; border-style:none;">
			<div style="border-style:none;margin-left:5%;margin-right:5%;margin-bottom:1%;width:90%; height:10%">
				<span>
					<form:textarea id="contactData" path="contactData" placeholder="Ihre Kontaktdaten" style="border:LightGray 1px ${borderStyle};height:100%;width:80%;"></form:textarea>
				</span>
				
				<span>
					<img id="qrCode" src="${qrCodeUrl}" height="100%">
				</span>
			</div>
		</div>
		<c:if test="${showSheets}">
			<div id="sheets" style="margin-top:1px; margin-left:0px; width:100%; border-top: gray 2px dashed; height:30%;">
				<%for (int i = 0; i < 5; i++) {%>
			
					<div style="margin:0px; width:16.5%; border-right: grey 2px dashed; height:100%; float:left; box-sizing:border-box; -moz-box-sizing:border-box; -webkit-box-sizing:border-box;">
						 <div style="-moz-transform: rotate(270deg);  /* FF3.5+ */        
									 -o-transform: rotate(270deg);  /* Opera 10.5 */   
									    		-webkit-transform: rotate(270deg);  /* Saf3.1+, Chrome */              
									    		filter:  progid:DXImageTransform.Microsoft.BasicImage(rotation=3);  /* IE6,IE7 */          
									    		-ms-filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=3); /* IE8 */
									    		position:relative; left:-50%; top:40%; font-size:0.7em; width:200%">
						 	<div>
						 		${command.headline}
						 	</div>	
						 	<div style="float:left; width:75%;">
						 		${command.contactData}
						 	</div>
						 	<div>
						 		<img src="${qrCodeUrl}" width="25%">
						 	</div>
						 </div>
					</div>
				<%} %>
				
				<div style="margin:0px; width:16.5%; border-style:none; height:100%; float:left; box-sizing:border-box; -moz-box-sizing:border-box; -webkit-box-sizing:border-box;">
					 <div style="-moz-transform: rotate(270deg);  /* FF3.5+ */        
								 -o-transform: rotate(270deg);  /* Opera 10.5 */   
								    		-webkit-transform: rotate(270deg);  /* Saf3.1+, Chrome */              
								    		filter:  progid:DXImageTransform.Microsoft.BasicImage(rotation=3);  /* IE6,IE7 */          
								    		-ms-filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=3); /* IE8 */
								    		position:relative; left:-50%; top:40%; font-size:0.7em; width:200%">
					 	<div>
					 		${command.headline}
					 	</div>	
					 	<div style="float:left; width:75%;">
					 		${command.contactData}
					 	</div>
					 	<div>
					 		<img src="${qrCodeUrl}" width="25%">
					 	</div>
					 </div>
				</div>
			</div>
		</c:if>
		</div>
 	</div>