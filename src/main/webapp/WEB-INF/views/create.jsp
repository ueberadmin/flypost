<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	    <form:form action="${context}/aushaenge" method="post" enctype="multipart/form-data">
		    <div>
				<div style="width:30%;border-style:none;">
					<table style="width:100%;border-style:none">
						<tr style="width:100%;border-style:none"><td>Dein Paper-Post</td></tr>
						<tr style="width:100%;border-style:none">
							<td style="width:70%;border-style:none">Ausdrucken und verteilen</td>	
							<td style="width:30%;border-style:none;text-align:right">
								<input type="image" src="${context}/icons/save-icon-small.png" alt="Speichern und Veröffentlichen" style="align:right;border-style:none; height:33%;">			
							</td>
						</tr>
					</table>
				</div>
			
	    	</div>
		    <div class="aspectwrapper">
				<div class="content">
				
					<div style="margin-top:10%; width:100%; border-style:none;">
						<form:input path="headline" type="text" placeholder="Überschrift hier eingeben" style="border-style:none;margin-left:5%;margin-right:5%;width:90%;" />
					</div>
					
					<div style="margin-top:10%; width:100%; border-style:none;">
						<div style="border:grey 1px dashed;margin-left:5%;margin-right:5%;width:90%; height:20%">
							<input type="file" id="files" name="image" />
							<output id="list"></output>
						</div>
					</div>
					<div style="margin-top:10%; width:100%; border-style:none;">
						<form:textarea path="description" placeholder="Hier eine ausführliche Beschreibung eingeben." style="border-style:none;margin-left:5%;margin-right:5%;width:90%;height:20%"></form:textarea>
					</div>
					<div style="margin-top:10%; width:100%; border-style:none;">
						<form:textarea path="contactData" placeholder="Ihre Kontaktdaten" style="border-style:none;margin-left:5%;margin-right:5%;width:60%;height:10%"></form:textarea>
					</div>
			
				</div>
		  	</div>
	    </form:form>	
	</body>
	
	
    <!--     	
        	<div>
	        	<label for="image">Bild</label>
	        	<input type="file" id="files" name="image" />
				<output id="list"></output>
        	</div>
         -->	
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
              var span = document.createElement('span');
              span.innerHTML = ['<img class="thumb" src="', e.target.result, '" title="', escape(theFile.name), '" width="100%" height="100%" />'].join('');
              document.getElementById('list').insertBefore(span, null);
            };
          })(f);

          // Read in the image file as a data URL.
          reader.readAsDataURL(f);
        }
      }

      document.getElementById('files').addEventListener('change', handleFileSelect, false);
	
      </script>
</html>