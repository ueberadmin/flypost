<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Flypost</title>
		<link rel="stylesheet" type="text/css" href="${context}/css/edit.css">
		<c:if test="${!forEdit}">
			<link rel="stylesheet" type="text/css" href="${context}/css/readonly.css">
		</c:if>
		<script type="text/javascript" src="${context}/js/jquery-1.3.1.min.js"></script>
		<script type="text/javascript">
			function updateSize() {
				var windowWidth = window.innerWidth;
				var windowHeight = window.innerHeight;
				
				var newWidth = windowWidth;
				var newHeight = windowHeight;
				if (windowWidth>windowHeight) {
					newWidth = windowHeight / 1.414285714;
				} else {
					newHeight = windowWidth * 1.414285714;
				}
				
				
				var all = document.getElementById('flypost');
				all.style.height = newHeight;
				all.style.width = newWidth;
				
				document.getElementById('navi').style.width = newWidth; 
			}
			
			window.onresize = updateSize;
		</script> 
	</head>
		<body onload="updateSize()">
	    <form:form method="post" enctype="multipart/form-data">
		    <div>
				<div id="navi" style="width:30%;border-style:none;">
					<table style="width:100%;border-style:none">
						<tr style="width:100%;border-style:none"><td>Dein Paper-Post</td></tr>
						<tr style="width:100%;border-style:none">
							<td style="width:70%;border-style:none">Ausdrucken und verteilen</td>	
							<td style="width:30%;border-style:none;text-align:right" align="right">
								<c:if test="${forEdit}">
									<div style="float:right;overflow-y: auto; margin-left:5%;">
										<input id="saveIcon" type="image" src="${context}/icons/save-icon-small-inactive.png" alt="Speichern und Veröffentlichen" style="align:right;border-style:none;">
									</div>
								</c:if>
								<div style="float:right;overflow-y: auto;margin-left:5%;">
									<a href="javascript:print($('#flypost'));">
										<img src="${context}/icons/print.png" alt="Drucken" title="Drucken" style="align:right;border-style:none;">
									</a>
								</div>
								<div style="float:right;overflow-y: auto;">
									<c:if test="${isLoggedIn}">
										<a href="${context}/ausloggen?url=${currentUrl}" style="height:100%;">
										<img src="${context}/icons/logout.png" alt="Ausloggen" title="Ausloggen" style="align:right;border-style:none;height:100%;">
										</a>
									</c:if>
									<c:if test="${!isLoggedIn}">
										<a href="${context}/einloggen?url=${currentUrl}" style="height:100%;">
											<img src="${context}/icons/login.png" alt="Einloggen" title="Einloggen" style="align:right;border-style:none;height:100%;">
										</a>
									</c:if>
									
								</div>								
											
							</td>
						</tr>
					</table>
				</div>
	    	</div>
	    	
	    	<%@include file="flypost.jsp" %>
		    
	    </form:form>	
	</body>

 	<script type="text/javascript">
    
    function handleFileSelect(evt) {
        var files = evt.target.files; // FileList object

        // Loop through the FileList and render image files as thumbnails.
        for (var i = 0, f; f = files[i]; i++) {

          // Only process image files.
          if (!f.type.match('image.*')) {
            continue;
          }

          var reader = new FileReader();

          // Closure to capture the file information.
          reader.onload = (function(theFile) {
            return function(e) {
              // Render thumbnail.
              var span = document.getElementById('imageArea');
              span.innerHTML = ['<img class="thumb" src="', e.target.result, '" title="', escape(theFile.name), '" height="80%" />'].join('');
            };
          })(f);

          // Read in the image file as a data URL.
          reader.readAsDataURL(f);
        }
      }

      var filesInput = document.getElementById('files');
      if (filesInput) {
      	filesInput.addEventListener('change', handleFileSelect, false);
      }
      
      var isModified = false;
      function modified() {
    	  if (!isModified) {
    		  isModified = true;
    		  document.getElementById('saveIcon').src="${context}/icons/save-icon-small.png";
    	  }
      }
      
      
      document.getElementById('headline').addEventListener('keydown', modified, false);
      document.getElementById('files').addEventListener('change', modified, false);
      document.getElementById('description').addEventListener('keydown', modified, false);
      document.getElementById('contactData').addEventListener('keydown', modified, false);
      
      function print(div) {
    	  var data = div.html();
    	  var mywindow = window.open('${printUrl}', 'Druckansicht', 'height=594,width=420');;
		  
          mywindow.print();
          //mywindow.close();

          return true;
      }
    
   </script>
</html>