<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Zettl</title>
		<link rel="stylesheet" type="text/css" href="${context}/css/zettl.css">
		<link rel="stylesheet" type="text/css" href="${context}/css/description.css">
		<!-- link rel="stylesheet" type="text/css" href="${context}/css/bootstrap.min.css" -->
		<script type="text/javascript" src="${context}/js/zettl.js"></script>
		<script type="text/javascript" src="${context}/js/description.js"></script>
		<script type="text/javascript" src="${context}/js/jquery-1.3.1.min.js"></script>
		<script src="${context}/tinymce/tinymce.min.js"></script>
		<script>
        	tinymce.init({	selector:'textarea',
        					menubar : false,
        					plugins: "image,doksoft_image",
        					toolbar: "bold italic | fontselect | fontsizeselect | doksoft_image",
        					relative_urls: false,
        					statusbar: false
        				});
		</script>
	</head>
	
	<body onload="init()">
		<div class="zettl">
			<%@include file="/WEB-INF/views/header.jsp" %>
			<div id="zettl_marker01">
				<a href="#marker01"><span class="tooltip">Zettl ist ...</span><img src="${context}/img/orange_dot.png"></a>
			</div>
			<div id="zettl_marker02">
				<a href="#marker02"><span class="tooltip">Mit Zettl kannst Du ...</span><img src="${context}/img/orange_dot.png"></a>
			</div>
			<div id="zettl_marker03">
				<a href="#marker03"><span class="tooltip">Wer zettlt?</span><img src="${context}/img/orange_dot.png"></a>
			</div>
			
			
			<form:form method="post" id="descriptionForm" class="descriptionForm" enctype="multipart/form-data">
				<div class="left">
					<div class="descriptionContainer">
						<form:textarea path="description" class="description" placeholder="Dein Zettltxt ..." tabindex="-1" readonly="readonly" />
					</div>
					<div class="submit_button">
						<button type="submit" class="zettl_button">Zettln</button>
					</div>
				</div>						
			</form:form>
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
		
	</body>
	<script type="text/javascript">
		//window.onresize = updateSize;
	</script>
</html>		