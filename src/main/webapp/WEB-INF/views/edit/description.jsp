<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Zettl</title>
		<link rel="stylesheet" type="text/css" href="${context}/css/zettl.css">
		<link rel="stylesheet" type="text/css" href="${context}/css/description.css">
		<link rel="stylesheet" type="text/css" href="${context}/css/bootstrap.min.css">
		<script type="text/javascript" src="${context}/js/zettl.js"></script>
		<script type="text/javascript" src="${context}/js/description.js"></script>
		<script type="text/javascript" src="${context}/js/jquery-1.3.1.min.js"></script>
		<script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
		<script>
        	tinymce.init({	selector:'textarea',
        					menubar : false,
        					plugins: "image",
        					toolbar: "undo redo | bold italic | fontselect | fontsizeselect"
        				});
		</script>
	</head>
	
	<body onload="init()">
		<div class="zettl">
			<%@include file="/WEB-INF/views/header.jsp" %>
			<form:form method="post" id="descriptionForm" class="descriptionForm" enctype="multipart/form-data">
				<div class="left">
					<div>
						<form:textarea path="description" class="description" placeholder="Dein Zettltxt ..." tabindex="-1" />
					</div>
					<div class="submit_button">
						<button type="submit">Zettln</button>
					</div>
				</div>
				
				<div class="right">
					<div class="btn-group btn-group-vertical">
						<!-- 
						<button class="btn" onclick="increaseFont(); return false;">A+</button>
							<button class="btn" onclick="decreaseFont(); return false;">A-</button>
							<br>
							 -->
							<button id="upimage" class="btn btn-file" onclick="return false;">
								<img src="${context}/icons/upload-icon.png" border="0" width="100%">
							</button>
						<input type="file" id="image"  name="image" style="display:none" />
						<script type="text/javascript">
							$("#upimage").click(function () {
							    $("#image").trigger('click');
							});
						</script>
					</div>
					<div id="imageArea" class="imageArea">
						<c:if test="${imageUrl != null}">
							<img class="thumb" src="${imageUrl}" />
						</c:if>
					</div>
					<script type="text/javascript">
					 var filesInput = document.getElementById('image');
					  if (filesInput) {
					  	filesInput.addEventListener('change', handleFileSelect, false);
					  }
					</script>
				</div>			
				
			</form:form>
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
		
	</body>
	<script type="text/javascript">
		//window.onresize = updateSize;
	</script>
</html>		