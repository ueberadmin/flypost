<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <body>
        <h1>Flypost</h1>
        <h2>Neu</h2>
        <form:form action="${context}/aushaenge" method="post" enctype="multipart/form-data">
        	<div>
        		<form:label path="headline">&Uuml;berschrift</form:label>
        		<form:input rows="3" path="headline"></form:input>
        	</div>
        	
        	<div>
	        	<label for="image">Bild</label>
	        	<input type="file" id="files" name="image" />
				<output id="list"></output>
        	</div>
        	
        	<div>
        		<form:label path="description">Beschreibung</form:label>
        		<form:textarea rows="10" path="description"></form:textarea>
        	</div>
        	
        	<div>
        		<form:label path="contactData">Kontaktdaten</form:label>
        		<form:textarea rows="3" path="contactData"></form:textarea>
        	</div>
        	
        	<button type="submit">Ver&ouml;ffentlichen</button>
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
              var span = document.createElement('span');
              span.innerHTML = ['<img class="thumb" src="', e.target.result,
                                '" title="', escape(theFile.name), '"/>'].join('');
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