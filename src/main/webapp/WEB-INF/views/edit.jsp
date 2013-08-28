<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<style type="text/css">
			h1 { font-size: 1.8em; font-weight: normal; font-family: 'Times New Roman'; }

			.aspectwrapper {
			display: inline-block; /* shrink to fit */
			width: 30%; /* whatever width you like */
			position: relative; /* so .content can use position: absolute */
			}
			.aspectwrapper::after {
			padding-top: 141.4285714%; /* percentage of containing block _width_ */
			display: block;
			content: '';
			}
			.content {
			position: absolute;
			top: 0; bottom: 0; right: 0; left: 0; /* follow the parent's edges */
			outline: thin solid black; /* just so you can see the box */
			}

		</style>
	</head>
		<body>
	    <h1>Flypost</h1>
	    <form:form method="post" enctype="multipart/form-data">
		    <div>
				<div style="width:30%;border-style:none;">
					<table style="width:100%;border-style:none">
						<tr style="width:100%;border-style:none"><td>Dein Paper-Post</td></tr>
						<tr style="width:100%;border-style:none">
							<td style="width:70%;border-style:none">Ausdrucken und verteilen</td>	
							<td style="width:30%;border-style:none;text-align:right">
								<input id="saveIcon" type="image" src="${context}/icons/save-icon-small-inactive.png" alt="Speichern und Veröffentlichen" style="align:right;border-style:none; height:33%;">			
							</td>
						</tr>
					</table>
				</div>
			
	    	</div>
		    <div class="aspectwrapper">
				<div class="content">
				
					<div style="margin-top:10%; width:100%; border-style:none;">
						<form:input id="headline" path="headline" type="text" placeholder="Überschrift hier eingeben" style="border-style:none;margin-left:5%;margin-right:5%;width:90%;" />
					</div>
					
					<div style="margin-top:10%; width:100%; border-style:none;">
						<div style="border:grey 1px dashed;margin-left:5%;margin-right:5%;width:90%; height:30%">
							<input type="file" id="files" name="image" style="height:20%" />
							<div id="imageArea">
								<c:if test="${imageUrl != null}">
									<img class="thumb" src="${imageUrl}" style="height:80%" />
								</c:if>
							</div>
						</div>
					</div>
					<div style="margin-top:1%; width:100%; border-style:none;">
						<form:textarea id="description" path="description" placeholder="Hier eine ausführliche Beschreibung eingeben." style="border-style:none;margin-left:5%;margin-right:5%;width:90%;height:20%"></form:textarea>
					</div>
					<div style="margin-top:1%; width:100%; border-style:none;">
						<div style="border-style:none;margin-left:5%;margin-right:5%;width:90%; height:10%">
							<span>
								<form:textarea id="contactData" path="contactData" placeholder="Ihre Kontaktdaten" style="border-style:none;height:100%;width:80%;"></form:textarea>
							</span>
							<span>
								<img id="qrCode" src="${qrCodeUrl}" height="100%">
							</span>
							
						</div>
					</div>
			
				</div>
		  	</div>
	    </form:form>	
	</body>

 	<script>
    
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

      document.getElementById('files').addEventListener('change', handleFileSelect, false);
      
      var isModified = false;
      function modified() {
    	  if (!isModified) {
    		  isModified = true;
    		  document.getElementById('saveIcon').src="${context}/icons/save-icon-small.png";
    	  }
      }
      
      document.getElementById('headline').addEventListener('change', modified, false);
      document.getElementById('files').addEventListener('change', modified, false);
      document.getElementById('description').addEventListener('change', modified, false);
      document.getElementById('contactData').addEventListener('change', modified, false);
    
      </script>
</html>